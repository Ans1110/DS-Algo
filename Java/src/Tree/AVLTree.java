package Tree;

public class AVLTree {
    private AVLTreeNode root;

    private int height(AVLTreeNode node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(AVLTreeNode node) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int balanceFactor(AVLTreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private AVLTreeNode rightRotate(AVLTreeNode node) {
        AVLTreeNode child = node.left;
        AVLTreeNode grandChild = child.right;
        child.right = node;
        node.left = grandChild;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    private AVLTreeNode leftRotate(AVLTreeNode node) {
        AVLTreeNode child = node.right;
        AVLTreeNode grandChild = child.left;
        child.left = node;
        node.right = grandChild;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    public AVLTreeNode rotate(AVLTreeNode node) {
        int balanceFactor = balanceFactor(node);

        if (balanceFactor > 1) {
            if (balanceFactor(node.left) >= 0) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balanceFactor < -1) {
            if (balanceFactor(node.right) <= 0) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    private AVLTreeNode insertHelper(AVLTreeNode node, int val) {
        if (node == null) return new AVLTreeNode(val);

        if (val < node.val) {
            node.left = insertHelper(node.left, val);
        } else if (val > node.val) {
            node.right = insertHelper(node.right, val);
        } else {
            return node;
        }

        updateHeight(node);
        return rotate(node);
    }

    public void insert(int val) {
        root = insertHelper(root, val);
    }

    private AVLTreeNode removeHelper(AVLTreeNode node, int val) {
        if (node == null) return null;

        if (val < node.val) {
            node.left = removeHelper(node.left, val);
        } else if (val > node.val) {
            node.right = removeHelper(node.right, val);
        } else {
            if (node.left == null || node.right == null) {
                AVLTreeNode child = node.left != null ? node.left : node.right;
                if (child == null) {
                    return null;
                } else {
                    node = child;
                }
            } else {
                AVLTreeNode tmp = node.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                node.right = removeHelper(node.right, tmp.val);
                node.val = tmp.val;
            }
        }
        updateHeight(node);
        return rotate(node);
    }

    public void remove(int val) {
        root = removeHelper(root, val);
    }
}

