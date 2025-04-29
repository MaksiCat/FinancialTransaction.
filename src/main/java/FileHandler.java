import java.io.*;
import java.math.BigDecimal;// For representing monetary values precisely
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Transaction> readTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();// Creating a list to store transactions

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line; // To store each line read from the file
            // Loop through each line in the file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");// Splitting the line by '|' delimiter
// Check if the line has exactly 5 parts
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    BigDecimal amount = new BigDecimal(parts[4]);
// Create a new Transaction object with the parsed data
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            // Handle file reading errors
            System.out.println("Error when reading the file: " + e.getMessage());
        }

        return transactions;
    }
    // Method to write a single transaction to the file
    public static void writeTransaction(String filename, Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            // Format the transaction data into a single line separated by '|'
            String line = String.format("%s|%s|%s|%s|%s",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
            bw.write(line);// Write the formatted line to the file
            bw.newLine();// Write a newline character after the line
        } catch (IOException e) {
            // Handle file writing errors
            System.out.println("Error when writing a file: " + e.getMessage());
        }
    }
}
