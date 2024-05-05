package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import pack.mealT;


public class app {
	
	public static void main(String[] args) {
		add_meals();
		read_meals(1800);
		
		System.out.println("Query over, exiting.");
	}
	
	public static void add_meals() {
		try {
			Class.forName("org.neo4j.jdbc.Driver");
			Properties props = new Properties();
			props.setProperty("user", "neo4j");
			props.setProperty("password", "neo4j");
			Connection con = 
					DriverManager.getConnection("jdbc:neo4j:http://localhost:8389",props);
			String query;
			
			mealT[] meals = new mealT[3];
			meals[0] = new mealT("m1", "Piedone", 1710, "Pizza");
			meals[0] = new mealT("m2", "Magyaros", 1850, "Pizza");
			meals[0] = new mealT("m3", "BBQ_csirkemell_box", 2120, "Húsétel");
			
			query = "ceate (a:meal {_id:{1}, name:{2}, price:{3}, category:{4}})";
			PreparedStatement stmt = con.prepareStatement(query);
			for (int i=0; i<5; i++) {
				stmt.setString(1,  meals[i].id);
				stmt.setString(2,  meals[i].name);
				stmt.setInt(3,  meals[i].price);
				stmt.setString(4,  meals[i].category);
				stmt.executeUpdate();
			}
			
			con.close();
		}	catch(Exception ee) {
			System.out.println(ee.getMessage());
		}
	}
	
	public static void read_meals(int limprice) {
		try {
			Class.forName("org.neo4j.jdbc.Driver");
			Properties props = new Properties();
			props.setProperty("user", "neo4j");
			props.setProperty("password", "neo4j");
			Connection con = 
					DriverManager.getConnection("jdbc:neo4j:http://localhost:8389",props);
			
			String query = "MATCH (a:meal) WHERE a.price > {1} RETURN a.name";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1,  limprice);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("a.name"));
			}
			
			con.close();
		}	catch(Exception ee) {
			System.out.println(ee.getMessage());
		}
	}
	
}
