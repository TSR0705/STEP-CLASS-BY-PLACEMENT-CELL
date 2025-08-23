import java.util.Scanner;

public class CaseConverter {

    // Method to convert a character to uppercase using ASCII values
    public static char toUpperCase(char ch) {
        // Check if the character is a lowercase letter (ASCII 97-122)
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32);  // Convert by subtracting 32 from the ASCII value
        }
        return ch;  // Return unchanged if it's not a lowercase letter
    }

    // Method to convert a character to lowercase using ASCII values
    public static char toLowerCase(char ch) {
        // Check if the character is an uppercase letter (ASCII 65-90)
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);  // Convert by adding 32 to the ASCII value
        }
        return ch;  // Return unchanged if it's not an uppercase letter
    }

    // Method to convert text to title case
    public static String toTitleCase(String text) {
        StringBuilder titleCase = new StringBuilder();
        boolean newWord = true;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            
            if (Character.isWhitespace(ch)) {
                newWord = true;
            } else {
                if (newWord) {
                    ch = toUpperCase(ch);  // Capitalize first character of each word
                    newWord = false;
                } else {
                    ch = toLowerCase(ch);  // Convert other characters to lowercase
                }
            }
            titleCase.append(ch);
        }

        return titleCase.toString();
    }

    // Method to compare results with built-in methods
    public static boolean compareWithBuiltInMethods(String text) {
        String manualUpper = "";
        String manualLower = "";
        String manualTitleCase = toTitleCase(text);

        // Convert each character to uppercase or lowercase using ASCII values
        for (int i = 0; i < text.length(); i++) {
            manualUpper += toUpperCase(text.charAt(i));
            manualLower += toLowerCase(text.charAt(i));
        }

        // Use the built-in methods
        String builtInUpper = text.toUpperCase();
        String builtInLower = text.toLowerCase();
        
        return manualUpper.equals(builtInUpper) && manualLower.equals(builtInLower) && manualTitleCase.equals(builtInTitleCase(text));
    }

    // Method to convert text to title case using built-in method
    public static String builtInTitleCase(String text) {
        String[] words = text.split(" ");
        StringBuilder titleCase = new StringBuilder();
        
        for (String word : words) {
            titleCase.append(word.substring(0, 1).toUpperCase());
            titleCase.append(word.substring(1).toLowerCase());
            titleCase.append(" ");
        }
        
        return titleCase.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        // Convert to uppercase, lowercase, and title case using ASCII values
        StringBuilder upperCaseText = new StringBuilder();
        StringBuilder lowerCaseText = new StringBuilder();
        StringBuilder titleCaseText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            upperCaseText.append(toUpperCase(text.charAt(i)));
            lowerCaseText.append(toLowerCase(text.charAt(i)));
        }
        titleCaseText.append(toTitleCase(text));

        // Display the results in a tabular format
        System.out.println("\n------------------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s%-20s\n", "Original Text", "Uppercase (Manual)", "Lowercase (Manual)", "Title Case (Manual)");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s%-20s\n", text, upperCaseText.toString(), lowerCaseText.toString(), titleCaseText.toString());
        System.out.println("------------------------------------------------------------");

        // Compare with built-in methods
        boolean isSame = compareWithBuiltInMethods(text);
        System.out.println("Does manual conversion match built-in methods? " + isSame);

        scanner.close();
    }
}
