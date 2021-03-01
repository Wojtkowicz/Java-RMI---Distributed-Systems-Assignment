// Remote Object has a single method that is passed
// the name of a country and returns the capital city.
import org.joda.money.Money;
import org.joda.time.*;
import java.rmi.*;

public interface BankServer extends Remote
{

  public long login(String username, String password) throws RemoteException, InvalidLogin;

  public void deposit(int accountnum, Money amount, long sessionID) throws RemoteException, InvalidSession;
  
  public void withdraw(int accountnum, Money amount, long sessionID) throws RemoteException, InvalidSession;
  
  public Money getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession;

  public Statement getStatement(DateTime from, DateTime to, long sessionID) throws RemoteException, InvalidSession;

}
