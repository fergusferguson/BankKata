import java.util.ArrayList;

public class BankAccount {

    public int startingBalance;

    public ArrayList<Transaction> accountTransactions = new ArrayList<Transaction>();

    public BankAccount() { this.startingBalance = 0; }

    public BankAccount(int startingBalance) {
        this.startingBalance = startingBalance;
    }

    public void printStatement() {

        int runningBalance = startingBalance;

        System.out.println("DATE|AMOUNT|BALANCE");
        for (Transaction trans : accountTransactions) {
            runningBalance += trans.getTransactionAmount();
            System.out.println(trans.getTransactionDate() + "|" + trans.getTransactionAmount() + "|" + runningBalance);
        }
    }

    public void deposit(int depositAmount) {
        if (isNotPositive(depositAmount)) {
            throw new IllegalArgumentException("You must deposit a positive value!");
        }
        Transaction transaction = new Transaction("17/01/2017", depositAmount);
        accountTransactions.add(transaction);
    }

    private boolean isNotPositive(int amount) {
        return amount <= 0;
    }

    public void withdraw(int withdrawAmount) {

        if (isNotPositive(withdrawAmount)) {
            throw new IllegalArgumentException("You must withdraw a positive value!");
        }
        withdrawAmount = withdrawAmount * -1;
        Transaction transaction = new Transaction("17/01/2017", withdrawAmount);
        accountTransactions.add(transaction);
    }

}
