import java.util.*;

public class TextProcessor {

    // Method to clean and validate input
    public static String cleanInput(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        // Remove leading/trailing spaces and multiple spaces between words
        input = input.trim().replaceAll("\\s+", " ");
        // Convert to proper case (first letter capital, rest lower)
        StringBuilder properCase = new StringBuilder();
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                properCase.append(Character.toUpperCase(words[i].charAt(0)));
                if (words[i].length() > 1) {
                    properCase.append(words[i].substring(1).toLowerCase());
                }
                if (i < words.length - 1) {
                    properCase.append(" ");
                }
            }
        }
        return properCase.toString();
    }

    // Method to analyze text
    public static void analyzeText(String text) {
        if (text.isEmpty()) {
            System.out.println("No text to analyze.");
            return;
        }

        // Count words
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        // Count characters excluding spaces
        int charCount = text.replace(" ", "").length();

        // Count sentences (ending with ., !, ?)
        int sentenceCount = text.split("[.!?]").length;

        // Find longest word
        String longestWord = "";
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", ""); // remove punctuation
            if (cleanWord.length() > longestWord.length()) {
                longestWord = cleanWord;
            }
        }

        // Find most common character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.replaceAll("\\s+", "").toCharArray()) {
            c = Character.toLowerCase(c);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        char mostCommonChar = ' ';
        int maxFreq = 0;
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostCommonChar = entry.getKey();
            }
        }

        // Display statistics
        System.out.println("\n--- Text Analysis ---");
        System.out.println("Total Words: " + wordCount);
        System.out.println("Total Characters (excluding spaces): " + charCount);
        System.out.println("Total Sentences: " + sentenceCount);
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Most Common Character: '" + mostCommonChar + "' occurred " + maxFreq + " times");
    }

    // Method to create word array and sort alphabetically
    public static String[] getWordsSorted(String text) {
        String[] words = text.replaceAll("[^a-zA-Z ]", "").split("\\s+"); // remove punctuation
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TEXT PROCESSOR ===");

        // 1. Ask user for a paragraph
        System.out.print("Enter a paragraph of text: ");
        String input = scanner.nextLine();

        // 2. Clean and validate input
        String cleanedText = cleanInput(input);
        System.out.println("\nCleaned Text: " + cleanedText);

        // 3. Analyze the text
        analyzeText(cleanedText);

        // 4. Show words in alphabetical order
        String[] sortedWords = getWordsSorted(cleanedText);
        System.out.println("\nWords in Alphabetical Order:");
        for (String word : sortedWords) {
            System.out.println(word);
        }

        // 5. Allow user to search for specific words
        System.out.print("\nEnter a word to search: ");
        String searchWord = scanner.nextLine().trim();
        boolean found = false;
        for (String word : sortedWords) {
            if (word.equalsIgnoreCase(searchWord)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("'" + searchWord + "' was found in the text.");
        } else {
            System.out.println("'" + searchWord + "' was NOT found in the text.");
        }

        scanner.close();
    }
}
