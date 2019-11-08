import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

//        int a = 42;
//        System.out.println(((Integer)a).hashCode());
//
//        int b = -42;
//        System.out.println(((Integer)b).hashCode());
//
//        double c = 3.1415926;
//        System.out.println(((Double)c).hashCode());
//
//        String d = "imooc";
//        System.out.println(d.hashCode());
//
//        Student student = new Student(3, 2 ,"bobo","liu");
//        System.out.println(student.hashCode());
//
//        HashSet<Student> set = new HashSet<>();
//        set.add(student);
//
//        HashMap<Student, Integer> scores = new HashMap<>();
//        scores.put(student, 100);


        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("D:\\Project\\Java\\DataStructure\\AVL\\src\\test\\pride-and-prejudice.txt", words)) {

//            Collections.sort(words);
            long startTime1 = System.nanoTime();

            AVL<String, Integer> map = new AVL<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            for (String word : words)
                map.contains(word);

            long endTime1 = System.nanoTime();
            double time1 = (endTime1 - startTime1) / 1000000000.0;
            System.out.println(time1);

            long startTime2 = System.nanoTime();

            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }
            for (String word : words)
                bst.contains(word);

            long endTime2 = System.nanoTime();
            double time2 = (endTime2 - startTime2) / 1000000000.0;
            System.out.println(time2);

            long startTime3 = System.nanoTime();

            RedBlackTree<String, Integer> rbt = new RedBlackTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, rbt.get(word) + 1);
                else
                    rbt.add(word, 1);
            }
            for (String word : words)
                rbt.contains(word);

            long endTime3 = System.nanoTime();
            double time3 = (endTime3 - startTime3) / 1000000000.0;
            System.out.println(time3);

            long startTime4 = System.nanoTime();

            HashTable<String, Integer> hash = new HashTable<>();
            for (String word : words) {
                if (hash.contains(word))
                    hash.set(word, rbt.get(word) + 1);
                else
                    hash.add(word, 1);
            }
            for (String word : words)
                hash.contains(word);

            long endTime4 = System.nanoTime();
            double time4 = (endTime4 - startTime4) / 1000000000.0;
            System.out.println(time4);
        }

        System.out.println();
    }
}
