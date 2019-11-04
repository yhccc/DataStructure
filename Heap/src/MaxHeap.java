public class MaxHeap<E extends Comparable<E>> {
    /*
        二叉堆是一棵完全二叉树
            堆中某个结点的值总是不大于其父结点的值（最大堆），即父结点的值大于等于子结点的值
            相应可以定义最小堆
        用数组存储完全二叉树，父亲结点与孩子结点索引的计算公式：
            (从1开始存储，便于计算)
            parent (i) = i / 2
            left child (i) = 2 * i
            right child (i) = 2 * i + 1
            (从0开始存储)
            parent (i) = (i - 1) / 2
            left child (i) = 2 * i + 1
            right child (i) = 2 * i + 2
     */

    /*
        扩展：d叉堆
             索引堆：保存所有元素的索引
             二项堆
             斐波那契堆
     */

    private Array<E> data;

    /**
     * 构造函数
     * @param capacity 容量
     */
    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    /**
     * 将任意数组整理成堆的形状
     * 实现：从最后一个非叶子结点向前遍历进行siftDown操作
     * 将n个元素逐个插入到一个空堆中，算法复杂度是O(nlogn)
     * heapify的过程，算法复杂度为O(n)
     */
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--)
            siftDown(i);
    }

    /**
     * 无参数构造函数, 默认容量为10
     */
    public MaxHeap(){
        data = new Array<>();
    }

    /**
     * @return 返回堆中的元素个数
     */
    public int size(){
        return data.getSize();
    }

    /**
     * @return 堆是否为空
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 向堆中添加元素
     * @param e 待添加元素
     */
    public void add(E e){
        // 思路：将元素添加到数组末尾
        //       对添加的元素进行上浮操作
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 取出堆中最大元素
     * @return 堆顶元素(最大元素)
     */
    public E extractMax(){
        // 思路是：堆中最后一个元素取代堆顶元素
        //        对现在堆顶元素进行下沉操作
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * @return 堆中最大的元素(即堆顶元素)
     */
    public E findMax(){
        if (isEmpty())
            throw new IllegalArgumentException("Cannot findMax when heap is empty.");
        return data.get(0);
    }

    /**
     * replace: 取出最大元素后，放入一个新元素
     * 实现: 可以先extractMax，再add，两次O(logn)的操作
     * 实现：可以直接将堆顶元素替换成新元素，然后siftDown，一次O(logn)的操作
     */
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
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
    // 上浮操作，调整添加元素的位置
    private void siftUp(int index){
        while(index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0){
            data.swap(index, parent(index));
            index = parent(index);

//            if (data.get(index).compareTo(data.get(parent(index))) > 0) {
//                data.swap(index, parent(index));
//                index = parent(index);
//            }
//            else
//                return;
        }
    }
    // 下沉操作，删除元素后，调整堆的结构
    private void siftDown(int index){
        // 有左孩子
        while(leftChild(index) < data.getSize()){
            int swapIndex = leftChild(index);
            // 有右孩子
            if (rightChild(index) < data.getSize()) {
                swapIndex = data.get(leftChild(index)).
                        compareTo(data.get(rightChild(index))) > 0 ?
                        leftChild(index) : rightChild(index);
            }
            // 下沉
            if (data.get(index).compareTo(data.get(swapIndex)) < 0){
                data.swap(index, swapIndex);  // 和较大的孩子进行交换
                index = swapIndex;
            }
            else{
                return;
            }
        }
    }
}
