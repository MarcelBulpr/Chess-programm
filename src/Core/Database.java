package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		createTable();
		zuege();                                             
	}

	public static void zuege() throws Exception {
		final int zuege_anzahl = 1;
		final String zuege_art = "Hallo";
		try {
			Connection conn = getConnection();
			PreparedStatement xc = conn.prepareStatement(
					"INSERT INTO zuege(first, last) VALUES ('" + zuege_anzahl + "', '" + zuege_art + "')"); 

			xc.executeUpdate();

		} catch (Exception x) {
			System.out.println(x);
		} 

		}
	

	public static void createTable() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXITS zuege(id int NOT NULL AUTO_INCREMENT, first int(80), last(255))"); 
			create.executeUpdate();

		} catch (Exception y) {
			System.out.println(y);
		} 

		}
	
	public static Connection getConnection() throws Exception {

		try {
			String driver = "con.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost/schach-historty";
			String username = "Schachfigur";
			String password = "Schachfigur";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);

			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;

	}

}