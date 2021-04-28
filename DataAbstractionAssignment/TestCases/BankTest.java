import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class BankTest {
    Customer testSetCustomer;
    Withdraw testSetWithdraw;
    Deposit testSetDeposit;
    Date testSetDate;

    @Before
    public void setUp(){
        testSetCustomer = new Customer();
        testSetWithdraw = new Withdraw(500, testSetDate, "");
        testSetDeposit = new Deposit(500, testSetDate, "");
        testSetDate = new Date();
    }

    @Test
    public void testDepositedChecking(){
        //Check that account is clear
        assertEquals(0, testSetCustomer.getCheckBalance(), 0.0);
        //insert the amount to Checking
        testSetCustomer.deposit(100.0, testSetDate, "CHECKING");
        //check the balance for change in Checking
        assertEquals(100, testSetCustomer.getCheckBalance(), 0.0);
    }

    @Test
    public void testDepositedSavings(){
        //Check that account is clear
        assertEquals(0, testSetCustomer.getSavingBalance(), 0.0);
        //insert the amount to Savings
        testSetCustomer.deposit(100.0, testSetDate, "SAVINGS");
        //check the balance for change in Savings
        assertEquals(100, testSetCustomer.getSavingBalance(), 0.0);
    }

    @Test
    public void testWithdrewChecking(){
        //insert the amount to Checking
        testSetCustomer.deposit(200.0, testSetDate, "CHECKING");
        //check the balance for change in Checking
        assertEquals(200, testSetCustomer.getCheckBalance(), 0.0);
        //withdraw the amount from Checking
        testSetCustomer.withdraw(100,testSetDate, "CHECKING");
        //check the changed balance in Checking
        assertEquals(100, testSetCustomer.getCheckBalance(),0.0);
        //check that the Checking account doesn't have overdraft
        assertFalse(testSetCustomer.getCheckBalance() < 0);
    }

    @Test
    public void testWithdrewSavings(){
        //insert the amount to Savings
        testSetCustomer.deposit(200.0, testSetDate, "SAVINGS");
        //check the balance for change in Savings
        assertEquals(200, testSetCustomer.getSavingBalance(), 0.0);
        //withdraw the amount from Savings
        testSetCustomer.withdraw(100,testSetDate, "SAVINGS");
        //check the changed balance in Savings
        assertEquals(100, testSetCustomer.getSavingBalance(),0.0);
        //check that the Savings account doesn't have overdraft
        assertFalse(testSetCustomer.getSavingBalance() < 0);
    }

    @Test
    public void testWDtoStringChecking() {
        //insert the amount to Checking account
        testSetCustomer.deposit(1000.0, testSetDate, "CHECKING");
        //check the balance for change in Checking
        assertEquals(1000, testSetCustomer.getCheckBalance(), 0.0);
        //clear the records
        testSetCustomer.getWithdraws().clear();
        //withdraw the amount from Checking, inside the method, the toString method will be called
        testSetCustomer.withdraw(500.0, testSetDate, "CHECKING");
        //check if the records are correct.
        assertEquals
                (testSetCustomer.getWithdraws().get(0),
                        testSetCustomer.withdraw.toString());
    }

    @Test
    public void testWDtoStringSavings(){
        //insert the amount to Savings account
        testSetCustomer.deposit(1000.0, testSetDate, "SAVINGS");
        //check the balance for change in Savings
        assertEquals(1000, testSetCustomer.getSavingBalance(), 0.0);
        //clear the records
        testSetCustomer.getWithdraws().clear();
        //withdraw the amount from Savings, inside the method, the toString method will be called
        testSetCustomer.withdraw(500.0, testSetDate, "SAVINGS");
        //check if the records are correct.
        assertEquals
                (testSetCustomer.getWithdraws().get(0),
                        testSetCustomer.withdraw.toString());
    }

    @Test
    public void testDPtoStringChecking(){
        //clear the records
        testSetCustomer.getDeposits().clear();
        //insert the amount to Checking, inside the method, the toString method will be called
        testSetCustomer.deposit(1000.0, testSetDate, "CHECKING");
        //check if the records are correct.
        assertEquals
                (testSetCustomer.getDeposits().get(0),
                        testSetCustomer.deposit.toString());
    }

    @Test
    public void testDPtoStringSavings(){
        //clear the records
        testSetCustomer.getDeposits().clear();
        //insert the amount to Savings, inside the method, the toString method will be called
        testSetCustomer.deposit(1000.0, testSetDate, "SAVINGS");
        //check if the records are correct.
        assertEquals
                (testSetCustomer.getDeposits().get(0),
                        testSetCustomer.deposit.toString());
    }

    @Test
    public void testCheckOverdraftChecking(){
        //insert the to Checking account
        testSetCustomer.deposit(750, testSetDate, "CHECKING");
        //withdraw exceeding amount from Checking account
        testSetCustomer.withdraw(1000, testSetDate, "CHECKING");
        //checks that withdraw method will be cancelled and amount in Checking will not be changed
        assertEquals(750, testSetCustomer.getCheckBalance(), 0.0);
    }

    @Test
    public void testCheckOverdraftSavings(){
        //insert the to Savings account
        testSetCustomer.deposit(750, testSetDate, "SAVINGS");
        //withdraw exceeding amount from Savings account
        testSetCustomer.withdraw(1000, testSetDate, "SAVINGS");
        //checks that withdraw method will be cancelled and amount in Savings will not be changed
        assertEquals(750, testSetCustomer.getSavingBalance(), 0.0);
    }
}
