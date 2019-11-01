public class LinkedListQueue<E> implements Queue<E> {

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

    // 头结点(队首->出队)
    private Node head;
    // 尾结点(队尾->入队)
    private Node tail;

    private int size;

    /**
     * 构造函数
     */
    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * @return 链表队列中元素的个数
     */
    @Override
    public int getSize()
    {
        return size;
    }

    /**
     * @return 链表队列是否为空
     */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 入队
     * @param e 待添加的元素
     */
    @Override
    public void enqueue(E e){
        // 如果队尾为null，则代表整个队列为空，head也为null
        if (tail == null) {
            // 需要同时维护tail和head
            tail = new Node(e);
            head = tail;
        }
        else {
            // 正确的应该是tail = tail.next = new Node(e);从右往左赋值
//            tail = new Node(e, tail.next);
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    /**
     * 出队
     * @return 出队元素
     */
    @Override
    public E dequeue(){
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        // 出队后head为null，代表整个队列为空，出队前队列中只有一个元素
        // 故出队后tail也为null，不能让其继续指向retNode
        if (head == null)
            tail = null;
        size--;
        return retNode.e;
    }

    /**
     * 返回队首元素
     * @return 队首元素值
     */
    @Override
    public E getFront(){
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        res.append("LinkedListQueue: front ");
        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("Null tail");

        return res.toString();
    }
}
