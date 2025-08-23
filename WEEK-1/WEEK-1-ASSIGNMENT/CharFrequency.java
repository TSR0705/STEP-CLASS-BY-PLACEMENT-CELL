import java.util.Scanner;

public class CharFrequency {
    public static String[][] getCharFrequency(String text) {
        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        String[][] result = new String[text.length()][2];
        int idx = 0;
        boolean[] added = new boolean[256];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!added[ch]) {
                result[idx][0] = String.valueOf(ch);
                result[idx][1] = String.valueOf(freq[ch]);
                added[ch] = true;
                idx++;
            }
        }

        String[][] finalResult = new String[idx][2];
        System.arraycopy(result, 0, finalResult, 0, idx);
        return finalResult;
    }

    public static void display(String[][] data) {
        System.out.printf("%-10s %-10s%n", "Character", "Frequency");
        for (String[] row : data) {
            System.out.printf("%-10s %-10s%n", row[0], row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        display(getCharFrequency(text));
        sc.close();
    }
}
