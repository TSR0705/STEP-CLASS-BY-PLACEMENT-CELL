public class PersonalFinanceManager {
    public static void main(String[] args) {
        // Set bank name once for all accounts (static)
        PersonalAccount.setBankName("SmartBank");

        // Create 3 different accounts
        PersonalAccount acc1 = new PersonalAccount("Alice");
        PersonalAccount acc2 = new PersonalAccount("Bob");
        PersonalAccount acc3 = new PersonalAccount("Charlie");

        // Perform transactions on Alice's account
        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1500, "Rent");
        acc1.addExpense(500, "Groceries");

        // Perform transactions on Bob's account
        acc2.addIncome(7000, "Salary");
        acc2.addIncome(2000, "Freelance Project");
        acc2.addExpense(3000, "Shopping");

        // Perform transactions on Charlie's account
        acc3.addIncome(10000, "Business Profit");
        acc3.addExpense(2000, "Travel");
        acc3.addExpense(1000, "Food");

        // Display account summaries
        System.out.println("\n--- Account Summaries ---");
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Show static info (shared across all accounts)
        System.out.println("\n--- Bank Info ---");
        System.out.println("Bank Name: " + PersonalAccount.getBankName());
        System.out.println("Total Accounts Created: " + PersonalAccount.getTotalAccounts());
    }
}

// ================== PersonalAccount Class ==================
class PersonalAccount {
    // Instance variables (unique to each account)
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    // Static variables (shared by all accounts)
    private static int totalAccounts = 0;
    private static String bankName;
    private static int accountCounter = 1; // for unique account numbers

    // Constructor
    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0;
        this.totalIncome = 0;
        this.totalExpenses = 0;
        totalAccounts++;
    }

    // Instance Methods
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(accountHolderName + " received income: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid income amount!");
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(accountHolderName + " spent: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid or insufficient balance for expense!");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\nAccount Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Total Savings: " + calculateSavings());
    }

    // Static Methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static String getBankName() {
        return bankName;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        return "ACC" + String.format("%03d", accountCounter++);
    }
}
