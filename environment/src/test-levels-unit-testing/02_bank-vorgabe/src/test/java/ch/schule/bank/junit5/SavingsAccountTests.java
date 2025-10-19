package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse SavingsAccount.
 *
 * @author XXX
 * @version 1.0
 */
public class SavingsAccountTests
{
	@Test
	public void test()
	{
		// Create a new SavingsAccount
		SavingsAccount account = new SavingsAccount("S-1001");

		// Deposit some money
		assertTrue(account.deposit(100, 5000));
		assertEquals(5000, account.getBalance());

		// Withdraw amount less than balance → succeeds
		assertTrue(account.withdraw(101, 3000));
		assertEquals(2000, account.getBalance());

		// Withdraw amount equal to balance → succeeds
		assertTrue(account.withdraw(102, 2000));
		assertEquals(0, account.getBalance());

		// Withdraw amount greater than balance → fails
		assertFalse(account.withdraw(103, 100));
		assertEquals(0, account.getBalance());

		// Withdraw negative amount → fails
		assertFalse(account.withdraw(104, -100));
		assertEquals(0, account.getBalance());
	}
}
