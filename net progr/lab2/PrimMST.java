import java.util.*;

class Edge {
    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class PrimMST {
    public static void main(String[] args) {

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge('a', 'c', 6));
        edges.add(new Edge('a', 'b', 6));
        edges.add(new Edge('a', 'd', 6));
        edges.add(new Edge('b', 'd', 2));
        edges.add(new Edge('c', 'd', 2));

        List<Edge> mst = primMST(edges);

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.start + " - " + edge.end + " : " + edge.weight);
        }
    }

    public static List<Edge> primMST(List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();
        Set<Character> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        char startVertex = 'a';
        visited.add(startVertex);

        for (Edge edge : edges) {
            if (edge.start == startVertex) {
                pq.offer(edge);
            }
        }

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            if (!visited.contains(minEdge.end)) {
                visited.add(minEdge.end);
                mst.add(minEdge);

                for (Edge edge : edges) {
                    if (edge.start == minEdge.end && !visited.contains(edge.end)) {
                        pq.offer(edge);
                    }
                }
            }
        }

        return mst;
    }
}
