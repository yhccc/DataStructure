import java.util.TreeSet;

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",
                ".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-",
                "-.--","--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word : words)
        {
            String decode = "";
            for (int i = 0; i < word.length(); i++)
                decode += codes[word.charAt(i) - 'a'];
            set.add(decode);
        }
        return set.size();
    }
}