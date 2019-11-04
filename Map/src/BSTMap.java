public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    /*
        有序映射：键具有顺序性
     */

    // 数据存储在"结点"中
    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;

        // 构造函数
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value){
        root = add(root, key, value);
    }
    private Node add(Node node, K key, V value){
        if (node == null){
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else  // key.compareTo(node.key == 0)
            node.value = value;
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");
        node.value = value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root ,key);
        return node == null ? null : node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 以下为辅助函数

    // 返回以node为根结点的二分搜索树中，key所在的结点
    private Node getNode(Node node, K key){
        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else
            return getNode(node.right, key);
    }
    // 返回树中最小的元素
    private Node minimum(){
        Node node = root;
        while(node.left != null)
            node = node.left;
        return node;
    }
    // 从二分搜索树中删除最小值所在结点
    private Node removeMin(Node node){
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;// 结点与树脱离关系
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }
    // 从二分搜索树中删除以key为键的结点
    private Node remove(Node node, K key){
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            node.left = remove(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = remove(node.right, key);
        else
        {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除结点左右子树均不为空
            // 找到比待删除结点大的最小结点，即待删除结点右子树的最小结点
            // 用这个结点顶替待删除结点的位置
            Node newNode = removeMin(node.right);
            newNode.left = node.left;
            newNode.right = node.right;
            node.left = null;
            node.right = null;
            return newNode;
        }
        return node;
    }
}
