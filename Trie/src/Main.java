import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Pride and Prejudice");
//
//        ArrayList<String> words = new ArrayList<>();
//        if (FileOperation.readFile("D:\\Project\\Java\\DataStructure\\Trie\\src\\test\\pride-and-prejudice.txt", words)){
//            long startTime = System.nanoTime();
//
//            BSTSet<String> set = new BSTSet<>();
//            for (String word: words)
//                set.add(word);
//            for (String word: words)
//                set.contains(word);
//
//            long endTime = System.nanoTime();
//            double time = (endTime - startTime) / 1000000000.0;
//
//            System.out.println("Total different words: " + set.getSize());
//            System.out.println("BSTSet: " + time + "s");
//
//
//            startTime = System.nanoTime();
//
//            Trie tree = new Trie();
//            for (String word: words)
//                tree.add(word);
//            for (String word: words)
//                tree.contains(word);
//
//            endTime = System.nanoTime();
//            time = (endTime - startTime) / 1000000000.0;
//
//            System.out.println("Total different words: " + tree.size());
//            System.out.println("Trie: " + time + "s");
//        }

        Trie tree = new Trie();
        tree.add("deer");
        tree.add("dear");
        tree.remove("deer");
        tree.contains("deer");
        tree.contains("dear");
    }
}
