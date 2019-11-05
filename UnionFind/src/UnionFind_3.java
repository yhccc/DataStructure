// 第三版并查集 QuickUnion 利用数组实现
// 在第二版的基础上实现，基于size优化，合并时查看两颗树的结点个数，结点少的指向结点多的
// 大大降低了树的高度，避免退化成链表
public class UnionFind_3 implements UnionFind {

    // 索引表示编号，内部存储的元素为所属的集合，指向父亲结点
    private int[] parent;
    private int[] sz;  // sz[i]表示以i为根结点的树中元素的个数

    /**
     * 构造函数
     * @param size 并查集中存储元素的个数
     */
    public UnionFind_3(int size){
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        // 注：pRoot = parent[pRoot]  qRoot = parent[qRoot]
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;
        // 合并时查看两颗树的结点个数，结点少的指向结点多的
        if (sz[pRoot] < sz[qRoot]){
            parent[pRoot] = parent[qRoot];
            sz[qRoot] += sz[pRoot];
        }
        else{
            parent[qRoot] = parent[pRoot];
            sz[pRoot] += sz[qRoot];
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("P is out of bound.");
        while (p != parent[p])
            p = parent[p];
        return p;
    }
}
