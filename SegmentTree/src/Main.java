public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> seg = new SegmentTree<>(nums,
                (a, b) -> a + b);
//        System.out.println(seg);

        System.out.println(seg.query(0, 2));
        System.out.println(seg.query(2, 4));
    }
}
