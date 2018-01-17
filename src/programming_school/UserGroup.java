package programming_school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserGroup {
	
	private int id;
	private String groupName;
	
	public UserGroup(String groupName) {
		this.groupName = groupName;
	}
	
	public UserGroup() {};
	
	
	//funkcje do zrobienia: dodawanie i uaktualnianie grupy, wczytywanie grupy po id, kasowanie grupy, listowanie grup
	
	//dodawanie grupy do bazy grup - używamy na obiekcie klasy UserGroup
	public void addGroupToDB(Connection conn) throws SQLException {
		if (this.id == 0) { //jeśli id = 0 to znaczy że tworzymy nową grupę
			String sql = "INSERT INTO user_group(name) VALUES (?)";
			String generatedColumns[] = { "ID" }; //dowiadujemy się jakie było ID ostatniego rzędu
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.groupName);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}else { //jeśli inne niż zero to uaktualniamy
			String sql = "UPDATE user_group SET name = ? WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.groupName);
			preparedStatement.setInt(2, this.id);
			preparedStatement.executeUpdate();
		}
	}
	
	
	
	
	
	
	//wczytywanie grupy po ID - metoda statyczna więc używamy już na klasie, nie na obiekcie	
	static public UserGroup loadGroupById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user_group WHERE id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			UserGroup loadedGroup = new UserGroup();
			loadedGroup.id = resultSet.getInt("id");
			loadedGroup.groupName = resultSet.getString("name"); // groupName to nazwa w klasie, name - kolumna w tabeli
			return loadedGroup;
		}
		return null;
	}
	
	
	
	
	//wczytujemy listę wszystkich grup
	static public UserGroup[] loadAllGroups(Connection conn) throws SQLException {
		ArrayList<UserGroup> groups = new ArrayList<UserGroup>();
		String sql = "SELECT * FROM user_group"; PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			UserGroup loadedGroup = new UserGroup();
			loadedGroup.id = resultSet.getInt("id");
			loadedGroup.groupName = resultSet.getString("name");
			groups.add(loadedGroup);
		}
		UserGroup[] groupArray = new UserGroup[groups.size()];
		groupArray = groups.toArray(groupArray);
		return groupArray;
	}
	
	
	
	
	
	
	
	//kasujemy grupę z bazy - musimy użyć na obiekcie z klasy UserGroup
	public void groupDelete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM user_group WHERE id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id=0;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getId() {   
		return id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	

}