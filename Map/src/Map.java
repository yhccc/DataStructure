public interface Map<K, V> {
    /*
        多重映射：键可以重复
     */

    /*
        Map == Dictionary
        存储(key, value)数据对的数据结构
        根据键，能快速找到值
              key->value
        字典  单词->释意
        名册  身份证->人
        车辆管理    车牌号->车
        数据库  id->信息
        词频统计    单词->频率
     */

    // 增
    void add(K key, V value);
    // 删
    V remove(K key);
    // 改
    void set(K key, V value);
    // 查
    boolean contains(K key);
    V get(K key);
    int getSize();
    boolean isEmpty();
}
