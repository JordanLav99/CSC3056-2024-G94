package tests;

import model.Account;
import java.util.Calendar;
import java.util.Date;
import utils.TestUtils;

public class AccountTest {

    public static void testConstructors() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        Date test_openingDate = calendar.getTime();
        
        String test_accountNumber = "123456";
        String test_usernameOfAccountHolder = "John_Dobbin";
        String test_accountType = "Current Account";
        
        Account testAccount = new Account(test_accountNumber, test_usernameOfAccountHolder, test_accountType, test_openingDate);

        assert test_accountNumber.equals(testAccount.getAccount_number()) : "Account number does not match";
        assert test_usernameOfAccountHolder.equals(testAccount.getUsername_of_account_holder()) : "Username of account holder does not match";
        assert test_accountType.equals(testAccount.getAccount_type()) : "Account type does not match";
        assert test_openingDate.equals(testAccount.getAccount_opening_date()) : "Account opening date does not match";

        System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructors passed" + TestUtils.TEXT_COLOR_RESET);
    }

    public static void testSetters() {
        Account testAccount = new Account("initial", "initialUser", "InitialType", new Date());
        String new_accountNumber = "654321";
        String new_usernameOfAccountHolder = "Mike_Smith";
        String new_accountType = "Savings Account";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        Date new_openingDate = calendar.getTime();

        testAccount.setAccount_number(new_accountNumber);
        testAccount.setUsername_of_account_holder(new_usernameOfAccountHolder);
        testAccount.setAccount_type(new_accountType);
        testAccount.setAccount_opening_date(new_openingDate);

        assert new_accountNumber.equals(testAccount.getAccount_number()) : "Setter for account number failed";
        assert new_usernameOfAccountHolder.equals(testAccount.getUsername_of_account_holder()) : "Setter for username of account holder failed";
        assert new_accountType.equals(testAccount.getAccount_type()) : "Setter for account type failed";
        assert new_openingDate.equals(testAccount.getAccount_opening_date()) : "Setter for account opening date failed";

        System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestSetters passed" + TestUtils.TEXT_COLOR_RESET);
    }

    public static void main(String[] args) {
        testConstructors();
        testSetters();
    }
}
