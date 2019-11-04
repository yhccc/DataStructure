public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> link = new LinkedList<>();
        link.removeElement(3);
        for (int i = 0; i < 5; i++){
            link.addLast(i);
            System.out.println(link);
        }
        link.addLast(3);
        System.out.println(link);
        System.out.println(link.contains(2));
        System.out.println(link.contains(5));
        link.removeElement(3);
        System.out.println(link);

//        link.add(2, 666);
//        System.out.println(link);

//        System.out.println(link.get(3));
//        System.out.println(link.contains(9));
//        link.set(4, 9);
//        System.out.println(link.contains(9));

//        link.remove(2);
//        System.out.println(link);
//        link.removeFirst();
//        System.out.println(link);
//        link.removeLast();
//        System.out.println(link);

    }
}
