public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> link = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            link.addFirst(i);
            System.out.println(link);
        }

        link.add(2, 666);
        System.out.println(link);

//        System.out.println(link.get(3));
//        System.out.println(link.contains(9));
//        link.set(4, 9);
//        System.out.println(link.contains(9));

        link.remove(2);
        System.out.println(link);
        link.removeFirst();
        System.out.println(link);
        link.removeLast();
        System.out.println(link);

    }
}
