package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author kenneth x colin
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void test()
	{
		PromoYouthSavingsAccount account = new PromoYouthSavingsAccount("Y-1001");

		// Deposit positive amount
		assertTrue(account.deposit(100, 10000)); // 10000 + 1% bonus = 10100
		assertEquals(10100, account.getBalance());

		// Deposit another amount
		assertTrue(account.deposit(101, 5000)); // 5000 + 1% = 5050
		assertEquals(15150, account.getBalance());

		// Deposit negative amount → should fail
		assertFalse(account.deposit(102, -1000));
		assertEquals(15150, account.getBalance()); // balance unchanged
	}
}
