package programming_school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	
	Connection dbConnection = null;
	
	
	//metoda na nawiązanie polączenia
	public Connection getConnection() {
		try {
			this.dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warsztaty 2?useSSL=false",
					"root",
					"coderslab"
					);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return this.dbConnection;
	}
	
	
	
	//metoda na zamknięcie polączenia
	public void closeConnection() {
		if(this.dbConnection != null) {
			try {
				this.dbConnection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}