import java.util.*;

public class DistanceVectorRouting {
    private int[][] distanceMatrix;
    private int[][] routingTable;c
    private int numberOfNodes;

    public DistanceVectorRouting(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        distanceMatrix = new int[numberOfNodes][numberOfNodes];
        routingTable = new int[numberOfNodes][numberOfNodes];
    }

    public void initializeDistanceMatrix(int[][] adjacencyMatrix) {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                distanceMatrix[i][j] = adjacencyMatrix[i][j];
                routingTable[i][j] = j;
            }
        }
    }

    public void bellmanFordAlgorithm() {
        for (int k = 0; k < numberOfNodes; k++) {
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    if (distanceMatrix[i][k] + distanceMatrix[k][j] < distanceMatrix[i][j]) {
                        distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                        routingTable[i][j] = routingTable[i][k];
                    }
                }
            }
        }
    }
    public void printRoutingTable() {
        System.out.println("Routing Table:");
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.print("Router " + i + ": ");
            for (int j = 0; j < numberOfNodes; j++) {
                System.out.print("To " + j + " via " + routingTable[i][j] + ", Cost " + distanceMatrix[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                {0, 3, 0, 7},
                {3, 0, 2, 0},
                {0, 2, 0, 1},
                {7, 0, 1, 0}
        };

        DistanceVectorRouting dvr = new DistanceVectorRouting(adjacencyMatrix.length);
        dvr.initializeDistanceMatrix(adjacencyMatrix);
        dvr.bellmanFordAlgorithm();
        dvr.printRoutingTable();
    }
}
