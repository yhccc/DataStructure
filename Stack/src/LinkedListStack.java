public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    /**
     * 链表栈构造函数
     */
    public LinkedListStack(){
        list = new LinkedList<>();
    }

    /**
     * @return 链表栈中元素的个数
     */
    @Override
    public int getSize(){
        return list.getSize();
    }

    /**
     * @return 链表栈是否为空
     */
    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 压栈
     * @param e 待压栈的元素
     */
    @Override
    public void push(E e){
        list.addFirst(e);
    }

    /**
     * 出栈
     * @return 出栈元素的值
     */
    @Override
    public E pop(){
        return list.removeFirst();
    }

    /**
     * @return 返回栈顶元素的值
     */
    @Override
    public E peek(){
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("LinkedListStack: top ", list.getSize()));
        res.append(list);
        return res.toString();
    }
}
