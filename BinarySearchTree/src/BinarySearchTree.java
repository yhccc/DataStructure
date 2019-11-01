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


public class BinarySearchTree<E extends Comparable<E>> {
    /*
        二分搜索树是二叉树
            每个结点的值：
                ·大于其左子树的所有结点的值
                ·小于其右子树的所有结点的值
            每一个子树都是二分搜索树
            存储的元素必须有可比较性
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
}
