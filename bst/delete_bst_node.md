# How to Approach the "Delete Node in a BST" Problem

Deleting a node from a Binary Search Tree (BST) is a classic problem that requires careful handling to preserve the BST property:
- Left subtree nodes < root
- Right subtree nodes > root

The challenge lies in restructuring the tree correctly when the target node is removed.

---

## Step 1: Understand the Goal
We are given:
- A BST
- A `key` value representing the node we need to delete

The output should be:
- The BST **after deletion**
- BST property must remain valid

---

## Step 2: Cases to Consider
When we find the node that matches the key, there are **three main cases**:

### Case 1: Node is a leaf (no children)
- Simply remove it.
- Since it has no subtrees, the parent just "forgets" this child.

### Case 2: Node has exactly one child
- Remove the node.
- Connect its parent directly to its only child.
- This way, the child subtree "moves up" one level.

### Case 3: Node has two children
- This is the tricky one.
- We cannot just delete the node, because both subtrees must stay attached.
- The standard approach:
  - Find the **inorder successor** (the smallest node in the right subtree), or alternatively the **inorder predecessor** (the largest node in the left subtree).
  - Replace the value of the node to be deleted with this successor/predecessor value.
  - Delete that successor/predecessor node from its original position (this will fall back to Case 1 or Case 2).

---

## Step 3: Think in Terms of Recursion
- Each call looks at the current node:
  - If the key is smaller → go left.
  - If the key is larger → go right.
  - If the key matches → handle one of the three cases.
- After handling, return the **new root** of the current subtree.
- This ensures parent links are updated correctly.

---

## Step 4: Complexity Consideration
- **Time complexity**: O(h), where h is the height of the tree.
  - Worst case: O(n) for a skewed tree.
  - Best/average case: O(log n) for a balanced tree.
- **Space complexity**: O(h) due to recursive calls.

---

## Step 5: Edge Cases
- Key is not present in the tree (no deletion needed).
- Tree is empty (immediately return null).
- Deleting the root node (tree restructuring should still follow the same rules).

---

## Step 6: Key Takeaways
- Break the problem into **searching** and **handling cases**.
- The crux lies in **Case 3 (two children)**, where successor/predecessor replacement is required.
- Always think: "What should the parent pointer look like after this deletion?"

---

✍️ With this structured thought process, you can derive the code naturally in any language. The actual implementation becomes straightforward once you internalize these cases.

