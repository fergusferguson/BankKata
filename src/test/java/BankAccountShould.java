import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.rules.ExpectedException.none;


public class BankAccountShould {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public ExpectedException thrown = none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void print_only_headers_when_no_transactions() {
        BankAccount bankAccount = new BankAccount();

        bankAccount.printStatement();

        assertEquals("DATE|AMOUNT|BALANCE\r\n", outContent.toString());
    }

    @Test
    public void increment_balance_by_10_when_deposit_10() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit("17/01/2017", 10);

        bankAccount.printStatement();

        assertEquals("DATE|AMOUNT|BALANCE\r\n17/01/2017|10|10\r\n", outContent.toString());
    }

    @Test
    public void reduce_balance_by_10_when_withdraw_10() {
        BankAccount bankAccount = new BankAccount(10);
        bankAccount.withdraw("17/01/2017", 10);

        bankAccount.printStatement();

        assertEquals("DATE|AMOUNT|BALANCE\r\n17/01/2017|-10|0\r\n", outContent.toString());
    }

    @Test
    public void throw_exception_when_deposit_is_zero_or_less() {

        BankAccount bankAccount = new BankAccount();

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("You must deposit a positive value!");

        bankAccount.deposit("17/01/2017", -10);
    }

    @Test
    public void throw_exception_when_withdrawal_is_zero_or_less() {
        BankAccount bankAccount = new BankAccount(10);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("You must withdraw a positive value!");

        bankAccount.withdraw("17/01/2017", -10);
    }

    @Test
    public void print_multiple_deposit_transactions_on_separate_lines() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit("17/01/2017", 20);
        bankAccount.deposit("17/01/2017", 30);

        bankAccount.printStatement();

        assertEquals("DATE|AMOUNT|BALANCE\r\n17/01/2017|20|20\r\n17/01/2017|30|50\r\n", outContent.toString());
    }

    @Test
    public void print_mix_of_deposit_and_withdrawal_transactions_on_separate_lines() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit("17/01/2017", 20);
        bankAccount.withdraw("17/01/2017", 10);

        bankAccount.printStatement();

        assertEquals("DATE|AMOUNT|BALANCE\r\n17/01/2017|20|20\r\n17/01/2017|-10|10\r\n", outContent.toString());
    }

}
