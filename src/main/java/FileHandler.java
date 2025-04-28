import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Transaction> readTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    BigDecimal amount = new BigDecimal(parts[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error when reading the file: " + e.getMessage());
        }

        return transactions;
    }

    public static void writeTransaction(String filename, Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            String line = String.format("%s|%s|%s|%s|%s",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error when writing a file: " + e.getMessage());
        }
    }
}
