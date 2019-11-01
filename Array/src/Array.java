public class Array<E> {
    /*
        数组最大的优点：快速查询
        数组最好应用于"索引有语义"的情况
        但并非所有有语义的索引都适用于数组：如身份证号
        数组也可以处理"索引没有语义"的情况
     */

    /*
        使用泛型让我们的数据结构可以放置"任何"数据类型
        不可以放置基本数组类型, 只能是类对象
        boolean, byte, char, short, int, long, float, double
        每个基本数据类型都有对应的包装类（两者可自动转换）
        Boolean, Byte, Char, Short, Integer, Long, Float, Double
     */

    private E[] data;
    private int size;

    /**
     * 构造函数
     * @param capacity 传入数组的容量capacity构造Array
     */
    public Array(int capacity){
        data = (E[])(new Object[capacity]);
        size = 0;
    }

    /**
     * 无参数构造函数, 默认容量为10
     */
    public Array(){
        this(10);
    }

    // 传入一个数组来构造新数组

    /**
     * @return 数组的元素个数
     */
    public int getSize(){
        return size;
    }

    /**
     * @return 数组的容量
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * @return 数组是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在第index个位置插入一个新元素e
     * @param index 待添加的位置
     * @param e 待添加的元素
     */
    public void add(int index, E e){
        // 判断index是否合法
        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed. Require index >= 0 and index <= size.");
        // 判断数组是否已满
        if (size == data.length)
            resize(2 * data.length);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    /**
     * 往数组末尾添加元素
     * @param e 待添加的元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 往数组头部添加元素
     * @param e 待添加的元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 获取数组中的元素
     * @param index 待获取元素的索引
     * @return 元素的值
     */
    public E get(int index){
        // 判断index是否合法
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed. Require index >= 0 and index <= size.");
        return data[index];
    }

    /**
     * 获得数组中第一个元素
     * @return 第一个元素的值
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获得数组中最后一个元素
     * @return 最后一个元素的值
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 设置数组中的元素
     * @param index 待设置元素的索引
     * @param e 待设置元素的值
     */
    public void set(int index, E e){
        // 判断index是否合法
        if (index < 0 || index > size)
            throw new IllegalArgumentException("set failed. Require index >= 0 and index <= size.");
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e 待查找元素
     * @return 是否存在
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++)
            if (data[i].equals(e))
                return true;
        return false;
    }

    /**
     * 查找数组中是否有元素e
     * @param e 待查找元素
     * @return 该元素的索引
     */
    public int find(E e){
        for (int i = 0; i < size; i++)
            if (data[i].equals(e))
                return i;
        return -1;
    }

    // findAll

    /**
     * 删除数组中索引为index的元素
     * @param index 待删除元素的索引
     * @return 删除元素的值
     */
    public E remove(int index){
        // 判断index是否合法
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed. Require index >= 0 and index < size.");
        E ret = data[index];
        for (int i = index; i < size - 1; i++)
            data[i] = data[i + 1];
        size--;
        data[size] = null; // loitering objects != memory leak

        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    /**
     * 删除数组中第一个元素
     * @return 删除元素的值
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除数组中最后一个元素
     * @return 删除元素的值
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     * @param e 待删除的元素值
     * @return 是否删除
     */
    public boolean removeElement(E e){
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    // removeAllElement


    /**
     * 数组的动态扩容
     * @param newCapacity 扩容后的容量
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
