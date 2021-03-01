import java.rmi.*;

public class ATMClient
{
	public static void main (String args[])
	{
		try {
			String target = (args.length == 0) ? "Ireland" : args[0];
			BankServer cities = (BankServer) Naming.lookup("//localhost/Capitals");
			String capital = cities.getCapital(target);
			System.out.println(capital);
		}
		catch (Exception e) {}
		}
	}
