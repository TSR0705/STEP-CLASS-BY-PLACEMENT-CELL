
import java.util.Scanner;

public class SubstringReplacer {

    // Method to find all occurrences of the substring and store the starting positions in an array
    public static int[] findOccurrences(String text, String substring) {
        int[] positions = new int[text.length()];
        int count = 0;
        int index = text.indexOf(substring);

        while (index != -1) {
            positions[count++] = index;
            index = text.indexOf(substring, index + 1);  // Find next occurrence
        }

        // Resize the array to fit the exact number of occurrences
        int[] result = new int[count];
        System.arraycopy(positions, 0, result, 0, count);
        return result;
    }

    // Method to replace the substring manually without using replace()
    public static String replaceSubstring(String text, String substring, String replacement) {
        int[] positions = findOccurrences(text, substring);
        StringBuilder newText = new StringBuilder();

        int lastIndex = 0;

        for (int position : positions) {
            newText.append(text, lastIndex, position);  // Add text before the match
            newText.append(replacement);  // Add replacement
            lastIndex = position + substring.length();  // Move to the next part after the substring
        }

        // Append the remaining part of the original text after the last occurrence
        newText.append(text.substring(lastIndex));

        return newText.toString();
    }

    // Method to compare the result with the built-in replace() method
    public static boolean compareWithBuiltInReplace(String originalText, String substring, String replacement) {
        String manualResult = replaceSubstring(originalText, substring, replacement);
        String builtInResult = originalText.replace(substring, replacement);

        return manualResult.equals(builtInResult);  // Return true if both results match
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input
        System.out.print("Enter the main text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the substring to find: ");
        String substring = scanner.nextLine();

        System.out.print("Enter the replacement substring: ");
        String replacement = scanner.nextLine();

        // Call the method to manually replace the substring
        String replacedText = replaceSubstring(text, substring, replacement);

        // Display the results
        System.out.println("Original Text: " + text);
        System.out.println("Replaced Text (Manual): " + replacedText);

        // Compare with built-in replace() method
        boolean isSame = compareWithBuiltInReplace(text, substring, replacement);
        System.out.println("Does manual replacement match built-in replace()? " + isSame);

        scanner.close();
    }
}

