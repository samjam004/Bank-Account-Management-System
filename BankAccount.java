
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Jun 3 2023
 * Bank Account Project
 * Samuel Mount
 */
public class BankAccount implements Account {

    private double balance;
    private double interest = 0.0;

    public BankAccount(double initial) {
        this.balance = initial;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public void setInterest(double rate) {
        this.interest = rate;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterest() {
        return interest;
    }

    //Ensures that the object being access is a checking account by checking it's simple name
    public boolean isChecking() {
        if (this.getClass().getSimpleName().equals("Checking")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        //Initializes the list of BankAccount objects, the scanner, and the loop variables for authentication
        //and usage
        boolean start = true;   
        boolean password = false;
        List<BankAccount> accountList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println("---------------------------------------------");
        System.out.println("Welcome to the Bank Account System");
        System.out.println("---------------------------------------------");

        //Two initial accounts are created as an example
        Checking ex1 = new Checking(200);
        Savings ex2 = new Savings(4000);
        ex2.setInterest(0.03);
        accountList.add(ex1);
        accountList.add(ex2);

        //Password authentication event in a loop until correct password is entered
        while (!password) {
            System.out.println("Please input the password:");
            if (sc.next().equals("12345")) {
                password = true;
            } else {
                System.out.println("Wrong password!");
            }
        }
        //Bank action selection event in a loop until exit is selected
        while (start) {
            System.out.println("1> Get account summary\n2> Create a new account\n" +
            "3> Make deposit\n4> Withdraw\n5> Process check\n6> Exit\n");
            
            int ans = sc.nextInt();
            
            //Account Summary
            if (ans == 1) {
                System.out.println("Account #     Type      Balance     Interest Rate");
                
                //Cycles through the list of bank account objects and outputs relevant information
                for (int i = 0; i < accountList.size(); i++) {
                    
                    String type = accountList.get(i).getClass().getSimpleName();
                    String balance = Double.toString(accountList.get(i).getBalance());
                    String interest =  Double.toString(accountList.get(i).getInterest());
                    String result = String.format("%-9s %-11s %-10s", type, balance, interest);
                    System.out.println((i + 1) + "             " + result);
                }
                System.out.println("");
            
                //Account Creation
            } else if (ans == 2) {
                System.out.println("Please Select Account Type:");
                System.out.println("1> Checking Account\n2> Saving Account");
                int ans2 = sc.nextInt();
                
                //Checking Account Creation
                if (ans2 == 1) {
                    System.out.println("Please Input Your Initial Deposit:");
                    double in = sc.nextDouble();
                    Checking account = new Checking(in);
                    accountList.add(account);

                //Savings Account Creation
                } else if (ans2 == 2) {
                    System.out.println("Please Input Your Initial Deposit:");

                    double in = sc.nextDouble();

                    System.out.println("Would you like to use the default interest or choose your own?");
                    System.out.println("1> Default\n2> Edit Rate");
                    
                    int ans3 = sc.nextInt();

                    //Interest Rate Options
                    if (ans3 == 1) {
                        Savings account = new Savings(in);
                        account.setInterest(0.3);
                        accountList.add(account);

                        //For Custom Interest Rate
                    } else if (ans3 == 2) {
                        System.out.println("Please type the desired interest rate:");
                        double rate = sc.nextDouble(); 
                        Savings account = new Savings(in);
                        account.setInterest(rate);
                        accountList.add(account);
                    } else {
                        System.out.println("Invalid Interest Rate");
                    }
                } else {
                    System.out.println("Invalid Type of Account");
                }

                //Depositing event
            } else if (ans == 3) {
                System.out.println("Choose which account should receive the deposit:");
                int actnum = sc.nextInt();
                System.out.println("Please enter the amount:");
                double amount = sc.nextDouble();
                accountList.get(actnum - 1).deposit(amount);
                System.out.println("Transaction Complete");

                //Withdraw event
            } else if (ans == 4) {
                System.out.println("Choose which account to withdraw from:");
                int actnum2 = sc.nextInt();
                System.out.println("Please enter the amount:");
                double fee = sc.nextDouble();

                //Ensures that account has sufficient funds to withdraw
                if (accountList.get(actnum2 - 1).getBalance() < fee) {
                    System.out.println("Insufficient Funds\nTransaction Failed");
                } else {
                    accountList.get(actnum2 - 1).withdraw(fee);
                    System.out.println("Transaction Complete");
                }

            //Check Processing Event
            } else if (ans == 5) {

                System.out.println("Enter which account to process the check under:");
                int actnum3 = sc.nextInt();

                //Ensures account is a checking account
                if (accountList.get(actnum3-1).isChecking()) {
                    System.out.println("Enter check number");
                    int cn = sc.nextInt();
                    System.out.println("Enter the amount on the check");
                    double chkamt= sc.nextDouble();
                    accountList.get(actnum3-1).deposit(chkamt);
                    Checking checkingAccount = (Checking) accountList.get(actnum3 - 1);
                    checkingAccount.processCheck();
                } else {
                    System.out.println("Invalid Account to Process Checks\nTransaction Failed");
                }

            //Exit Sequence
            } else if (ans == 6) {
                System.out.println("Thank You, Have a Great Day!");
                start = false;
                sc.close();
            }
        }
    }
    
    
}

