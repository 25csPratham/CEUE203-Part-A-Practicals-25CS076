import java.util.*;

public class DriverLeaderboard {
    public static void printTopN(Map<String, Integer> scoreMap, int n) {
        List<PlayerScore> leaderboard = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            leaderboard.add(new PlayerScore(entry.getKey(), entry.getValue()));
        }

        // Comparator: Sort by score descending; tie-break by name ascending
        leaderboard.sort((p1, p2) -> {
            int scoreDiff = Integer.compare(p2.getScore(), p1.getScore());
            if (scoreDiff != 0) {
                return scoreDiff;
            }
            return p1.getName().compareToIgnoreCase(p2.getName());
        });

        System.out.println("=== Top " + n + " Leaderboard (Ties Broken by Name) ===");
        System.out.printf("%-6s %-15s %s\n", "RANK", "PLAYER", "SCORE");
        System.out.println("------------------------------------");
        int limit = Math.min(n, leaderboard.size());
        for (int i = 0; i < limit; i++) {
            PlayerScore p = leaderboard.get(i);
            System.out.printf("#%-5d %-15s %d\n", (i + 1), p.getName(), p.getScore());
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Riya", 2850);
        scores.put("Aarav", 3200);
        scores.put("Vivaan", 3200); // Tied score with Aarav -> Alphabetical order tie-breaker
        scores.put("Ananya", 2400);
        scores.put("Kabir", 3100);
        scores.put("Diya", 2950);
        scores.put("Arjun", 2850);  // Tied score with Riya

        printTopN(scores, 5);
    }
}