import java.util.*;

/**
 * FractionalKnapsack.java
 * Usage: javac FractionalKnapsack.java && java FractionalKnapsack
 * Greedy fractional knapsack implementation.
 */
public class PR3 {
    static class Item {
        int id;
        double value, weight;
        Item(int id, double value, double weight) { this.id = id; this.value = value; this.weight = weight; }
        double ratio() { return value / weight; }
        public String toString() { return "Item" + id + "(v=" + value + ",w=" + weight + ")"; }
    }

    public static Map<String, Object> solve(Item[] items, double capacity) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio(), a.ratio()));
        double remaining = capacity;
        double totalValue = 0.0;
        Map<Integer, Double> fractions = new LinkedHashMap<>();

        for (Item it : items) {
            if (remaining <= 0) break;
            if (it.weight <= remaining) {
                fractions.put(it.id, 1.0);
                remaining -= it.weight;
                totalValue += it.value;
            } else {
                double frac = remaining / it.weight;
                fractions.put(it.id, frac);
                totalValue += it.value * frac;
                remaining = 0;
            }
        }
        Map<String, Object> out = new HashMap<>();
        out.put("maxValue", totalValue);
        out.put("fractions", fractions);
        return out;
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(0, 60, 10),
                new Item(1, 100, 20),
                new Item(2, 120, 30)
        };
        double capacity = 50;
        Map<String, Object> ans = solve(items, capacity);
        System.out.println("Capacity = " + capacity);
        System.out.println("Max value achievable = " + ans.get("maxValue"));
        System.out.println("Fractions taken (id -> fraction) = " + ans.get("fractions"));
    }
}
