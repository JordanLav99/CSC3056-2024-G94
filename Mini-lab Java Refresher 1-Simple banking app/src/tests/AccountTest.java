package tests;

import model.Account;
import java.util.Calendar;
import java.util.Date;

public class AccountTest {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.MAY); 
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        
        Date openingDate = calendar.getTime();
        
        Account testAccount = new Account("John", "John_Dobbin", "Current Account", openingDate);
        
        System.out.println(testAccount);
    }
}
