public class SegmentTree<E> {
    /*
        线段树：平衡二叉树（叶子结点的最大深度之比最小深度大一）
        经典问题：
            区间染色
                m次操作后，我们可以在[i, j]区间内看见多少种颜色
            区间查询
                基于区间的统计查询
     */

    /*
        我们的线段树不考虑添加元素, 区间固定(静态)
        如果区间有n个元素，需要4n的空间来存储这棵线段树
     */

    /*
        扩展：
            对于一个区间进行更新----懒惰更新
            二维线段树-----四叉树
            三维线段树-----八叉树
            动态线段树(节省空间)
                链式存储，每一个元素用一个结点来表示，存区间的左右边界，存左右孩子结点
                初始区间非常大时，当我们有关注区间后再动态创建线段树
     */

    /*
        区间操作的另一个重要数据结构
            树状数组(BIT)Binary Index Tree
        区间相关的问题
            RMQ(Range Minimum Query)区间最值问题--ST算法
     */

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    /**
     * 构造函数
     * @param arr 将数组转换成线段树
     */
    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[])new Object[arr.length * 4];
        buildSegmentTree(0,0,data.length - 1);
    }
    // 在treeIndex的位置创建表示区间[l, r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + ((r - l)>>1);
        buildSegmentTree(leftChild(treeIndex), l, mid);
        buildSegmentTree(rightChild(treeIndex), mid + 1, r);

        tree[treeIndex] = merger.merge(
                tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
    }

    /**
     * @return 线段树中存储元素的个数
     */
    public int getSize(){
        return data.length;
    }

    /**
     * 根据索引获取元素值
     * @param index 待获取元素的索引
     * @return 元素值
     */
    public E get(int index){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        return data[index];
    }

    /**
     * 返回区间[queryL, queryR]的值
     */
    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal");
        return query(0,0,data.length-1,queryL,queryR);
    }
    // 在treeIndex为根的线段树中[l, r]的范围里，搜索区间[queryL, queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + ((r - l)>>1);
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 替换数组中索引为index的值
     * @param index 待更新索引
     * @param e 待更新的值
     */
    public void set(int index, E e){
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }
    // 在以treeIndex为根的线段树中更新index的值为e
    public void set(int treeIndex, int l, int r, int index, E e){
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + ((r - l)>>1);
        int lefeTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else
            set(lefeTreeIndex, l, mid, index, e);
        tree[treeIndex] = merger.merge(tree[lefeTreeIndex], tree[rightTreeIndex]);
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子结点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子结点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++){
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");
            if (i != tree.length - 1)
                res.append(" ");
        }
        res.append(']');
        return res.toString();
    }
}
