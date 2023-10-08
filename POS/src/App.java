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
        String password = input.nextLine();
        // input.close();

        Database conn = new Database(username, password);

        while (!conn.isConnected()) {
            System.out.print("Enter username: ");
            username = input.nextLine();
            System.out.print("Enter password: ");
            password = input.nextLine();

            conn = new Database(username, password);
        }
        input.close();


        // conn.addEmployee(5555, "tempM", "tempM", "tempM", "2011-10-31", 20, "manager");
        // conn.deleteEmployee(5555);

        // conn.addInventoryItem(5555, "tempii", "2023-02-18", 10000, 790);
        // conn.updateInventoryItemAmountRemaining(5555, 1000);
        // conn.updateInventoryItemAmountUsed(5555, 800);
        // conn.updateInventoryItemName(5555, "tempII");
        // conn.restockInventoryItem(5555, "2023-10-07", 1000);
        // conn.deleteInventoryItem(5555);

        // conn.addMenuItem(5555, "tempmi", 45.59);
        // conn.updateMenuItemName(5555, "tempMI");
        // conn.updateMenuItemPrice(5555, 5.59);
        // conn.deleteMenuItem(5555);

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
