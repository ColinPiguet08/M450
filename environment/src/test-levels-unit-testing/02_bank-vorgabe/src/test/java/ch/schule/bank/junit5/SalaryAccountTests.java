package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests der Klasse SalaryAccount.
 *
 * @author kenneth x colin
 * @version 1.1
 */
public class SalaryAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void test()
	{
		// Create SalaryAccount with credit limit -5000
		SalaryAccount account = new SalaryAccount("P-1001", -5000);

		// Deposit some money
		assertTrue(account.deposit(100, 2000));
		assertEquals(2000, account.getBalance());

		// Withdraw within limit
		assertTrue(account.withdraw(101, 6000)); // 2000 - 6000 = -4000 >= -5000
		assertEquals(-4000, account.getBalance());

		// Withdraw exceeding credit limit
		assertFalse(account.withdraw(102, 2000)); // -4000 - 2000 = -6000 < -5000
		assertEquals(-4000, account.getBalance()); // balance unchanged

		// Withdraw negative amount → fails
		assertFalse(account.withdraw(103, -100));
		assertEquals(-4000, account.getBalance());

		// Deposit another positive amount
		assertTrue(account.deposit(104, 1000));
		assertEquals(-3000, account.getBalance());
	}
}
