import java.util.Scanner;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for input string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Process each character
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;

            // 1. Display char and ASCII code
            System.out.println("\nCharacter: " + ch + " | ASCII: " + ascii);

            // 2. Classify character
            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);

            // 3. If letter, show both upper and lower case versions with ASCII codes
            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.println("Uppercase: " + upper + " (ASCII: " + (int) upper + ")");
                System.out.println("Lowercase: " + lower + " (ASCII: " + (int) lower + ")");
            }

            // 4. Difference between upper & lower case ASCII values (if applicable)
            if (Character.isLetter(ch)) {
                int diff = Math.abs((int) Character.toUpperCase(ch) - (int) Character.toLowerCase(ch));
                System.out.println("ASCII difference (Upper - Lower): " + diff);
            }
        }

        // ASCII table example
        System.out.println("\nASCII Table (65 to 70):");
        displayASCIITable(65, 70);

        // Caesar Cipher example
        System.out.print("\nEnter shift value for Caesar Cipher: ");
        int shift = scanner.nextInt();
        String encrypted = caesarCipher(input, shift);
        System.out.println("Encrypted text: " + encrypted);

        // ASCII conversion examples
        int[] asciiArray = stringToASCII(input);
        System.out.print("\nString to ASCII: ");
        for (int val : asciiArray) {
            System.out.print(val + " ");
        }

        String fromAscii = asciiToString(asciiArray);
        System.out.println("\nASCII to String: " + fromAscii);

        scanner.close();
    }

    // Classify character
    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        else if (Character.isLowerCase(ch)) return "Lowercase Letter";
        else if (Character.isDigit(ch)) return "Digit";
        else return "Special Character";
    }

    // Toggle case using ASCII manipulation
    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) {
            return (char) (ch + 32); // Upper to lower
        } else if (Character.isLowerCase(ch)) {
            return (char) (ch - 32); // Lower to upper
        }
        return ch;
    }

    // Caesar Cipher
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            result.append(ch);
        }
        return result.toString();
    }

    // Display ASCII table for a range
    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }

    // Convert String to ASCII array
    public static int[] stringToASCII(String text) {
        int[] asciiValues = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiValues[i] = (int) text.charAt(i);
        }
        return asciiValues;
    }

    // Convert ASCII array back to String
    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}
