
import java.util.Scanner;

public class TrimStringUsingCharAt {

    // Method to find start and end indexes after trimming spaces
    public static int[] findTrimIndexes(String str) {
        int start = 0;
        int end = str.length() - 1;

        // Find first non-space character from start
        while (start <= end && str.charAt(start) == ' ') {
            start++;
        }

        // Find first non-space character from end
        while (end >= start && str.charAt(end) == ' ') {
            end--;
        }

        return new int[]{start, end};
    }

    // Method to create substring using charAt()
    public static String createSubstring(String str, int start, int end) {
        String result = "";
        for (int i = start; i <= end; i++) {
            result += str.charAt(i);
        }
        return result;
    }

    // Method to compare two strings using charAt()
    public static boolean compareStrings(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string with spaces: ");
        String input = sc.nextLine();

        // Using user-defined trim
        int[] indexes = findTrimIndexes(input);
        String trimmedUsingCharAt = createSubstring(input, indexes[0], indexes[1]);

        // Using built-in trim
        String trimmedUsingBuiltIn = input.trim();

        // Display results
        System.out.println("Trimmed using charAt: \"" + trimmedUsingCharAt + "\"");
        System.out.println("Trimmed using built-in trim: \"" + trimmedUsingBuiltIn + "\"");

        // Compare
        boolean areEqual = compareStrings(trimmedUsingCharAt, trimmedUsingBuiltIn);
        System.out.println("Both methods give same result? " + areEqual);

        sc.close();
    }
}
