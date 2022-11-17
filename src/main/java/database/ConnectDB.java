package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {

	public Connection conn;
	public Statement stmt;

	public ConnectDB() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); // load driver
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerdb", "root", "1234");
			stmt = conn.createStatement();
		}

		catch (ClassNotFoundException d) {
			System.out.println("Error - " + d.getMessage());
		} catch (SQLException e) {
			System.out.println("Error - " + e.getMessage());
		}
	}

	public Connection getConneection() {
		return conn;
	}

	public Statement getStatements() {
		return stmt;
	}
}
