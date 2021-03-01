// Remote Object has a single method that is passed
// the name of a country and returns the capital city.
import java.rmi.*;

public interface BankServer extends Remote
{
  String getCapital(String Country) throws RemoteException;
}
