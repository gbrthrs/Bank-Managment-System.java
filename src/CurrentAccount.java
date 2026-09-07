public class CurrentAccount extends Account{
    private double overdraflimit;

    public CurrentAccount(String accountNumber, Person person, double balance, double overdraflimit) {
        super(accountNumber, person, balance);
        this.overdraflimit = overdraflimit;
    }

    @Override
    public void accountType() {
        System.out.println("Account Type; Current Account");
    }

    public double getOverdraflimit() {
        return overdraflimit;
    }
}