public interface UnionFind {
    /*
        并查集
        解决连接问题(比结点间路径问题要回答的问题少)
            网络中结点间的连接状态
        数学中的集合的实现
     */

    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
    int getSize();
}
