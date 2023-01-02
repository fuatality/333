
import java.util.Scanner;

public class Knapsack{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of items
        int items = sc.nextInt();

        // Read the weights and profits of the items
        int[] weights = new int[items];
        int[] profits = new int[items];
        for (int i = 0; i < items; i++) {
            weights[i] = sc.nextInt();
        }
        for (int i = 0; i < items; i++) {
            profits[i] = sc.nextInt();
        }

        // Read the maximum weight that can be carried
        int maxWeight = sc.nextInt();

        // Read the minimum profit required
        int minProfit = sc.nextInt();

        // Close the scanner
        sc.close();

        // Perform an exhaustive search to find a subset of items that meet the constraints
        boolean found = false;
        for (int i = 0; i < (1 << items); i++) {
            // Calculate the total weight and profit of the current subset
            int weight = 0;
            int profit = 0;
            for (int j = 0; j < items; j++) {
                if ((i & (1 << j)) > 0) {
                    weight += weights[j];
                    profit += profits[j];
                }
            }

            // Check if the current subset meets the constraints
            if (weight <= maxWeight && profit >= minProfit) {
                found = true;
                break;
            }
        }

        // Print the result
        if (found) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
