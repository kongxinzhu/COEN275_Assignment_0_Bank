public class BankAccount {
	private int accountNumber;
	private int PinNumber;
	private double savingsBalance;
	private double checkingBalance;
	private String accountHolderName;
	private boolean hasCheckingAccount;
	private double updateCheckingBalance;
	private double updateSavingBalance;

	
	// constructor with checking account
	public BankAccount(int accountNumber, int PinNumber, double savingsBalance,
			double checkingBalance, String accountHolderName, boolean hasCheckingAccount) {
		this.accountNumber = accountNumber;
		this.PinNumber = PinNumber;
		this.savingsBalance = savingsBalance;
		this.checkingBalance = checkingBalance;
		this.accountHolderName = accountHolderName;
		this.hasCheckingAccount = hasCheckingAccount;
	}
	
	// constructor without checking account
	public BankAccount(int accountNumber, int PinNumber, double savingsBalance,
			String accountHolderName, boolean hasCheckingAccount) {
		this.accountNumber = accountNumber;
		this.PinNumber = PinNumber;
		this.savingsBalance = savingsBalance;
		this.accountHolderName = accountHolderName;
		this.hasCheckingAccount = hasCheckingAccount;
	}
	
	// get account number
	public int getAccountNumber() {
		return this.accountNumber;
	}
	
	// get account holder's name
	public String getAccountHolderName() {
		return this.accountHolderName;
	}
	
	// get savings balance
	public double getSavingsBalance() {
		return this.savingsBalance;
	}
	
	// get checking balance if there is a checking account
	public double getCheckingsBalance() throws UnsupportedOperationException {
		if(this.hasCheckingAccount == true) {
			return this.checkingBalance;
		} else {
			throw new UnsupportedOperationException("No checking account");
		}
	}
	
	// set checking balance if there is a checking account
	public void setCheckingsBalance(double newBalance) throws UnsupportedOperationException {
		if(this.hasCheckingAccount == true) {
			this.checkingBalance = newBalance;
		} else {
			throw new UnsupportedOperationException("No checking account");
		}
	}
	
	// set savings balance
	public void setSavingsBalance(double newBalance) {
		this.savingsBalance = newBalance;
	}
	
	// to know if there is a checking account
	public boolean hasCheckingAccount() {
		return this.hasCheckingAccount;
	}
	
	// Pin verification
	public boolean checkPinNumber(int target) {
		boolean result = false;
		if(this.PinNumber == target) {
			result = true;
		}
		return result;
	}
	
	// print with the format1
	public String printCSV() throws UnsupportedOperationException {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getAccountNumber()).append(",").append(this.PinNumber).append(",").append(this.getSavingsBalance()).append(",");
		if(this.hasCheckingAccount()) {
			sb.append(this.getCheckingsBalance()).append(",");
		}
		sb.append(this.getAccountHolderName());
		return sb.toString();
	}
}
