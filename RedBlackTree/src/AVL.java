import java.util.ArrayList;

public class AVL<K extends Comparable<K>, V> {
    /*
        AVL树的优化
            当一个结点的高度没有变化时，其祖先结点的高度和平衡不需要再维护
     */

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVL(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }
    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){
        // 递归结束条件
        if(node == null){
            size ++;
            return new Node(key, value);
        }

        // 添加元素
        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护
        // LL 新添加的结点是不平衡结点左孩子的左孩子
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        // RR 新添加的结点是不平衡结点右孩子的右孩子
        else if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        // LR 新添加的结点是不平衡结点左孩子的右孩子
        else if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            leftRotate(node.left);
            return rightRotate(node);
        }
        // RL 新添加的结点是不平衡结点右孩子的左孩子
        else if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    //
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

        Node retNode;
        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            retNode = node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            else {
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        // 此处判断放置后面出现空指针异常
        if (retNode == null)
            return null;

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL 新添加的结点是不平衡结点左孩子的左孩子
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
            // RR 新添加的结点是不平衡结点右孩子的右孩子
        else if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);
            // LR 新添加的结点是不平衡结点左孩子的右孩子
        else if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL 新添加的结点是不平衡结点右孩子的左孩子
        else if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }
    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
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
    // 返回以node为根节点的二分搜索树中，key所在的节点
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

    // 返回一个结点的高度值
    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }
    // 返回一个结点的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }
    // 二叉树中序遍历
    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced(){
        return  isBalanced(root);
    }
    // 判断以node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node){
        if (node == null)
            return true;

        if (Math.abs(getBalanceFactor(node)) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 右旋转，对结点y进行右旋转操作，返回旋转后新的根结点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    // x.right = y
    // y.left = T3
    private Node rightRotate(Node y){
        Node T3 = y.left.right;
        Node x = y.left;
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }
    // 左旋转，对结点node进行左旋转操作，返回旋转后新的根结点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node T2 = y.right.left;
        Node x = y.right;
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }
}
