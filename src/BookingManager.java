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
	
	private static void addConcert(String name, String time, String loc, String date, int seats) throws SQLException{
		stmt.executeUpdate("INSERT INTO table_name VALUES ("+name+","+time+","+loc+","+date+","+seats+");");
	}
	
	/*public static Concert getConcertFromDatabase(){
		Concert concert;
		stmt.execute("SELECT");
		return concert;
	}*/
	
	
}
