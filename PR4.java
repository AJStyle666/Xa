import java.util.*;

/**
 * ZeroOneKnapsack.java
 * Usage: javac ZeroOneKnapsack.java && java ZeroOneKnapsack
 * Classic DP for 0-1 knapsack with item reconstruction.
 */
public class PR4 {
    public static Map<String, Object> solve(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(dp[i - 1][w], val[i - 1] + dp[i - 1][w - wt[i - 1]]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        int maxValue = dp[n][W];
        List<Integer> chosen = new ArrayList<>();
        int w = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                chosen.add(i - 1); // item (i-1) included
                w -= wt[i - 1];
            }
        }
        Collections.reverse(chosen);

        Map<String, Object> out = new HashMap<>();
        out.put("maxValue", maxValue);
        out.put("chosenItems", chosen);
        return out;
    }

    public static void main(String[] args) {
        int[] wt = {10, 20, 30};
        int[] val = {60, 100, 120};
        int W = 50;
        Map<String, Object> res = solve(wt, val, W);
        System.out.println("Max value = " + res.get("maxValue"));
        System.out.println("Chosen item indices (0-based) = " + res.get("chosenItems"));
    }
}
