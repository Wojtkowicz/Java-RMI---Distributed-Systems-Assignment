import org.joda.money.Money;

import java.util.List;

public class Account {

    private String userName;

    private String password;

    private Money balance;

    private final Integer accountNumber;

    private List<Transaction> transactions;

    public Account(String userName, String password, Money balance, Integer accountNumber){
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return userName;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void saveTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void depositFunds(Money amount){
        balance = balance.plus(amount);
    }

    public void withdrawFunds(Money amount){
        balance = balance.minus(amount);
    }

    public Money getBalance(){
        return balance;
    }

    public boolean verifyUser(String userName, String password){
        if(this.userName.equals(userName) && this.password.equals(password)){
            return true;
        }
        return false;
    }

    public Integer getAccountNumber(){
        return accountNumber;
    }

}
