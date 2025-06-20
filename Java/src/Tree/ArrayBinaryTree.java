package Tree;

import java.util.ArrayList;
import java.util.List;

public class ArrayBinaryTree {
    private final List<Integer> tree;

    public ArrayBinaryTree(List<Integer> tree) {
        this.tree = tree;
    }

    public int size() {
        return this.tree.size();
    }

    public Integer val(int i) {
        if (i < 0 || i >= size()) return null;
        return this.tree.get(i);
    }

    public Integer left(int i) {
        return 2 * i + 1;
    }

    public Integer right(int i) {
        return 2 * i + 2;
    }

    public Integer parent(int i) {
        return (i - 1) / 2;
    }

    public List<Integer> levelOrder() {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < size(); i++) {
            res.add(val(i));
        }
        return res;
    }

    private void dfs(Integer i, String order, List<Integer> res) {
        if (val(i) == null) return;

        if ("pre".equals(order)) {
            res.add(val(i));
        }
        dfs(left(i), order, res);

        if ("in".equals(order)) {
            res.add(val(i));
        }
        dfs(right(i), order, res);

        if ("post".equals(order)) {
            res.add(val(i));
        }
    }

    public List<Integer> preOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0, "pre", res);
        return res;
    }

    public List<Integer> inOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0, "in", res);
        return res;
    }

    public List<Integer> postOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0, "post", res);
        return res;
    }
}
