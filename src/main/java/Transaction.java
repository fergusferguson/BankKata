
public class Transaction {

    private String transactionDate;
    private int transactionAmount;

    public Transaction(String transactionDate, int transactionAmount) {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }


    public int getTransactionAmount() {
        return transactionAmount;
    }
}
