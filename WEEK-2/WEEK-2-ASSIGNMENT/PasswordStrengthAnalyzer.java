import java.util.*;

public class PasswordStrengthAnalyzer {

    // Method to analyze password stats
    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digit = 0, special = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int ascii = (int) c;

            if (ascii >= 65 && ascii <= 90) upper++; // A-Z
            else if (ascii >= 97 && ascii <= 122) lower++; // a-z
            else if (ascii >= 48 && ascii <= 57) digit++; // 0-9
            else if (ascii >= 33 && ascii <= 126) special++; // printable specials
        }

        return new int[]{upper, lower, digit, special};
    }

    // Method to calculate score
    public static int calculateScore(String password, int upper, int lower, int digit, int special) {
        int score = 0;

        // Length points
        if (password.length() > 8) {
            score += (password.length() - 8) * 2;
        }

        // Variety bonus
        if (upper > 0) score += 10;
        if (lower > 0) score += 10;
        if (digit > 0) score += 10;
        if (special > 0) score += 10;

        // Deduct for common patterns
        String lowerPass = password.toLowerCase();
        String[] weakPatterns = {"123", "abc", "qwerty", "password"};
        for (String p : weakPatterns) {
            if (lowerPass.contains(p)) {
                score -= 15;
            }
        }

        return Math.max(score, 0); // never negative
    }

    // Method to get strength label
    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    // Password generator
    public static String generatePassword(int length) {
        if (length < 6) length = 6; // minimum safe length

        Random rand = new Random();
        String upperSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerSet = "abcdefghijklmnopqrstuvwxyz";
        String digitSet = "0123456789";
        String specialSet = "!@#$%^&*()-_=+[]{};:,.<>?";

        String allChars = upperSet + lowerSet + digitSet + specialSet;

        StringBuilder sb = new StringBuilder();

        // Ensure at least one from each category
        sb.append(upperSet.charAt(rand.nextInt(upperSet.length())));
        sb.append(lowerSet.charAt(rand.nextInt(lowerSet.length())));
        sb.append(digitSet.charAt(rand.nextInt(digitSet.length())));
        sb.append(specialSet.charAt(rand.nextInt(specialSet.length())));

        // Fill remaining
        for (int i = 4; i < length; i++) {
            sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        // Shuffle characters for randomness
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) {
            chars.add(sb.charAt(i));
        }
        Collections.shuffle(chars);

        StringBuilder finalPass = new StringBuilder();
        for (char c : chars) {
            finalPass.append(c);
        }

        return finalPass.toString();
    }

    // Display results in table
    public static void displayResults(String[] passwords) {
        System.out.printf("%-20s %-6s %-6s %-6s %-6s %-8s %-6s %-10s\n",
                "Password", "Len", "Upper", "Lower", "Digit", "Special", "Score", "Strength");
        System.out.println("---------------------------------------------------------------------------------");

        for (String pass : passwords) {
            int[] counts = analyzePassword(pass);
            int score = calculateScore(pass, counts[0], counts[1], counts[2], counts[3]);
            String strength = getStrength(score);

            System.out.printf("%-20s %-6d %-6d %-6d %-6d %-8d %-6d %-10s\n",
                    pass, pass.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
        }
    }

    // Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many passwords do you want to analyze?");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] passwords = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            passwords[i] = sc.nextLine();
        }

        // Show analysis
        System.out.println("\nPassword Strength Analysis:");
        displayResults(passwords);

        // Generate new strong password
        System.out.print("\nEnter desired length for generated password: ");
        int length = sc.nextInt();
        String newPass = generatePassword(length);
        System.out.println("Generated Strong Password: " + newPass);

        sc.close();
    }
}
