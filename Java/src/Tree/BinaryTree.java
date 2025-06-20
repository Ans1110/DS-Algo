package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    public List<Integer> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return list;
    }

    public List<Integer> preOrder(TreeNode root) {
        if (root == null) return null;
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        list.addAll(preOrder(root.left));
        list.addAll(preOrder(root.right));
        return list;
    }

    public List<Integer> inOrder(TreeNode root) {
        if (root == null) return null;
        List<Integer> list = new ArrayList<>();
        list.addAll(inOrder(root.left));
        list.add(root.val);
        list.addAll(inOrder(root.right));
        return list;
    }

    public List<Integer> postOrder(TreeNode root) {
        if (root == null) return null;
        List<Integer> list = new ArrayList<>();
        list.addAll(postOrder(root.left));
        list.addAll(postOrder(root.right));
        list.add(root.val);
        return list;
    }
}
