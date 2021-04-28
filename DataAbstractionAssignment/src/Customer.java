import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Customer {
    private final int accountNumber;
    private ArrayList<String> deposits;
    private ArrayList<String> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private final String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Savings";
    private final int OVERDRAFT = -100;
    Random random;
    Withdraw withdraw;
    Deposit deposit;

    Customer(){
        //create default constructor
        accountNumber = 45123;
        checkBalance = 0;
        savingBalance = 0;
        deposits = new ArrayList<>();
        withdraws = new ArrayList<>();
        name = "Louis Wane";
        random = new Random();
    }
    Customer(String name, int accountNumber, double checkBalance, double savingBalance){
        //constructor code here
        this.accountNumber = accountNumber;
        this.checkBalance = checkBalance;
        this.savingBalance = savingBalance;
        this.name = name;

    }

    //Requires: amt >= 0
    //Modifies: this
    //Effects: adds amt to this. If amt < 0, an error message pops up.
    public void deposit(double amt, Date date, String account) {
        if (amt >= 0){
                if (account.equals("CHECKING")) {
                    checkBalance += amt;
                } else if (account.equals("SAVINGS")){
                    savingBalance += amt;
                }
            deposit = new Deposit(amt, date, account);
            deposits.add(deposit.toString());
        } else{
            System.out.println("Invalid amount, please provide a valid amount.");
        }
    }
    //Requires: amt >= 0
    //Modifies: this
    //Effects: If there isn't an overdraft, subtracts amt from this.
    // Else, pops up an error message.
    public void withdraw(double amt, Date date, String account){
        if (amt >= 0) {
            if (account.equals("CHECKING") && checkOverdraft(amt, account)) {
                checkBalance -= amt;
            } else if (account.equals("SAVINGS") && checkOverdraft(amt, account)) {
                savingBalance -= amt;
            }
            withdraw = new Withdraw(amt, date, account);
            withdraws.add(withdraw.toString());
         }else{
            System.out.println("Invalid amount, please provide a valid amount.");
        }
        }

    private boolean checkOverdraft(double amt, String account){
        if (account.equals("CHECKING")){
            return !((checkBalance - amt) < 0);
        } else
        return !((savingBalance - amt) < 0);
    }
    //do not modify
    public void displayDeposits(){
        for(String d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(String w : withdraws){
            System.out.println(w);
        }
    }
    public double getCheckBalance() {
        return checkBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public ArrayList<String> getDeposits() {
        return deposits;
    }

    public ArrayList<String> getWithdraws() {
        return withdraws;
    }
}
