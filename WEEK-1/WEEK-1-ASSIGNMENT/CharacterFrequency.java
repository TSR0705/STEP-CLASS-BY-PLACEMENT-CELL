import java.util.Scanner;

public class CharacterFrequency {

    // Method to find frequency of characters
    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int n = chars.length;
        int[] freq = new int[n];

        // Initialize frequencies
        for (int i = 0; i < n; i++) {
            freq[i] = 1;
        }

        // Nested loop to count frequency
        for (int i = 0; i < n; i++) {
            if (chars[i] == '0') continue; // skip already counted duplicates
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; // mark duplicate
                }
            }
        }

        // Count how many unique characters to create final array
        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '0') uniqueCount++;
        }

        // Store characters and their frequencies
        String[] result = new String[uniqueCount];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] != '0') {
                result[index] = chars[i] + " : " + freq[i];
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Call the method to find frequency
        String[] frequencies = findFrequency(input);

        // Display results
        System.out.println("\nCharacter Frequencies:");
        for (String s : frequencies) {
            System.out.println(s);
        }

        scanner.close();
    }
}
