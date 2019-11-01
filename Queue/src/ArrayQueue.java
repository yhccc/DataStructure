public class ArrayQueue<E> implements Queue<E>{

    private Array<E> array;

    /**
     * 构造函数
     * @param capacity 传入数组的容量capacity构造ArrayQueue
     */
    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    /**
     * 无参构造函数
     */
    public ArrayQueue(){
        array = new Array<>();
    }

    /**
     * @return 数组队列的容积
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    /**
     * @return 数组队列的元素个数
     */
    @Override
    public int getSize(){
        return array.getSize();
    }

    /**
     * @return 数组队列是否为空
     */
    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    /**
     * 入队
     * @param e 待添加的元素
     */
    @Override
    public void enqueue(E e){
        array.addLast(e);
    }

    /**
     * 出队
     * @return 出队元素
     */
    @Override
    public E dequeue(){
        return array.removeFirst();
    }

    /**
     * 返回队首元素
     * @return 队首元素值
     */
    @Override
    public E getFront(){
        //return array.get(getSize() - 1);
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("ArrayQueue: size = %d, capacity = %d\n", array.getSize(), array.getCapacity()));
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
