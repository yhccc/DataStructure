/*
    为什么要有树结构？
        将数据使用树结构存储后，某些操作出奇的高效
            ·二分查找树
            ·平衡二叉树：AVL；红黑树
            ·堆；并查集
            ·线段树；Trie(字典树, 前缀树)
*/

/*
    二叉树：动态数据结构，具有天然递归结构
        具有唯一根结点
        每个结点最多有两个孩子结点
        每个结点最多有一个父亲结点
        没有孩子的结点称为叶子结点
*/

/*
    二分搜索树是二叉树
        每个结点的值：
            ·大于其左子树的所有结点的值
            ·小于其右子树的所有结点的值
        每一个子树都是二分搜索树
        存储的元素必须有可比较性
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
    /*
        我们的二分搜索树不包含重复元素，用递归实现，非递归也可实现
        若想包含重复元素的话，只需要定义：
            左子树小于等于结点；或右子树大于等于结点
            使用维护count的二分搜索树：
                count表示该Node结点有多少个
     */

    /*
        二分搜索树的顺序性：
        可以很方便的找到二分搜索树中的
            最大值maximum, 最小值minimum
            一个给定值(需要在二分搜索树中)的后继successor, 前驱precursor
            一个给定值(可以不在二分搜索树中)的小于给定值的最大值floor
                                          大于给定值的最小值ceil

        二分搜索树的变种：
        维护size的二分搜索树：Node结点类添加size属性
            size表示以该Node为根结点的二分搜索树中存有多少个元素，便于实现以下两个方法：
                rank: 一个给定值在二分搜索树中排名第几
                select: 排名第5的元素是哪一个
        维护depth的二分搜索树：
            depth表示该Node结点所在的深度，root结点深度为0
     */
    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    /**
     * 构造函数
     */
    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    /**
     * @return 二分搜索树中元素的个数
     */
    public int getSize(){
        return size;
    }

    /**
     * @return 二分搜索树是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向二分搜索树中添加元素
     * @param e 待添加元素
     */
    public void add(E e){
        root = add(root, e);
    }
    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新结点后二分搜索树的根
    private Node add(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if(e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }

    /**
     * 查找二分搜索树中是否包含元素e
     * @param e 待查找元素
     * @return 是否包含元素e
     */
    public boolean contains(E e){
        return contains(root, e);
    }
    // 查找以root为根的树中是否包含元素e，递归算法
    private boolean contains(Node node, E e){
        if (root == null)
            return false;

        if (e.equals(root.e))
            return true;
        else if (e.compareTo(root.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }
    // 前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node){
        if (node == null)
            return;
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);

        // 等价逻辑
//        if (node != null) {
//            System.out.print(node.e + " ");
//            preOrder(node.left);
//            preOrder(node.right);
//        }
    }
    // 前序遍历非递归算法(依托于栈)
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.print(cur.e + " ");
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    /**
     * 中序遍历
     * 数据排序输出
     */
    public void inOrder(){
        inOrder(root);
    }
    // 中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node){
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }
    // 中序遍历非递归算法(依托于栈)
    public void inOrderNR(){
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            Node cur = stack.pop();
            System.out.print(cur.e + " ");
            node = cur.right;
        }
    }

    /**
     * 后序遍历
     * 应用：为二分搜索树释放内存
     */
    public void postOrder(){
        postOrder(root);
    }
    // 后序遍历以node为根的二分搜索树，递归算法
    private void postOrder(Node node){
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }
    // 后序遍历非递归算法(依托于栈)
    public void postOrderNR(){
        Stack<Node> stack = new Stack<>();
        Node node = root;
        Node pre = null;
        Node cur = null;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            cur = stack.peek();
            if (cur.right == null || pre == cur.right) {
                stack.pop();
                System.out.print(cur.e + " ");
                pre = cur;
            }
            else {
                node = cur.right;
            }
        }
    }

    /**
     * 层序遍历(广度优先)
     * 意义：更快的找到问题的解
     *       常用于算法设计中-无权图最短路径
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.print(cur.e + " ");

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * @return 返回树中最小的元素
     */
    public E minimum(){
        // 递归实现
//        if (isEmpty())
//            throw new IllegalArgumentException("BST is empty.");
//        return minimum(root);

        // 非递归实现
        Node node = root;
        while(node.left != null)
            node = node.left;
        return node.e;
    }
    private E minimum(Node node){
        if (node.left == null)
            return node.e;
        return minimum(node.left);
    }

    /**
     * @return 返回树中最大的元素
     */
    public E maximum(){
        // 递归实现
//        if (isEmpty())
//            throw new IllegalArgumentException("BST is empty.");
//        return maximum(root);

        // 非递归实现
        Node node = root;
        while(node.right != null)
            node = node.right;
        return node.e;
    }
    private E maximum(Node node){
        if (node.right == null)
            return node.e;
        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在结点
     * @return 最小值
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    // 向以node为根的二分搜索树中删除最小值，递归算法
    // 返回删除结点后二分搜索树的根
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

    /**
     * 从二分搜索树中删除最大值所在结点
     * @return 最大值
     */
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }
    // 向以node为根的二分搜索树中删除最大值，递归算法
    // 返回删除结点后二分搜索树的根
    private Node removeMax(Node node){
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;// 结点与树脱离关系
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 从二分搜索树中删除指定结点
     * @param e 待删除元素的值
     */
    public void remove(E e){
        root = remove(root, e);
    }
    // 向以node为根的二分搜索树中删除指定结点，递归算法
    // 返回删除结点后二分搜索树的根
    private Node remove(Node node, E e){
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0)
            node.left = remove(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = remove(node.right, e);
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

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }
    // 生成以node为根结点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e +  '\n');
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }
    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i<depth; i++)
            res.append("--");
        return res.toString();
    }
}
