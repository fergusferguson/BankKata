import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BankAccount {

    public int startingBalance;

    public ArrayList<Transaction> accountTransactions = new ArrayList<Transaction>();

    public BankAccount() {
        this.startingBalance = 0;
    }

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

    public void deposit(String depositDate, int depositAmount) {
        if (!isPositive(depositAmount)) {
            throw new IllegalArgumentException("You must deposit a positive value!");
        }
        Transaction transaction = new Transaction(depositDate, depositAmount);
        accountTransactions.add(transaction);
    }

    private boolean isPositive(int amount) {
        return amount > 0;
    }

    public void withdraw(String withdrawalDate, int withdrawAmount) {

        if (!isPositive(withdrawAmount)) {
            throw new IllegalArgumentException("You must withdraw a positive value!");
        }
        withdrawAmount = withdrawAmount * -1;
        Transaction transaction = new Transaction(withdrawalDate, withdrawAmount);
        accountTransactions.add(transaction);
    }

    public Date convertToDate(String stringDate) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateWithdrawalDate = null;

        try {
            dateWithdrawalDate = formatter.parse(stringDate);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return dateWithdrawalDate;
    }


}
