package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Booking.
 *
 * @author kenneth x colin
 * @version 1.1
 */
public class BookingTests
{
	/**
	 * Tests f�r die Erzeugung von Buchungen.
	 */
	@Test
	public void testInitialization()
	{
		Booking booking = new Booking(19835, 5000);

		assertEquals(19835, booking.getDate());
		assertEquals(5000, booking.getAmount());
	}

	/**
	 * Experimente mit print().
	 */
	@Test
	public void testPrint()
	{
		Booking booking = new Booking(19835, 5000);

		// Capture console output
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream original = System.out;
		System.setOut(new PrintStream(out));

		booking.print(10000);

		System.setOut(original);

		String output = out.toString().trim();

		// Expected formatted output
		String expected = "06.02.2025       0.05       0.15";

		assertEquals(expected, output);
	}
}
