import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String filename = "D:\\Project\\Java\\DataStructure\\Set\\src\\test\\pride-and-prejudice.txt";

        Map<String, Integer> bst = new BSTMap<>();
        Map<String, Integer> list = new LinkedListMap<>();
        double time1 = testMap(bst, filename);
        double time2 = testMap(list, filename);
        System.out.println(time1);
        System.out.println(time2);
    }

    private static double testMap(Map<String, Integer> map, String filename){
        long startTime = System.nanoTime();
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)){
            for (String word: words){
                if (!map.contains(word))
                    map.add(word, 1);
                else
                    map.set(word, map.get(word) + 1);
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
