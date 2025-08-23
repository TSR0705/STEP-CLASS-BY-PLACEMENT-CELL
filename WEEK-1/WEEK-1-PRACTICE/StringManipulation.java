public class StringManipulation {
    public static void main(String[] args) {
        // 1. Create the string "Java Programming" using different methods

        // Method 1: String literal
        String str1 = "Java Programming";

        // Method 2: Using new String() constructor
        String str2 = new String("Java Programming");

        // Method 3: Using a character array
        char[] charArray = {'J','a','v','a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str3 = new String(charArray);

        // Print all strings
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("String 3: " + str3);

        // Compare strings using == (reference comparison)
        System.out.println("\nComparisons using ==:");
        System.out.println("str1 == str2: " + (str1 == str2)); // false, different objects
        System.out.println("str1 == str3: " + (str1 == str3)); // false, different objects
        System.out.println("str2 == str3: " + (str2 == str3)); // false, different objects

        // Compare strings using .equals() (value comparison)
        System.out.println("\nComparisons using .equals():");
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true, same content
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true, same content
        System.out.println("str2.equals(str3): " + str2.equals(str3)); // true, same content

        // Explanation:
        System.out.println("\nExplanation:");
        System.out.println("== checks if two references point to the same object in memory.");
        System.out.println(".equals() checks if the contents of the strings are the same.");

        // TODO: Create a string with escape sequences
        String quote = "Programming Quote:\n\"Code is poetry\" - Unknown\nPath: C:\\Java\\Projects";
        System.out.println("\n" + quote);
    }
}
