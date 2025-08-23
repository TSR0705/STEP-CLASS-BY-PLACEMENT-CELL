import java.util.*;

public class EmailAnalyzer {

    static class EmailInfo {
        String email;
        String username;
        String domain;
        String domainName;
        String extension;
        boolean isValid;

        EmailInfo(String email, boolean isValid, String username, String domain, String domainName, String extension) {
            this.email = email;
            this.isValid = isValid;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
        }
    }
 
    public static boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');

        if (atIndex == -1 || atIndex != lastAtIndex) return false;

        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1) return false;

        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);

        return !username.isEmpty() && !domain.isEmpty();
    }
 
    public static EmailInfo extractEmailInfo(String email) {
        boolean valid = isValidEmail(email);
        String username = "", domain = "", domainName = "", extension = "";

        if (valid) {
            int atIndex = email.indexOf('@');
            username = email.substring(0, atIndex);
            domain = email.substring(atIndex + 1);

            int dotIndex = domain.lastIndexOf('.');
            if (dotIndex != -1) {
                domainName = domain.substring(0, dotIndex);
                extension = domain.substring(dotIndex + 1);
            }
        }

        return new EmailInfo(email, valid, username, domain, domainName, extension);
    }

 
    public static void analyzeStatistics(List<EmailInfo> emails) {
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainFrequency = new HashMap<>();

        for (EmailInfo info : emails) {
            if (info.isValid) {
                validCount++;
                totalUsernameLength += info.username.length();
                domainFrequency.put(info.domain, domainFrequency.getOrDefault(info.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }

        String mostCommonDomain = domainFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("N/A");

        double avgUsernameLength = validCount > 0 ? (double) totalUsernameLength / validCount : 0;

        System.out.println("\n Email Statistics:");
        System.out.println("Total Valid Emails   : " + validCount);
        System.out.println("Total Invalid Emails : " + invalidCount);
        System.out.println("Most Common Domain   : " + mostCommonDomain);
        System.out.printf("Average Username Length: %.2f\n", avgUsernameLength);
    }
 
    public static void displayResults(List<EmailInfo> emails) {
        System.out.printf("\n%-30s %-15s %-25s %-20s %-10s %-10s\n",
                "Email", "Username", "Domain", "Domain Name", "Extension", "Valid");
        System.out.println("-----------------------------------------------------------------------------------------------");

        for (EmailInfo info : emails) {
            System.out.printf("%-30s %-15s %-25s %-20s %-10s %-10s\n",
                    info.email, info.username, info.domain, info.domainName, info.extension,
                    info.isValid ? "Yes" : "No");
        }
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<EmailInfo> emailList = new ArrayList<>();

        System.out.println("Enter email addresses (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            emailList.add(extractEmailInfo(input));
        }

        displayResults(emailList);
        analyzeStatistics(emailList);
    }
}
