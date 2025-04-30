import java.math.BigDecimal;// Imports the BigDecimal class for precise decimal number calculations (useful for money)
import java.time.LocalDate;// Imports the LocalDate class to handle date information
import java.util.ArrayList;// Imports the ArrayList class, a resizable list implementation
import java.util.List;// Imports the List interface
import java.util.stream.Collectors;// Imports the Collectors utility class for stream operations

public class TransactionLedger {
    private List<Transaction> transactions;// Private list to store all transactions
    // Constructor that initializes the transaction list by reading from a CSV file
    public TransactionLedger() {
        transactions = FileHandler.readTransactions("transactions.csv");// Reads existing transactions from the CSV file

    }
    // Method to add a deposit (positive amount) to the ledger
    public void addDeposit(String description, String vendor, BigDecimal amount) {
        // Creates a new Transaction object with the current date and time
        Transaction deposit = new Transaction(LocalDate.now(), java.time.LocalTime.now(), description, vendor, amount);
        transactions.add(deposit);// Adds the deposit to the internal list
        FileHandler.writeTransaction("transactions.csv", deposit);// Writes the deposit to the CSV file
    }
    // Method to add a payment (negative amount) to the ledger
    public void addPayment(String description, String vendor, BigDecimal amount) {
        // Creates a new Transaction with a negative amount (to represent a payment)
        Transaction payment = new Transaction(LocalDate.now(), java.time.LocalTime.now(), description, vendor, amount.negate());
        transactions.add(payment);// Adds the payment to the internal list
        FileHandler.writeTransaction("transactions.csv", payment);// Writes the payment to the CSV file
    }
    // Returns all transactions in reverse order (newest first)
    public List<Transaction> getAllTransactions() {
        List<Transaction> reversed = new ArrayList<>(transactions);// Copies the original list
        java.util.Collections.reverse(reversed);// Reverses the list to show the most recent transactions first
        return reversed;// Returns the reversed list
    }
    // Returns only deposit transactions (amount > 0)
    public List<Transaction> getDeposits() {
        return getAllTransactions().stream()// Creates a stream from all transactions
                .filter(t -> t.getAmount().compareTo(BigDecimal.ZERO) > 0)// Filters only positive amounts
                .collect(Collectors.toList());// Collects the results into a list
    }
    // Returns only payment transactions (amount < 0)
    public List<Transaction> getPayments() {
        return getAllTransactions().stream().filter(t -> t.getAmount().compareTo(BigDecimal.ZERO) < 0).collect(Collectors.toList());
    }
}