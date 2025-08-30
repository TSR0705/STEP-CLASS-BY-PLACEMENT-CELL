public class BankAccount {
    // ---------- Private Instance Variables ----------
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // ---------- Static Variables ----------
    private static int totalAccounts = 0;   // count of accounts created
    private static int accountCounter = 0;  // used to generate unique account numbers

    // ---------- Constructor ----------
    public BankAccount(String accountHolderName, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    // ---------- Instance Methods ----------
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive!");
            return;
        }
        balance += amount;
        System.out.println(accountHolderName + " deposited " + amount + ". New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
            return;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.println(accountHolderName + " withdrew " + amount + ". Remaining Balance: " + balance);
        } else {
            System.out.println("Insufficient funds for " + accountHolderName + "!");
        }
    }

    public double checkBalance() {
        return balance;
    }

    public void displayAccountInfo() {
        System.out.println("----- Account Information -----");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("-------------------------------");
    }

    // ---------- Static Methods ----------
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("ACC%03d", accountCounter); // ACC001, ACC002...
    }

    // ---------- Main Method ----------
    public static void main(String[] args) {
        // Array to store accounts (no collections used)
        BankAccount[] accounts = new BankAccount[3];

        // Create accounts
        accounts[0] = new BankAccount("Rahul Sharma", 5000);
        accounts[1] = new BankAccount("Ananya Singh", 10000);
        accounts[2] = new BankAccount("Aman Verma", 2000);

        // Perform transactions
        accounts[0].deposit(2000);
        accounts[1].withdraw(3000);
        accounts[2].withdraw(2500); // insufficient funds

        // Display account info
        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }

        // Show static info
        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());

        // Demonstrate difference between static & instance members
        System.out.println("Rahul’s Balance: " + accounts[0].checkBalance());
        System.out.println("Ananya’s Balance: " + accounts[1].checkBalance());
        System.out.println("Aman’s Balance: " + accounts[2].checkBalance());
    }
}
