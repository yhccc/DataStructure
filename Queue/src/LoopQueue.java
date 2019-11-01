public class LoopQueue<E> implements Queue<E> {

    /*
        注：data.length = capacity + 1
            所有的求余操作都是对data.length
            capacity是真正的容积
     */

    private E[] data;
    // 队首与队尾
    private int front, tail;
    // 队中元素个数
    private int size;

    /**
     * 构造函数（由于循环队列浪费一个空间，故进行+1操作）
     * @param capacity 队列容量
     */
    public LoopQueue(int capacity){
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    /**
     * 无参构造函数
     */
    public LoopQueue(){
        this(10);
    }

    /**
     * 获得循环队列的容积
     * @return 容积大小
     */
    public int getCapacity(){
        return data.length - 1;
    }

    /**
     * @return 循环队列是否为空
     */
    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    /**
     * @return 循环队列中元素的个数
     */
    @Override
    public int getSize(){
        return size;
    }

    /**
     * 入队
     * @param e 待入队的元素
     */
    @Override
    public void enqueue(E e){
        if ((tail + 1) % data.length == front)
            resize(2 * getCapacity());
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    /**
     * 出队
     * @return 出队元素的值
     */
    @Override
    public E dequeue(){
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    /**
     * @return 队首元素值
     */
    @Override
    public E getFront(){
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return data[front];
    }

    /**
     * 动态改变容器的容积大小
     * @param newCapacity 新的容积大小
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity + 1];
        for(int i = 0; i < size; i++)
            newData[i] = data[(i + front) % data.length];
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
