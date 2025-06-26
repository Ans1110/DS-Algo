package Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjMat {
    List<Integer> vertices;
    List<List<Integer>> adjMat;

    public GraphAdjMat(int[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.adjMat = new ArrayList<>();

        for (int val : vertices) {
            addVertex(val);
        }

        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    public int size() {
        return vertices.size();
    }

    public void addVertex(int val) {
        int n = size();
        vertices.add(val);
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newRow.add(0);
        }
        adjMat.add(newRow);
        for(List<Integer> row : adjMat) {
            row.add(0);
        }
    }

    public void removeVertex(int index) {
        if (index >= size()) throw new Error("Index out of bounds");

        vertices.remove(index);
        adjMat.remove(index);
        for (List<Integer> row : adjMat) {
            row.remove(index);
        }
    }

    public void addEdge(int from, int to) {
        if (from < 0 || to < 0 || from >= size() || to >= size() || from == to) throw new Error("Index out of bounds");
        adjMat.get(from).set(to, 1);
        adjMat.get(to).set(from, 1);
    }

    public void removeEdge(int from, int to) {
        if (from < 0 || to < 0 || from >= size() || to >= size() || from == to) throw new Error("Index out of bounds");
        adjMat.get(from).set(to, 0);
        adjMat.get(to).set(from, 0);
    }

    public void print() {
        System.out.println("Vertices: \n" + vertices);
        System.out.println("Adjacency Matrix: \n" + adjMat);
    }
}
