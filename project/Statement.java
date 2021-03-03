import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface Statement extends Serializable {
    public int getAccountnum();  // returns account number associated with this statement

    public LocalDateTime getStartDate(); // returns start Date of Statement

    public LocalDateTime getEndDate(); // returns end Date of Statement

    public String getAccoutName(); // returns name of account holder
    
    public List<Transaction> getTransactions(); // return list of transactions included in this statement  
}
