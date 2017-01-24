import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	private static ArrayList<BankAccount> listOfBankAccount;
	private final String locationOfBankDatabaseFile = "resources/bankDB.csv";
	public Bank() throws FileNotFoundException {
		listOfBankAccount = new ArrayList<BankAccount>();
		getList();
	}
	
	public BankAccount getLogIn(Scanner console) {
		boolean logIn = false;
		int testCounter = 0;
		System.out.print("Enter in Bank Account Number:");
		BankAccount user = checkAccountNumber(console.nextInt());
		if(user == null) {
			System.out.println("Can not find this account\n");
		}
		while(user != null && testCounter < 3 && logIn == false) {
		
			// let user enter password
			System.out.print("Enter in Pin:");
			
			// if user enter correctly password
			if(user.checkPinNumber(console.nextInt())) {
				logIn = true;
			} else { 
				// if user didn't enter password correctly
				testCounter++;
				if(testCounter >= 3) {
					user = null;
					break;
				}
			}
		}
		return user;
	}
	
	public void getMenu(BankAccount user, Scanner console) throws UnsupportedOperationException {
		if(user.hasCheckingAccount() == true) {
			System.out.println("Hello " + user.getAccountHolderName() + ", which account would you like to access:\n");
			System.out.println("1)Checkings\n2)Savings");
			System.out.print("Choice:");
			int account = console.nextInt();
			if( account == 1) {
				System.out.printf("Your Checkings account has a balance of $%.2f%n", user.getCheckingsBalance());
				goProceed(user, account, console);
			} else if(account == 2) {
				System.out.printf("Your Savings account has a balance of $%.2f%n", user.getSavingsBalance());
				goProceed(user, account, console);
			}
		} else {
			System.out.println("Hello " + user.getAccountHolderName() +  ",");
			System.out.printf("Your Savings account has a balance of $%.2f%n", user.getSavingsBalance());
			goProceed(user, 2, console);
		}
		System.out.println("");
	}
	
	public void writeData() throws IOException {
		FileWriter writer  = new FileWriter(new File(locationOfBankDatabaseFile));
		StringBuilder line = new StringBuilder();
		for(BankAccount bankAccount : listOfBankAccount) {
			line.append(bankAccount.printCSV()).append("\n");
		
		}
		writer.write(line.toString());
		writer.close();
	}
	
	public void goProceed(BankAccount user,int account, Scanner console) throws UnsupportedOperationException {
		System.out.println("How would you like to proceed?");
		System.out.println("(Enter in positive number to deposit, negative numer to withdraw)");
		System.out.print("Amount:");
		double newBalance = 0;
		if(account == 1) {
			newBalance += user.getCheckingsBalance() + console.nextInt();
			user.setCheckingsBalance(newBalance);
			System.out.printf("Thank you, your new Checkings balance is $%.2f", newBalance);
		} else if(account == 2){
			newBalance += user.getSavingsBalance() + console.nextInt();
			user.setSavingsBalance(newBalance);
			System.out.printf("Thank you, your new Savings balance is $%.2f", newBalance);
		}
	}
	
	public BankAccount checkAccountNumber(int targetAccountNumber) {
		BankAccount user = null;
		for(BankAccount bankAccount : listOfBankAccount) {
			if(bankAccount.getAccountNumber() == targetAccountNumber) {
				return bankAccount;
			} 
		}
		return user;
	}
	
	public void getList() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(locationOfBankDatabaseFile));
		String line = null;
        while(scanner.hasNext()){
        	line = scanner.nextLine();
        	listOfBankAccount.add(getBankAccount(line));
        }
        scanner.close();
	}
	
	
	public static BankAccount getBankAccount(String line) {
		String[] informations = line.split(",");
		boolean hasCheckingAccount = false;
		int accountNumber = Integer.parseInt(informations[0]);
		int PinNumber = Integer.parseInt(informations[1]);
		double savingsBalance = Double.parseDouble(informations[2]);
		double checkingBalance = 0;
		String accountHolderName = null;
		BankAccount bankAccount = null;
        if(informations.length == 5) {
        	hasCheckingAccount = true;
        	checkingBalance = Double.parseDouble(informations[3]);
        	accountHolderName = informations[4];
        	bankAccount = new BankAccount(accountNumber, PinNumber, savingsBalance,
        			checkingBalance, accountHolderName, hasCheckingAccount);
        } else {
        	accountHolderName = informations[3];
        	bankAccount = new BankAccount(accountNumber, PinNumber, savingsBalance, accountHolderName, hasCheckingAccount);
        }
		return bankAccount;
	}
}
