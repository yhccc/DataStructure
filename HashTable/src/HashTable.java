import java.util.TreeMap;

public class HashTable<K, V> {
    /*
        哈希函数：“键”转换为“索引”
            很难保证每一个“键”通过哈希函数的转换对应不同的“索引” -> 哈希冲突
        哈希表充分体现了算法设计领域的经典思想：空间换时间
        哈希表是时间和空间之间的平衡
        哈希函数的设计很重要：“键”通过哈希函数获得的“索引”分布越均匀越好
     */

    /*
        哈希函数的设计原则：
        一致性：如果a == b，则hash(a) == hash(b)
        高效性：计算高效简便
        均匀性：哈希值均匀分布
            小范围正整数直接使用
            小范围负整数进行偏移
            大整数(身份证号)，通用做法：取模
                mod 10000   -> 相当于取身份证后四位
                mod 1000000 -> 相当于取身份证后六位(前两位表示生日，取值01-31，造成分布不均匀)
                这样取模对信息的利用不充分
                解决办法：模一个素数
            浮点型、字符串、复合类型(转成整型处理)
     */

    /*
        哈希冲突的处理
            Separate Chaining(链地址法)
            Open Addressing 开放地址法(线性探测法：遇到哈希冲突+1 、平方探测法、二次哈希法)
            再哈希法 Rehashing
            Coalesced Hashing 综合了Separate Chaining和Open Addressing
     */

    /*
        实现存在BUG
            因为红黑树Key要求可比较
            而哈希表Key不要求可比较
     */

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189,
            805306457, 1610612741};
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static int capacityIndex = 0;

    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    /**
     * 哈希表构造函数
     */
    public HashTable(){
        this.M = capacity[capacityIndex];
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashTable[i] = new TreeMap<>();
    }

    /**
     * @return 哈希表中元素的个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 向哈希表中添加元素
     * @param key 待添加元素的键
     * @param value 待添加元素的值
     */
    public void add(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key))
            map.put(key, value);
        else{
            map.put(key, value);
            size++;

            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    /**
     * 哈希表中删除元素
     * @param key 待删除元素的键
     * @return 删除元素的值
     */
    public V remove(K key){
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && M / 2 >= capacity[0]) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    /**
     * 修改哈希表中的元素
     * @param key 待修改元素的键
     * @param value 修改后元素的值
     */
    public void set(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key))
            map.put(key, value);
        else
            throw new IllegalArgumentException(key + " doesn't exist!");
    }

    /**
     * 查找哈希表中是否存在某元素
     * @param key 待查找元素的键
     * @return 是否存在
     */
    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    /**
     * 查找哈希表中是否存在某元素
     * @param key 待查找元素的键
     * @return 查找元素的值
     */
    public V get(K key){
        return hashTable[hash(key)].get(key);
    }

    /**
     * 哈希函数
     * @param key 键值 -> hashCode -> 取绝对值 -> 对容积取模
     * @return 哈希表中存放的位置
     */
    private int hash(K key){
        //      相当于取绝对值
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 动态扩容
     * @param newM 新的容积
     */
    private void resize(int newM){
        // 创建容积变化后的哈希表
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        // 记录原容积并将新容积赋给M, 防止hash()函数计算出错
        int oldM = M;
        this.M = newM;

        // 将原哈希表中元素依次放入新的哈希表中
        for (int i = 0; i < oldM; i++){
            TreeMap<K, V> map = hashTable[i];
            for (K key: map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }

        this.hashTable = newHashTable;
    }
}
