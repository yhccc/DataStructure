class Solution {
    public int firstUniqChar(String s) {
        // 每一个字符都和一个索引相对应
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if(freq[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}