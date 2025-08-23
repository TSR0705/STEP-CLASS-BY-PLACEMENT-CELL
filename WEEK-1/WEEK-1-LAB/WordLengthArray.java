import java.util.Scanner;

public class WordLengthArray {

    // Method to split the input text into words using charAt() method
    public static String[] splitTextIntoWords(String text) {
        StringBuilder word = new StringBuilder();
        // Dynamically count number of words by space
        int wordCount = 0;
        
        // Iterate through each character to split words manually
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar != ' ' && currentChar != '\n' && currentChar != '\t') {
                word.append(currentChar);
            } else {
                if (word.length() > 0) {
                    wordCount++;
                    word.setLength(0); // Reset word after adding it
                }
            }
        }
        
        if (word.length() > 0) wordCount++; // For the last word
        
        // Now, split again based on spaces and return the words
        String[] words = new String[wordCount];
        int index = 0;
        word.setLength(0);  // Clear word buffer

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar != ' ' && currentChar != '\n' && currentChar != '\t') {
                word.append(currentChar);
            } else {
                if (word.length() > 0) {
                    words[index++] = word.toString();
                    word.setLength(0); // Reset word buffer
                }
            }
        }
        
        // Add the last word if it exists
        if (word.length() > 0) {
            words[index] = word.toString();
        }

        return words;
    }

    // Method to find the length of a string without using length()
    public static int getStringLength(String str) {
        int length = 0;
        try {
            while (true) {
                str.charAt(length);
                length++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // When the end of the string is reached, we catch the exception
        }
        return length;
    }

    // Method to create a 2D array of words and their corresponding lengths
    public static String[][] getWordAndLengths(String[] words) {
        String[][] result = new String[words.length][2];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int length = getStringLength(word); // Find the length of the word
            result[i][0] = word;
            result[i][1] = String.valueOf(length); // Convert length to String
        }

        return result;
    }

    // Method to display the results in a tabular format
    public static void displayResult(String[][] wordLengthArray) {
        System.out.println("Word\t\tLength");
        System.out.println("----------------------");
        for (int i = 0; i < wordLengthArray.length; i++) {
            // Convert the length string to an integer for proper formatting
            int length = Integer.parseInt(wordLengthArray[i][1]);
            System.out.println(wordLengthArray[i][0] + "\t\t" + length);
        }
    }

    public static void main(String[] args) {
        // Take input from user using Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String text = scanner.nextLine();
        
        // Split the text into words
        String[] words = splitTextIntoWords(text);
        
        // Get the 2D array of words and their corresponding lengths
        String[][] wordLengthArray = getWordAndLengths(words);
        
        // Display the result in a tabular format
        displayResult(wordLengthArray);
    }
}




