import java.util.*;

public class DriverUniqueVisitors {
    public static void main(String[] args) {
        // List of login records with duplicate visitors
        List<String> logins = Arrays.asList(
            "alice", "bob", "charlie", "alice", "david",
            "bob", "eve", "frank", "alice", "charlie"
        );

        System.out.println("=== Raw Login Stream ===");
        System.out.println(logins);

        // 1. HashSet for counting unique users
        Set<String> uniqueSet = new HashSet<>(logins);
        System.out.println("\nTotal unique users (using HashSet): " + uniqueSet.size());

        // 2. LinkedHashSet to preserve first-seen insertion order
        Set<String> orderedSet = new LinkedHashSet<>(logins);
        System.out.println("First-seen ordered unique visitors (using LinkedHashSet):");
        System.out.println(orderedSet);

        // Print first 3 distinct names
        System.out.println("\nFirst 3 distinct visitors:");
        int count = 0;
        for (String user : orderedSet) {
            System.out.println((count + 1) + ". " + user);
            count++;
            if (count == 3) break;
        }
    }
}