package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import model.Account;
import model.Transaction;
import model.User;

public class SimpleBankingApp {
    public static Vector<User> users = new Vector<>();
    public static Vector<Account> accounts = new Vector<>();
    public static Vector<Transaction> transactions = new Vector<>();

    public static void loadUserData() {
        users.add(new User("mike", "my_passwd", "Mike", "Smith", "07771234567"));
        users.add(new User("james.cameron@gmail.com", "angel", "James", "Cameron", "07777654321"));
        users.add(new User("julia.roberts@gmail.com", "change_me", "Julia", "Roberts", "07770123456"));
    }

    public static void printAllUsers() {
        System.out.println("There are: " + users.size() + " users in the system.");
        System.out.println(String.format("%-25s| %-15s| %-15s| %-15s| %-15s",
                "username", "password", "first_name", "last_name", "mobile_number"));
        System.out.println("-------------------------------------------------------------------------------------------");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println();
    }

    public static void loadAccountData() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            accounts.add(new Account("5495-1234", "mike", "Standard", sdf.parse("20/08/2019")));
            accounts.add(new Account("5495-1239", "mike", "Standard", sdf.parse("20/08/2020")));
            accounts.add(new Account("5495-1291", "mike", "Saving", sdf.parse("21/07/2019")));
            accounts.add(new Account("5495-6789", "David.McDonald@gmail.com", "Saving", sdf.parse("20/08/2019")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void printAllAccounts() {
        System.out.println("Accounts: initial state, after loading...");
        System.out.println("There are: " + accounts.size() + " accounts in the system.");
        System.out.println(String.format("%-15s| %-30s| %-10s| %-15s| %-10s",
                "Account #", "username_of_account_holder", "type", "opening_date", "Balance"));
        System.out.println("--------------------------------------------------------------------------------");
        for (Account account : accounts) {
            System.out.println(String.format("%-15s| %-30s| %-10s| %-15s| $%-10.2f",
                    account.getAccount_number(),
                    account.getUsername_of_account_holder(),
                    account.getAccount_type(),
                    new SimpleDateFormat("MMM d, yyyy").format(account.getAccount_opening_date()),
                    getBalance(account.getAccount_number())));
        }
        System.out.println();
    }

    public static void addTransaction(String account_number, double amount) {
        transactions.add(new Transaction(account_number, amount, Calendar.getInstance().getTime()));
    }
	
    public static double getBalance(String account_number) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getAccount_number().equals(account_number)) {
                balance += transaction.getTransaction_amount();
            }
        }
        return balance;
    }

    public static void main(String[] args) {
        loadUserData();
        printAllUsers();

        loadAccountData();
        printAllAccounts();

        addTransaction("5495-1234", -50.21);
        System.out.println("Account: after the 1st addTransaction function call...");
        printAllAccounts();

        addTransaction("5495-1234", 520.00);
        addTransaction("9999-1111", 21.00); // This account number doesn't match any in 'accounts'
        System.out.println("Account: after the 2nd/3rd addTransaction function calls...");
        printAllAccounts();
    }
}


