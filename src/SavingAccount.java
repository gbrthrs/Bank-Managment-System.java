public class SavingAccount extends Account {

    private double interestRate;

    public SavingAccount(String accountNumber, Person person,
                         double balance, double interestRate) {

        super(accountNumber, person, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void accountType() {
        System.out.println("Account Type: Saving Account");
    }

    public void calculateInterest() {

        double interest = (balance * interestRate) / 100;

        balance = balance + interest;

        System.out.println("Interest Added: " + interest);
        System.out.println("New Balance: " + balance);
    }
}