// import java.rmi.server.ExportException;
import java.sql.*;
// import java.util.Scanner;

public class Database {

    private Connection conn;
    final private String teamName = "08m";
    final private String dbName = "csce315331_" + teamName + "_db";
    final private String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    private Statement createStatement;

    public Database() {
        conn = null;
        createStatement = null;
    }

    public Database(String userName, String password) {
        try {
            conn = DriverManager.getConnection(dbConnectionString, userName, password);
            createStatement = conn.createStatement();
            System.out.println("Database Connection Established");
        }
        catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Databasae Connection Failed");
            // System.exit(0);
        }
    }

    public void closeDatabase() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database Connection Closed");
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Database Connection NOT Closed");
            }
        }
    }

    // EMPLOYEE SECTION

    // adds an Employee to the employee table. returns the added Employee on success and null on fail.
    public void addEmployee(int id, String userName, String password, String name, String startDate, double salary, String position) {
        // ResultSet newEmployee = null;
        try {
            // why doesn't java have raw strings?????
            // newEmployee = createStatement.executeQuery(
            createStatement.execute(
                "INSERT INTO employee (id, username, password, name, start_date, salary, position) VALUES (" +
                id + ", \'" + userName + "\', \'" + password + "\', \'" + name + "\', \'" + startDate +
                "\', " + salary + ", \'" + position + "\');"
            );
            System.out.println("Successfully added Employee " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to add Employee");
            e.printStackTrace();
        }
        // return newEmployee;
    }

    public void deleteEmployee(int id) {
        try {
            createStatement.execute(
                "DELETE FROM employee WHERE id = " + id + ";"
            );
            System.out.println("Successfully deleted Employee " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to delete Employee " + id);
            e.printStackTrace();
        }
    }

    // INVENTORY SECTION

    // adds an Inventory Item to the inventory table. returns the added Inventory Item on success and null on fail.
    public void addInventoryItem(int id, String name, String lastRestockDate, int amountRemaining, int amountUsed) {
        // ResultSet newInventoryItem = null;
        try {
            // newInventoryItem = createStatement.executeQuery(
            createStatement.execute(
                "INSERT INTO inventory (id, name, last_restock_date, amount_remaining, amount_used) VALUES (" +
                id + ", \'" + name + "\', \'" + lastRestockDate + "\', " + amountRemaining + ", " + amountUsed + ");"
            );
            System.out.println("Successfully added Inventory Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to add Inventory Item");
            e.printStackTrace();
        }
        // return newInventoryItem;
    }

    public void deleteInventoryItem(int id) {
        try {
            createStatement.execute(
                "DELETE FROM inventory WHERE id = " + id + ";"
            );
            System.out.println("Successfully deleted Inventory Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to delete Inventory Item " + id);
            e.printStackTrace();
        }
    }

    public void updateInventoryItemAmountRemaining(int id, int newAmountRemaining) {
        try {
            // ResultSet inventoryItem = createStatement.executeQuery(
            //     "SELECT * FROM inventory WHERE id = " + id + ";"
            // );
            // inventoryItem.next();
            // int currentAmountRemaining = inventoryItem.getInt("amount_remaining");
            // System.out.println("Got amount_remaining");
            // int currentAmountUsed = inventoryItem.getInt("amount_used");
            // System.out.println("Got amount_used");
            // System.out.println(
            //     "UPDATE inventory " +
            //     "SET amount_remaining = " + newAmountRemaining + ", " + 
            //     "amount_used = " + (currentAmountUsed + (currentAmountRemaining - newAmountRemaining)) + " " +
            //     "WHERE id = " + id + ";"
            // );
            createStatement.execute(
                "UPDATE inventory " +
                "SET amount_remaining = " + newAmountRemaining + " " + 
                // "amount_used = " + (currentAmountUsed + (currentAmountRemaining - newAmountRemaining)) + " " +
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Inventory Item " + id + " amounts");
            System.out.println("Successfully updated Inventory Item " + id + " amount remaining");
        }
        catch (Exception e) {
            System.out.println("Failed to update Inventory Item " + id + " Amount remaining");
            e.printStackTrace();
        }
    }

    public void updateInventoryItemAmountUsed(int id, int newAmountUsed) {
        try {
            createStatement.execute(
                "UPDATE inventory " +
                "SET amount_used = " + newAmountUsed + " " +
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Inventory Item " + id + " amount used");
        }
        catch (Exception e) {
            System.out.println("Failed to update Inventor Item " + id + " Amount used");
            e.printStackTrace();
        }
    }

    public void updateInventoryItemName(int id, String newName) {
        try {
            createStatement.execute(
                "UPDATE inventory " +
                "SET name = \'" + newName + "\' " +
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Inventory Item " + id + " name");
        }
        catch (Exception e) {
            System.out.println("Failed to update Inventory Item " + id + " name");
            e.printStackTrace();
        }
    }

    public void restockInventoryItem(int id, String restockDate, int restockAmount) {
        try {
            ResultSet inventoryItem = createStatement.executeQuery(
                "SELECT * FROM inventory WHERE id = "+ id + ";"
            );
            inventoryItem.next();
            int amountRemaining = inventoryItem.getInt("amount_remaining");
            createStatement.execute(
                "UPDATE inventory " + 
                "SET amount_remaining = " + (amountRemaining + restockAmount) + ", " + 
                "last_restock_date = \'" + restockDate + "\' " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully restocked Inventory Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to restock Inventory Item " + id);
            e.printStackTrace();
        }
    }

    // MENU SECTION

    // adds a Menu Item to the menu table. returns the added Menu Item on success and null on fail.
    public void addMenuItem(int id, String name, double price) {
        // ResultSet newMenuItem = null;
        try {
            // newMenuItem = createStatement.executeQuery(
            createStatement.execute(
                "INSERT INTO menu (id, name, price) VALUES (" +
                id + ", \'" + name + "\', " + price + ");"
            );
            System.out.println("Successfully added Menu Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to add Menu Item");
            e.printStackTrace();
        }
        // return newMenuItem;
    }

    public void deleteMenuItem(int id) {
        try {
            createStatement.execute(
                "DELETE FROM menu WHERE id = " + id + ";"
            );
            System.out.println("Successfully deleted Menu Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to delete Menu Item " + id);
            e.printStackTrace();
        }
    }

    // updates the price of a Menu Item
    public void updateMenuItemPrice(int id, double newPrice) {
        try {
            createStatement.execute(
                "UPDATE menu " +
                "SET price = " + newPrice + " " +
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Menu Item " + id + " price");
        }
        catch (Exception e) {
            System.out.println("Faled to update Menu Item " + id + " price");
            e.printStackTrace();
        }
    }

    public void updateMenuItemName(int id, String newName) {
        try {
            createStatement.execute(
                "UPDATE menu " + 
                "SET name = \'" + newName + "\' " +
                "WHERE id = " + id + ";" 
            );
            System.out.println("Successfully updated Menu Item " + id + " name");
        }
        catch (Exception e) {
            System.out.println("Failed to update Menu Item " + id + " name");
            e.printStackTrace();
        }
    }

}
