import java.util.*;

/**
 * JobSequencing.java
 * Usage: javac JobSequencing.java && java JobSequencing
 * Simple greedy job sequencing with deadlines.
 */
public class PR2 {
    static class Job {
        String id;
        int deadline;
        int profit;
        Job(String id, int deadline, int profit) {
            this.id = id; this.deadline = deadline; this.profit = profit;
        }
        public String toString() { return id + "(d=" + deadline + ",p=" + profit + ")"; }
    }

    public static Map<String, Object> scheduleJobs(Job[] jobs) {
        Arrays.sort(jobs, (a, b) -> Integer.compare(b.profit, a.profit)); // desc profit
        int maxDeadline = 0;
        for (Job j : jobs) maxDeadline = Math.max(maxDeadline, j.deadline);
        String[] slots = new String[maxDeadline + 1]; // 1..maxDeadline
        int totalProfit = 0;

        for (Job j : jobs) {
            for (int d = Math.min(maxDeadline, j.deadline); d >= 1; d--) {
                if (slots[d] == null) {
                    slots[d] = j.id;
                    totalProfit += j.profit;
                    break;
                }
            }
        }

        List<String> scheduled = new ArrayList<>();
        for (int i = 1; i <= maxDeadline; i++) if (slots[i] != null) scheduled.add(slots[i]);

        Map<String, Object> res = new HashMap<>();
        res.put("scheduled", scheduled);
        res.put("totalProfit", totalProfit);
        return res;
    }

    public static void main(String[] args) {
        Job[] jobs = {
                new Job("A", 2, 100),
                new Job("B", 1, 19),
                new Job("C", 2, 27),
                new Job("D", 1, 25),
                new Job("E", 3, 15)
        };
        Map<String, Object> res = scheduleJobs(jobs);
        System.out.println("Jobs (sorted by profit): " + Arrays.toString(jobs));
        System.out.println("Scheduled (time order): " + res.get("scheduled"));
        System.out.println("Total profit = " + res.get("totalProfit"));
    }
}
