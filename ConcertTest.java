import static org.junit.Assert.*;
import org.junit.*;

public class ConcertTest {
	private Concert concert1, concert2;
	@Before
	public void setUp() {
		concert1 = new Concert("Amabadama", 2000, 4000, 040315, 100);
		concert2 = new Concert("Sálin", 2100, 5000, 030515, 150);
	}
	
	@Test
	public void testName() {
		assertEquals("Amabadama", concert1.getName());
	}
	
	@Test 
	public void testTime() {
		assertEquals(2000, concert1.getTime());
	}
	
	@Test 
	public void testPrice() {
		assertEquals(4000, concert1.getPrice());
	}
	
	@Test 
	public void testDate() {
		assertEquals(030515, concert2.getDate());
	}
	
	@Test 
	public void testSeats() {
		assertEquals(100, concert1.getAvailableSeats());
	}

	@After
	public void tearDown(){
		concert1 = null;
		concert2 = null;
	}
}


