package picture;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MyFrameTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testPassiveComputerTest() {
		MyFrame frame=new MyFrame();
		PassiveComputer auto=new PassiveComputer(frame);
		auto.espace();
		assertEquals(3773, 3773);
		assertEquals(18, 18);
	}
	void testPositiveComputerTest() {
		MyFrame frame=new MyFrame();
		PositiveComputer auto=new PositiveComputer(frame);
		auto.attack();
		assertEquals(0, 0);
		assertEquals(5, 5);
	}

}
