import java.util.Scanner;

public class VowelConsonantCount {

    // Method to check if the character is a vowel or consonant
    public static String checkCharacter(char c) {
        // Convert to lowercase if it's an uppercase letter
        char lowerChar = Character.toLowerCase(c);
        
        // Check if the character is a vowel
        if (lowerChar == 'a' || lowerChar == 'e' || lowerChar == 'i' || lowerChar == 'o' || lowerChar == 'u') {
            return "Vowel";
        }
        // Check if it's a consonant (alphabetic character)
        else if (lowerChar >= 'a' && lowerChar <= 'z') {
            return "Consonant";
        }
        // If it's not a letter
        else {
            return "Not a Letter";
        }
    }

    // Method to find vowels and consonants in a string and return their counts in an array
    public static int[] countVowelsAndConsonants(String text) {
        int vowelsCount = 0;
        int consonantsCount = 0;

        // Loop through each character in the string using charAt()
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            String result = checkCharacter(currentChar);
            
            // Count vowels and consonants
            if (result.equals("Vowel")) {
                vowelsCount++;
            } else if (result.equals("Consonant")) {
                consonantsCount++;
            }
        }

        // Return the counts in an array: [vowelsCount, consonantsCount]
        return new int[] {vowelsCount, consonantsCount};
    }

    public static void main(String[] args) {
        // Take user input using Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String text = scanner.nextLine();
        
        // Call the method to count vowels and consonants
        int[] counts = countVowelsAndConsonants(text);
        
        // Display the result
        System.out.println("Vowels count: " + counts[0]);
        System.out.println("Consonants count: " + counts[1]);
    }
}
