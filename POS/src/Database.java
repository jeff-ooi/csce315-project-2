// import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
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

    public boolean isConnected() {
        return conn != null;
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

    public ResultSet getSingleEmployee(int id) {
        ResultSet employee = null;
        try {
            employee = createStatement.executeQuery(
                "SELECT * FROM employee where id = " + id + ";"
            );
            System.out.println("Got Employee " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get Employee " + id);
            e.printStackTrace();
        }
        return employee;
    }
    
    // returns all the employees in the employee table
    public ResultSet getEmployees() {
        ResultSet employees = null;
        try {
            employees = createStatement.executeQuery(
                "SELECT * FROM employee;"
            );
            System.out.println("Got Employees");
        }
        catch (Exception e) {
            System.out.println("Failed to get Employees");
            e.printStackTrace();
        }
        return employees;
    }

    // adds an Employee to the employee table
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

    public ResultSet getSingleInventoryItem(int id) {
        ResultSet inventoryItem = null;
        try {
            inventoryItem = createStatement.executeQuery(
                "SELECT * FROM inventory where id = " + id + ";"
            );
            System.out.println("Got Inventory Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get Inventory Item " + id);
            e.printStackTrace();
        }
        return inventoryItem;
    }

    public ResultSet getInventory() {
        ResultSet inventory = null;
        try {
            inventory = createStatement.executeQuery(
                "SELECT * FROM inventory;"
            );
            System.out.println("Got Inventory");
        }
        catch (Exception e) {
            System.out.println("Failed to get Inventory");
            e.printStackTrace();
        }
        return inventory;
    }

    // adds an Inventory Item to the inventory table
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
            System.out.println("Failed to update Inventory Item " + id + " Amount used");
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

    public ResultSet getSingleMenuItem(int id) {
        ResultSet menuItem = null;
        try {
            menuItem = createStatement.executeQuery(
                "SELECT * FROM menu where id = " + id + ";"
            );
            System.out.println("Got Menu Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get Menu Item " + id);
            e.printStackTrace();
        }
        return menuItem;
    }

    public ResultSet getMenu() {
        ResultSet menu = null;
        try {
            menu = createStatement.executeQuery(
                "SELECT * FROM menu;"
            );
            System.out.println("Got Menu");
        }
        catch (Exception e) {
            System.out.println("Failed to get Menu");
            e.printStackTrace();
        }
        return menu;
    }

    // adds a Menu Item to the menu table
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

    public void updateMenuItemInventoryItems(int id, ArrayList<Integer> inventoryIds) {
        try {
            createStatement.execute(
                "DELETE FROM menu_inventory WHERE menu_id = " + id + ";"
            );

            for (int invId : inventoryIds) {
                createStatement.execute(
                    "INSERT INTO menu_inventory (menu_id, inventory_id) VALUES (" +
                    id + ", " + invId + ");"
                );
            }
            System.out.println("Successfully updated Menu Item " + id " \'s inventory items");

        }
        catch (Exception e) {
            System.out.println("Failed to update Menu Item " + id + "\'s inventory items");
            e.printStackTrace();
        }
    }

    public ResultSet getMenuItemInventoryItems(int id) {
        ResultSet invItems = null;
        try {
            invItems = createStatement.executeQuery(
                "SELECT * FROM menu_inventory WHERE menu_id = " + id + ";"
            );
            System.out.println("Got inventory items for Menu Item " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get inventory items from Menu Item " + id);
            e.printStackTrace();
        }
        return invItems;
    }

    // ORDERS SECTION

    public ResultSet getSingleOrder(int id) {
        ResultSet order = null;
        try {
            order = createStatement.executeQuery(
                "SELECT * FROM orders where id = " + id + ";"
            );
            System.out.println("Got Order " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get Order " + id);
            e.printStackTrace();
        }
        return order;
    }

    public ResultSet getAllOrders() {
        ResultSet orders = null;
        try {
            orders = createStatement.executeQuery(
                "SELECT * FROM orders;"
            );
            System.out.println("Got Orders");
        }
        catch (Exception e) {
            System.out.println("Failed to get Orders");
            e.printStackTrace();
        }
        return orders;
    }

    public ResultSet getMenuItemsFromOrder(int id) {
        ResultSet orderItems = null;
        try {
            orderItems = createStatement.executeQuery(
                "SELECT * FROM order_menu WHERE order_id = " + id + ";"
            );
            System.out.println("Got Menu Items for Order " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get Menu Items for Order " + id);
        }
        return orderItems;
    }

    public ResultSet getAddOnsForMenuItemInOrder(int orderId, int menuItemId) {
        ResultSet addOns = null;
        try {
            addOns = createStatement.executeQuery(
                "SELECT * FROM order_add_ons WHERE order_menu_junction id = (" + 
                "SELECT id FROM order_menu WHERE order_id = " + orderId + " AND menu_id = " + menuItemId + ");"
            );
        }
        catch (Exception e) {
            System.out.println("Failed to get Add-Ons for Menu Item in an Order");
        }
        return addOns;
    }

    // this one is going to be hard
    public void addOrder(int id, double price, java.util.Date dateTime, ArrayList<Integer> menuItemIds, ArrayList<ArrayList<Integer>> addOnIdsForEachMenuItem) {        
        try {
            createStatement.execute(
                "INSERT INTO orders (id, price, date_time) VALUES (" +
                id + ", " + price + ", " + dateTime + ");"
            );

            // update order menu junction table
            ResultSet maxIdSet = null;
            maxId = createStatement.executeQuery (
                "SELECT id FROM order_menu ORDER BY id DESC LIMIT 1"
            );
            int maxId = maxIdSet.getInt("id");

            for (int i = 0; i < menuItemIds.size(); i++) {
                maxId += 1;

                createStatement.execute(
                    "INSERT INTO order_menu (id, order_id, menu_id) VALUES (" +
                    maxId + ", " + id + ", " + menuItemIds.get(i) + ");"
                );

                // update order add_on junction table
                for (int addOnId : addOnIdsForEachMenuItem.get(i)) {
                    createStatement.execute(
                        "INSERT INTO order_add_ons (order_menu_junction_id, add_on_id) VALUES (" +
                        maxId + ", " + addOnId + ");"
                    );
                }
            }
        }
        catch (Exception e) {
            System.out.println("Failed to add Order");
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        return;
    }

}
