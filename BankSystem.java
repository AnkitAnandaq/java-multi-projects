import java.util.*;

// Base Class - BankAccount
class BankAccount {
    protected int accountNumber;
    protected String accountHolder;
    protected double balance;

    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully!");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully!");
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount!");
            return false;
        }
    }

    public void displayBalance() {
        System.out.println("Account Number: " + accountNumber + ", Balance: ₹" + balance);
    }
}

// Derived Class - SavingsAccount
class SavingsAccount extends BankAccount {
    private double interestRate = 2.5;

    public SavingsAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    public void addInterest() {
        double interest = (balance * interestRate) / 100;
        balance += interest;
        System.out.println("Interest of ₹" + interest + " added. New Balance: ₹" + balance);
    }
}

// Derived Class - CurrentAccount
class CurrentAccount extends BankAccount {
    private double overdraftLimit = 5000;

    public CurrentAccount(int accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully!");
            return true;
        } else {
            System.out.println("Overdraft limit exceeded or invalid amount!");
            return false;
        }
    }
}

// Bank Management System
class BankSystem {
    private static HashMap<Integer, BankAccount> accounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Open Account\n2. Deposit\n3. Withdraw\n4. Check Balance\n5. Transfer Money\n6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    openAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    transferMoney();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void openAccount() {
        System.out.print("Enter Account Holder Name: ");
        sc.nextLine(); // Consume newline
        String name = sc.nextLine();
        System.out.print("Choose Account Type (1. Savings, 2. Current): ");
        int type = sc.nextInt();
        int accNo = accounts.size() + 1001; // Unique Account Number
        BankAccount account;

        if (type == 1) {
            account = new SavingsAccount(accNo, name, 0);
        } else {
            account = new CurrentAccount(accNo, name, 0);
        }

        accounts.put(accNo, account);
        System.out.println("Account Created! Account Number: " + accNo);
    }

    public static void depositMoney() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        if (accounts.containsKey(accNo)) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = sc.nextDouble();
            accounts.get(accNo).deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        if (accounts.containsKey(accNo)) {
            System.out.print("Enter Amount to Withdraw: ");
            double amount = sc.nextDouble();
            accounts.get(accNo).withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public static void checkBalance() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        if (accounts.containsKey(accNo)) {
            accounts.get(accNo).displayBalance();
        } else {
            System.out.println("Account not found!");
        }
    }

    public static void transferMoney() {
        System.out.print("Enter Your Account Number: ");
        int senderAccNo = sc.nextInt();
        System.out.print("Enter Receiver Account Number: ");
        int receiverAccNo = sc.nextInt();

        if (accounts.containsKey(senderAccNo) && accounts.containsKey(receiverAccNo)) {
            System.out.print("Enter Amount to Transfer: ");
            double amount = sc.nextDouble();
            if (accounts.get(senderAccNo).withdraw(amount)) {
                accounts.get(receiverAccNo).deposit(amount);
                System.out.println("Transfer Successful!");
            } else {
                System.out.println("Transfer Failed!");
            }
        } else {
            System.out.println("Invalid Account Numbers!");
        }
    }
}
