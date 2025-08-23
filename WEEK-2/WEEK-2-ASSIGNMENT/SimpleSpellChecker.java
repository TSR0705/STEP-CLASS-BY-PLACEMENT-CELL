import java.util.Scanner;

public class SimpleSpellChecker {

    // Method to split sentence into words without using split()
    public static String[] splitSentence(String sentence) {
        int n = sentence.length();
        String[] words = new String[50]; // assuming max 50 words
        int wordCount = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            char c = sentence.charAt(i);
            if (c == ' ' || c == '.' || c == ',' || c == '!' || c == '?') {
                if (start < i) {
                    words[wordCount++] = sentence.substring(start, i);
                }
                start = i + 1;
            }
        }

        // Last word
        if (start < n) {
            words[wordCount++] = sentence.substring(start, n);
        }

        // Trim array to actual word count
        String[] result = new String[wordCount];
        for (int i = 0; i < wordCount; i++) {
            result[i] = words[i];
        }
        return result;
    }

    // Method to calculate string distance
    public static int stringDistance(String w1, String w2) {
        int len1 = w1.length();
        int len2 = w2.length();
        int distance = Math.abs(len1 - len2);

        // Compare characters for min length
        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    // Method to find closest dictionary word
    public static String findClosestWord(String word, String[] dictionary) {
        int minDist = Integer.MAX_VALUE;
        String closest = word; // default = same word

        for (String dictWord : dictionary) {
            int dist = stringDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                closest = dictWord;
            }
        }

        // If distance acceptable (≤ 2), return suggestion else same word
        if (minDist <= 2 && !closest.equalsIgnoreCase(word)) {
            return closest + " (dist=" + minDist + ")";
        } else {
            return "Correct";
        }
    }

    // Method to display report
    public static void displayResults(String[] words, String[] dictionary) {
        System.out.printf("%-15s %-20s %-15s\n", "Word", "Suggestion", "Status");
        System.out.println("-----------------------------------------------------");
        for (String w : words) {
            String suggestion = findClosestWord(w, dictionary);
            if (suggestion.equals("Correct")) {
                System.out.printf("%-15s %-20s %-15s\n", w, "-", "Correct");
            } else {
                System.out.printf("%-15s %-20s %-15s\n", w, suggestion, "Misspelled");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample dictionary
        String[] dictionary = {"hello", "world", "java", "spell", "checker", "program"};

        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();

        // Split into words
        String[] words = splitSentence(sentence);

        // Display spell check results
        displayResults(words, dictionary);

        sc.close();
    }
}
