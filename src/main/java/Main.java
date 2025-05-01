// Importing necessary libraries
import java.math.BigDecimal;// For precise financial calculations
import java.util.List;// For using List interface
import java.util.Scanner;// For user input
// Main class containing the application's entry point
public class Main {
    public static void main(String[] args) {
        TransactionLedger ledger = new TransactionLedger();// Create a ledger to store transactions
        Scanner scanner = new Scanner(System.in);// Create a scanner for reading user input
        boolean running = true;// Flag to control the main loop
// Main application loop
        while (running) {
            // Display the main menu
            System.out.println("\n==== Financial Ledger ====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("R) Reports");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().toUpperCase();// Read user input and convert to uppercase
// Handle user choice
            switch (choice) {
                case "D":// Add Deposit
                    System.out.print("Enter description: ");
                    String depDesc = scanner.nextLine();// Read description

                    System.out.print("Enter vendor: ");
                    String depVendor = scanner.nextLine();// Read vendor

                    System.out.print("Enter amount: ");
                    BigDecimal depAmount = new BigDecimal(scanner.nextLine());// Read and convert amount

                    ledger.addDeposit(depDesc, depVendor, depAmount);// Add deposit to ledger
                    System.out.println("Deposit added.");
                    break;

                case "P":// Make Payment
                    System.out.print("Enter description: ");
                    String payDesc = scanner.nextLine();// Read description

                    System.out.print("Enter vendor: ");
                    String payVendor = scanner.nextLine();// Read vendor

                    System.out.print("Enter amount: ");
                    BigDecimal payAmount = new BigDecimal(scanner.nextLine());// Read and convert amount

                    ledger.addPayment(payDesc, payVendor, payAmount);// Add payment to ledger
                    System.out.println("Payment added.");
                    break;

                case "L":// Open Ledger Menu
                    showLedgerMenu(ledger, scanner);// Call method to display ledger submenu
                    break;

                case "R":
                    showReportsMenu(ledger, scanner);
                    break;
                case "X":// Exit application
                    running = false;// Set running to false to end loop
                    break;

                default:// Invalid input handling
                    System.out.println("Invalid choice. Try again.");
            }
        }
// Display exit message
        System.out.println("Application closed.");
    }
    // Method to display the ledger submenu
    private static void showLedgerMenu(TransactionLedger ledger, Scanner scanner) {
        boolean back = false;// Flag to return to the main menu
// Ledger submenu loop
        while (!back) {
            // Display ledger menu options
            System.out.println("\n==== Ledger ====");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().toUpperCase();// Read user choice
// Handle user input for ledger menu
            switch (choice) {
                case "A":
                    printTransactions(ledger.getAllTransactions());// Show all transactions
                    break;
                case "D":
                    printTransactions(ledger.getDeposits());// Show only deposits
                    break;
                case "P":
                    printTransactions(ledger.getPayments());// Show only payments
                    break;
                case "H":
                    back = true;// Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");// Handle invalid input
            }
        }
    }

    // Method to display the reports submenu
    private static void showReportsMenu(TransactionLedger ledger, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n==== Reports ====");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    printTransactions(ledger.getTransactionsMonthToDate());
                    break;
                case "2":
                    printTransactions(ledger.getTransactionsPreviousMonth());
                    break;
                case "3":
                    printTransactions(ledger.getTransactionsYearToDate());
                    break;
                case "4":
                    printTransactions(ledger.getTransactionsPreviousYear());
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    printTransactions(ledger.getTransactionsByVendor(vendor));
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    // Method to print a list of transactions
    private static void printTransactions(List<Transaction> transactions) {
    for (Transaction t : transactions) {
        // Print transaction details in formatted output
    System.out.printf("%s %s %s %s %.2f\n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
    }
    }
}