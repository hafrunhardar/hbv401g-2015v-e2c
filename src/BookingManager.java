import java.sql.*;

public class BookingManager {
	Connection conn = null;
	Statement stmt = null;
	
	private createDatabase{
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword"); 

		System.out.println("Creating database...");
		stmt = conn.createStatement(); 

		int Result = stmt.executeUpdate("CREATE DATABASE Concerts");
	}
}
