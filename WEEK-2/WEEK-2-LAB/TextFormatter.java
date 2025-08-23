import java.util.*;

public class TextFormatter {

     
    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;

        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ' ') {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        return words;
    }

    
    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;

        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;

            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();

            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) line.append(" ");
                }
                while (line.length() < width) line.append(" ");
            } else {
                int totalSpaces = width - lineLen + gaps;
                int spacePerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap + (extraSpaces-- > 0 ? 1 : 0); s++) {
                            line.append(" ");
                        }
                    }
                }
            }

            lines.add(line.toString());
            i = j;
        }

        return lines;
    }

    
    public static List<String> centerAlign(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();

        for (String line : lines) {
            int padding = (width - line.trim().length()) / 2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < padding; i++) sb.append(" ");
            sb.append(line.trim());
            centered.add(sb.toString());
        }

        return centered;
    }
 
    public static long formatWithConcatenation(List<String> words, int width) {
        long start = System.nanoTime();
        String result = "";
        int i = 0;

        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;

            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }

            int gaps = j - i - 1;
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    result += words.get(k);
                    if (k < j - 1) result += " ";
                }
                while (result.length() % width != 0) result += " ";
            } else {
                int totalSpaces = width - lineLen + gaps;
                int spacePerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    result += words.get(k);
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap + (extraSpaces-- > 0 ? 1 : 0); s++) {
                            result += " ";
                        }
                    }
                }
            }

            result += "\n";
            i = j;
        }

        return System.nanoTime() - start;
    }

   
    public static void displayFormatted(List<String> lines, String title) {
        System.out.println("\n" + title);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            System.out.printf("Line %2d (%2d chars): %s\n", i + 1, line.length(), line);
        }
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text to format:");
        String text = scanner.nextLine();

        System.out.print("Enter desired line width: ");
        int width = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<String> words = splitWords(text);

        long startSB = System.nanoTime();
        List<String> justified = justifyText(words, width);
        long endSB = System.nanoTime();

        List<String> centered = centerAlign(justified, width);
        long concatTime = formatWithConcatenation(words, width);
        long sbTime = endSB - startSB;

        System.out.println("\nOriginal Text:\n" + text);
        displayFormatted(justified, " Left-Justified Text:");
        displayFormatted(centered, " Center-Aligned Text:");

        System.out.println("\n Performance Comparison:");
        System.out.printf("StringBuilder Time   : %d ns\n", sbTime);
        System.out.printf("String Concatenation : %d ns\n", concatTime);
    }
}

