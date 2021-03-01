import java.io.Serializable;

public class Transaction implements Serializable {
    // Needs some accessor methods to return information about the transaction
    public Money getAmount();

    public Date getDate();

    public String description;

}
