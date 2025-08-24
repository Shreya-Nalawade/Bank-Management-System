import Accounts.*;
import Services.*;
import java.math.BigDecimal;
import java.util.Scanner;
public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SavingAccountService savingService = new SavingAccountService();
        JointAccountService jointService = new JointAccountService();
        Fixed_AccountServices fixedService = new Fixed_AccountServices();
        CurrentAccountService currentService = new CurrentAccountService();

        while (true) {
            try {
                System.out.println("\n------ Bank System ------");
                System.out.println("1. Current Account Menu");
                System.out.println("2. Fixed Deposit Account Menu");
                System.out.println("3. Joint Account Menu");
                System.out.println("4. Saving Account Menu");
                System.out.println("5. Exit");
                System.out.print("Please choose an option: ");
                int accountMenuChoice = Integer.parseInt(sc.nextLine());

                switch (accountMenuChoice) {
                    case 1:
                        currentMenu(currentService);
                        break;
                    case 2:
                        fixedMenu(fixedService);
                        break;
                    case 3:
                        jointMenu(jointService);
                        break;
                    case 4:
                        savingMenu(savingService);
                        break;
                    case 5:
                        System.out.println("Exited Account Management System.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid account menu choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    

    private static void currentMenu(CurrentAccountService service) {
    	Scanner sc=new Scanner(System.in);
        CurrentAccount currentAccount = null;  

        while (true) {
            System.out.println("\n--- Current Account Operations ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an operation: ");
            int choice = Integer.parseInt(sc.nextLine());

            
            if (choice == 5) break;

            String id = "";
            CurrentAccount acc = null;

            
            if (choice != 1) {
                System.out.print("Enter Account ID: ");
                id = sc.nextLine();
                
                acc = service.getAccount(id);
                if (acc == null) {
                    System.out.println("Account not found.");
                    continue;
                }
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter Account ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accNum = sc.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    BigDecimal init;
                    try {
                        init = new BigDecimal(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount format.");
                        break;
                    }
                    currentAccount = new CurrentAccount(id, init, name, accNum);
                    service.createAccount(currentAccount);
                    break;

                case 2:
                    System.out.print("Enter Deposit Amount: ");
                    try {
                        BigDecimal depositAmount = new BigDecimal(sc.nextLine());
                        service.deposit(acc, depositAmount);
             
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Invalid amount format.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Withdrawal Amount: ");
                    try {
                        BigDecimal withdrawAmount = new BigDecimal(sc.nextLine());
                        service.withdraw(acc, withdrawAmount);
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("Invalid amount format.");
                    }
                    break;
                    
                case 4: 
                    service.displayAccount(acc);
                    break;


                case 5:
                    return;

                default:
                    System.out.println("Invalid operation.");
            }
        }
    }


    private static void fixedMenu(Fixed_AccountServices service) {
    	Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Fixed Deposit Account Operations ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account");
            System.out.println("5. Calculate Maturity Amount");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an operation: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 7) break;

            String id = "";
            if (choice != 1) {
                System.out.print("Enter Account ID: ");
                id = sc.nextLine();
                Fixed_DepositAccount acc = service.getAccount(id);
                if (acc == null) {
                    System.out.println("Account not found.");
                    continue;
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Account ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accNum = sc.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    BigDecimal init = new BigDecimal(sc.nextLine());
                    System.out.print("Enter Duration in Months: ");
                    int months = Integer.parseInt(sc.nextLine());
                    Fixed_DepositAccount newAcc = new Fixed_DepositAccount(id, init, months, name, accNum);
                    service.createAccount(newAcc);
                    break;

                case 2:
                	Fixed_DepositAccount depAcc = service.getAccount(id);
                    System.out.print("Enter Deposit Amount: ");
                    BigDecimal depositAmt = new BigDecimal(sc.nextLine());
                    service.deposit(depAcc, depositAmt);
                    break;

                case 3:
                    Fixed_DepositAccount withAcc = service.getAccount(id);
                    System.out.print("Enter Withdrawal Amount: ");
                    BigDecimal withdrawAmt = new BigDecimal(sc.nextLine());
                    service.withdraw(withAcc, withdrawAmt);
                    break;

                case 4:
                    Fixed_DepositAccount acc = service.getAccount(id);
                    service.displayAccount(acc);
                    break;

                case 5:
                    Fixed_DepositAccount intAcc = service.getAccount(id);
                    BigDecimal maturity = intAcc.calculateMaturityAmount();
                    System.out.println(" Maturity Amount: ₹" + maturity);
                    break;
                    

                default:
                    System.out.println("Invalid operation.");
            }
        }
    }

  

    private static void jointMenu(JointAccountService service) 
    {
    	Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Joint Account Operations ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an operation: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 6) break;

            String id = "";
            if (choice != 1) {
                System.out.print("Enter Account ID: ");
                id = sc.nextLine();
                Joint_Account acc = service.getAccount(id);
                if (acc == null) {
                    System.out.println("Account not found.");
                    continue;
                }
            }

            switch (choice) {
                case 1:
                    
                	 System.out.print("Enter Account ID: ");
                     id = sc.nextLine();
                     System.out.print("Enter Primary Holder Name: ");
                     String primary = sc.nextLine();
                     System.out.print("Enter Secondary Holder Name: ");
                     String secondary = sc.nextLine();
                     System.out.print("Enter Account Number: ");
                     String accNum = sc.nextLine();
                     System.out.print("Enter Initial Deposit: ");
                     BigDecimal init;
                     try {
                         init = new BigDecimal(sc.nextLine());
                     } catch (NumberFormatException e) {
                         System.out.println("Invalid amount.");
                         break;
                     }
                     String[] holders = {primary, secondary};
                     service.createAccount(id, holders, accNum, init);
                     break;
                     
                case 2:
                   
                  
                    System.out.print("Enter Deposit Amount: ");
                    try {
                        BigDecimal depositAmount = new BigDecimal(sc.nextLine());
                        service.deposit(id, depositAmount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 3:
                  
                    System.out.print("Enter Withdrawal Amount: ");
                    try {
                        BigDecimal withdrawAmount = new BigDecimal(sc.nextLine());
                        service.withdraw(id, withdrawAmount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                    }
                    break;
                    
                case 4:
                	
                	service.displayAccount(id);
                     break;
                     
                case 5:
                   
                     System.out.print("Enter number of months: ");
                     try {
                         int months = Integer.parseInt(sc.nextLine());
                         System.out.println("Interest: ₹" + service.calculateInterest(id, months));
                     } catch (NumberFormatException e) {
                         System.out.println("Invalid number of months.");
                     }
                     break;


                default:
                    System.out.println("Invalid operation.");
            }
        }
    }




    private static void savingMenu(SavingAccountService service) 
    {
    	Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Saving Account Operations ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an operation: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 6) break;  // Exit the menu if '6' is selected

            System.out.print("Enter Account ID: ");
            String id = sc.nextLine();  // Account ID input

            switch (choice) {
                case 1:  // Create a new account
                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accNum = sc.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    BigDecimal init = new BigDecimal(sc.nextLine());
                    SavingAccount sa = new SavingAccount(id, init, name, accNum);
                    service.createAccount(sa);  // Add the account to the service
                    break;

                case 2:  // Deposit money
                    SavingAccount depAcc = service.getAccount(id);  
                    if (depAcc != null) {
                        System.out.print("Enter Deposit Amount: ");
                        BigDecimal depositAmount = new BigDecimal(sc.nextLine());
                        service.deposit(depAcc, depositAmount);  
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:  // Withdraw money
                    SavingAccount withAcc = service.getAccount(id);  
                    if (withAcc != null) {
                        System.out.print("Enter Withdrawal Amount: ");
                        BigDecimal withdrawAmount = new BigDecimal(sc.nextLine());
                        if (service.withdraw(withAcc, withdrawAmount)) {
                            System.out.println("Withdrawal successful.");
                        } else {
                            System.out.println("Insufficient funds or invalid amount.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:  // Display account details
                    SavingAccount displayAcc = service.getAccount(id);  
                    if (displayAcc != null) {
                        service.displayAccount(displayAcc);  
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:  // Calculate interest
                    SavingAccount interestAcc = service.getAccount(id);  
                    if (interestAcc != null) {
                        System.out.print("Enter months: ");
                        int months = Integer.parseInt(sc.nextLine());
                        BigDecimal interest = service.calculateInterest(interestAcc, months);  
                        System.out.println("Interest: ₹" + interest);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                default:
                    System.out.println("Invalid operation. Please choose a valid option.");
            }
        }
    }

}
