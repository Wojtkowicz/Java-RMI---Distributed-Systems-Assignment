import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String description; //Description of entire transaction
    private LocalDateTime date; //Date on which transaction took place
    private BigDecimal amount; //amount of money the transaction occurred for
    private String transactionType; //the type of transaction that took place i.e deposit or withdraw

    Transaction(String transactionType,  BigDecimal amount, LocalDateTime date){
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
        description = date.toLocalDate() +" "+transactionType+" of amount $"+amount;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public String getDescription(){
        return description;
    }

    public String getTransactionType(){
        return transactionType;
    }

    @Override
    public String toString(){
        return description;
    }
}
