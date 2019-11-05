public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num:nums) {
            bst.add(num);
        }
        bst.contains(3);
//        bst.preOrder();
//        System.out.println();
//        bst.preOrderNR();
//        System.out.println();
//        bst.inOrder();
//        System.out.println();
//        bst.inOrderNR();
//        System.out.println();
//        bst.postOrder();
//        System.out.println();
//        bst.postOrderNR();
//        System.out.println();
//        bst.levelOrder();
//        System.out.println();
//
//        bst.removeMin();
//        bst.inOrder();
//        System.out.println();
//        bst.removeMax();
//        bst.inOrder();
//        System.out.println();
//        bst.remove(4);
//        bst.inOrder();
//        System.out.println();
    }
}
