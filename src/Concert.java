
public class Concert {
	private String name, time, price, date;
	private int seats;

	public Concert() {

	}
	
	public String getName(){
		return name;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getPrice(){
		return price;
	}
	
	public String getDate(){
		return date;
	}
	
	public int getAvailableSeats(){
		return seats;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public void setPrice(String price){
		this.price = price;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setAvailableSeats(int seats){
		this.seats = seats;
	}
}
