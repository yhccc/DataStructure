public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
//        else if(head.val == val)
//            return removeElements(head.next, val);
//        else{
//            head.next = removeElements(head.next, val);
//            return head;
//        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        System.out.println((new Solution3()).removeElements(head, 6));
    }
}
