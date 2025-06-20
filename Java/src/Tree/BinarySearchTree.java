package Tree;

public class BinarySearchTree {
    private TreeNode root;

    public TreeNode search(int num) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < num) {
                cur = cur.right;
            } else if (cur.val > num) {
                cur = cur.left;
            } else {
                break;
            }
        }
        return cur;
    }

    public void insert(int num) {
        if (root == null) {
            root = new TreeNode(num);
        }

        TreeNode cur = root, pre = null;
        while (cur != null) {
            pre = cur;
            if (cur.val < num) {
                cur= cur.right;
            } else {
                cur = cur.left;
            }
        }

        TreeNode node = new TreeNode(num);
        if (pre.val < num) {
            pre.right = node;
        } else {
            pre.left = node;
        }
    }

    public void remove(int num) {
        if (root == null) return;
        TreeNode cur = root, pre = null;

        while (cur != null) {
            if (cur.val == num) break;
            pre = cur;
            if (cur.val < num) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        if (cur == null) return;

        if (cur.left == null || cur.right == null) {
            TreeNode child = cur.left != null ? cur.left : cur.right;

            if (cur != root) {
                if (pre.left == cur) {
                    pre.left = child;
                } else {
                    pre.right = child;
                }
            } else {
                root = child;
            }
        } else {
            TreeNode tmp = cur.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            remove(tmp.val);
            cur.val = tmp.val;
        }
    }
}
