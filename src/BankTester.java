import java.io.IOException;
import java.util.Scanner;

public class BankTester {
	public static void main(String[] args)  {
		try{
			Bank bank1 = new Bank();
			boolean programOn = true;
			Scanner console = new Scanner(System.in);
			String nextLine = null;
			
			while(programOn) {
				System.out.print("Options: \n\t1)Login\n\t2)Save and Quit \nChoice:");
				BankAccount user = null;
				nextLine = console.nextLine();
				
				// if user want to log in
				if(nextLine.equals("1")) {
					user = bank1.getLogIn(console);
					nextLine = console.nextLine();
					if(user != null) {
						bank1.getMenu(user,console);
					} 
					
				} else if(console.nextLine().equals("2")) { 
					// 1 if user want to quit
					programOn = false;
					bank1.writeData();
					console.close();
				}
			}
		} catch (UnsupportedOperationException e) {
			System.out.println("Error: No checking account");
		} catch (IOException i) {
			System.out.println("Error: No file corresponding");
		}
			System.exit(0);
	}
}
