

//Checking Account that is a bank account 
public class Checking extends BankAccount{
    private double balance;
    final double interest = 0.0;
    public Checking(double initial) {
        super(initial);
        this.balance = initial;
    }
    
    public void processCheck(){

        System.out.println("Process complete");
    }
}