import java.util.ArrayList;
import java.util.LinkedList;

class Node { 
    int val;
    Node left, right;
    Node(int val) {
        this.val = val;
    }
}

class TreeThread {

    Node cur = null;
    Node left = null;
    Node rightmost = null;
    ArrayList<Integer> ans;

    ArrayList<Integer> solve(Node node) {
        ans = new ArrayList<>();
        cur = node;
        while(cur != null) {
            left = cur.left;
            if (null == left) {
                ans.add(cur.val);
                cur = cur.right;
            }
            else {
                Node predecessor = cur.left;
                while (predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == cur) {
                    ans.add(cur.val); // add the current value
                    predecessor.right = null; // break the thread
                    cur = cur.right; // move towards the right or current
                }
                else {
                    predecessor.right = cur; // create the thread
                    cur = left; // move towards left
                }
            }
        }
        return ans;
    }

    // Node getRightMost(Node node) {
    //     if (node.right == null || node.right == cur) {
    //         return node;
    //     }
    //     return getRightMost(node.right);
    // }

    public static void main(String... args) {
        TreeThread tt = new TreeThread();
        Node tree = createTree(new int[]{75,66,85,46,72,-1,95});
        ArrayList<Integer> inorder = tt.solve(tree);
        System.out.println(inorder);
    }

    static Node createTree(int[] levels) {
        LinkedList<Node> queue = new LinkedList<>();

        Node root = new Node(levels[0]);
        queue.add(root);

        for(int i=1; i<levels.length; i += 2) {
            Node left = levels[i] == -1 ? null : new Node(levels[i]);
            Node right = levels[i+1] == -1 ? null : new Node(levels[i+1]);
            

            // in each level nodes are filled from left to right
            Node cur = queue.removeFirst();
            if (null != cur) {
                cur.left = left;
                cur.right = right;
            }

            queue.add(left);
            queue.add(right);
        }
        return root;
    }
}