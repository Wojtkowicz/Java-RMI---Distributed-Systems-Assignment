import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatementImpl implements Statement{

    //account has list of transactions, statement created by bank
    private ArrayList<Transaction> transactions;
    private int accountNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String accountName;
    private Account accountOfStatement;

    public StatementImpl(int accountNumber, LocalDateTime startTime, LocalDateTime endTime, Account accountDetails){
        System.out.println("Constructor start...");
        this.accountNumber = accountNumber;
        this.startDate = startTime;
        this.endDate = endTime;
        accountOfStatement = accountDetails;
        accountName = accountDetails.getAccountName();
        transactions = new ArrayList<>();
        processTransactions();
        System.out.println("Constructor end ...");
    }
    @Override
    public int getAccountnum() {
        return accountNumber;
    }

    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public String getAccoutName() {
        return accountName;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }

    //Gets all transactions from account and keeps only those within dates specified
    private void processTransactions(){
        List<Transaction> AccountTransactions = accountOfStatement.getAllTransactions();
        for(Transaction t: AccountTransactions){
            if(t.getDate().isBefore(endDate) && t.getDate().isAfter(startDate)){
                transactions.add(t);
            }
        }
    }
}
