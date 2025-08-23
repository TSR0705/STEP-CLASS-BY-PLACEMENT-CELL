import java.util.Scanner;

public class CharFrequencyCharAt {

    // Method to find frequency of characters using charAt() and ASCII array
    public static String[][] characterFrequency(String text) {
        int[] freq = new int[256]; // ASCII array to store frequency

        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        // Count unique characters to size the result array
        int uniqueCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean alreadyCounted = false;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == c) {
                    alreadyCounted = true;
                    break;
                }
            }
            if (!alreadyCounted) uniqueCount++;
        }

        // Create 2D array to store character and frequency
        String[][] result = new String[uniqueCount][2];
        int index = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean alreadyCounted = false;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == c) {
                    alreadyCounted = true;
                    break;
                }
            }
            if (!alreadyCounted) {
                result[index][0] = Character.toString(c);
                result[index][1] = Integer.toString(freq[c]);
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Call method to get frequency
        String[][] freqResult = characterFrequency(input);

        // Display results
        System.out.println("\nCharacter Frequencies:");
        for (int i = 0; i < freqResult.length; i++) {
            System.out.println(freqResult[i][0] + " : " + freqResult[i][1]);
        }

        scanner.close();
    }
}
