public interface Stack<E> {
    /*
        后进先出
        Last In First Out(LIFO)
        栈的应用：Undo操作(撤销)
                 程序调用系统栈
                 括号匹配
     */

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
