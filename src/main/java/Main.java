import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionLedger ledger = new TransactionLedger();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n==== Financial Ledger ====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.print("Enter description: ");
                    String depDesc = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String depVendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    BigDecimal depAmount = new BigDecimal(scanner.nextLine());
                    ledger.addDeposit(depDesc, depVendor, depAmount);
                    System.out.println("Deposit added.");
                    break;

                case "P":
                    System.out.print("Enter description: ");
                    String payDesc = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String payVendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    BigDecimal payAmount = new BigDecimal(scanner.nextLine());
                    ledger.addPayment(payDesc, payVendor, payAmount);
                    System.out.println("Payment added.");
                    break;

                case "L":
                    showLedgerMenu(ledger, scanner);
                    break;

                case "X":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Application closed.");
    }

    private static void showLedgerMenu(TransactionLedger ledger, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n==== Ledger ====");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    printTransactions(ledger.getAllTransactions());
                    break;
                case "D":
                    printTransactions(ledger.getDeposits());
                    break;
                case "P":
                    printTransactions(ledger.getPayments());
                    break;
                case "H":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static void printTransactions(List<Transaction> transactions) {
    for (Transaction t : transactions) {
    System.out.printf("%s %s %s %s %.2f/n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
    }
    }
}