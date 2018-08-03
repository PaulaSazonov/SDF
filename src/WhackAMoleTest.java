import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WhackAMoleTest {
	WhackAMole whackie;

	@Before
	public void setUp() {
		whackie = new WhackAMole(5, 5);
	}

	@Test
	public void testWhackAMole() {
		assertEquals(whackie.getAttemptsLeft(), 5);
		assertEquals(whackie.getMolegrid().length, 5);
	}

	@Test
	public void testPlace() {
		whackie.place(3, 4);
		assertTrue(whackie.getMolegrid()[3][4] == 'M');
		assertTrue(whackie.getMolesLeft() == 1);
	}

	@Test
	public void testWhack() {
		whackie.place(1, 2);
		whackie.whack(1, 2);
		assertEquals(whackie.getMolegrid()[1][2], 'W');
		assertEquals(whackie.getScore(), 1);
	}
}
