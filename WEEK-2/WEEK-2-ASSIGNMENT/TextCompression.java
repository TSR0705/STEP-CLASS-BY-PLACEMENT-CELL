import java.util.*;

public class TextCompression {

    // Step b: Count character frequency without HashMap
    public static Object[] countFrequency(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // check if already counted
            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (chars[j] == c) {
                    index = j;
                    break;
                }
            }

            if (index == -1) { // new char
                chars[uniqueCount] = c;
                freq[uniqueCount] = 1;
                uniqueCount++;
            } else { // increase freq
                freq[index]++;
            }
        }

        // trim arrays
        char[] finalChars = new char[uniqueCount];
        int[] finalFreq = new int[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            finalChars[i] = chars[i];
            finalFreq[i] = freq[i];
        }

        return new Object[]{finalChars, finalFreq};
    }

    // Step c: Create compression codes (shorter for frequent chars)
    public static String[][] generateCodes(char[] chars, int[] freq) {
        int n = chars.length;
        String[][] mapping = new String[n][2];

        // Sort characters by frequency (descending)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (freq[j] > freq[i]) {
                    // swap freq
                    int tmpF = freq[i];
                    freq[i] = freq[j];
                    freq[j] = tmpF;

                    // swap char
                    char tmpC = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tmpC;
                }
            }
        }

        // Assign shorter codes to frequent chars
        String[] codePool = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                             "@", "#", "$", "%", "&", "*", "+", "-", "=", "!"};

        for (int i = 0; i < n; i++) {
            mapping[i][0] = String.valueOf(chars[i]); // original char
            if (i < codePool.length) {
                mapping[i][1] = codePool[i]; // short code
            } else {
                mapping[i][1] = "X" + i; // fallback
            }
        }

        return mapping;
    }

    // Step d: Compress text
    public static String compress(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (String[] map : mapping) {
                if (map[0].charAt(0) == c) {
                    sb.append(map[1]);
                    break;
                }
            }
        }

        return sb.toString();
    }

    // Step e: Decompress text
    public static String decompress(String compressed, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (i < compressed.length()) {
            boolean found = false;
            for (String[] map : mapping) {
                String code = map[1];
                if (compressed.startsWith(code, i)) {
                    sb.append(map[0]);
                    i += code.length();
                    found = true;
                    break;
                }
            }
            if (!found) { // error case
                sb.append("?");
                i++;
            }
        }
        return sb.toString();
    }

    // Step f: Display analysis
    public static void displayAnalysis(String text, String compressed, String decompressed,
                                       char[] chars, int[] freq, String[][] mapping) {

        System.out.println("\nCharacter Frequency Table:");
        System.out.printf("%-10s %-10s\n", "Char", "Frequency");
        for (int i = 0; i < chars.length; i++) {
            System.out.printf("%-10s %-10d\n", chars[i], freq[i]);
        }

        System.out.println("\nCompression Mapping:");
        System.out.printf("%-10s %-10s\n", "Char", "Code");
        for (String[] map : mapping) {
            System.out.printf("%-10s %-10s\n", map[0], map[1]);
        }

        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);

        // compression efficiency
        int originalSize = text.length();
        int compressedSize = compressed.length();
        double ratio = (double) compressedSize / originalSize;
        double efficiency = (1 - ratio) * 100;

        System.out.printf("Original Size: %d, Compressed Size: %d\n", originalSize, compressedSize);
        System.out.printf("Compression Efficiency: %.2f%%\n", efficiency);
    }

    // Step g: Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text to compress:");
        String text = sc.nextLine();

        // Count frequency
        Object[] result = countFrequency(text);
        char[] chars = (char[]) result[0];
        int[] freq = (int[]) result[1];

        // Generate mapping codes
        String[][] mapping = generateCodes(chars, freq);

        // Compress
        String compressed = compress(text, mapping);

        // Decompress
        String decompressed = decompress(compressed, mapping);

        // Display analysis
        displayAnalysis(text, compressed, decompressed, chars, freq, mapping);

        sc.close();
    }
}
