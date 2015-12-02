package introduction;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilsTest {

	private MathUtils mathUtils = new MathUtils();

	@Test
	public void testGetCommonDivider() {
		assertEquals(3, mathUtils.getGreatestCommonDivider(6, 9));
		assertEquals(5, mathUtils.getGreatestCommonDivider(10, 5));
		assertEquals(6, mathUtils.getGreatestCommonDivider(18, 30));
		assertEquals(0, mathUtils.getGreatestCommonDivider(0, 0));
		assertEquals(1, mathUtils.getGreatestCommonDivider(1, 1));
		assertNotEquals(0, mathUtils.getGreatestCommonDivider(1, 1));
		assertNotEquals(5, mathUtils.getGreatestCommonDivider(5, 1));
	}

	@Test
	public void testGetSumOfDigits() {
		assertEquals(6, mathUtils.getSumOfDigits(123));
		assertEquals(1, mathUtils.getSumOfDigits(10000000));
		assertEquals(0, mathUtils.getSumOfDigits(0));
		assertEquals(10, mathUtils.getSumOfDigits(55));
		assertNotEquals(0, mathUtils.getSumOfDigits(111));
		assertNotEquals(1, mathUtils.getSumOfDigits(101));
	}

	@Test
	public void testIsPrime() {
		assertTrue(mathUtils.isPrime(3));
		assertTrue(mathUtils.isPrime(151));
		assertFalse(mathUtils.isPrime(15));
		assertFalse(mathUtils.isPrime(20));
	}

	@Test
	public void testGetSumOfRow() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFibonacciSeries() {
		assertArrayEquals(new int[] { 1, 1, 2, 3, 5 }, mathUtils.getFibonacciSeries(5));
		assertArrayEquals(new int[] {}, mathUtils.getFibonacciSeries(0));
		assertArrayEquals(new int[] { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233 },
				mathUtils.getFibonacciSeries(13));
	}

	@Test
	public void testGetPrimeSeries() {
		assertArrayEquals(new int[] { 2, 3, 5, 7, 11, }, mathUtils.getPrimeSeries(5));
		assertArrayEquals(new int[] {}, mathUtils.getPrimeSeries(0));
		assertArrayEquals(new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41 }, mathUtils.getPrimeSeries(13));
	}
}
