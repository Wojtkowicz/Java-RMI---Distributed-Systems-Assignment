import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class StatementImpl implements Statement{

    //account has list of transactions, statement created by bank
    private ArrayList<Transaction> transactions;
    private int accountNumber;
    private DateTime startDate;
    private DateTime endDate;
    private String accountName;
    private Account accountOfStatement;

    public StatementImpl(int accountNumber, DateTime startTime, DateTime endTime, Account accountDetails){
        this.accountNumber = accountNumber;
        this.startDate = startTime;
        this.endDate = endTime;
        accountOfStatement = accountDetails;
        accountName = accountDetails.getAccountName();
        processTransactions();
    }
    @Override
    public int getAccountnum() {
        return accountNumber;
    }

    @Override
    public DateTime getStartDate() {
        return startDate;
    }

    @Override
    public DateTime getEndDate() {
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
