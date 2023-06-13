

//Savings account that is a bank account
public class Savings extends BankAccount{
    private double balance;
    private double interest = 0.03;
    public Savings(double initial) {
        super(initial);
        this.balance = initial;
    }
        
}