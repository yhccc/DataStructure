public class AVLSet<E extends Comparable<E>> implements Set<E> {
    private AVL<E, Object> avl;
    public AVLSet(){
        avl = new AVL<>();
    }

    @Override
    public void add(E e) {
        avl.add(e, null);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }
}
