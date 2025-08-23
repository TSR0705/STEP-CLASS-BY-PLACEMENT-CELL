import java.util.Scanner;

public class StudentMarks {

    // Method to take scores input from the user
    public static int[][] inputScores(int n) {
        Scanner sc = new Scanner(System.in);
        int[][] scores = new int[n][3]; // 3 subjects

        for (int i = 0; i < n; i++) {
            System.out.println("Enter marks for Student " + (i + 1) + ":");
            System.out.print("Physics: ");
            scores[i][0] = sc.nextInt();
            System.out.print("Chemistry: ");
            scores[i][1] = sc.nextInt();
            System.out.print("Maths: ");
            scores[i][2] = sc.nextInt();
        }
        return scores;
    }

    // Method to calculate total, average, and percentage
    public static double[][] calculatePercentage(int[][] scores) {
        int n = scores.length;
        double[][] result = new double[n][3]; // total, average, percentage
        for (int i = 0; i < n; i++) {
            int total = scores[i][0] + scores[i][1] + scores[i][2];
            double average = total / 3.0;
            double percentage = (total / 300.0) * 100;
            result[i][0] = total;
            result[i][1] = Math.round(average * 100.0) / 100.0;      // round to 2 digits
            result[i][2] = Math.round(percentage * 100.0) / 100.0;   // round to 2 digits
        }
        return result;
    }

    // Method to calculate grade based on percentage
    public static String[] calculateGrade(double[][] percentages) {
        String[] grades = new String[percentages.length];
        for (int i = 0; i < percentages.length; i++) {
            double pct = percentages[i][2];
            if (pct >= 80) grades[i] = "A";
            else if (pct >= 70) grades[i] = "B";
            else if (pct >= 60) grades[i] = "C";
            else if (pct >= 50) grades[i] = "D";
            else if (pct >= 40) grades[i] = "E";
            else grades[i] = "R";
        }
        return grades;
    }

    // Method to display the scorecard in tabular format
    public static void displayScorecard(int[][] scores, double[][] percentages, String[] grades) {
        System.out.println("Student\tPhysics\tChemistry\tMaths\tTotal\tAverage\tPercentage\tGrade");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < scores.length; i++) {
            System.out.println((i + 1) + "\t" + scores[i][0] + "\t" + scores[i][1] + "\t\t" + scores[i][2]
                    + "\t" + (int)percentages[i][0] + "\t" + percentages[i][1] + "\t" + percentages[i][2]
                    + "\t\t" + grades[i]);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[][] scores = inputScores(n);
        double[][] percentages = calculatePercentage(scores);
        String[] grades = calculateGrade(percentages);
        displayScorecard(scores, percentages, grades);
    }
}
