import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args){
        int n = 10000000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++)
            testData.add(random.nextInt(Integer.MAX_VALUE));

        // Test BST
//        long startTime = System.nanoTime();
//        BST<Integer, Integer> bst = new BST<>();
//        for (Integer x : testData)
//                bst.add(x, null);
//        long endTime = System.nanoTime();
//        double time = (endTime - startTime) / 1000000000.0;
//        System.out.println(time);

        // Test AVL
        long startTime = System.nanoTime();
        AVL<Integer, Integer> avl = new AVL<>();
        for (Integer x : testData)
            avl.add(x, null);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println(time);

        // Test RBT
        startTime = System.nanoTime();
        RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
        for (Integer x : testData)
            rbt.add(x, null);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println(time);

        // Test HashTable
        startTime = System.nanoTime();
        HashTable<Integer, Integer> hash = new HashTable<>();
        for (Integer x : testData)
            hash.add(x, null);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println(time);
    }
}
