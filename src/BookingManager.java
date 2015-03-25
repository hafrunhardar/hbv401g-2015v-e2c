import java.sql.*;

public class BookingManager {
	private static Connection conn = null;
	private static Statement stmt = null;
	
	private static void createDatabase() throws SQLException{
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword"); 

		System.out.println("Creating database...");
		stmt = conn.createStatement(); 

		stmt.executeUpdate("CREATE DATABASE Booking");
		stmt.executeUpdate("CREATE TABLE Concerts(name varchar(30), time varchar(8), loc varchar(10), date varchar(10) seats int );");
		
		conn.close();
	}
	
	private static void addConcert(String inputName, String inputTime, String inputLoc, String inputDate) throws SQLException{
		stmt.executeUpdate("INSERT INTO Concerts VALUES ("+inputName+","+inputTime+","+inputLoc+","+inputDate+",100);");		
	}
	
	private static boolean checkIfConcertExists(String inputName, String inputTime){
		if (!stmt.execute("SELECT name, time FROM Concerts WHERE name="+inputName+" AND time="+inputTime+"")) return true;
		return false;
	}
	
	/*
	Statement stmt3 = con.createStatement();
	ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM "+lastTempTable+") AS count");
	    while(rs3.next()){
	    count = rs3.getInt("count");
	    }
	*/
	private static int checkSeats(){
		ResultSet rs = stmt.execute("SELECT seats AS count FROM Concerts");
		int numberOfSeats = rs.getInt("count");
		return numberOfSeats;
	}

	public static String bookConcert(String inputName, String inputTime, String inputLoc, String inputDate){
		if (!checkIfConcertExists(inputName, inputTime)) {
			addConcert(inputName, inputTime, inputLoc, inputDate);
		}
		if (checkSeats() > 0) {
			int numberOfSeats = checkSeats();
			numberOfSeats--;
			stmt.executeUpdate("UPDATE Concerts SET (seats = "+seats+") WHERE name="+inputName+" AND time="+inputTime+";");
			return "Confirmed purchase :)";
		}
		return "No seats available :(";
	}
	
	
	/*public static Concert getConcertFromDatabase(){
		Concert concert;
		stmt.execute("SELECT");
		return concert;
	}*/
	
}
