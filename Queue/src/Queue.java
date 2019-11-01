public interface Queue<E> {
    /*
        先进先出
        First In First Out（FIFO）
     */

    /*
        循环队列
        front 队首
        tail 队尾
        front == tail 表示队列为空
        (tail + 1) % capacity == front 表示队列满
        浪费一个空间
     */
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
