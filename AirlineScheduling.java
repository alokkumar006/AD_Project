import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Class to represent a directed edge in the airline network
class Edge {
    int source;
    int destination;
    int capacity;
    int flow;

    public Edge(int source, int destination, int capacity) {
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        this.flow = 0;
    }
}

// Class to represent the airline scheduling algorithm
public class AirlineSchedulingAlgorithm {
    private int numAirports;
    private List<Edge>[] graph;

    public AirlineSchedulingAlgorithm(int numAirports) {
        this.numAirports = numAirports;
        this.graph = new ArrayList[numAirports];

        for (int i = 0; i < numAirports; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination, int capacity) {
        Edge forwardEdge = new Edge(source, destination, capacity);
        Edge backwardEdge = new Edge(destination, source, 0);
        forwardEdge.flow = capacity;

        graph[source].add(forwardEdge);
        graph[destination].add(backwardEdge);
    }

    private boolean bfs(int source, int destination, int[] parent) {
        boolean[] visited = new boolean[numAirports];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge edge : graph[current]) {
                int next = edge.destination;

                if (!visited[next] && edge.capacity > edge.flow) {
                    queue.add(next);
                    visited[next] = true;
                    parent[next] = current;
                }
            }
        }

        return visited[destination];
    }

    private int fordFulkerson(int source, int destination) {
        int[] parent = new int[numAirports];
        int maxFlow = 0;

        while (bfs(source, destination, parent)) {
            int minCapacity = Integer.MAX_VALUE;
            int current = destination;

            while (current != source) {
                int prev = parent[current];
                for (Edge edge : graph[prev]) {
                    if (edge.destination == current) {
                        minCapacity = Math.min(minCapacity, edge.capacity - edge.flow);
                        break;
                    }
                }
                current = prev;
            }

            current = destination;

            while (current != source) {
                int prev = parent[current];
                for (Edge edge : graph[prev]) {
                    if (edge.destination == current) {
                        edge.flow += minCapacity;
                        break;
                    }
                }
                current = prev;
            }

            maxFlow += minCapacity;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        // Create an instance of the airline scheduling algorithm
        AirlineSchedulingAlgorithm scheduler = new AirlineSchedulingAlgorithm(4);

        // Add flights between airports
        scheduler.addEdge(0, 1, 3);
        scheduler.addEdge(0, 2, 2);
        scheduler.addEdge(1, 2, 2);
        scheduler.addEdge(1, 3, 3);
        scheduler.addEdge(2, 3, 3);


        int source = 0;
        int destination = 3;

        // Apply the network flow algorithm to optimize scheduling
        int maxFlow = scheduler.fordFulkerson(source, destination);

        System.out.println("Maximum flow: " + maxFlow);
    }
}
