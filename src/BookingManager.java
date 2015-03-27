import java.sql.*;

public class BookingManager {
	private Statement stmt;
	private Connection conn; 
	
	// BookingManager creates the database - may only be called once!
	public BookingManager() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Booking.db");
			stmt = null;
			createDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createDatabase() throws SQLException{
		stmt = conn.createStatement(); 
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Concerts(name varchar(100), time varchar(8), loc varchar(50), date varchar(10), seats int);");
	}
	
	private void addConcert(String inputName, String inputTime, String inputLoc, String inputDate) throws SQLException{
		stmt = conn.createStatement(); 	
		stmt.executeUpdate("INSERT INTO Concerts VALUES('"+inputName+"','"+inputTime+"','"+inputLoc+"','"+inputDate+"',100);");
	}
	
	private boolean checkIfConcertExists(String inputName, String inputTime, String inputDate) throws SQLException{
		stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery("SELECT name, time, date FROM Concerts WHERE name='"+inputName+"' AND time='"+inputTime+"' AND date='"+inputDate+"';");
		if (rs.getRow() != 0) return true;
		return false;
	}
	
	private int checkSeats() throws SQLException{
		stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery("SELECT seats AS count FROM Concerts");
		int numberOfSeats = rs.getInt("count");
		return numberOfSeats;
	}

	public Concert bookConcert(Concert concert/*, String inputName, String inputTime, String inputLoc, String inputDate*/) throws SQLException{
		String inputName = concert.getName(); 
		String inputTime = concert.getTime(); 
		String inputLoc  = concert.getLoc(); 
		String inputDate = concert.getLoc();
		
		if (!checkIfConcertExists(inputName, inputTime, inputDate)) {
			addConcert(inputName, inputTime, inputLoc, inputDate);
		}
		if (checkSeats() > 0) {
			int numberOfSeats = checkSeats();
			numberOfSeats--;
			concert.setAvailableSeats(numberOfSeats);
			stmt = conn.createStatement(); 
			stmt.executeUpdate("UPDATE Concerts SET seats="+numberOfSeats+" WHERE name='"+inputName+"' AND time='"+inputTime+"';");
			
			return concert;
		}
		//þarf að breyta. 
		return concert;
	}	
}
