
import java.util.Scanner;

public class CaesarCipher {

    // Method to encrypt the text using ASCII character shifting
    public static String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int ascii = (int) ch;
            
            if (ch >= 'A' && ch <= 'Z') { // Uppercase letters
                ascii = ((ascii - 'A' + shift) % 26 + 26) % 26 + 'A';  // Wrap around for uppercase
            } else if (ch >= 'a' && ch <= 'z') { // Lowercase letters
                ascii = ((ascii - 'a' + shift) % 26 + 26) % 26 + 'a';  // Wrap around for lowercase
            }
            // Non-alphabetic characters are not changed
            encryptedText.append((char) ascii);
        }
        
        return encryptedText.toString();
    }

    // Method to decrypt the text by reversing the shift
    public static String decrypt(String encryptedText, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        
        for (int i = 0; i < encryptedText.length(); i++) {
            char ch = encryptedText.charAt(i);
            int ascii = (int) ch;
            
            if (ch >= 'A' && ch <= 'Z') { // Uppercase letters
                ascii = ((ascii - 'A' - shift) % 26 + 26) % 26 + 'A';  // Reverse the shift for uppercase
            } else if (ch >= 'a' && ch <= 'z') { // Lowercase letters
                ascii = ((ascii - 'a' - shift) % 26 + 26) % 26 + 'a';  // Reverse the shift for lowercase
            }
            // Non-alphabetic characters are not changed
            decryptedText.append((char) ascii);
        }
        
        return decryptedText.toString();
    }

    // Method to display ASCII values before and after encryption
    public static void displayASCIIValues(String text, String encryptedText) {
        System.out.println("Original text with ASCII values:");
        for (int i = 0; i < text.length(); i++) {
            System.out.println(text.charAt(i) + " = " + (int) text.charAt(i));
        }

        System.out.println("\nEncrypted text with ASCII values:");
        for (int i = 0; i < encryptedText.length(); i++) {
            System.out.println(encryptedText.charAt(i) + " = " + (int) encryptedText.charAt(i));
        }
    }

    // Method to validate that decryption returns the original text
    public static boolean validateDecryption(String originalText, String decryptedText) {
        return originalText.equals(decryptedText);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input for the text to encrypt and the shift value
        System.out.print("Enter the text to encrypt: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter the shift value (integer): ");
        int shift = scanner.nextInt();
        
        // Encrypt the text
        String encryptedText = encrypt(text, shift);
        
        // Decrypt the text
        String decryptedText = decrypt(encryptedText, shift);

        // Display results
        System.out.println("\n-------------------------------------------");
        System.out.println("Original Text: " + text);
        displayASCIIValues(text, encryptedText);  // Display ASCII values before and after encryption
        System.out.println("\nEncrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        // Validate decryption
        if (validateDecryption(text, decryptedText)) {
            System.out.println("\nDecryption is successful: The decrypted text matches the original.");
        } else {
            System.out.println("\nDecryption failed: The decrypted text does not match the original.");
        }

        scanner.close();
    }
}
