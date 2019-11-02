public class LinkedListRecursion<E> {
    /*
        链表的递归实现
     */

    // 数据存储在"结点"中
    private class Node{
        public E e;
        public Node next;

        // 构造函数
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head;
    private int size;

    /**
     * 构造函数
     */
    public LinkedListRecursion(){
        head = null;
        size = 0;
    }

    /**
     * @return 获取链表中元素的个数
     */
    public int getSize(){
        return size;
    }

    /**
     * @return 链表是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    public void addLast(E e){
        head = addLast(head, e);
    }
    private Node addLast(Node head, E e){
        if(head == null) {
            size++;
            return new Node(e);
        }
        head.next = addLast(head.next, e);
        return head;
    }

    public boolean contains(E e){
        return contains(head, e);
    }
    private boolean contains(Node head, E e){
        if (head == null)
            return false;

        if (e.equals(head.e))
            return true;
        else
            return contains(head.next, e);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        res.append(String.format("LinkedList: size = %d\n", size));
        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("Null");

        return res.toString();
    }
}
