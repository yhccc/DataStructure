// 第五版并查集 QuickUnion 利用数组实现
// 在第四版的基础上实现路径压缩
// rank不在表示高度，而是表示一个排名，排序
public class UnionFind_5 implements UnionFind {

    // 索引表示编号，内部存储的元素为所属的集合，指向父亲结点
    private int[] parent;
    private int[] rank;  // rank[i]表示以i为根结点的树中元素的层数

    /**
     * 构造函数
     * @param size 并查集中存储元素的个数
     */
    public UnionFind_5(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
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
        // 合并时查看两颗树的高度，低的指向高的
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = parent[qRoot];
        }
        else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = parent[pRoot];
        }
        else{
            parent[qRoot] = parent[pRoot];
            rank[pRoot]++;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("P is out of bound.");
        while (p != parent[p]) {
            // 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
