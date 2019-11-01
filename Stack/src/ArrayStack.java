public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    /**
     * 构造函数
     * @param capacity 传入数组的容量capacity构造ArrayStack
     */
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    /**
     * 无参构造函数
     */
    public ArrayStack(){
        array = new Array<>();
    }

    /**
     * @return 数组栈的容积
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    /**
     * @return 数组栈的元素个数
     */
    @Override
    public int getSize(){
        return array.getSize();
    }

    /**
     * @return 数组栈是否为空
     */
    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    /**
     * 压栈
     * @param e 待添加的元素
     */
    @Override
    public void push(E e){
        array.addLast(e);
    }

    /**
     * 出栈
     * @return 出栈元素
     */
    @Override
    public E pop(){
        return array.removeLast();
    }

    /**
     * 返回栈顶元素
     * @return 栈顶元素值
     */
    @Override
    public E peek(){
        //return array.get(getSize() - 1);
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("ArrayStack: size = %d, capacity = %d\n", array.getSize(), array.getCapacity()));
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}
