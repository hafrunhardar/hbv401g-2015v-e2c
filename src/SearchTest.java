import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

public class SearchTest {
	private Search s1;
	private ArrayList<Concert> concerts;

	@Before
	public void setUp() {
		s1 = new Search();
		concerts = new ArrayList<Concert>();
	}

	@Test
	public void testFilteredByName() {
		concerts = s1.getApisData();
		concerts = s1.getFilteredData("Amabadama", "", "", "");
		assertEquals(concerts.get(0).getName(), "Amabadama");
		assertEquals(concerts.get(0).getTime(), "20:00");
		assertEquals(concerts.get(0).getPrice(), "4000");
		assertEquals(concerts.get(0).getDate(), "24-03-2015");	
	}
	
	@Test
	public void testFilteredByTime() {
		concerts = s1.getApisData();
		concerts = s1.getFilteredData("", "20:00", "", "");
		assertEquals(concerts.get(0).getName(), "Amabadama");
		assertEquals(concerts.get(0).getTime(), "20:00");
		assertEquals(concerts.get(0).getPrice(), "4000");
		assertEquals(concerts.get(0).getDate(), "24-03-2015");
	}
	
	@Test
	public void testFilteredByPrice() {
		concerts = s1.getApisData();
		concerts = s1.getFilteredData("", "", "6000", "");
		assertEquals(concerts.get(0).getName(), "Sálin");
		assertEquals(concerts.get(0).getTime(), "21:00");
		assertEquals(concerts.get(0).getPrice(), "6000");
		assertEquals(concerts.get(0).getDate(), "30-03-2015");
	}
	
	@Test
	public void testFilteredByDate() {
		concerts = s1.getApisData();
		concerts = s1.getFilteredData("", "", "", "30-03-2015");
		assertEquals(concerts.get(0).getName(), "Sálin");
		assertEquals(concerts.get(0).getTime(), "21:00");
		assertEquals(concerts.get(0).getPrice(), "6000");
		assertEquals(concerts.get(0).getDate(), "30-03-2015");
	}
	
	@Test
	public void testNoConcert() {
		concerts = s1.getApisData();
		concerts = s1.getFilteredData("", "", "", "28-03-2015");
		assertEquals(concerts.size(), 0);
	}
	
	@Test
	public void testApisData() {
		concerts = s1.getApisData();
		assertEquals(concerts.get(0).getName(), "Amabadama");
	}
	
	@After
	public void tearDown(){
		s1 = null;
		concerts = null;
	}

}
