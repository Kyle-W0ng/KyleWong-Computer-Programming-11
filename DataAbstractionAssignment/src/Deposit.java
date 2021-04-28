import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    public Deposit(double amt, Date date, String account){
        this.amount = amt;
        this.date = date;
        this.account = account;
    }

    //Requires: amount>=0, date, account
    //Effects: Returns a new deposit record back to the method deposit and insert a new record to the deposits there
    public String toString(){
        return "Date: " + date + " - Deposit of: $" + amount +" to account: " + account;
    }
}
