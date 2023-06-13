public interface Account {


    /*
     *This is everything a bank account should be able to do, besides change interest because itsunecessary
     */

    void deposit(double amount);
    double getBalance();
    void withdraw(double amount);
    void setInterest(double rate);
    double getInterest();

}