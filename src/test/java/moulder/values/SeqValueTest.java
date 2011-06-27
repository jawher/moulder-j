package moulder.values;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SeqValueTest {
	private SeqValue<String> values;

	@Before
	public void setUp() {
		values = new SeqValue<String>("Summer", "Autumn", "Winter", "Spring");
	}

	@Test
	public void test() {
		assertEquals("Summer", values.get());
		assertEquals("Autumn", values.get());
		assertEquals("Winter", values.get());
		assertEquals("Spring", values.get());
		assertNull(values.get());
	}

	@Test
	public void testRepeatLast() {
		SeqValue<String> rValues = values.repeatLast();
		assertEquals("Summer", rValues.get());
		assertEquals("Autumn", rValues.get());
		assertEquals("Winter", rValues.get());
		assertEquals("Spring", rValues.get());

		for (int i = 0; i < 100; i++) {
			assertEquals("Spring", rValues.get());
		}
	}
	
	@Test
	public void testCycle() {
		SeqValue<String> rValues = values.cycle();
		
		for (int i = 0; i < 100; i++) {
			assertEquals("Summer", rValues.get());
			assertEquals("Autumn", rValues.get());
			assertEquals("Winter", rValues.get());
			assertEquals("Spring", rValues.get());
		}
	}
}
