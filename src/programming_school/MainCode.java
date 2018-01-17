package programming_school;

import java.sql.SQLException;
import java.util.Scanner;

public class MainCode {

	public static void main(String[] args) {
		
		
		//sprawdzamy działanie metod z klasy User
		
		
		
	
 //dodajemy nowego użytkownika metodą saveToDb z klasy User - działa
		
		User user1 = new User();
		
		System.out.print("Podaj nazwę użytkownika. ");
		String username = getConsoleString();
		
		System.out.print("Podaj email. ");
		String email = getConsoleString();
		
		System.out.print("Podaj hasło. ");
		String password = getConsoleString();
		
		user1.setUsername(username);
		user1.setEmail(email);
		user1.setPassword(password);
		
		//tworzymy polaczenie
		dbConnection connection = new dbConnection();
		
		//wywołujemy odpowiednią metodę
		try {
			user1.saveToDB(connection.getConnection());
			System.out.println("Użytkownika pomyślnie dodano do bazy danych.");
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		//zamykamy połączenie
		connection.closeConnection();
		
		
//koniec dodawania nowego użytkownika 	
		
		
		
		
		
		
		
		
		
		
		
	/*	 //Użytkownik wprowadza z klawiatury którego usera z bazy chce podejrzeć, wczytujemy go i wypisujemy na ekran - działa
		
		User user2 = new User();
		System.out.print("Podaj ID użytkownika którego chcesz wyświetlić. ");
		int userid = getNumberFromConsole();
		// tworzymy polaczenie
		dbConnection connection = new dbConnection();
		try {
			user2 = User.loadUserById(connection.getConnection(), userid);
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// zamykamy połączenie
		connection.closeConnection();
		if (user2 != null) { // nulla dostajemy gdy user o podanym id nie istnieje
			// wypisujemy na ekran pobrane dane
			System.out.println(printUserData(user2));
		} else {
			System.out.println("Użytkownik o wybranym ID nie istnieje.");
		}
		
		//zamykamy połączenie
		connection.closeConnection();
		
// koniec wypisywania użytkownika o podanym ID*/	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/* // wczytujemy i wypisujemy wszystkich użytkowników z bazy - działa
		
		User[] userList = null;
		
		//tworzymy polaczenie
		dbConnection connection = new dbConnection();
		
		try {
			userList = User.loadAllUsers(connection.getConnection());
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (userList.length > 0) {
			for (User user : userList) {
				System.out.println(printUserData(user));
			}
		} else {
			System.out.println("Baza użytkowników jest pusta.");
		}
			
				
		//zamykamy połączenie
		connection.closeConnection();
		
	//koniec wczytywania i wypisywania wszystkich użytkowników z bazy */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*		//usuwanie użytkownika z bazy
		User userToDelete = new User();
		System.out.print("Podaj ID użytkownika którego chcesz usunąć. ");
		int userid = getNumberFromConsole();
		// tworzymy polaczenie
		dbConnection connection = new dbConnection();
		try {
			userToDelete = User.loadUserById(connection.getConnection(), userid);
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// kasujemy użytkownika o podanym id
		if (userToDelete != null) {
			try {
				userToDelete.userDelete(connection.getConnection());
				System.out.println("Użytkownika usunięto pomyślnie.");
			} catch (Exception e) {
				System.out.println("Wystąpił problem z bazą danych - użytkownik nie istnieje? ");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Użytkownik o podanym ID nie istnieje.");
		}
		// zamykamy połączenie
		connection.closeConnection();
	
	// koniec kasowania użytkownika z bazy */
	
	
	
	
	
		
		
		
		
		
 /*modyfikowanie istniejącego użytkownika - działa		
		
	
	//modyfikujemy istniejącego użytkownika. w tym celu należy go wczytać, wyświetlić dane na ekranie aby było wiadomo co można zmienić
	//pobrać od użytkownika nowe dane, ustawić je na obiekcie setterami i zapisać
	
	
		//Użytkownik wprowadza z klawiatury którego usera z bazy chce podejrzeć, wczytujemy go i wypisujemy na ekran - działa
		
		User userToChange = new User();
		
		System.out.print("Podaj ID użytkownika którego chcesz zmienić. ");
		int userid = getNumberFromConsole();
		
		//tworzymy polaczenie
		dbConnection connection = new dbConnection();		
		
		try {
			userToChange = User.loadUserById(connection.getConnection(), userid);
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. Użytkownik nie istnieje?");
			System.out.println(e.getMessage());
			e.printStackTrace();
			//zamykamy połączenie
			connection.closeConnection();
		}
		
		
		if (userToChange != null) { // działamy tylko jeśli użytkownik o podanym id istnieje
			// pobieramy dane i wypisujemy je na ekran
			System.out.println(printUserData(userToChange));
			String newUserName, newUseEmail;
			System.out.println("\nPodaj nowe dane użytkownika. Podaj imię użytkownika: ");
			newUserName = getConsoleString();
			userToChange.setUsername(newUserName);
			System.out.println("\nPodaj email użytkownika: "); // email musi być unikatowy, trzeba później dorobić sprawdzanie
			newUseEmail = getConsoleString();
			userToChange.setEmail(newUseEmail);
			// zapisujemy zmodyfikowane dane
			try {
				userToChange.saveToDB(connection.getConnection());
				System.out.println("\nDane użytkownika pomyślnie zmieniono.");
			} catch (SQLException e) {
				System.out.println(
						"Wystąpił problem z bazą, danych użytkownika nie zmieniono. Może taki email już istnieje (musi być unikatowy)?");
				System.out.println(e.getMessage());
			}
			// zamykamy połączenie
			connection.closeConnection();
		} else {
			System.out.println("Użytkownik o wybranym ID nie istnieje.");
			// zamykamy połączenie
			connection.closeConnection();
		}
	
// koniec modyfikowania użytkownika */	
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//sprawdzamy działanie metod z klasy UserGroup
		
		
		


		
/*		//Użytkownik wprowadza z klawiatury którą grupę z bazy chce podejrzeć, wczytujemy ją i wypisujemy na ekran - działa
		
		UserGroup group2 = new UserGroup();
		System.out.print("Podaj ID grupy którą chcesz wyświetlić. ");
		int userid = getNumberFromConsole();
		// tworzymy polaczenie
		dbConnection connection = new dbConnection();
		try {
			group2 = UserGroup.loadGroupById(connection.getConnection(), userid);
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// zamykamy połączenie
		connection.closeConnection();
		if (group2 != null) { // nulla dostajemy gdy grupa o podanym id nie istnieje
			// wypisujemy na ekran pobrane dane
			System.out.println(printGroupData(group2));
		} else {
			System.out.println("Grupa o wybranym ID nie istnieje.");
		}
		
		//zamykamy połączenie
		connection.closeConnection();
		
		// koniec wypisywania użytkownika o podanym ID */	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*		// wczytujemy i wypisujemy wszystkie grupy z bazy - działa
		
		UserGroup[] groupList = null;
		
		//tworzymy polaczenie
		dbConnection connection = new dbConnection();
		
		try {
			groupList = UserGroup.loadAllGroups(connection.getConnection());
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (groupList.length > 0) {
			for (UserGroup group : groupList) {
				System.out.println(printGroupData(group));
			}
		} else {
			System.out.println("Baza grup jest pusta.");
		}
			
				
		//zamykamy połączenie
		connection.closeConnection();
		
	//koniec wczytywania i wypisywania wszystkich grup z bazy */
		
		
		
		
		
		
		
		
/*		//usuwanie grupy z bazy
		UserGroup groupToDelete = new UserGroup();
		System.out.print("Podaj ID grupy którą chcesz usunąć. ");
		int userid = getNumberFromConsole();
		// tworzymy polaczenie
		dbConnection connection = new dbConnection();
		try {
			groupToDelete = UserGroup.loadGroupById(connection.getConnection(), userid);
		} catch (SQLException e) {
			System.out.println("Wystąpił problem z bazą danych. ");
			System.out.println(e.getMessage());
		}
		// kasujemy grupę o podanym id
		if (groupToDelete != null) {
			try {
				groupToDelete.groupDelete(connection.getConnection());
				System.out.println("Grupę usunięto pomyślnie.");
			} catch (Exception e) {
				System.out.println("Wystąpił problem z bazą danych - grupa nie istnieje? ");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Grupa o podanym ID nie istnieje.");
		}
		// zamykamy połączenie
		connection.closeConnection();
		
		
	// koniec kasowania grupy z bazy */
		
		
		
		
		
		
		
	
	
	} //koniec metody main
	
	
	
	
	
	
	
	
	
	static String getConsoleString() {
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Wpisz odpowiedź: ");
		String string = myScanner.nextLine();
		return string;
	}
	
	
	static int getNumberFromConsole() {
		 
 		@SuppressWarnings("resource")
 		Scanner myScanner = new Scanner(System.in);
 		int number;
 		System.out.println("Wpisz liczbę: ");
 
 		try {
 			number = myScanner.nextInt();
 		} catch (Exception e) {
 			System.out.println("To nie jest liczba!");
 			number = getNumberFromConsole();
 		}
 		return number;
 	}
	
	
	static String printUserData(User user) {
		try {
			return "\nOto dane wybranego użytkownika.\nID:    " + user.getId() + "\nImię:  " + user.getUsername() + "\nemail: " + user.getEmail();
		} catch (Exception e) {
			return "Użytkownik o podanym id nie istnieje.";
		}
	}
	
	static String printGroupData(UserGroup group) {
		try {
			return "\nOto dane wybranej grupy.\nID:     " + group.getId() + "\nNazwa:  " + group.getGroupName();
		} catch (Exception e) {
			return "Grupa o podanym id nie istnieje.";
		}
	}



}