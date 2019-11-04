public class LinkedListMap<K, V> implements Map<K, V> {

    /*
        无序映射：键没有顺序性
     */

    // 数据存储在"结点"中
    private class Node{
        public K key;
        public V value;
        public Node next;

        // 构造函数
        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Node(K key){
            this(key, null, null);
        }
        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }
        // Map中已有相应的key
        else
            node.value = value;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while(prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }
        if (prev.next != null) {
            Node del = prev.next;
            prev.next = del.next;
            del.next = null;
            size--;
            return del.value;
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");
        node.value = value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur != null){
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
