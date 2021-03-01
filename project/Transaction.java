import org.joda.money.Money;
import org.joda.time.*;
import java.io.Serializable;

public class Transaction implements Serializable {

    private String description; //Description of entire transaction
    private DateTime date; //Date on which transaction took place
    private Money amount; //amount of money the transaction occurred for
    private String transactionType; //the type of transaction that took place i.e deposit or withdraw

    Transaction(String transactionType, Money amount, DateTime date){
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
        description = date+" "+transactionType+" of amount "+amount;
    }

    public Money getAmount(){
        return amount;
    }

    public DateTime getDate(){
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
