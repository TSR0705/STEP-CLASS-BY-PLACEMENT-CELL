import java.util.*;

public class StringUtilityApp {
    // StringBuilder for efficient string building
    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=== STRING UTILITY APPLICATION ===");

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Text Analysis");
            System.out.println("2. String Transformation");
            System.out.println("3. ASCII Operations");
            System.out.println("4. Performance Testing");
            System.out.println("5. String Comparison Analysis");
            System.out.println("6. Custom String Algorithms");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    performTextAnalysis(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter text: ");
                    String text = scanner.nextLine();
                    System.out.println("Enter operations (comma separated: trim, upper, lower, replace): ");
                    String[] ops = scanner.nextLine().split(",");
                    System.out.println("Result: " + performTransformations(text, ops));
                    break;
                case 3:
                    System.out.print("Enter text: ");
                    performASCIIOperations(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter number of iterations: ");
                    performPerformanceTest(scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Enter first string: ");
                    String s1 = scanner.nextLine();
                    System.out.print("Enter second string: ");
                    String s2 = scanner.nextLine();
                    performComparisonAnalysis(new String[]{s1, s2});
                    break;
                case 6:
                    System.out.print("Enter text: ");
                    performCustomAlgorithms(scanner.nextLine());
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            displayResults();
        } while (choice != 0);

        scanner.close();
    }

    // 1. Text Analysis
    public static void performTextAnalysis(String text) {
        output.append("\n=== TEXT ANALYSIS ===\n");
        output.append("Length: ").append(text.length()).append("\n");
        String[] words = text.trim().split("\\s+");
        output.append("Word count: ").append(words.length).append("\n");
        output.append("Sentence count: ").append(text.split("[.!?]").length).append("\n");

        // Character frequency
        int[] freq = new int[256];
        for (char c : text.toCharArray()) freq[c]++;
        output.append("Character Frequency:\n");
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) output.append((char) i).append(": ").append(freq[i]).append("\n");
        }
    }

    // 2. String Transformations
    public static String performTransformations(String text, String[] operations) {
        StringBuilder sb = new StringBuilder(text);
        for (String op : operations) {
            op = op.trim().toLowerCase();
            switch (op) {
                case "trim": sb = new StringBuilder(sb.toString().trim()); break;
                case "upper": sb = new StringBuilder(sb.toString().toUpperCase()); break;
                case "lower": sb = new StringBuilder(sb.toString().toLowerCase()); break;
                case "replace": sb = new StringBuilder(sb.toString().replace("a", "@")); break;
                default: break;
            }
        }
        return sb.toString();
    }

    // 3. ASCII Operations
    public static void performASCIIOperations(String text) {
        output.append("\n=== ASCII OPERATIONS ===\n");
        for (char c : text.toCharArray()) {
            output.append(c).append(" -> ").append((int) c).append("\n");
        }
        // Simple Caesar cipher (+3)
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) encrypted.append((char) (c + 3));
        output.append("Encrypted (+3): ").append(encrypted).append("\n");
    }

    // 4. Performance Testing
    public static void performPerformanceTest(int iterations) {
        output.append("\n=== PERFORMANCE TEST ===\n");

        long start = System.nanoTime();
        String s = "";
        for (int i = 0; i < iterations; i++) s += "x";
        long end = System.nanoTime();
        output.append("String concat: ").append(end - start).append(" ns\n");

        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) sb.append("x");
        end = System.nanoTime();
        output.append("StringBuilder: ").append(end - start).append(" ns\n");

        start = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) sbf.append("x");
        end = System.nanoTime();
        output.append("StringBuffer: ").append(end - start).append(" ns\n");
    }

    // 5. String Comparison Analysis
    public static void performComparisonAnalysis(String[] strings) {
        String str1 = strings[0];
        String str2 = strings[1];

        output.append("\n=== STRING COMPARISON ===\n");
        output.append("== : ").append(str1 == str2).append("\n");
        output.append("equals : ").append(str1.equals(str2)).append("\n");
        output.append("equalsIgnoreCase : ").append(str1.equalsIgnoreCase(str2)).append("\n");
        output.append("compareTo : ").append(str1.compareTo(str2)).append("\n");
        output.append("compareToIgnoreCase : ").append(str1.compareToIgnoreCase(str2)).append("\n");

        // Similarity %
        int distance = levenshteinDistance(str1, str2);
        double similarity = (1 - (double) distance / Math.max(str1.length(), str2.length())) * 100;
        output.append("Similarity: ").append(String.format("%.2f", similarity)).append("%\n");
    }

    private static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // 6. Custom Algorithms
    public static void performCustomAlgorithms(String text) {
        output.append("\n=== CUSTOM ALGORITHMS ===\n");

        // Palindrome
        String reversed = new StringBuilder(text).reverse().toString();
        output.append("Palindrome? ").append(text.equalsIgnoreCase(reversed)).append("\n");

        // Simple pattern matching
        if (text.contains("Java")) output.append("Contains 'Java' keyword\n");
        else output.append("Does not contain 'Java'\n");
    }

    // Helper method
    public static void displayResults() {
        System.out.println(output.toString());
        output.setLength(0); // reset for next use
    }
}
