import java.util.Scanner;
import java.util.Arrays;

public class WordLengthAnalysis {

    // Method to find the length of a String without using the built-in length() method
    public static int getStringLength(String str) {
        int length = 0;
        try {
            while (true) {
                str.charAt(length);
                length++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Catch exception when the end of the string is reached
        }
        return length;
    }

    // Method to split the text into words using the charAt() method
    public static String[] splitTextIntoWords(String text) {
        int textLength = getStringLength(text);
        
        // Step 1: Count the number of spaces and store their indexes
        int[] spaceIndexes = new int[textLength]; // Array to store space indexes
        int spaceCount = 0;

        for (int i = 0; i < textLength; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[spaceCount++] = i;
            }
        }

        // Step 2: Calculate the number of words
        int wordCount = spaceCount + 1; // Words are spaces + 1
        String[] words = new String[wordCount];
        
        // Step 3: Extract words from the text using space indexes
        int startIdx = 0;
        for (int i = 0; i < wordCount; i++) {
            int endIdx = (i < spaceCount) ? spaceIndexes[i] : textLength;
            words[i] = text.substring(startIdx, endIdx).trim();
            startIdx = endIdx + 1;
        }

        return words;
    }

    // Method to create a 2D array of word and corresponding length
    public static String[][] getWordAndLengthArray(String[] words) {
        String[][] result = new String[words.length][2];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int length = getStringLength(word); // Find the length of the word
            result[i][0] = word;
            result[i][1] = String.valueOf(length); // Convert length to String
        }

        return result;
    }

    // Method to find the shortest and longest strings from the 2D array
    public static int[] findShortestAndLongest(String[][] wordLengthArray) {
        int[] result = new int[2]; // [0] = shortest length, [1] = longest length
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < wordLengthArray.length; i++) {
            int length = Integer.parseInt(wordLengthArray[i][1]);
            if (length < minLength) {
                minLength = length;
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }

        result[0] = minLength;
        result[1] = maxLength;
        return result;
    }

    public static void main(String[] args) {
        // Take user input using Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String text = scanner.nextLine();

        // Split the text into words using the custom split method
        String[] words = splitTextIntoWords(text);

        // Create a 2D array of words and their corresponding lengths
        String[][] wordLengthArray = getWordAndLengthArray(words);

        // Find the shortest and longest words from the 2D array
        int[] lengths = findShortestAndLongest(wordLengthArray);

        // Display the result
        System.out.println("Words: " + Arrays.toString(words));
        System.out.println("Shortest word length: " + lengths[0]);
        System.out.println("Longest word length: " + lengths[1]);
    }
}
