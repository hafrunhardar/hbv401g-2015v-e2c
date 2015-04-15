import java.sql.*;

public class BookingManager {
	private static BookingManager INSTANCE = createBookingManager();
	private Statement stmt;
	private Connection conn; 
	
	
		
	// BookingManager creates the database - may only be called once!
	private BookingManager() throws DataNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new DataNotFoundException("Unable to connect to database");
			
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Booking.db");
			stmt = null;
			createDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Unable to connect to database");
		}
	}
	
	private static BookingManager createBookingManager() {
		try{
			INSTANCE = new BookingManager();
		} catch (final DataNotFoundException e){
			
		}
		return INSTANCE;
	}

	private void createDatabase() throws DataNotFoundException{
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Unable to connect to database");
		} 
		try {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Concerts(name varchar(100), time varchar(8), loc varchar(50), date varchar(10), seats int);");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Could not create table 'Concerts'");
		}
	}
	
	/*private void destroyTable() throws SQLException{
		stmt = conn.createStatement(); 
		stmt.executeUpdate("DROP TABLE Concerts");
	}*/
	
	private void addConcert(Concert concert) throws DataNotFoundException{
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Unable to connect to database");
		} 	
		try {
			stmt.executeUpdate("INSERT INTO Concerts VALUES('"+concert.getName()+"','"+concert.getTime()+"','"+concert.getLoc()+"','"+concert.getDate()+"',100);");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Unable to add Concert to database");
		}
	}
	
	private boolean checkIfConcertExists(Concert concert) throws DataNotFoundException{
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Unable to connect to database");
		} 
		int count = 0;
		ResultSet rs;
		try {
			rs = stmt.executeQuery("SELECT * FROM Concerts WHERE name='"+concert.getName()+"' AND time='"+concert.getTime()+"' AND date='"+concert.getDate()+"';");
			while(rs.next()){
				System.out.println(rs.getString("name")+" - ég er til");
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Error in executing query, checking if concert exists");
		}
		if(count>0) return true;
		return false;
	}

	public Boolean bookConcert(Concert concert) throws DataNotFoundException{
		if (!checkIfConcertExists(concert)) {
			System.out.println("ég er ekki til..");
			addConcert(concert);
		}
		int numberOfSeats = -1;
		try{
			ResultSet rs = stmt.executeQuery("SELECT seats FROM Concerts WHERE name='"+concert.getName()+"' AND time='"+concert.getTime()+"' AND date='"+concert.getDate()+"';");
			while(rs.next()){
				numberOfSeats = rs.getInt("seats");
			}
			
			if (numberOfSeats > 0) {
				numberOfSeats--;
				concert.setAvailableSeats(numberOfSeats);
				stmt = conn.createStatement(); 
				stmt.executeUpdate("UPDATE Concerts SET seats="+numberOfSeats+" WHERE name='"+concert.getName()+"' AND time='"+concert.getTime()+"';");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataNotFoundException("Error in executing query, checking if concert exists");
		}
		 return false;
	}

	public static BookingManager getInstance() {
		return INSTANCE;
	}	
}
