import java.util.*;

public class DriverWordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is an object-oriented programming language. Java is widely used for enterprise software. "
                         + "Object-oriented concepts in Java make code reusable and robust. Java threads support concurrency.";

        System.out.println("=== Input Paragraph ===");
        System.out.println(paragraph + "\n");

        // Normalize and split by non-alphanumeric characters
        String[] words = paragraph.toLowerCase().split("[^a-zA-Z0-9]+");

        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            if (!w.isEmpty()) {
                freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
            }
        }

        // Sort entries by frequency (descending) using Comparator
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(freqMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int cmp = e2.getValue().compareTo(e1.getValue()); // Descending by count
                if (cmp == 0) {
                    return e1.getKey().compareTo(e2.getKey());   // Ascending alphabetically
                }
                return cmp;
            }
        });

        System.out.println("=== Word Frequencies (Sorted Highest First) ===");
        System.out.printf("%-18s %s\n", "WORD", "FREQUENCY");
        System.out.println("---------------------------");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.printf("%-18s %d\n", entry.getKey(), entry.getValue());
        }
    }
}