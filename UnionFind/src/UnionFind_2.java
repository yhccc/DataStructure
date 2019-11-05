// 第二版并查集 QuickUnion 利用数组实现(特殊的树结构)
// 极端情况下退化成链表
public class UnionFind_2 implements UnionFind {

    // 索引表示编号，内部存储的元素为所属的集合，指向父亲结点
    private int[] parent;

    /**
     * 构造函数
     * @param size 并查集中存储元素的个数
     */
    public UnionFind_2(int size){
        parent = new int[size];

        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
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
