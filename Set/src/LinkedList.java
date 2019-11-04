public class LinkedList<E> {
    /*
        真正的最简单的动态数据结构
        更深入的理解引用（或指针）
        更深入的理解递归：链表有天然的递归结构特性
        辅助组成其他数据结构
     */

    /*
        链表不适用于索引有语义的情况
        优点：真正的动态，不需要处理固定容量的问题
        缺点：丧失了随机访问的能力
        注：参数中带有index的方法均非常用操作，仅作为练习使用
     */

    /*
        虚拟头结点解决头结点与其他结点操作不同的问题
        其他形式的链表：双向链表、循环链表
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

    // 链表的虚拟头结点
    private Node dummyHead;
    // 链表的头结点
//    private Node head;
    // 链表中元素的个数
    private int size;

    /**
     * 构造函数
     */
    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 传入一个数组，数组转变成链表的构造函数

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

    /**
     * 在链表的index(0 - size)位置添加新的元素e
     * @param index 待添加元素的位置索引
     * @param e 待添加元素的值
     */
    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed, Illegal index.");

        // 由于链表头结点没有前置结点，需要特殊处理
//        if(index == 0)
//            addFirst(e);
        // 使用虚拟头结点解决该问题
        Node prev = dummyHead;
        for(int i = 0; i < index; i++)
            prev = prev.next;
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头添加新的元素
     * @param e 待添加元素
     */
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
        add(0, e);
    }

    /**
     * 在链表尾添加新的元素
     * @param e 待添加元素
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 获得链表的第index个位置的元素
     * @param index 待查找的位置
     * @return 查找元素的值
     */
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed, Illegal index.");

        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        return cur.e;
    }

    /**
     * 获取链表的第一个元素
     * @return 第一个元素的值
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获取链表的最后一个元素
     * @return 最后一个元素的值
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 修改链表的第index个位置的元素
     * @param index 待修改元素的位置
     * @param e 修改后元素的值
     */
    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed, Illegal index.");
        Node cur = dummyHead.next;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    /**
     * 查找链表中是否有元素
     * @param e 待查找元素
     * @return 是否存在
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除链表的第index个位置的元素
     * @param index 待删除元素的位置
     * @return 删除元素的值
     */
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed, Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        Node delNode = prev.next;
        prev.next = prev.next.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    /**
     * 删除链表的第一个元素
     * @return 删除元素的值
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除链表的最后一个元素
     * @return 删除元素的值
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 删除链表中指定元素
     * @param e 待删除元素的值
     */
    public void removeElement(E e){
        Node prev = dummyHead;
        while(prev.next != null){
            if (prev.next.e.equals(e)) {
                Node del = prev.next;
                prev.next = del.next;
                del.next = null;
                size--;
            }
            else{
                prev = prev.next;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        res.append(String.format("LinkedList: size = %d\n", size));
        Node cur = dummyHead.next;
//        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
//            res.append(cur + "->");
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("Null");

        return res.toString();
    }
}
