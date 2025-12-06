class ReverseList {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node reverse(Node head) {
        Node prev = head;
        for (Node n = head.next; n != null;) {
            Node next = n.next;
            n.next = prev;
            prev = n;
            n = next;
        }

        head.next = null;
        return prev;
    }

    static Node createList(int[] values) {
        Node head = null, prt = null;
        for (int val : values) {
            Node n = new Node(val);
            if (head == null) {
                head = n;
            }
            else {
                prt.next = n;
            }
            prt = n;
        }
        return head;
    }

    static void print(Node head) {
        for(Node n = head; n != null; n = n.next) {
            System.out.print(n.val+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] values = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Node head = createList(values);
        Node rev = reverse(head);
        System.out.println("Original: ");
        print(head);
        System.out.println("Reverse: ");
        print(rev);
    }
}