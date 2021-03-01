import org.joda.money.Money;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.rmi.*;

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
						bank.login(args[1], args[2]);
					}
					break;
				case "deposit":
					//DEPOSIT ID ACCOUNTNUMBER  AMOUNT
					if(args[1].length() == 0 || args[2].length() == 0 || args[3].length() == 0){
						System.out.println("Incorrect input for deposit");
					}else{
						bank.deposit(Integer.parseInt(args[2]), Money.parse(args[3]), Long.parseLong(args[1]));
					}
					break;
				case "withdraw":
					//WITHDRAW ID ACCOUNTNUMBER AMOUNT
					if(args[1].length() == 0 || args[2].length() == 0 || args[3].length() == 0){
						System.out.println("Incorrect input for withdraw");
					}else{
						bank.withdraw(Integer.parseInt(args[2]), Money.parse(args[3]), Long.parseLong(args[1]));
					}
					break;
				case "balance":
					//BALANCE ID ACCOUNTNUMBER
					if(args[1].length() == 0 || args[2].length() == 0){
						System.out.println("Incorrect input for balance");
					}else{
						bank.getBalance(Integer.parseInt(args[2]), Long.parseLong(args[1]));
					}
					break;
				case "statement":
					//STATEMENT ID ACCOUNTNUMBER DATESTART DATEEND
					if(args[1].length() == 0 || args[2].length() == 0|| args[3].length() == 0|| args[4].length() == 0){
						System.out.println("Incorrect input for statement");
					}else{
						DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yyyy");
						bank.getStatement(format.parseDateTime(args[3]), format.parseDateTime(args[4]), Long.parseLong(args[1]));
					}
					break;
				default:
					System.out.println("Unknown Command Entered");
			}
			//String target = (args.length == 0) ? "Ireland" : args[0];

			//String capital = cities.getCapital(target);
			//System.out.println(capital);
		}
		catch (Exception e) {}
		}

	}
