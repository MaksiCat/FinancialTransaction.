import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionLedger {
    private List<Transaction> transactions;

    public TransactionLedger() {
        transactions = FileHandler.readTransactions("transactions.csv");
    }

    public void addDeposit(String description, String vendor, BigDecimal amount) {
        Transaction deposit = new Transaction(LocalDate.now(), java.time.LocalTime.now(), description, vendor, amount);
        transactions.add(deposit);
        FileHandler.writeTransaction("transactions.csv", deposit);
    }

    public void addPayment(String description, String vendor, BigDecimal amount) {
        Transaction payment = new Transaction(LocalDate.now(), java.time.LocalTime.now(), description, vendor, amount.negate());
        transactions.add(payment);
        FileHandler.writeTransaction("transactions.csv", payment);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> reversed = new ArrayList<>(transactions);
        java.util.Collections.reverse(reversed);
        return reversed;
    }

    public List<Transaction> getDeposits() {
        return getAllTransactions().stream()
                .filter(t -> t.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }

    public List<Transaction> getPayments() {
        return getAllTransactions().stream().filter(t -> t.getAmount().compareTo(BigDecimal.ZERO) < 0).collect(Collectors.toList());
    }
}