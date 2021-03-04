// Remote Object has a single method that is passed
// the name of a country and returns the capital city.

import java.math.BigDecimal;
import java.rmi.*;
import java.time.LocalDate;

public interface BankServer extends Remote
{

  public long login(String username, String password) throws RemoteException, InvalidLogin;

  public Boolean deposit(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession;
  
  public Boolean withdraw(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession, InsufficientBalance;
  
  public BigDecimal getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession;

  public Statement getStatement(int accountnum, LocalDate from, LocalDate to, long sessionID) throws RemoteException, InvalidSession;

}
