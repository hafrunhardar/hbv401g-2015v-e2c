
public class Concert {
	private String name;
	private int time, price, date, seats;

	public Concert(String name, int time, int price, int date, int seats) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.time = time;
		this.price = price;
		this.date = date;
		this.seats = seats;
	}
	
	public String getName(){
		return name;
	}
	
	public int getTime(){
		return time;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getDate(){
		return date;
	}
	
	public int getAvailableSeats(){
		return seats;
	}
}
