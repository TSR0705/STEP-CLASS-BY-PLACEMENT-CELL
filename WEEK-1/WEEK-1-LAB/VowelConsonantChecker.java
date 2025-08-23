import java.util.Scanner;

public class VowelConsonantChecker {

    // Method to check if a character is vowel, consonant, or not a letter
    public static String checkCharType(char ch) {
        // Convert uppercase to lowercase
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32);
        }

        // Check for vowel
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return "Vowel";
        } 
        // Check for consonant
        else if (ch >= 'a' && ch <= 'z') {
            return "Consonant";
        } 
        // Not a letter
        else {
            return "Not a Letter";
        }
    }

    // Method to find vowels and consonants in a string and store in 2D array
    public static String[][] findCharTypes(String str) {
        String[][] result = new String[str.length()][2];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            result[i][0] = Character.toString(ch);
            result[i][1] = checkCharType(ch);
        }
        return result;
    }

    // Method to display 2D array in tabular format
    public static void displayTable(String[][] arr) {
        System.out.println("Character\tType");
        System.out.println("------------------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + "\t\t" + arr[i][1]);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] charTypes = findCharTypes(input);
        displayTable(charTypes);

        sc.close();
    }
}









