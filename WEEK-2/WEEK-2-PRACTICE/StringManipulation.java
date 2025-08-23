import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a sentence
        System.out.print("Enter a sentence with mixed formatting: ");
        String sentence = scanner.nextLine();

        // 1. trim() - Remove extra spaces from start and end
        String trimmed = sentence.trim();
        System.out.println("\nTrimmed: " + trimmed);

        // 2. replace() - Replace spaces with underscores
        String replacedSpaces = trimmed.replace(" ", "_");
        System.out.println("Spaces replaced with underscores: " + replacedSpaces);

        // 3. replaceAll() - Remove all digits
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("Without digits: " + noDigits);

        // 4. split() - Split sentence into words
        String[] words = trimmed.split("\\s+"); // split by spaces
        System.out.println("Words array: " + Arrays.toString(words));

        // 5. join() - Rejoin words with " | "
        String joined = String.join(" | ", words);
        System.out.println("Joined with | : " + joined);

        // Extra methods
        String noPunctuation = removePunctuation(trimmed);
        System.out.println("Without punctuation: " + noPunctuation);

        String capitalized = capitalizeWords(noPunctuation);
        System.out.println("Capitalized: " + capitalized);

        String reversedOrder = reverseWordOrder(trimmed);
        System.out.println("Reversed word order: " + reversedOrder);

        System.out.println("Word Frequency:");
        countWordFrequency(trimmed);

        scanner.close();
    }

    // Method to remove punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", ""); // removes punctuation
    }

    // Method to capitalize first letter of each word
    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    // Method to reverse the order of words
    public static String reverseWordOrder(String text) {
        String[] words = text.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }

    // Method to count word frequency
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        for (String word : frequency.keySet()) {
            System.out.println(word + " : " + frequency.get(word));
        }
    }
}
