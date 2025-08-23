import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ask user for their full name
        System.out.print("Enter your full name (first and last): ");
        String fullName = scanner.nextLine().trim();

        // 2. Ask user for their favorite programming language
        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine().trim();

        // 3. Ask user for a sentence about their programming experience
        System.out.print("Describe your programming experience in a sentence: ");
        String experience = scanner.nextLine().trim();

        // --- Processing the input ---

        // 1. Extract first and last name
        String firstName = "";
        String lastName = "";

        // Split the full name by space
        String[] nameParts = fullName.split(" ");
        if (nameParts.length >= 2) {
            firstName = nameParts[0];
            lastName = nameParts[nameParts.length - 1]; // Handles middle names too
        } else if (nameParts.length == 1) {
            firstName = nameParts[0];
            lastName = "(Not provided)";
        }

        // 2. Count total characters in the sentence (excluding spaces)
        int charCount = experience.replace(" ", "").length();

        // 3. Convert programming language to uppercase
        String languageUpper = language.toUpperCase();

        // 4. Display formatted summary
        System.out.println("\n--- Summary ---");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Programming Language (Uppercase): " + languageUpper);
        System.out.println("Programming Experience: " + experience);
        System.out.println("Total Characters in Experience (excluding spaces): " + charCount);

        scanner.close();
    }
}
