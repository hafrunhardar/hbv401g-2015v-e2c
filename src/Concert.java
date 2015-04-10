
public class Concert {
	private String name, time, loc, date;
	private int seats = 100, price = 5000;
	
// Getters
	public String getName(){
		return name;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getLoc(){
		return loc;
	}
	
	public String getDate(){
		return date;
	}
	
	public int getAvailableSeats(){
		return seats;
	}
	
	public int getPrice(){
		return price;
	}

	
// Setters
	public void setName(String name){
		this.name = name;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public void setLoc(String loc){
		this.loc = loc;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setAvailableSeats(int seats){
		this.seats = seats;
	}

}
