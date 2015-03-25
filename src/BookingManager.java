import java.sql.*;

public class BookingManager {
	private Statement stmt;
	private Connection conn; 
	
	public BookingManager() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword");
			stmt = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createDatabase() throws SQLException{
		System.out.println("Connecting to database...");

		System.out.println("Creating database...");
		stmt = conn.createStatement(); 

		stmt.executeUpdate("CREATE DATABASE Booking");
		stmt.executeUpdate("CREATE TABLE Concerts(name varchar(30), time varchar(8), loc varchar(10), date varchar(10) seats int );");
	}
	
	private void addConcert(String inputName, String inputTime, String inputLoc, String inputDate) throws SQLException{
		stmt = conn.createStatement(); 
		stmt.executeUpdate("INSERT INTO Concerts VALUES ("+inputName+","+inputTime+","+inputLoc+","+inputDate+",100);");		
	}
	
	private boolean checkIfConcertExists(String inputName, String inputTime, String inputDate) throws SQLException{
		stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery("SELECT name, time, date FROM Concerts WHERE name="+inputName+" AND time="+inputTime+" AND date="+inputDate+"");
		if (rs.getRow() != 0) return true;
		return false;
	}
	
	/*
	Statement stmt3 = con.createStatement();
	ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM "+lastTempTable+") AS count");
	    while(rs3.next()){
	    count = rs3.getInt("count");
	    }
	*/

	private int checkSeats() throws SQLException{
		stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery("SELECT seats AS count FROM Concerts");
		int numberOfSeats = rs.getInt("seats");
		return numberOfSeats;
	}

	public String bookConcert(String inputName, String inputTime, String inputLoc, String inputDate) throws SQLException{
		if (!checkIfConcertExists(inputName, inputTime, inputDate)) {
			addConcert(inputName, inputTime, inputLoc, inputDate);
		}
		if (checkSeats() > 0) {
			int numberOfSeats = checkSeats();
			numberOfSeats--;
			stmt = conn.createStatement(); 
			stmt.executeUpdate("UPDATE Concerts SET (seats = "+numberOfSeats+") WHERE name="+inputName+" AND time="+inputTime+";");
			return "Purchase confirmed :)";
		}
		return "No seats available :(";
	}
		
	/*public static Concert getConcertFromDatabase(){
		Concert concert;
		stmt.execute("SELECT");
		return concert;
	}*/
	
}
