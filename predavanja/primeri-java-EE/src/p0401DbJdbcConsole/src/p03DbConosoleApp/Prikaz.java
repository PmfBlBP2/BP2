package p03DbConosoleApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prikaz {

	static void ispisSvihProizvoda(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Proizvod WHERE Kolicina > -1");
			while (rs.next()) {
				System.out.printf("%8d", rs.getInt("ProizvodId"));
				System.out.printf("%30s", rs.getString(2));
				System.out.printf("%4d", rs.getInt(3));
				System.out.printf("%10s", rs.getString("JedinicaMere"));
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			 Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
		} catch (Exception e) {
			System.out.println("Nema veze sa odgovarajucom bibilotekom");
			System.out.println(e.toString());
			System.exit(-1);
		}
		System.out.println("Veza sa odgovarajucom bibliotekom: OK");
		Connection conn = null;
//		String urlKonekcije = "jdbc:mysql://127.0.0.1:3307/matf_2015_01_proizvodi"
//				+ "?user=root&password=&autoReconnect=true";
		
		String urlKonekcije = "jdbc:sqlserver://127.0.0.1:1433;"
				+ "databaseName=matf_2015_01_proizvodi;" 
				+ "integratedSecurity=false;user=vlado;password=vlado;";
		try {
			conn = DriverManager.getConnection(urlKonekcije);
		} catch (SQLException e) {
			System.out.println("Nekorektan URL konekcije ");
			System.out.println(e.toString());
			System.exit(-2);
		}
		System.out.println("URL konekcije: OK");
		ispisSvihProizvoda(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Nekorektno zatvaranje konekcije ");
			System.out.println(e.toString());
			System.exit(-3);
		}
	}
}
