import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class BankServerImpl implements BankServer
{
  // constructor is required in RMI
  BankServerImpl() throws RemoteException
  {
    super(); 	// call the parent constructor
  }

    private List<Account> accounts; // users accounts
  // Remote method we are implementing!
 /* public String getCapital(String country) throws RemoteException
  {
    System.out.println("Sending return string now - country requested: " + country);
    if (country.toLowerCase().compareTo("usa") == 0)
    return "Washington";
    else if (country.toLowerCase().compareTo("ireland") == 0)
    return "Dublin";
    else if (country.toLowerCase().compareTo("france") == 0)
    return "Paris";
    return "Don't know that one!";
  }*/

  // main is required because the server is standalone
  public static void main(String args[])
  {
    try
    {
      // First reset our Security manager
      if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
            System.out.println("Security manager set");
        }

      // Create an instance of the local object
      BankServer bankServer = new BankServerImpl();
      System.out.println("Instance of City Server created");
      BankServer stub = (BankServer) UnicastRemoteObject.exportObject(bankServer, 0);

      // Put the server object into the Registry
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind("Capitals", stub);
      System.out.println("Name rebind completed");
      System.out.println("Server ready for requests!");
    }
    catch(Exception exc)
    {
      System.out.println("Error in main - " + exc.toString());
    }
  }


  @Override
  public long login(String username, String password) throws RemoteException, InvalidLogin {
    return 0;
  }

  @Override
  public void deposit(int accountnum, Money amount, long sessionID) throws RemoteException, InvalidSession {

  }

  @Override
  public void withdraw(int accountnum, Money amount, long sessionID) throws RemoteException, InvalidSession {

  }

  @Override
  public Money getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession {
    return null;
  }

  @Override
  public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
    return null;
  }
}
