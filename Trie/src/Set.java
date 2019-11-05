public interface Set<E> {
    /*
        集合：每一个元素不能重复存在
             应用于去重，客户统计，词汇量统计
        多重集合：元素可以重复的集合
     */

    void add(E e); // 不能添加重复元素
    void remove(E e);
    boolean isEmpty();
    boolean contains(E e);
    int getSize();
}
