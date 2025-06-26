package Graph;

import java.util.*;

public class GraphAdjList {
    Map<Vertex, List<Vertex>> adjList;

    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();

        for (Vertex[] edge: edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    public int size() {
        return adjList.size();
    }

    public void addEdge(Vertex from, Vertex to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to) || from == to) throw new Error("Vertex not found");
        adjList.get(from).add(to);
        adjList.get(to).add(from);
    }

    public void removeEdge(Vertex from, Vertex to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to) || from == to) throw new Error("Vertex not found");
        adjList.get(from).remove(to);
        adjList.get(to).remove(from);
    }

    public void addVertex(Vertex vet) {
        if (adjList.containsKey(vet)) return;
        adjList.put(vet, new ArrayList<>());
    }

    public void removeVertex(Vertex vet) {
        if (!adjList.containsKey(vet)) return;
        adjList.remove(vet);
        for (List<Vertex> list : adjList.values()) {
            list.remove(vet);
        }
    }

    public void print() {
        for (Map.Entry<Vertex, List<Vertex>> pair : adjList.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex v : pair.getValue()) {
                tmp.add(v.val);
            }
            System.out.println(pair.getKey().val + " -> " + tmp);
        }
    }

    public List<Vertex> BFS(GraphAdjList graph, Vertex startVet) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVet);
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(startVet);
        while (!queue.isEmpty()) {
            Vertex vet = queue.poll();
            res.add(vet);
            for (Vertex adVet : graph.adjList.get(vet)) {
                if (visited.contains(adVet)) continue;
                queue.offer(adVet);
                visited.add(adVet);
            }
        }

        return res;
    }

    private void dfs(GraphAdjList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        res.add(vet);
        visited.add(vet);
        for (Vertex adVet : graph.adjList.get(vet)) {
            if (visited.contains(adVet)) continue;
            dfs(graph, visited, res, adVet);
        }
    }

    public List<Vertex> DFS(GraphAdjList graph, Vertex startVet) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        dfs(graph, visited, res, startVet);
        return res;
    }
}

