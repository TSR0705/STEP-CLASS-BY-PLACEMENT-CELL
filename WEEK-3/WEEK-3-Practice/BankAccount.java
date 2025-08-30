public class BankAccount {
    // ---------- Static (class-level) variables ----------
    private static String bankName;
    private static int totalAccounts = 0;
    private static double interestRate;

    // ---------- Instance (object-level) variables ----------
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // ---------- Constructor ----------
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++; // Increment static counter
    }

    // ---------- Static methods ----------
    public static void setBankName(String name) {
        bankName = name;
    }

    public static void setInterestRate(double rate) {
        interestRate = rate;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static void displayBankInfo() {
        System.out.println("Bank Name: " + bankName);
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("--------------------------------");
    }

    // ---------- Instance methods ----------
    public void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolder + " deposited " + amount + ". New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew " + amount + ". Remaining Balance: " + balance);
        } else {
            System.out.println(accountHolder + " has insufficient funds!");
        }
    }

    public void calculateInterest() {
        double interest = balance * interestRate / 100;
        System.out.println(accountHolder + " earns interest: " + interest);
    }

    public void displayAccountInfo() {
        System.out.println("Account Info:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: " + balance);
        System.out.println("--------------------------------");
    }

    // ---------- Main Method ----------
    public static void main(String[] args) {
        // Set bank name and interest rate (static members)
        BankAccount.setBankName("National Bank of India");
        BankAccount.setInterestRate(5.0);

        // Create multiple BankAccount objects (instance members)
        BankAccount acc1 = new BankAccount("A101", "Rahul Sharma", 10000);
        BankAccount acc2 = new BankAccount("A102", "Ananya Singh", 20000);

        // Show that static members are shared across all accounts
        BankAccount.displayBankInfo();

        // Show instance-specific information
        acc1.displayAccountInfo();
        acc2.displayAccountInfo();

        // Perform transactions
        acc1.deposit(5000);
        acc1.withdraw(3000);
        acc1.calculateInterest();

        acc2.deposit(2000);
        acc2.withdraw(25000); // insufficient funds
        acc2.calculateInterest();

        // Show updated static info
        System.out.println("Total Accounts (via static method): " + BankAccount.getTotalAccounts());

        // Demonstrate calling static method via object (not recommended, but possible)
        acc1.displayBankInfo();  // same as BankAccount.displayBankInfo()
    }
}
