// 第一版并查集 QuickFind 利用数组实现
public class UnionFind_1 implements UnionFind{

    // 索引表示编号，内部存储的元素为所属的集合
    private int[] id;

    /**
     * 构造函数
     * @param size 并查集中存储元素的个数
     */
    public UnionFind_1(int size){
        id = new int[size];

        for (int i = 0; i < id.length; i++)
            id[i] = i;
    }

    /**
     * 查询编号p和编号q两个元素是否属于同一集合(是否连接)
     * @return 是否连接
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
    }

    /**
     * @return 并查集中存储元素的个数
     */
    @Override
    public int getSize() {
        return id.length;
    }

    // 查找编号p所对应的集合编号
    private int find(int p){
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("P is out of bound.");
        return  id[p];
    }
}
