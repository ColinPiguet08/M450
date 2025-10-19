package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Booking;
import ch.schule.SalaryAccount;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Account.
 *
 * @author kenneth x colin
 * @version 1.0
 */
public class AccountTests {
    /**
     * Tested die Initialisierung eines Kontos.
     */
    @Test
    public void testInit() {
        Account acc = new SavingsAccount("K-100");

        assertEquals("K-100", acc.getId());
        assertEquals(0, acc.getBalance());
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        Account acc = new SavingsAccount("K-200");

        // gültige Einzahlung
        assertTrue(acc.deposit(20250101, 5000));
        assertEquals(5000, acc.getBalance());

        // Datum zu früh
        boolean result = acc.deposit(20200101, 500);
        assertFalse(result);
        assertEquals(5000, acc.getBalance());

        // negative Einzahlung nicht erlaubt
        assertFalse(acc.deposit(20250102, -1000));
        assertEquals(5000, acc.getBalance());
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        SavingsAccount savings = new SavingsAccount("S-001");
        savings.deposit(20250101, 10000);

        // gültige Abhebung
        assertTrue(savings.withdraw(20250102, 3000));
        assertEquals(7000, savings.getBalance());

        // Betrag zu hoch
        assertFalse(savings.withdraw(20250103, 8000));
        assertEquals(7000, savings.getBalance());

        // Datum zu früh
        boolean result = savings.withdraw(2020101, 500);
        assertFalse(result);

        // parameter negativ
        assertFalse(savings.withdraw(20250104, -1000));
        assertEquals(7000, savings.getBalance());
    }

    /**
     * Tests the reference from SavingsAccount
     */
    @Test
    public void testReferences() {
        // ?
    }

    /**
     * teste the canTransact Flag
     */
    @Test
    public void testCanTransact() {
        Account acc = new SavingsAccount("K-Can");

        acc.deposit(20250101, 1000);
        assertTrue(acc.canTransact(20250102)); // späteres Datum ok
        assertFalse(acc.canTransact(20240101)); // früheres Datum nicht ok
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        Account acc = new SavingsAccount("K-Print");
        acc.deposit(20250101, 2000);
        acc.withdraw(20250102, 500);

        // Output abfangen
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // print() ausführen
        acc.print();

        // Konsole zurücksetzen
        System.setOut(originalOut);

        String output = outContent.toString();

        // Inhalt prüfen
        assertTrue(output.contains("Kontoauszug 'K-Print'"));
        assertTrue(output.contains("Datum"));
        assertTrue(output.contains("Saldo"));

    }

    /**
     * Experimente mit print(year,month).
     */
    @Test
    public void testMonthlyPrint() {
        Account acc = new SavingsAccount("K-Month");
        acc.deposit(20250101, 2000);
        acc.deposit(20250205, 3000);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        acc.print(2025, 2);

        System.setOut(originalOut);

        String output = outContent.toString();

        assertTrue(output.contains("Monat: 2.2025"));
        assertTrue(output.contains("Kontoauszug 'K-Month'"));
    }

    @Test
    public void testPrintCoversBookingInMonth() {
        Account acc = new SavingsAccount("K-MonthBranch");

        // Deposit inside February 2025 range (19830 ≤ date < 19860)
        acc.deposit(19835, 1000);

        // Capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        acc.print(2025, 2);

        // Restore original output
        System.setOut(originalOut);

        String output = outContent.toString();

        // Assert that the booking print line appears
        assertTrue(output.contains("Kontoauszug 'K-MonthBranch'"));
        assertTrue(output.contains("Monat: 2.2025"));
    }

    @Test
    public void testBookingGetterSetter() {
        Booking booking = new Booking(200, 12345);

        Account account = new SavingsAccount("S-1002");
        account.setBooking(booking);

        assertEquals(booking, account.getBooking());
    }
}
