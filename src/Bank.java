public class Bank {

    private String bankname;
    private Account[] accounts;
    private int count;

    public Bank(String bankname) {
        this.bankname = bankname;
        accounts = new Account[100];
        count = 0;
    }

    public void createAccount(Account account) {
        if (count < accounts.length) {
            accounts[count] = account;
            count++;

            System.out.println("Account Created Successful!");
        }
        else {
            System.out.println("Account Limit Full!");
        }
    }

    public Account searchAccount(String accountNumber) {

        for (int i = 0; i < count; i++) {

            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }

        return null;
    }

    public void deposite(String accountNumber, double amount) {

        Account account = searchAccount(accountNumber);

        if (account != null) {
            account.deposite(amount);
        }
        else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(String accountNumber, double amount) {

        Account account = searchAccount(accountNumber);

        if (account != null) {
            account.withdraw(amount);
        }
        else {
            System.out.println("Account not Found!");
        }
    }

    public void displayAllaccounts() {

        if (count == 0) {
            System.out.println("No Accounts Available");
            return;
        }

        for (int i = 0; i < count; i++) {

            accounts[i].accountType();
            accounts[i].displayAccount();

            System.out.println("-------------------------------");
        }
    }

    public void deleteAccount(String accountNumber) {

        int index = -1;

        for (int i = 0; i < count; i++) {

            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                index = i;
                break;
            }
        }

        if (index != -1) {

            for (int i = index; i < count - 1; i++) {
                accounts[i] = accounts[i + 1];
            }

            accounts[count - 1] = null;
            count--;

            System.out.println("Account Deleted Successful!");
        }
        else {
            System.out.println("Account Not Found!");
        }
    }
}