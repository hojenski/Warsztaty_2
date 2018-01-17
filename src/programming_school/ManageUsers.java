package programming_school;
//ManageUsers początek

import java.sql.SQLException;
import java.util.Scanner;

public class ManageUsers {
	public static void main(String[] args) {

	User user1 = new User();
	System.out.println("Podaj nazwę użytkowinika: ");
	String username = getConsoleString();	
	
	System.out.println("Podaj email: ");
	String email = getConsoleString();
	
	System.out.println("podaj hasło: ");
	String password = getConsoleString();
	
	user1.setUsername(username);
	user1.setEmail(email);
	user1.setPassword(password);
	
	// tworzenie połaczenia
	DbConnection connection = new DbConnection();
	
	//metoda zapisywania użytkownika w bazie danych
	
	try {
		user1.saveUserToDB(connection.getConnection());
		System.out.println("Użytkownika pomyślnie dodano do bazy danych");
	} catch (SQLException e) {
        System.out.println("Wystąpił problem z bazą danych. ");
        System.out.println(e.getMessage());
        e.printStackTrace();
}

//zamykamy połączenie
connection.closeConnection();
// koniec motedy
	
	
	
	}	
	
	
    
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

//static String printGroupData(Group group) {
//        try {
//                return "\nOto dane wybranej grupy.\nID:     " + group.getId() + "\nNazwa:  " + group.getGroupName();
//        } catch (Exception e) {
//                return "Grupa o podanym id nie istnieje.";
//        }
//}
}



