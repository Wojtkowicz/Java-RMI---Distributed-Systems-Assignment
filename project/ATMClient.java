import java.math.BigDecimal;
import java.rmi.*;
import java.time.LocalDate;
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
						try {
							System.out.println("Log in successful!, session ID: " + bank.login(args[1], args[2]) + ", valid for 5 minutes");
						}catch (Exception e){
							System.out.println("Login failed!");
						}
					}
					break;
				case "deposit":
					//DEPOSIT ID ACCOUNTNUMBER  AMOUNT
					if(args[1].length() == 0 || args[2].length() == 0 || args[3].length() == 0){
						System.out.println("Incorrect input for deposit");
					}else{
						System.out.println("Depositing $" + args[3] + " to account " + args[2]);
						try {
							bank.deposit(Integer.parseInt(args[2]), new BigDecimal(args[3]), Long.parseLong(args[1]));
							System.out.println("Deposit Successful!");
						}catch (Exception e){
							System.out.println("Deposit failed!");
						}
					}
					break;
				case "withdraw":
					//WITHDRAW ID ACCOUNTNUMBER AMOUNT
					if(args[1].length() == 0 || args[2].length() == 0 || args[3].length() == 0){
						System.out.println("Incorrect input for withdraw");
					}else{
						System.out.println("Withdrawing $" + args[3] + " to account " + args[2]);
						try{
							bank.withdraw(Integer.parseInt(args[2]), new BigDecimal(args[3]), Long.parseLong(args[1]));
							System.out.println("Withdraw Successful!");
						}catch (Exception e){
							System.out.println("Withdraw failed!");
						}
					}
					break;
				case "balance":
					//BALANCE ID ACCOUNTNUMBER
					if(args[1].length() == 0 || args[2].length() == 0){
						System.out.println("Incorrect input for balance");
					}else{
						try {
							System.out.println("Getting balance for account " + args[2]);
							System.out.println("Balance: $" + bank.getBalance(Integer.parseInt(args[2]), Long.parseLong(args[1])));
						}catch (Exception e){
					        System.out.println("Error getting balance!");
				}
					}
					break;
				case "statement":
					//STATEMENT ID ACCOUNTNUMBER DATESTART DATEEND
					if(args[1].length() == 0 || args[2].length() == 0|| args[3].length() == 0|| args[4].length() == 0){
						System.out.println("Incorrect input for statement");
					}else{
						System.out.println("Getting Statement for account " + args[2]);
						try {
							Statement statement = bank.getStatement(Integer.parseInt(args[2]), LocalDate.parse(args[3]), LocalDate.parse(args[4]), Long.parseLong(args[1]));
							System.out.println("Statement: \n" +
									"\nAccount holder: " + statement.getAccoutName() +
									"\nAccount number: " + statement.getAccountnum() +
									"\nStatement start date: " + statement.getStartDate().toLocalDate() +
									"\nStatement end date: " + statement.getEndDate().toLocalDate() + "\n"
							);
							statement.getTransactions().stream().forEach(t -> {
								System.out.println(t.toString());
							});

						}catch (Exception e){
							System.out.println("Error getting statement!");
						}
					}
					break;
				default:
					System.out.println("Unknown Command Entered");
			}
		}
		catch (Exception e) {}
		}

	}
