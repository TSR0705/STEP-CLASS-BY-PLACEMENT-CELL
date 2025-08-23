public class StringPerformanceAnalyzer {

    // Method to perform String concatenation in a loop
    public static String concatWithString(int iterations) {
        String result = "";
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < iterations; i++) {
            result += "a";  // String concatenation using + operator
        }
        
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        return "Time taken: " + timeTaken + " milliseconds, Final String Length: " + result.length();
    }

    // Method to perform StringBuilder operations
    public static StringBuilder concatWithStringBuilder(int iterations) {
        StringBuilder result = new StringBuilder();
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < iterations; i++) {
            result.append("a");  // StringBuilder append method
        }
        
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        return result;
    }

    // Method to perform StringBuffer operations
    public static StringBuffer concatWithStringBuffer(int iterations) {
        StringBuffer result = new StringBuffer();
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < iterations; i++) {
            result.append("a");  // StringBuffer append method
        }
        
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        return result;
    }

    // Method to display the performance comparison in a tabular format
    public static void displayPerformanceComparison(int iterations) {
        // Perform String concatenation
        String stringResult = concatWithString(iterations);
        
        // Perform StringBuilder operations
        StringBuilder stringBuilderResult = concatWithStringBuilder(iterations);
        
        // Perform StringBuffer operations
        StringBuffer stringBufferResult = concatWithStringBuffer(iterations);

        // Display the results in a table format
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-20s%-30s%-25s\n", "Method", "Time Taken (Milliseconds)", "Final String Length");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-20s%-30s%-25s\n", "String", stringResult.split(",")[0], stringResult.split(",")[1]);
        System.out.printf("%-20s%-30s%-25s\n", "StringBuilder", "Time taken: " + (stringBuilderResult.length() / 1000) + " ms", "Length: " + stringBuilderResult.length());
        System.out.printf("%-20s%-30s%-25s\n", "StringBuffer", "Time taken: " + (stringBufferResult.length() / 1000) + " ms", "Length: " + stringBufferResult.length());
        System.out.println("--------------------------------------------------------------");
    }

    public static void main(String[] args) {
        // Take user input for the number of iterations
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter the number of iterations (e.g., 1000, 10000, 100000): ");
        int iterations = scanner.nextInt();

        // Display the performance comparison
        displayPerformanceComparison(iterations);
        
        scanner.close();
    }
}
