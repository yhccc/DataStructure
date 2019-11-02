class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 对第一个结点进行特殊处理
        while(head != null && head.val == val)
            head = head.next;
        if(head == null)
            return head;

        ListNode prev = head;
        while(prev.next != null)
        {
            if(prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        return head;
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        System.out.println((new Solution()).removeElements(head, 6));
    }
}