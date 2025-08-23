
import java.util.Scanner;

public class RockPaperScissors {

    // Method to get computer choice randomly
    public static String getComputerChoice() {
        int choice = (int) (Math.random() * 3); // 0, 1, or 2
        switch (choice) {
            case 0:
                return "Rock";
            case 1:
                return "Paper";
            default:
                return "Scissors";
        }
    }

    // Method to determine winner of a single game
    public static String findWinner(String user, String computer) {
        if (user.equalsIgnoreCase(computer)) {
            return "Draw";
        } else if ((user.equalsIgnoreCase("Rock") && computer.equals("Scissors")) ||
                   (user.equalsIgnoreCase("Paper") && computer.equals("Rock")) ||
                   (user.equalsIgnoreCase("Scissors") && computer.equals("Paper"))) {
            return "User";
        } else {
            return "Computer";
        }
    }

    // Method to calculate stats and percentages
    public static String[][] calculateStats(String[] results) {
        int userWins = 0;
        int computerWins = 0;
        int draws = 0;
        int totalGames = results.length;

        for (String res : results) {
            if (res.equals("User")) userWins++;
            else if (res.equals("Computer")) computerWins++;
            else draws++;
        }

        double userPercent = (userWins * 100.0) / totalGames;
        double computerPercent = (computerWins * 100.0) / totalGames;

        String[][] stats = new String[4][2];
        stats[0][0] = "User Wins";      stats[0][1] = Integer.toString(userWins);
        stats[1][0] = "Computer Wins";  stats[1][1] = Integer.toString(computerWins);
        stats[2][0] = "Draws";          stats[2][1] = Integer.toString(draws);
        stats[3][0] = "Winning %";      stats[3][1] = "User: " + String.format("%.2f", userPercent) + "%, Computer: " + String.format("%.2f", computerPercent) + "%";

        return stats;
    }

    // Method to display game results and stats
    public static void displayResults(String[] userChoices, String[] computerChoices, String[] winners, String[][] stats) {
        System.out.println("Game\tUser\tComputer\tWinner");
        System.out.println("--------------------------------------");
        for (int i = 0; i < winners.length; i++) {
            System.out.println((i+1) + "\t" + userChoices[i] + "\t" + computerChoices[i] + "\t\t" + winners[i]);
        }

        System.out.println("\nStatistics:");
        for (String[] stat : stats) {
            System.out.println(stat[0] + ": " + stat[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of games: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        String[] userChoices = new String[n];
        String[] computerChoices = new String[n];
        String[] winners = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter your choice for game " + (i + 1) + " (Rock/Paper/Scissors): ");
            String userChoice = sc.nextLine();
            String computerChoice = getComputerChoice();

            String winner = findWinner(userChoice, computerChoice);

            userChoices[i] = userChoice;
            computerChoices[i] = computerChoice;
            winners[i] = winner;
        }

        String[][] stats = calculateStats(winners);
        displayResults(userChoices, computerChoices, winners, stats);

        sc.close();
    }
}


