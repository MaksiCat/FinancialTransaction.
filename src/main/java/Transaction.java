// Importing necessary libraries for date, time, and precise decimal values
import java.math.BigDecimal; // Used for representing currency values accurately
import java.time.LocalDate;// Represents a date without a time zone (e.g., 2025-04-29)
import java.time.LocalTime;// Represents a time without a date (e.g., 14:30:00)
// Declaring the Transaction class to model a financial transaction
public class Transaction {
        private LocalDate date;
        private LocalTime time;
        private String description;
        private String vendor;
        private BigDecimal amount;
    // Constructor to initialize all fields of a transaction object
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, BigDecimal amount) {
        this.date = date;// Set the transaction date
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    // Getter method to return the date of the transaction
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public BigDecimal getAmount() { return amount; }
}



