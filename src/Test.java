import java.io.*;
import java.util.Scanner;

public class Test {

    // PIN file name
    static final String PIN_FILE = "security_pin.txt";

    // ================= LOAD PIN FROM FILE =================
    public static String loadPin() {

        try {
            File file = new File(PIN_FILE);

            // If file does not exist, create default PIN
            if (!file.exists()) {

                FileWriter writer = new FileWriter(PIN_FILE);
                writer.write("12345678");
                writer.close();

                return "12345678";
            }

            Scanner fileInput = new Scanner(file);

            if (fileInput.hasNextLine()) {
                String pin = fileInput.nextLine();
                fileInput.close();

                return pin;
            }

            fileInput.close();

        } catch (IOException e) {

            System.out.println("Error loading PIN.");
        }

        return "12345678";
    }


    // ================= SAVE PIN TO FILE =================
    public static void savePin(String pin) {

        try {

            FileWriter writer = new FileWriter(PIN_FILE);

            writer.write(pin);

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving PIN.");
        }
    }


    // ================= CHECK 8 DIGIT PIN =================
    public static boolean isValidPin(String pin) {

        return pin.matches("\\d{8}");
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Load PIN from file
        String securityPin = loadPin();

        // ================= SECURITY SYSTEM =================

        int attempts = 0;
        boolean accessGranted = false;

        System.out.println("========================================");
        System.out.println("          ABC BANK SECURITY");
        System.out.println("========================================");

        while (attempts < 3) {

            System.out.print("Enter 8-Digit Security PIN: ");
            String pin = input.nextLine();

            // Check PIN length and digits
            if (!isValidPin(pin)) {

                System.out.println(
                        "Invalid PIN! PIN must be exactly 8 digits."
                );

                attempts++;

                if (attempts < 3) {
                    System.out.println(
                            "Remaining Attempts: " + (3 - attempts)
                    );
                }

                continue;
            }

            // Check correct PIN
            if (pin.equals(securityPin)) {

                accessGranted = true;

                System.out.println("PIN Verified Successfully!");
                System.out.println("Access Granted.\n");

                break;

            } else {

                attempts++;

                System.out.println("Incorrect PIN!");

                if (attempts < 3) {

                    System.out.println(
                            "Remaining Attempts: " + (3 - attempts)
                    );
                }
            }
        }


        // ================= ACCESS DENIED =================

        if (!accessGranted) {

            System.out.println("\n========================================");
            System.out.println("      BANK MANAGEMENT SYSTEM LOCKED");
            System.out.println("========================================");

            input.close();
            return;
        }


        // ================= BANK SYSTEM =================

        Bank bank = new Bank("ABC Bank");

        while (true) {

            System.out.println("\n======== BANK MANAGEMENT SYSTEM ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Search Account");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Calculate Saving Interest");
            System.out.println("7. Delete Account");
            System.out.println("8. Change Security PIN");
            System.out.println("9. Exit");

            System.out.print("Enter Choice: ");
            int choice = input.nextInt();
            input.nextLine();


            switch (choice) {


                // ================= CREATE ACCOUNT =================

                case 1:

                    System.out.print("Customer ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Name: ");
                    String name = input.nextLine();

                    System.out.print("Phone: ");
                    String phone = input.nextLine();

                    System.out.print("Address: ");
                    String adress = input.nextLine();

                    Person person =
                            new Person(id, name, phone, adress);

                    System.out.print("Account Number: ");
                    String accountNumber = input.nextLine();

                    System.out.print("Opening Balance: ");
                    double balance = input.nextDouble();

                    System.out.println("1. Saving Account");
                    System.out.println("2. Current Account");

                    System.out.print("Choose Account Type: ");
                    int type = input.nextInt();

                    if (type == 1) {

                        System.out.print("Interest Rate: ");
                        double rate = input.nextDouble();

                        SavingAccount sa =
                                new SavingAccount(
                                        accountNumber,
                                        person,
                                        balance,
                                        rate
                                );

                        bank.createAccount(sa);

                    } else if (type == 2) {

                        System.out.print("Overdraft Limit: ");
                        double limit = input.nextDouble();

                        CurrentAccount ca =
                                new CurrentAccount(
                                        accountNumber,
                                        person,
                                        balance,
                                        limit
                                );

                        bank.createAccount(ca);

                    } else {

                        System.out.println("Invalid Account Type!");
                    }

                    break;


                // ================= DEPOSIT =================

                case 2:

                    System.out.print("Account Number: ");
                    String depositeAcc = input.nextLine();

                    System.out.print("Amount: ");
                    double deposite = input.nextDouble();

                    bank.deposite(depositeAcc, deposite);

                    break;


                // ================= WITHDRAW =================

                case 3:

                    System.out.print("Account Number: ");
                    String withdrawacc = input.nextLine();

                    System.out.print("Amount: ");
                    double withdraw = input.nextDouble();

                    bank.withdraw(withdrawacc, withdraw);

                    break;


                // ================= SEARCH ACCOUNT =================

                case 4:

                    System.out.print("Account Number: ");
                    String search = input.nextLine();

                    Account account =
                            bank.searchAccount(search);

                    if (account != null) {

                        account.accountType();
                        account.displayAccount();

                    } else {

                        System.out.println("Account Not Found!");
                    }

                    break;


                // ================= DISPLAY ALL =================

                case 5:

                    bank.displayAllaccounts();

                    break;


                // ================= CALCULATE INTEREST =================

                case 6:

                    System.out.print("Saving Account Number: ");
                    String interestAccount = input.nextLine();

                    Account interestAcc =
                            bank.searchAccount(interestAccount);

                    if (interestAcc instanceof SavingAccount) {

                        SavingAccount saving =
                                (SavingAccount) interestAcc;

                        saving.calculateInterest();

                    } else if (interestAcc != null) {

                        System.out.println(
                                "This is not a Saving Account!"
                        );

                    } else {

                        System.out.println("Account Not Found!");
                    }

                    break;


                // ================= DELETE ACCOUNT =================

                case 7:

                    System.out.print("Account Number: ");
                    String deleteAccount = input.nextLine();

                    bank.deleteAccount(deleteAccount);

                    break;


                // ================= CHANGE PIN =================

                case 8:

                    System.out.println(
                            "\n======= CHANGE SECURITY PIN ======="
                    );

                    System.out.print("Enter Current PIN: ");
                    String currentPin = input.nextLine();

                    if (!isValidPin(currentPin)) {

                        System.out.println(
                                "PIN must be exactly 8 digits!"
                        );

                        break;
                    }


                    if (currentPin.equals(securityPin)) {

                        System.out.print(
                                "Enter New 8-Digit PIN: "
                        );

                        String newPin = input.nextLine();


                        // Check new PIN
                        if (!isValidPin(newPin)) {

                            System.out.println(
                                    "New PIN must be exactly 8 digits!"
                            );

                            break;
                        }


                        System.out.print(
                                "Confirm New PIN: "
                        );

                        String confirmPin =
                                input.nextLine();


                        // Confirm PIN
                        if (newPin.equals(confirmPin)) {

                            securityPin = newPin;

                            // Save new PIN permanently
                            savePin(securityPin);

                            System.out.println(
                                    "PIN changed successfully!"
                            );

                        } else {

                            System.out.println(
                                    "New PIN and Confirm PIN do not match!"
                            );
                        }

                    } else {

                        System.out.println(
                                "Incorrect Current PIN!"
                        );

                        System.out.println(
                                "PIN was not changed."
                        );
                    }

                    break;


                // ================= EXIT =================

                case 9:

                    System.out.println(
                            "\nThank you for using ABC Bank!"
                    );

                    input.close();
                    return;


                // ================= INVALID CHOICE =================

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }
}