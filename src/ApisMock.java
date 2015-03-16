import java.util.*;
public class ApisMock {
	public static ArrayList<Concert> getData(){
		ArrayList<Concert> concerts = new ArrayList<Concert>();
		
		Concert concert1 = new Concert();
		concert1.setName("Amabadama");
		concert1.setTime("20:00");
		concert1.setPrice("4000");
		concert1.setDate("24-03-2015");
		concert1.setAvailableSeats(100);
		
		Concert concert2 = new Concert();
		concert2.setName("Sálin");
		concert2.setTime("21:00");
		concert2.setPrice("6000");
		concert2.setDate("30-03-2015");
		concert2.setAvailableSeats(100);
		
		concerts.add(concert1);
		concerts.add(concert2);

		System.out.println(concerts);
		return concerts;
	}
}
