public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    /**
     * 链表结点构造函数
     * @param arr 使用arr参数，创建一个链表，当前的ListNode为头结点
     */
    public ListNode(int[] arr){
        if(arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");

        this.val = arr[0];
        ListNode current = this;
        for(int i = 1; i < arr.length; i++){
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("Null");
        return res.toString();
    }
}