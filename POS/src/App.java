import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Connection conn = null;
        // final String teamName = "08m";
        // final String dbName = "csce315331_" + teamName + "_db";
        // final String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String pswd = input.nextLine();
        input.close();

        Database conn = new Database(username, pswd);

        conn.closeDatabase();
        // try {
        //     conn = DriverManager.getConnection(dbConnectionString, username, pswd);
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     System.err.println(e.getClass().getName() + ": " + e.getMessage());
        //     System.exit(0);
        // }

        // System.out.println("Opened database successfully");
        
        // // closing the connection
        // try {
        //     conn.close();
        //     System.out.println("Connection Closed.");
        // } catch (Exception e) {
        //     System.out.println("Connection NOT Closed.");
        // } // end try catch
    }
}
