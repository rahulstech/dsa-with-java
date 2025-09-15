

 class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
 }

class CopyList {
    static RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode tmp = head;
        while(tmp != null) {
            RandomListNode n = new RandomListNode(tmp.label);
            RandomListNode next = tmp.next;
            tmp.next = n;
            n.next = next;
            tmp = next;
        }

        System.out.print("Temporary: ");
        print(head);

        tmp = head;
        while(tmp != null) {
            RandomListNode rnd = tmp.random;
            RandomListNode copy = tmp.next;

            if (null == rnd) {
                copy.random = null; 
            }
            else {
                copy.random = rnd.next;
            }

            tmp = copy.next;
        }

        RandomListNode copyHead = null, copyTail = null;
        tmp = head;
        while(tmp != null) {
            RandomListNode copy = tmp.next;
            tmp.next = copy.next;
            if (copyHead == null) {
                copyHead = copyTail = copy;
            }
            else {
                copyTail.next = copy;
                copyTail = copy;
            }
            tmp = tmp.next;
        }

        return copyHead;
    }

    static RandomListNode createList() {
        RandomListNode n1 = new RandomListNode(1);
        RandomListNode n2 = new RandomListNode(2);
        RandomListNode n3 = new RandomListNode(3);
        RandomListNode n4 = new RandomListNode(4);
        n1.random = n3;
        n2.random = n1;
        n4.random = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        return n1;
    }

    static void print(RandomListNode head) {
        for(RandomListNode n = head; n != null; n = n.next) {
            RandomListNode rnd = n.random;
            System.out.print(n.label);
            if (null != rnd) {
                System.out.print("("+rnd.label+")");
            }
            else {
                System.out.print("(null)");
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RandomListNode org = createList();

        System.out.println("Original list: ");
        print(org);

        RandomListNode copy = copyRandomList(org);

        System.out.println("Copy: ");
        print(copy);
    }
}
