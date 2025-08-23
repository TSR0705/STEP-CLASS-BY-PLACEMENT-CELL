import java.util.Scanner;

public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ADVANCED STRING ANALYZER ===");
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        // Perform all comparisons
        performAllComparisons(str1, str2);

        // Similarity percentage
        double similarity = calculateSimilarity(str1, str2);
        System.out.printf("Similarity percentage: %.2f%%\n", similarity);

        // Performance analysis
        String[] inputs = {str1, str2, "Performance", "Testing", "StringBuilder", "Concatenation"};
        long startTime = System.nanoTime();
        String processed = optimizedStringProcessing(inputs);
        long endTime = System.nanoTime();
        System.out.println("\nOptimized processing result: " + processed);
        System.out.println("Processing time: " + (endTime - startTime) + " ns");

        // Memory analysis
        analyzeMemoryUsage(str1, str2, processed);

        // Demonstrate intern()
        demonstrateStringIntern();

        scanner.close();
    }

    // Method to calculate string similarity percentage using Levenshtein distance
    public static double calculateSimilarity(String str1, String str2) {
        int distance = levenshteinDistance(str1, str2);
        int maxLen = Math.max(str1.length(), str2.length());
        if (maxLen == 0) return 100.0;
        return ((double) (maxLen - distance) / maxLen) * 100;
    }

    private static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // Method to perform all comparison types
    public static void performAllComparisons(String str1, String str2) {
        System.out.println("\n=== STRING COMPARISONS ===");
        System.out.println("Reference equality (==): " + (str1 == str2));
        System.out.println("Content equality (equals): " + str1.equals(str2));
        System.out.println("Case-insensitive equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic comparison (compareTo): " + str1.compareTo(str2));
        System.out.println("Case-insensitive compareToIgnoreCase: " + str1.compareToIgnoreCase(str2));
    }

    // Method to analyze string memory usage
    public static void analyzeMemoryUsage(String... strings) {
        System.out.println("\n=== MEMORY USAGE ANALYSIS (approximate) ===");
        for (String s : strings) {
            if (s != null) {
                int bytes = 8 + (s.length() * 2); // approx. 2 bytes per char + object header
                System.out.println("\"" + s + "\" -> length: " + s.length() + ", approx memory: " + bytes + " bytes");
            }
        }
    }

    // Method to optimize string operations using StringBuilder
    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputs) {
            sb.append(s).append(" | ");
        }
        return sb.toString();
    }

    // Demonstrating intern() and string pool behavior
    public static void demonstrateStringIntern() {
        System.out.println("\n=== STRING INTERN DEMO ===");

        String a = new String("Java");
        String b = "Java";
        String c = a.intern();

        System.out.println("a == b : " + (a == b)); // false
        System.out.println("b == c : " + (b == c)); // true
        System.out.println("a.equals(b): " + a.equals(b)); // true

        String x = "HelloWorld";
        String y = new String("HelloWorld").intern();
        System.out.println("x == y : " + (x == y)); // true
    }
}
