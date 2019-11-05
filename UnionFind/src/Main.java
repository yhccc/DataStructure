import java.util.Random;

public class Main {

    private static double testUF(UnionFind uf, int m){
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
	    int size = 10000000;
	    int m = 10000000;
//	    UnionFind_1 uf1 = new UnionFind_1(size);
//	    System.out.println(testUF(uf1, m));
//        UnionFind_2 uf2 = new UnionFind_2(size);
//        System.out.println(testUF(uf2, m));
        UnionFind_3 uf3 = new UnionFind_3(size);
        System.out.println(testUF(uf3, m));
        UnionFind_4 uf4 = new UnionFind_4(size);
        System.out.println(testUF(uf4, m));
        UnionFind_5 uf5 = new UnionFind_5(size);
        System.out.println(testUF(uf5, m));
        UnionFind_6 uf6 = new UnionFind_6(size);
        System.out.println(testUF(uf6, m));
    }
}
