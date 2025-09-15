
class ReverseList2 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseBetween(ListNode A, int B, int C) {
        ListNode prev = null;
        int p = 1;
        while(p<B) {
            prev = null == prev ? A : prev.next;
            p++;
        }

        ListNode parent = prev;
        ListNode end = null == prev ? null : prev.next;
        ListNode cur = null == prev ? A : prev.next;
        ListNode next = null;
        p++;
        while(p<=C) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            p++;
        }

        if (null == parent) {
            return prev;
        }
        else {
            end.next = cur;
            parent.next = prev;
            return A;
        }
    }

    static ListNode createList(int[] A) {
        ListNode head = null, tail = null;
        for(int a : A) {
            ListNode n = new ListNode(a);
            if (head == null) {
                head = tail = n;
            }
            else {
                tail.next = n;
                tail = n;
            }
        }
        return head;
    }

    static void printList(ListNode A) {
        for(ListNode n = A; n != null; n = n.next) {
            System.out.print(n.val+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode A = createList(new int[]{1,2,3,4,5});
        System.out.println("Original: ");
        printList(A);

        ListNode n = reverseBetween(A, 2, 4);
        System.out.println("Reversed: ");
        printList(n);
    }
}
