import java.util.*;

public class ALIFUAT_OZTURK_S015467 {
    static final int MAX_N = 20; // maximum number of venture projects
    static final int SOURCE = 0; // source node
    static final int SINK = MAX_N + 1; // sink node

    static int n; // number of venture projects
    static char[] names; // names of the venture projects
    static int[] outcomes; // outcomes of the venture projects
    static List<Integer>[] dependencies; // dependencies of the venture projects

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read input from console
        n = in.nextInt();
        names = new char[n];
        for (int i = 0; i < n; i++) {
            names[i] = in.next().charAt(0);
        }
        outcomes = new int[n];
        for (int i = 0; i < n; i++) {
            outcomes[i] = in.nextInt();
        }
        dependencies = new List[n];
        for (int i = 0; i < n; i++) {
            dependencies[i] = new ArrayList<>();
        }
        while (in.hasNext()) {
            String edge = in.next();
            if (edge.equals("Decide")) {
                break;
            }
            int u = findIndex(edge.charAt(1));
            int v = findIndex(edge.charAt(3));
            dependencies[u].add(v);
        }

        // Initialize the network flow graph
        int[][] capacity = new int[MAX_N + 2][MAX_N + 2];
        int[][] flow = new int[MAX_N + 2][MAX_N + 2];
        for (int i = 0; i < n; i++) {
            if (outcomes[i] > 0) {
                capacity[SOURCE][i + 1] = outcomes[i];
            } else {
                capacity[i + 1][SINK] = -outcomes[i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j : dependencies[i]) {
                capacity[i + 1][j + 1] = Integer.MAX_VALUE;
            }
        }

        // Find the maximum flow in the network flow graph
        int maxFlow = 0;
        while (true) {
            int[] pred = new int[MAX_N + 2];
            Arrays.fill(pred, -1);
            pred[SOURCE] = SOURCE;
            int[] minCapacity = new int[MAX_N + 2];
            minCapacity[SOURCE] = Integer.MAX_VALUE;
            Queue<Integer> q = new LinkedList<>();
            q.offer(SOURCE);
            while (!q.isEmpty()) {
                int u = q.poll();
                if (u == SINK) {
                    break;
                }
                for (int v = 0; v < MAX_N + 2; v++) {
                    if (pred[v] == -1 && capacity[u][v] > flow[u][v]) {
                        pred[v] = u;
                        minCapacity[v] = Math.min(minCapacity[u], capacity[u][v] - flow[u][v]);
                        q.offer(v);
                    }
                }
            }
            if (pred[SINK] == -1) {
                break;
            }
            for (int v = SINK; v != SOURCE; v = pred[v]) {
                flow[pred[v]][v] += minCapacity[SINK];
                flow[v][pred[v]] -= minCapacity[SINK];
            }
            maxFlow += minCapacity[SINK];
        }

        // Print the executable venture project subset and the maximum profit
        System.out.print("Venture projects: ");
        for (int i = 0; i < n; i++) {
            if (flow[SOURCE][i + 1] > 0) {
                System.out.print(names[i] + " ");
            }
        }
        System.out.println();
        System.out.println("Maximum profit: " + maxFlow);
    }

    // Returns the index of the venture project with the given name
    static int findIndex(char name) {
        for (int i = 0; i < n; i++) {
            if (names[i] == name) {
                return i;
            }
        }
        return -1;
    }
}

