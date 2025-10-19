package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Bank;
import ch.schule.Booking;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests f�r die Klasse 'Bank'.
 *
 * @author kenneth x colin
 * @version 1.0
 */
public class BankTests {

    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    /**
     * Tests to create new Accounts
     */
    @Test
    public void testCreate() {
        String savingsId = bank.createSavingsAccount();
        assertTrue(savingsId.startsWith("S-"));
        assertEquals(0, bank.getBalance(savingsId));

        String promoId = bank.createPromoYouthSavingsAccount();
        assertTrue(promoId.startsWith("Y-"));

        String salaryId = bank.createSalaryAccount(-5000);
        assertTrue(salaryId.startsWith("P-"));

        assertNull(bank.createSalaryAccount(1000)); // invalid (positive limit)
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        String id = bank.createSavingsAccount();

        // Successful deposit
        assertTrue(bank.deposit(id, 100, 1000));
        assertEquals(1000, bank.getBalance(id));

        // Deposit to invalid account
        assertFalse(bank.deposit("INVALID", 100, 500));
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        String id = bank.createSavingsAccount();
        bank.deposit(id, 100, 1000);

        // Successful withdraw
        assertTrue(bank.withdraw(id, 101, 500));
        assertEquals(500, bank.getBalance(id));

        // Withdraw more than balance
        assertFalse(bank.withdraw(id, 102, 2000));

        // Invalid account
        assertFalse(bank.withdraw("INVALID", 100, 100));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        String id = bank.createSavingsAccount();
        bank.deposit(id, 100, 500);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        bank.print(id);
        bank.print("UNKNOWN"); // should do nothing safely

        System.setOut(System.out);

        String output = out.toString();
        assertTrue(output.contains(id));
        assertTrue(output.contains("Datum"));
    }

    /**
     * Experimente mit print(year, month).
     */
    @Test
    public void testMonthlyPrint() {
        String id = bank.createSavingsAccount();
        bank.deposit(id, 19800, 1000);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        bank.print(id, 2024, 7);
        bank.print("UNKNOWN", 2024, 7); // should do nothing safely

        System.setOut(System.out);

        String output = out.toString();
        assertTrue(output.contains("Monat"));
        assertTrue(output.contains(id));
    }

    /**
     * Testet den Gesamtkontostand der Bank.
     */
    @Test
    public void testBalance() {
        String id1 = bank.createSavingsAccount();
        String id2 = bank.createPromoYouthSavingsAccount();

        bank.deposit(id1, 100, 1000);
        bank.deposit(id2, 100, 500);

        assertEquals(1000, bank.getBalance(id1));
        assertEquals(505, bank.getBalance(id2)); // 1% bonus

        assertEquals(0, bank.getBalance("INVALID")); // nonexistent

        long totalBalance = bank.getBalance();
        assertEquals(-(1000 + 505), totalBalance);
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testTop5() {
        String[] ids = new String[6];
        for (int i = 0; i < 6; i++) {
            ids[i] = bank.createSavingsAccount();
            bank.deposit(ids[i], 100, i * 100);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        bank.printTop5();

        System.setOut(System.out);

        String output = out.toString();
        assertTrue(output.contains(ids[5]));
        assertFalse(output.contains(ids[0]));
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testBottom5() {
        String[] ids = new String[6];
        for (int i = 0; i < 6; i++) {
            ids[i] = bank.createSavingsAccount();
            bank.deposit(ids[i], 100, i * 100);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        bank.printBottom5();

        System.setOut(System.out);

        String output = out.toString();
        assertTrue(output.contains(ids[0]));
        assertFalse(output.contains(ids[5]));
    }

    @Test
    public void testAccountGetterSetter() {
        Account account = new SavingsAccount("S-1001");

        // setAccount and getAccount
        account.setBooking(new Booking(100, 5000));
        assertNotNull(account.getBooking());

        // Test Bank account getter/setter
        Bank bank = new Bank();
        bank.setAccount(account);
        assertEquals(account, bank.getAccount());
    }
}
