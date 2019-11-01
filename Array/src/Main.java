public class Main {

    public static void main(String[] args) {
//        Array<Integer> arr = new Array<>();
//        for (int i = 0; i < 10; i++)
//            arr.addLast(i);
//        System.out.println(arr);
//        arr.add(1, 100);
//        System.out.println(arr);
//        arr.addFirst(-1);
//        System.out.println(arr);
//
//        arr.remove(2);
//        System.out.println(arr);
//        arr.removeElement(4);
//        System.out.println(arr);
//        arr.removeLast();
//        arr.removeFirst();
//        System.out.println(arr);
//        System.out.println(arr.find(8));


        Array<Student> arr = new Array<>(2);
        arr.add(0,new Student("Alice", 100));
        arr.add(1,new Student("Bob", 66));
        arr.add(2,new Student("Charlie", 80));
        System.out.println(arr);
        System.out.println(arr.getCapacity());



    }

}
