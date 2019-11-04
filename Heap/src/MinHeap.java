public class MinHeap<E extends  Comparable<E>> {

    private Array<E> data;

    // 构造函数，参数为容积
    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }

    // 无参构造函数
    public MinHeap(){
        data = new Array<>();
    }

    // 元素个数
    public int size(){
        return data.getSize();
    }

    // 是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    public E findMin(){
        if (isEmpty())
            throw new IllegalArgumentException("Cannot findMin when heap is empty.");
        return data.get(0);
    }

    public E extractMin(){
        E ret = findMin();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲结点的索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index - 0 doesn't have parent");
        return (index - 1) / 2;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子结点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子结点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }
    // 上浮
    private void siftUp(int index){
        while(index > 0){
            if (data.get(index).compareTo(data.get(parent(index))) < 0){
                data.swap(index, parent(index));
                index = parent(index);
            }
            else
                return;
        }
    }
    // 下沉
    private void siftDown(int index){
        while(leftChild(index) < size()){
            // 与较小的交换
            int swapIndex = leftChild(index);
            if (rightChild(index) < size())
                swapIndex = data.get(leftChild(index)).compareTo(data.get(rightChild(index))) < 0 ?
                        leftChild(index) : rightChild(index);
            if (data.get(index).compareTo(data.get(swapIndex)) > 0){
                data.swap(index, swapIndex);
                index = swapIndex;
            }
            else
                return;
        }
    }
}
