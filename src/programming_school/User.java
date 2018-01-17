package programming_school;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mindrot.jbcrypt.BCrypt;

public class User {

	private int id;
	private String username;
	private String password;
	private String email;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.setPassword(password);
	}

	public User() {}; // pusty konstruktor
	
	
	

	
	


	//getter dla id
	public int getId() {   
		return this.id;
	}
	
	//setter dla hasla, haszuje je aby bylo gotowe do zapisania w bazie
	public void setPassword(String password) {
		this.password = password;
		}
	
	public String getPassword() {
		return this.password;
	}

	
	//gettery i settery dla username
	public String getUsername() {  
		return this.username;
	}
		
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	//gettery i settery dla email
	public String getEmail() {  
		return email;
	}
		
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	//dodawanie użytkownika do bazy - używamy na obiekcie klasy User
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) { //jeśli id = 0 to znaczy że tworzymy nowego użytkownika
			String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
			String generatedColumns[] = { "ID" }; //dowiadujemy się jakie było ID ostatniego rzędu
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}else { //jeśli inne niż zero to uaktualniamy
			String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.setInt(4, this.id);
			preparedStatement.executeUpdate();
		}
	}	
		
	//wczytywanie użytkownika po ID - metoda statyczna więc używamy już na klasie, nie na obiekcie	
	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM users WHERE id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			return loadedUser;
		}
		return null;
	}
	
	
	//wczytujemy wszystkich użytkowników
	static public User[] loadAllUsers(Connection conn) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users"; PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			users.add(loadedUser);
		}
		User[] uArray = new User[users.size()];
		uArray = users.toArray(uArray);
		return uArray;
	}
	
	
	//kasujemy użytkownika z bazy - musimy użyć na obiekcie z klasy User
	public void userDelete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM users WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id=0;
		}
	}
	
	
	
	
	

}