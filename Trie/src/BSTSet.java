public class BSTSet<E extends Comparable<E>> implements Set<E> {

    /*
        有序集合，元素具有顺序性
     */
    private BinarySearchTree<E> bst;

    public BSTSet(){
        bst = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }
}
