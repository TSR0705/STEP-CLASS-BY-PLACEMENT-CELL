import java.util.Scanner;
import java.util.Arrays;

public class CompareTextSplit {

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

    // Method to compare the two String arrays (custom split and built-in split)
    public static boolean compareArrays(String[] arr1, String[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        // Take user input using Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String text = scanner.nextLine();

        // Split the text using the custom method
        String[] customSplitWords = splitTextIntoWords(text);
        
        // Split the text using the built-in split() method
        String[] builtInSplitWords = text.split(" ");

        // Compare the two arrays and display the result
        boolean areArraysEqual = compareArrays(customSplitWords, builtInSplitWords);

        System.out.println("Custom Split Words: " + Arrays.toString(customSplitWords));
        System.out.println("Built-in Split Words: " + Arrays.toString(builtInSplitWords));
        System.out.println("Do the arrays match? " + areArraysEqual);
    }
}

