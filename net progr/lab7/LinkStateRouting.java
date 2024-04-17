import java.util.*;

public class LinkStateRouting {
    private int[][] adjacencyMatrix;
    private int numberOfNodes;
    private boolean[] visited;
    private int[] distance;

    public LinkStateRouting(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
        visited = new boolean[numberOfNodes];
        distance = new int[numberOfNodes];
    }

    public void initializeAdjacencyMatrix(int[][] matrix) {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                adjacencyMatrix[i][j] = matrix[i][j];
            }
        }
    }

    public int getMinimumDistanceVertex() {
        int minVertex = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfNodes; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minVertex = i;
            }
        }
        return minVertex;
    }

    public void dijkstraAlgorithm(int source) {
        Arrays.fill(visited, false);
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[source] = 0;

        for (int i = 0; i < numberOfNodes - 1; i++) {
            int currentVertex = getMinimumDistanceVertex();
            visited[currentVertex] = true;

            for (int j = 0; j < numberOfNodes; j++) {
                if (!visited[j] && adjacencyMatrix[currentVertex][j] != 0 &&
                        distance[currentVertex] != Integer.MAX_VALUE &&
                        distance[currentVertex] + adjacencyMatrix[currentVertex][j] < distance[j]) {
                    distance[j] = distance[currentVertex] + adjacencyMatrix[currentVertex][j];
                }
            }
        }

        printRoutingTable(source);
    }

    public void printRoutingTable(int source) {
        System.out.println("Routing Table:");
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println("To " + i + " Cost " + distance[i]);
      }
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                { 0, 3, 0, 7, 9 },
                { 3, 0, 2, 0, 4 },
                { 0, 2, 0, 1, 5 },
                { 7, 0, 1, 0, 3 },
                { 9, 4, 5, 3, 0 }
        };

        LinkStateRouting lsr = new LinkStateRouting(adjacencyMatrix.length);
        lsr.initializeAdjacencyMatrix(adjacencyMatrix);
        lsr.dijkstraAlgorithm(0);
    }
}
