import java.math.BigDecimal;
import java.rmi.*;
import java.time.LocalDateTime;

public class ATMClient
{
	public static void main (String args[])
	{
		try {
			BankServer bank = (BankServer) Naming.lookup("//localhost/Bank");
			switch (args[0]){
				case "login":
					//LOGIN USERNAME PASSWORD
					if(args[1].length() == 0 || args[2].length() == 0){
						System.out.println("Incorrect input for username and password");
					}else{
						System.out.println("Logging in ...");
						System.out.println("Logged in, session ID: " + bank.login(args[1], args[2]));
					}
					break;
				case "deposit":
					//DEPOSIT ID ACCOUNTNUMBER  AMOUNT
					if(args[1].length() == 0 || args[2].length() == 0 || args[3].length() == 0){
						System.out.println("Incorrect input for deposit");
					}else{
						System.out.println("Depositing $" + args[3] + " ...");
						if(bank.deposit(Integer.parseInt(args[2]), new BigDecimal(args[3]), Long.parseLong(args[1]))){
							System.out.println("Deposit Successful!");
						}
						else {
							System.out.println("Deposit failed!");
						}
					}
					break;
				case "withdraw":
					//WITHDRAW ID ACCOUNTNUMBER AMOUNT
					if(args[1].length() == 0 || args[2].length() == 0 || args[3].length() == 0){
						System.out.println("Incorrect input for withdraw");
					}else{
						System.out.println("Withdrawing $" + args[3] + " ...");
						if(bank.withdraw(Integer.parseInt(args[2]), new BigDecimal(args[3]), Long.parseLong(args[1]))){
							System.out.println("Withdraw Successful!");
						}
						else{
							System.out.println("Withdraw failed!");
						}
					}
					break;
				case "balance":
					//BALANCE ID ACCOUNTNUMBER
					if(args[1].length() == 0 || args[2].length() == 0){
						System.out.println("Incorrect input for balance");
					}else{
						System.out.println("Getting balance ... ");
						System.out.println("Balance: " + bank.getBalance(Integer.parseInt(args[2]), Long.parseLong(args[1])));
					}
					break;
				case "statement":
					//STATEMENT ID ACCOUNTNUMBER DATESTART DATEEND
					if(args[1].length() == 0 || args[2].length() == 0|| args[3].length() == 0|| args[4].length() == 0){
						System.out.println("Incorrect input for statement");
					}else{
						System.out.println("Getting Statement ...");
						Statement statement = bank.getStatement(Integer.parseInt(args[2]), LocalDateTime.parse(args[3]), LocalDateTime.parse(args[4]), Long.parseLong(args[1]));
						System.out.println("Statement: " + statement.getTransactions().toString());
					}
					break;
				default:
					System.out.println("Unknown Command Entered");
			}
		}
		catch (Exception e) {}
		}

	}
