import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Pride and Prejudice");
//
//        ArrayList<String> words1 = new ArrayList<>();
//        FileOperation.readFile("D:\\Project\\Java\\DataStructure\\Set\\src\\pride-and-prejudice.txt", words1);
//        System.out.println("Total words: " + words1.size());
//
//        LinkedListSet<String> set1 = new LinkedListSet<>();
//        for (String word: words1)
//            set1.add(word);
//        System.out.println("Total different words: " + set1.getSize());

        String filename = "D:\\Project\\Java\\DataStructure\\Set\\src\\test\\pride-and-prejudice.txt";
        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, filename);
        System.out.println(time1);
        LinkedListSet<String> listSet = new LinkedListSet<>();
        double time2 = testSet(listSet, filename);
        System.out.println(time2);
        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet, filename);
        System.out.println(time3);
    }

    private  static double testSet(Set<String> set, String filename){
        long startTime = System.nanoTime();

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile(filename, words);
        System.out.println("Total words: " + words.size());

        for (String word: words)
            set.add(word);
        for (String word: words)
            set.contains(word);
        System.out.println("Total different words: " + set.getSize());

        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }
}
