package ng.safebank;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import exception.WithdrawalLimitExceededException;

import java.math.BigDecimal;
import java.util.Scanner;

    public class BankingApplication {

        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {

            System.out.println("================================");
            System.out.println("       WELCOME TO SAFEBANK");
            System.out.println("================================");

            BankAccount account = createAccount();

            boolean running = true;

            while (running) {

                displayMenu();

                System.out.print("Select an option: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {

                    case "1" -> handleDeposit(account);

                    case "2" -> handleWithdrawal(account);

                    case "3" -> System.out.println(
                            "Current balance: ₦" + account.getBalance()
                    );

                    case "4" -> account.displayTransactionHistory();

                    case "5" -> {
                        System.out.println("Thank you for banking with SafeBank.");
                        running = false;
                    }

                    default -> System.out.println(
                            "Invalid selection. Please choose between 1 and 5."
                    );
                }
            }

            scanner.close();
        }

        private static void displayMenu() {

            System.out.println("\n========== SAFEBANK MENU ==========");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.println("===================================");
        }

        private static BankAccount createAccount() {

            System.out.print("Enter account holder's name: ");
            String name = scanner.nextLine().trim();

            while (name.isBlank()) {
                System.out.println("Name cannot be blank.");
                System.out.print("Enter account holder's name: ");
                name = scanner.nextLine().trim();
            }

            while (true) {

                System.out.print("Enter opening balance: ₦");
                String input = scanner.nextLine().trim();

                try {

                    BigDecimal openingBalance = new BigDecimal(input);

                    if (openingBalance.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println(
                                "Opening balance cannot be negative."
                        );
                        continue;
                    }

                    return new BankAccount(name, openingBalance);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid amount. Please enter a valid number."
                    );
                }
            }
        }

        private static void handleDeposit(BankAccount account) {

            System.out.print("Enter deposit amount: ₦");
            String input = scanner.nextLine().trim();

            try {
                BigDecimal amount = new BigDecimal(input);

                account.deposit(amount);

                System.out.println("Deposit successful!");
                System.out.println("New balance: ₦" + account.getBalance());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please enter a valid number.");

            } catch (InvalidAmountException e) {

                System.out.println(e.getMessage());
            }
        }private static void handleWithdrawal(BankAccount account) {

            System.out.print("Enter withdrawal amount: ₦");
            String input = scanner.nextLine().trim();

            try {
                BigDecimal amount = new BigDecimal(input);

                account.withdraw(amount);

                System.out.println("Withdrawal successful!");
                System.out.println("New balance: ₦" + account.getBalance());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please enter a valid number.");

            } catch (InvalidAmountException |
                     InsufficientFundsException |
                     WithdrawalLimitExceededException e) {

                System.out.println(e.getMessage());
            }
        }

    }
