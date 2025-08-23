public class  StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        // 1. Original length (including spaces)
        System.out.println("Original String: \"" + sampleText + "\"");
        System.out.println("Length (with spaces): " + sampleText.length());

        // 2. Remove leading and trailing spaces
        String trimmedText = sampleText.trim();
        System.out.println("Trimmed String: \"" + trimmedText + "\"");
        System.out.println("Length (without leading/trailing spaces): " + trimmedText.length());

        // 3. Character at index 5
        System.out.println("Character at index 5: " + sampleText.charAt(5));

        // 4. Extract substring "Programming"
        String sub = trimmedText.substring(5, 16); // from index 5 to 15
        System.out.println("Substring: " + sub);

        // 5. Index of word "Fun"
        System.out.println("Index of 'Fun': " + trimmedText.indexOf("Fun"));

        // 6. Check if string contains "Java"
        System.out.println("Contains 'Java'? " + trimmedText.contains("Java"));

        // 7. Check if string starts with "Java"
        System.out.println("Starts with 'Java'? " + trimmedText.startsWith("Java"));

        // 8. Check if string ends with '!'
        System.out.println("Ends with '!'? " + trimmedText.endsWith("!"));

        // 9. Convert to uppercase
        System.out.println("Uppercase: " + trimmedText.toUpperCase());

        // 10. Convert to lowercase
        System.out.println("Lowercase: " + trimmedText.toLowerCase());

        // Count vowels
        int vowelCount = countVowels(trimmedText);
        System.out.println("Number of vowels: " + vowelCount);

        // Find all occurrences of a character
        System.out.println("Occurrences of 'a':");
        findAllOccurrences(trimmedText, 'a');
    }

    // Method to count vowels using charAt()
    public static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase(); // make it easier to check
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    // Method to find all positions of a character
    public static void findAllOccurrences(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.println("Found at index: " + i);
            }
        }
    }
}
