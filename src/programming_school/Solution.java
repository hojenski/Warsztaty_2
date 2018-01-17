package programming_school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {
	
	private int id;
	private String created;
	private String updated;
	private String description;
	
	public Solution(String created, String updated, String description) {
		this.created = created;
		this.updated = updated;
		this.description = description;
		
	}
	
	public Solution() {}

	
	
	public int getId() {
		return id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	//dodawanie rozwiązania do bazy - używamy na obiekcie klasy Solution
	public void saveSolutionToDB(Connection conn) throws SQLException {
		if (this.id == 0) { //jeśli id = 0 to znaczy że tworzymy nowe rozwiązanie
			String sql = "INSERT INTO solution(created, updated, description) VALUES (?, ?, ?)";
			String generatedColumns[] = { "ID" }; //dowiadujemy się jakie było ID ostatniego rzędu
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.created);
			preparedStatement.setString(2, this.updated);
			preparedStatement.setString(3, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}else { //jeśli inne niż zero to uaktualniamy
			String sql = "UPDATE solution SET created = ?, updated = ?, description = ? WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.created);
			preparedStatement.setString(2, this.updated);
			preparedStatement.setString(3, this.description);
			preparedStatement.setInt(4, this.id);
			preparedStatement.executeUpdate();
		}
	}	
		
	//wczytywanie rozwiązania po ID - metoda statyczna więc używamy już na klasie, nie na obiekcie	
	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution WHERE id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getString("created");
			loadedSolution.updated = resultSet.getString("updated");
			loadedSolution.description = resultSet.getString("description");
			return loadedSolution;
		}
		return null;
	}
	
	
	//wczytujemy wszystkie rozwiązania
	static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution"; PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getString("created");
			loadedSolution.updated = resultSet.getString("updated");
			loadedSolution.description = resultSet.getString("description");
			solutions.add(loadedSolution);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	
	//kasujemy rozwiązanie z bazy - musimy użyć na obiekcie z klasy Solution
	public void solutionDelete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM solution WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id=0;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}