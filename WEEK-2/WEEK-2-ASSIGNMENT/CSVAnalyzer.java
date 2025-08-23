import java.util.*;

public class CSVAnalyzer {

    // Step b: Parse CSV-like data without split()
    public static String[][] parseCSV(String input) {
        List<List<String>> rows = new ArrayList<>();
        List<String> currentRow = new ArrayList<>();

        int start = 0;
        boolean inQuotes = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes; // toggle quotes
            } else if (c == ',' && !inQuotes) {
                currentRow.add(input.substring(start, i));
                start = i + 1;
            } else if (c == '\n' && !inQuotes) {
                currentRow.add(input.substring(start, i));
                rows.add(new ArrayList<>(currentRow));
                currentRow.clear();
                start = i + 1;
            }
        }

        // Last field
        currentRow.add(input.substring(start));
        rows.add(currentRow);

        // Convert to 2D array
        String[][] data = new String[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i).toArray(new String[0]);
        }
        return data;
    }

    // Step c: Validate & clean data
    public static String[][] cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = data[i][j].trim();

                // Remove quotes if present
                if (data[i][j].startsWith("\"") && data[i][j].endsWith("\"")) {
                    data[i][j] = data[i][j].substring(1, data[i][j].length() - 1);
                }
            }
        }
        return data;
    }

    // Helper: Check if numeric
    public static boolean isNumeric(String s) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= '0' && c <= '9') && c != '.') return false;
        }
        return true;
    }

    // Step d: Data analysis
    public static void analyzeData(String[][] data) {
        int cols = data[0].length;

        System.out.println("\n--- Column Analysis ---");

        for (int j = 0; j < cols; j++) {
            boolean numeric = true;
            double min = Double.MAX_VALUE, max = -Double.MAX_VALUE, sum = 0;
            int count = 0;
            Set<String> unique = new HashSet<>();
            int missing = 0;

            for (int i = 1; i < data.length; i++) {
                String value = data[i][j];
                if (value.isEmpty()) {
                    missing++;
                    continue;
                }

                if (isNumeric(value)) {
                    double num = Double.parseDouble(value);
                    min = Math.min(min, num);
                    max = Math.max(max, num);
                    sum += num;
                    count++;
                } else {
                    numeric = false;
                    unique.add(value);
                }
            }

            System.out.println("Column: " + data[0][j]);
            if (numeric && count > 0) {
                System.out.printf("   Min: %.2f, Max: %.2f, Avg: %.2f%n", min, max, (sum / count));
            } else {
                System.out.println("   Unique values: " + unique);
            }
            System.out.println("   Missing values: " + missing);
        }
    }

    // Step e: Format output
    public static void formatTable(String[][] data) {
        int cols = data[0].length;
        int[] colWidths = new int[cols];

        // Calculate max width per column
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < data.length; i++) {
                colWidths[j] = Math.max(colWidths[j], data[i][j].length());
            }
        }

        // Print table with borders
        System.out.println("\n--- Formatted Table ---");
        for (int i = 0; i < data.length; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.printf(" %-"+colWidths[j]+"s |", data[i][j]);
            }
            System.out.println();
        }
    }

    // Step f: Data summary report
    public static void summaryReport(String[][] data) {
        int total = data.length - 1; // excluding header
        int fields = total * data[0].length;
        int missing = 0;

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].isEmpty()) missing++;
            }
        }

        double completeness = 100.0 * (fields - missing) / fields;

        System.out.println("\n--- Data Summary Report ---");
        System.out.println("Total Records: " + total);
        System.out.println("Missing Fields: " + missing);
        System.out.printf("Data Completeness: %.2f%%%n", completeness);
    }

    // Step g: Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end with an empty line):");

        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            input.append(line).append("\n");
        }

        // Process CSV
        String[][] rawData = parseCSV(input.toString());
        String[][] cleaned = cleanData(rawData);

        formatTable(cleaned);
        analyzeData(cleaned);
        summaryReport(cleaned);
    }
}
