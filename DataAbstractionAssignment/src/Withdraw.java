import java.util.Date;


public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    public Withdraw(double amt, Date date, String account){
        this.amount = amt;
        this.date = date;
        this.account = account;

    }

    //Requires: amount>=0, date, account
    //Effects: Returns a new withdrawal record back to the method withdraw and insert a new record to the withdraws there
    public String toString(){
        return "Date: " + date + " - Withdrawal of: $" + amount +" from account: " + account;
    }
}
