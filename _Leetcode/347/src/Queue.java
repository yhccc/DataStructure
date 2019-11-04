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

    /*
        优先队列
        出队顺序和入队顺序无关，和优先级有关
        动态选择优先级最高的任务进行执行
     */
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
