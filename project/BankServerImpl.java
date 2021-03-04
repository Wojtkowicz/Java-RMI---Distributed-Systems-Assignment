import java.math.BigDecimal;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankServerImpl implements BankServer
{
  // constructor is required in RMI
  BankServerImpl() throws RemoteException
  {
    super(); 	// call the parent constructor
  }

  private static List<Account> accounts; // users accounts

  // main is required because the server is standalone
  public static void main(String args[])
  {
    accounts = new ArrayList<>();
    Account account1 = new Account("Bob_Boberson", "password123", new BigDecimal("3000"), 987654321);
    Account account2 = new Account("Tim_Timerson", "password456", new BigDecimal("2000"), 876543219);
    Account account3 = new Account("Jack_Jackson", "password789", new BigDecimal("1500"), 765432198);
    accounts.add(account1);
    accounts.add(account2);
    accounts.add(account3);

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
      registry.rebind("Bank", stub);
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
    Account userAccount = null;

    for (Account account : accounts) {
      if (account.getAccountName().equals(username)) {
        userAccount = account;
        System.out.println("located user's account");
      }
    }

    if(userAccount != null) {
      boolean response = userAccount.verifyUser(username, password);
      if (response) {
        System.out.println("generating session ID");
        long id = userAccount.generateSession();
        return id;
      } else {
        throw new InvalidLogin();
      }
    }
    else {
      throw new InvalidLogin();
    }

  }

  @Override
  public Boolean deposit(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
    Account userAccount = getAccount(accountnum);
    if(verifySession(accountnum, sessionID)){
      System.out.println("verified user successfully!");
      userAccount.depositFunds(amount);
      System.out.println("Saving transaction ...");
      Transaction transaction = new Transaction("Deposit", amount, LocalDateTime.now());
      System.out.println("transaction details: " + transaction.getDescription());
      userAccount.addTransaction(transaction);
      return true;
    }else {
      System.out.println("Invalid user information");
      throw new InvalidSession();
    }
  }

  @Override
  public Boolean withdraw(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession, InsufficientBalance {
    Account userAccount = getAccount(accountnum);
    if(verifySession(accountnum, sessionID)){
      System.out.println("verified user successfully!");
      if(userAccount.getBalance().compareTo(amount) == 1){
        userAccount.withdrawFunds(amount);
        userAccount.addTransaction(new Transaction("Withdraw", amount, LocalDateTime.now()));
      }
      else{
        throw new InsufficientBalance();
      }
      return true;
    }else {
      System.out.println("Invalid user information");
      throw new InvalidSession();
    }
  }

  @Override
  public BigDecimal getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession {
    Account userAccount = getAccount(accountnum);
    if(verifySession(accountnum, sessionID)){
      System.out.println("verified user successfully!");
      return userAccount.getBalance();
    }else {
      System.out.println("Invalid user information");
      throw new InvalidSession();
    }
  }

  @Override
  public Statement getStatement(int accountnum, LocalDate from, LocalDate to, long sessionID) throws RemoteException, InvalidSession {
    Account userAccount = getAccount(accountnum);
    if(verifySession(accountnum, sessionID)){
      System.out.println("verified user successfully!");
      System.out.println("Creating statement ...");
      LocalDateTime dtFrom = from.atStartOfDay();
      LocalDateTime dtTo = to.atStartOfDay();
      Statement statement = new StatementImpl(accountnum, dtFrom, dtTo, userAccount);
      System.out.println(statement.getTransactions().toString());
      return statement;
    }else {
      System.out.println("Invalid user information");
      throw new InvalidSession();
    }
  }

  private Account getAccount(int accountNum){
    Account userAccount = null;
    for (Account account : accounts) {
      if(accountNum == account.getAccountNumber()){
        userAccount = account;
      }
    }
    return userAccount;
  }

  private Boolean verifySession(int accountNum, Long sessionId){
    LocalDateTime currentTimeStamp = LocalDateTime.now();

    System.out.println("current time: " + currentTimeStamp);
    System.out.println("id time: " + getAccount(accountNum).getSessionIdDate());
    System.out.println("passed session id " + sessionId);
    System.out.println("account session id " + getAccount(accountNum).getSessionId());

    return (getAccount(accountNum).getSessionId().equals(sessionId) && currentTimeStamp.isBefore(getAccount(accountNum).getSessionIdDate()));
  }

}
