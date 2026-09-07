public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected Person person;
    public Account(String accountNumber,Person person,double balance )
    {
        this.accountNumber=accountNumber;
        this.person=person;
        this.balance=balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Person getPerson() {
        return person;
    }
    public void deposite(double ammount)
    {
        if(ammount>0)
        {
            balance+=ammount;
            System.out.println("Deposite Successful!");
        }
        else
        {
            System.out.println("Invalid Ammount!");
        }
    }
    public void withdraw(double ammount)
    {
        if(ammount>0&&ammount<=balance)
        {
            balance-=ammount;
            System.out.println("Withdraw Successful!");
        }
        else
        {
            System.out.println("Insufficient Balance!");
        }
    }
    public void checkBalance()
    {
        System.out.println("Current Balance: "+balance);
    }
    public void displayAccount()
    {
        System.out.println("Account Number: "+accountNumber);
        person.displayInformation();
        System.out.println("Balance: "+balance);
    }
    public abstract void accountType();
}