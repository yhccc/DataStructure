import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("D:\\Project\\Java\\DataStructure\\AVL\\src\\test\\pride-and-prejudice.txt", words)) {

//            Collections.sort(words);
//            long startTime1 = System.nanoTime();

            AVL<String, Integer> map = new AVL<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            for (String word : words) {
                map.remove(word);
                if (!map.isBalanced() || !map.isBST())
                    throw new RuntimeException("Error");
            }
//            for (String word : words)
//                map.contains(word);
//
//            long endTime1 = System.nanoTime();
//            double time1 = (endTime1 - startTime1) / 1000000000.0;
//            System.out.println(time1);

//            long startTime2 = System.nanoTime();
//
//            BST<String, Integer> bst = new BST<>();
//            for (String word : words) {
//                if (bst.contains(word))
//                    bst.set(word, bst.get(word) + 1);
//                else
//                    bst.add(word, 1);
//            }
//            for (String word : words)
//                bst.contains(word);
//
//            long endTime2 = System.nanoTime();
//            double time2 = (endTime2 - startTime2) / 1000000000.0;
//            System.out.println(time2);
        }

        System.out.println();
    }
}
