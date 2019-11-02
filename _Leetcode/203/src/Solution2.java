class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        // 使用虚拟头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while(prev.next != null)
        {
            if(prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        return dummyHead.next;

    }

    public static void main(String[] args){
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        System.out.println((new Solution2()).removeElements(head, 6));
    }
}