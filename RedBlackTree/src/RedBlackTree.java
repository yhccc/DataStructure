import java.util.ArrayList;

public class RedBlackTree<K extends Comparable<K>, V> {
    /*
        红黑树 2-3树的等价性
        2-3树
            满足红黑树的基本性质
            结点可以存放一个元素或者两个元素
            2-3树是一棵绝对平衡的树(从根结点到任意叶子结点经过的结点数相同)
                添加：新元素永远添加在叶子结点上，不添加在null的位置
        红黑树是保持"黑平衡"的二叉树，严格意义上，不是平衡二叉树。最大高度为2log n
     */

    /*
        性能总结：
            对于完全随机的数据，普通的二分搜索树很好用！
            缺点：极端情况下退化成链表(或者高度不平衡)

            对于查询较多的使用情况，AVL树很好用！

            红黑树牺牲了平衡性(2log n的高度)
            统计性能更优(综合增删改查所有的操作)
     */

    /*
        拓展：
            删除结点
            我们实现的是一个左倾红黑树，也可以实现一个右倾红黑树
            另一种统计性能优秀的树结构：Splay Tree（伸展树）
                局部性原理：刚被访问的内容下次高概率被再次访问
            基于红黑树的Map和Set
            算法导论中红黑树的实现
     */
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private  static final boolean RED = true;
    private  static final boolean BLACK = false;
    private Node root;
    private int size;

    public RedBlackTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向红黑树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
        // 保持根结点为黑色
        root.color = BLACK;
    }
    // 向以node为根的红黑树中插入元素(key, value)，递归算法
    // 返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);  // 默认插入红色结点
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 红黑树性质的维护
        // 左旋转
        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
        // 右旋转
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        // 颜色翻转
        if (isRed(node.left) && isRed(node.right))
            flipColor(node);

        return node;
    }

    // 从红黑树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }
    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
    // 返回以node为根的红黑树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }
    // 删除掉以node为根的红黑树中的最小节点
    // 返回删除节点后新的红黑树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }
    // 返回以node为根节点的红黑树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    // 判断结点node的颜色
    private boolean isRed(Node node){
        if (node == null)
            return BLACK;
        return node.color;
    }
    // 左旋转
    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node){
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        // 改变颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }
    // 右旋转
    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){
        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        // 改变颜色
        x.color = node.color;
        node.color = RED;

        return  x;
    }
    // 颜色翻转
    private void flipColor(Node node){
        node.color = RED;
        node.right.color = BLACK;
        node.left.color = BLACK;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("D:\\Project\\Java\\DataStructure\\AVL\\src\\test\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RedBlackTree<String, Integer> map = new RedBlackTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
