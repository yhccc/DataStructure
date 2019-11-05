import java.util.TreeMap;

public class Trie {
    /*
        字典树(前缀树)
        专门为处理字符串设计(前缀查询)
        局限性：空间占用过多
     */

    /*
        扩展:
            Trie的删除操作：如果待删除的word不是叶子结点，只将其isWord改为false即可
                           如果是叶子结点，则需要判断上方结点，删除下方结点后，next是否为空
        变种：
            压缩字典树CompressedTrie
            三分搜索字典树Ternary Search Trie
            后缀树
        更多字符串问题：
            子串查询：KMP，Boyer-Moore，Rabin-Karp
            文件压缩
            模式匹配(实现高效正则表达式)
            编译原理
            DNA本质也是字符串问题
     */

    private class Node{
        // 是否是一个单词
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    /**
     * @return 获得Trie中存储的单词数量
     */
    public int size(){
        return size;
    }

    /**
     * 向Trie中添加一个新的单词
     * @param word 待添加单词
     */
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        // 之前该单词不存在
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询单词word是否在Trie中
     * @param word 待查询单词
     * @return 是否存在
     */
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 删除Trie中一个单词
     * @param word 待删除单词
     * @return 是否删除成功
     */
    public boolean remove(String word){
        if (word == "")
            return false;
        return remove(root, word, 0);

        /*
        if (!contains(word))
            return false;
        remove(root, word,0);
        return true;
         */
    }
    private boolean remove(Node node, String word, int index){
        // 递归到低
        if (index == word.length()){
            // node为单词的最后一个结点
            if (!node.isWord)
                return false;
            // 若其isWord为真，改为假即删除该单词
            node.isWord = false;
            size--;
            return true;
        }

        char c = word.charAt(index);
        // Trie中不存在该单词
        if (!node.next.containsKey(c))
            return false;

        Node nextNode = node.next.get(c);
        boolean ret = remove(nextNode, word, index + 1);
        if (!nextNode.isWord && nextNode.next.size() == 0)
            node.next.remove(word.charAt(index));
        return ret;
    }

    /*
    private void remove(Node node, String word, int index){
        if (index == word.length()){
            node.isWord = false;
            size--;
            return;
        }
        char c = word.charAt(index);
        Node nextNode = node.next.get(c);
        remove(node.next.get(c), word, index + 1);
        if (!nextNode.isWord && nextNode.next.size() == 0)
            node.next.remove(c);
    }
     */

    /**
     * 前缀查询
     * @param prefix 待查询的前缀
     * @return 前缀是否存在
     */
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }

}
