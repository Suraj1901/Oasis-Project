import java.util.Scanner;

class User {
    private String userId;
    private String pin;
    private double balance;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    public void transfer(User recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed.");
        }
    }
}

class ATM {
    private User user;
    private Scanner scanner;

    public ATM(User user) {
        this.user = user;
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("Welcome, " + user.getUserId());
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }

    public void performTransaction(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Transactions History: No history available.");
                break;
            case 2:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                if (user.withdraw(withdrawAmount)) {
                    System.out.println("Withdrawal successful. Current balance: " + user.getBalance());
                }
                break;
            case 3:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                user.deposit(depositAmount);
                System.out.println("Deposit successful. Current balance: " + user.getBalance());
                break;
            case 4:
                System.out.print("Enter recipient's user ID: ");
                String recipientId = scanner.next();
                System.out.print("Enter amount to transfer: ");
                double transferAmount = scanner.nextDouble();
                User recipient = new User(recipientId, "", 0); // Dummy user for demonstration
                user.transfer(recipient, transferAmount);
                break;
            case 5:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        // Dummy user data
        User user = new User("user123", "1234", 1000);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        if (userId.equals(user.getUserId()) && user.validatePin(pin)) {
            ATM atm = new ATM(user);

            while (true) {
                atm.showMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                atm.performTransaction(choice);
            }
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }
    }
}
