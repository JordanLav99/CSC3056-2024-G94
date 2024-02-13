package tests;

import java.util.Calendar;
import java.util.Date;

import model.Transaction;

public class TransactionTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.MAY); 
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        
        Date transactionDate = calendar.getTime();
        Transaction testTransaction = new Transaction("40303840", 500.0, transactionDate);
        
        // Test the toString() method output (simple way to check all values)
        System.out.println("Transaction Details: " + testTransaction);

        // Check each field individually
        checkTransactionDetails("Account Number", "40303840", testTransaction.getAccount_number());
        checkTransactionDetails("Transaction Amount", 500.0, testTransaction.getTransaction_amount());
        checkTransactionDetails("Transaction Date", transactionDate, testTransaction.getTransaction_date());
    }

    private static void checkTransactionDetails(String fieldName, Object expected, Object actual) {
        System.out.println(fieldName + " - Expected: " + expected + ", Actual: " + actual + ", Test Passed: " + expected.equals(actual));
    }
}
