import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String userName;

    private String password;

    private BigDecimal balance;

    private final Integer accountNumber;

    private List<Transaction> transactions;

    private Long sessionId;

    private LocalDateTime sessionIdDate;

    public Account(String userName, String password, BigDecimal balance, Integer accountNumber){
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.accountNumber = accountNumber;
        transactions = new ArrayList<>();
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

    public void depositFunds(BigDecimal amount){
        balance.add(amount);
    }

    public void withdrawFunds(BigDecimal amount){
        balance.add(amount);
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public boolean verifyUser(String userName, String password){
        if(this.userName.equals(userName) && this.password.equals(password)){
            System.out.println("verified user's password successfully!");
            return true;
        }
        System.out.println("user's password invalid!");
        return false;
    }


    public Integer getAccountNumber(){
        return accountNumber;
    }


    public Long getSessionId() {
        return sessionId;
    }

    public LocalDateTime getSessionIdDate() {
        return sessionIdDate;
    }

    public Long generateSession(){
        System.out.println("generating sessionId...");
        sessionId =  Math.round(Math.random() * 1000000);
        sessionIdDate = LocalDateTime.now().plusMinutes(5);
        System.out.println("generated session ID:" + sessionId);
        return sessionId;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
}
