package com.mycompany.managergui;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection conn;
    final private String teamName = "08m";
    final private String dbName = "csce315331_" + teamName + "_db";
    final private String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    private Statement createStatement;

    /**
     * default constructor for the Database class.
     * Sets everything to null
     */
    public Database() {
        conn = null;
        createStatement = null;
    }

    /**
     * the parameterized constructor for the Database class.
     * Attempts to establish a connection to the database
     * @param userName the username used to connect to the database
     * @param password the password used to connect to the database
     */
    public Database(String userName, String password) {
        try {
            Class.forName("org.postgresql.Driver");
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

    /**
     * Checks if there is an active database connection
     * @return boolean stating whether there is an active database connection
     */
    public boolean isConnected() {
        return conn != null;
    }

    /**
     * closed the connection to the database
     */
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

    /**
     * gets the information of a single Employee from the database
     * @param id the id of the Employee
     * @return ResultSet of the Employee data in the database
     */
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
    
    /**
     * gets all the Employees from the database
     * @return ResultSet containing all of the Employee data in the database
     */
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

    /**
     * attempts to add an Employee to the database
     * @param id the id of the Employee
     * @param userName the username of the Employee
     * @param password the password of the Employee
     * @param name the name of the Employee
     * @param startDate the start date of the employee in "YYYY-MM-DD" format
     * @param salary the salary of the employee
     * @param position the position of the employee
     */
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

    /**
     * attempts to delete an Employee
     * @param id the id of the Employee to be deleted
     */
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

    /**
     * attempts to update the username of an Employee
     * @param id the id of the Employee
     * @param newUsername the new username of the Employee
     */
    public void updateEmployeeUsername(int id, String newUsername) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET username = " + newUsername + " " +
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee " + id + " username");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee " + id + " username");
            e.printStackTrace();
        }
    }

    /**
     * attempts to update the password of an Employee
     * @param id the id of the Employee
     * @param newPassword the new password of the Employee
     */
    public void updateEmployeePassword(int id, String newPassword) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET password = " + newPassword + " " +
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee " + id + " password");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee " + id + " password");
            e.printStackTrace();
        }
    }

    /**
     * attempts to update the name of an Employee
     * @param id the id of the Employee
     * @param newName the new name of the Employee
     */
    public void updateEmployeeName(int id, String newName) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET name = " + newName + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee " + id + " name");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee " + id + " name");
            e.printStackTrace();
        }
    }

    /**
     * attempts to update the start date of an Employee
     * @param id the id of the Employee
     * @param newStartDate the new start date of the Employee
     */
    public void updateEmployeeStartDate(int id, String newStartDate) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET start_date = " + newStartDate + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee " + id + " start_date");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee " + id + " start_date");
            e.printStackTrace();
        }
    }

    /**
     * attempts to update the salary of an Employee
     * @param id the id of the Employee
     * @param newSalary the new salary of the Employee
     */
    public void updateEmployeeSalary(int id, double newSalary) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET salary = " + newSalary + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee " + id + " salary");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee " + id + " salary");
            e.printStackTrace();
        }
    }

    /**
     * attempts to update the position of an Employee
     * @param id the id of the Employee
     * @param newPosition the new salary of the Employee
     */
    public void updateEmployeePosition(int id, String newPosition) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET position = " + newPosition + " " +
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee " + id + " position");
        }
        catch (Exception e) {
            System.out.println("Failed to updated Employee " + id + " salary");
            e.printStackTrace();
        }
    }

    // SHIFT SECTION

    /**
     * this is to get a single shift time
     * @param id
     * @return ResultSet with the shift start and end times or null
     */
    public ResultSet getSingleShift(int id) {
        ResultSet shift = null;
        try {
            shift = createStatement.executeQuery(
                "SELECT * FROM shifts WHERE id = " + id + ";"
            );
            System.out.println("Successfully got Shift " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get Shift " + id);
            e.printStackTrace();
        }
        return shift;
    }

    public void addShift(int id, int startTime, int endTime) {
        try {
            createStatement.execute(
                "INSERT INTO shifts (id, start_time, end_time) VALUES(" +
                id + ", " + startTime + ", " + endTime + ");"
            );
            System.out.println("Successfully added Shift " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to add Shift");
            e.printStackTrace();
        }
    }

    public void deleteShift (int id) {
        try {
            createStatement.execute(
                "DELETE FROM shifts WHERE id = " + id + ";"
            );
            System.out.println("Successfully deleted Shift " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to delete Shift " + id);
            e.printStackTrace();
        }
    }

    /**
     * updates the shift start time in 24-HOUR FORMAT (i.e. 1 PM is 13, 12 AM or midnight is 0)
     * @param id
     * @param newStartTime
     */
    public void updateShiftStartTime(int id, int newStartTime) {
        try {
            createStatement.execute(
                "UPDATE shifts " +
                "SET start_time = " + newStartTime + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println(
                "Successfully updated Shift " + id + " start_time"
            );
        }
        catch (Exception e) {
            System.out.println("Failed to update Shift " + id +  " start_time");
            e.printStackTrace();
        }
    }

    /**
     * updates the shift end time in 24-HOUR FORMAT (i.e. 1 PM is 13, 12 AM or midnight is 24)
     * @param id
     * @param newEndTime
     */
    public void updateShiftEndTime(int id, int newEndTime) {
        try {
            createStatement.execute(
                "UPDATE shifts " +
                "SET end_time = " + newEndTime + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println(
                "Successfully updated Shift " + id + " end_time"
            );
        }
        catch (Exception e) {
            System.out.println("Failed to update Shift " + id +  " end_time");
            e.printStackTrace();
        }
    }

    /**
     * this is to get the shift ids and times
     * NOT the actual employee shifts
     * @return ResultSet with all the shift ids and times or null
     */
    public ResultSet getAllShifts() {
        ResultSet shifts = null;
        try {
            shifts = createStatement.executeQuery(
                "SELECT * FROM shifts;"
            );
            System.out.println("Successfully got Shifts");
        }
        catch (Exception e) {
            System.out.println("Failed to get Shifts");
            e.printStackTrace();
        }
        return shifts;
    }

    // EMPLOYEE_SHIFTS JUNCTION TABLE SECTION

    /**
     * this gets the shifts of a single employee
     * @param id
     * @return ResultSet of the Employee Shifts for the Employee or null
     */
    public ResultSet getSingleEmployeeShifts(int id) {
        ResultSet employeeShift = null;
        try {
            employeeShift = createStatement.executeQuery(
                "SELECT * FROM employee_shift WHERE employee_id = " + id + ";"
            );
            System.out.println("Successfully got Employee Shifts for Employee " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to get Employee Shifts for Employee " + id);
            e.printStackTrace();
        }
        return employeeShift;
    }

    /**
     * this gets all the shifts each employee works
     * not to be confused with getAllShifts which gets all the start and end times of the shifts
     * @return ResultSet of all the Employee Shifts or null
     */
    public ResultSet getAllEmployeeShifts() {
        ResultSet employeeShifts = null;
        try {
            employeeShifts = createStatement.executeQuery(
                "SELECT * FROM employee_shift;"
            );
            System.out.println("Successfully got Employee Shifts");
        }
        catch (Exception e) {
            System.out.println("Failed to get Employee Shifts");
            e.printStackTrace();
        }
        return employeeShifts;
    }

    public void addEmployeeShift(int id, int shiftId, int employeeId, int month, int dayOfWeek) {
        try {
            createStatement.execute(
                "INSERT INTO employee_shift (id, shift_id, employee_id, month, day_of_week) VALUES (" +
                id + ", " + shiftId + ", " + employeeId + ", " + month + ", " + dayOfWeek + ");"
            );
            System.out.println("Successfully added Employee Shift");
        }
        catch (Exception e) {
            System.out.println("Failed to add Employee Shift");
            e.printStackTrace();
        }
    }

    public void deleteEmployeeShift(int id) {
        try {
            createStatement.execute(
                "DELETE FROM employee_shift WHERE id = " + id + ";"
            );
            System.out.println("Successfully deleted Employee Shift " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to delete Employee Shift " + id);
            e.printStackTrace();
        }
    }

    public void updateShiftId(int id, int newShiftId) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET shift_id = " + newShiftId + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee Shift " + id + " shift_id");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee Shift " + id + " shift_id");
            e.printStackTrace();
        }
    }
    
    public void updateEmployeeId(int id, int newEmployeeId) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET employee_id = " + newEmployeeId + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee Shift " + id + " employee_id");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee Shift " + id + " employee_id");
            e.printStackTrace();
        }
    }

    public void updateMonth(int id, int newMonth) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET month = " + newMonth + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee Shift " + id + " month");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee Shift " + id + " month");
            e.printStackTrace();
        }
    }

    public void updateDayOfWeek(int id, int newDayOfWeek) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET day_of_week = " + newDayOfWeek + " " + 
                "WHERE id = " + id + ";"
            );
            System.out.println("Successfully updated Employee Shift " + id + " day_of_week");
        }
        catch (Exception e) {
            System.out.println("Failed to update Employee Shift " + id + " day_of_week");
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

    public void updateMenuItemInventoryItems(int id) {
        return;
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
    public void addOrder(int id, double price, String dateTime, ArrayList<Integer> menuItemIds, ArrayList<ArrayList<Integer>> addOnIdsForEachMenuItem) {        
        return;
    }

    public void deleteOrder(int id) {
        try {
            ResultSet orderMenuItems = createStatement.executeQuery(
                "SELECT * FROM order_menu WHERE order_id = " + id + ";"
            );
            while (orderMenuItems.next()) {
                int orderMenuJunctionId = orderMenuItems.getInt("id");
                try {
                    createStatement.executeQuery(
                        "DELETE FROM order_add_ons WHERE order_menu_junction_id = " + orderMenuJunctionId + ";"
                    );
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                createStatement.executeQuery(
                    "DELETE FROM order_menu WHERE id = " + orderMenuJunctionId + ";"
                );
            }
            createStatement.executeQuery(
                "DELETE FROM orders WHERE id = " + id + ";"
            );
            System.out.println("Successfully deleted Order " + id);
        }
        catch (Exception e) {
            System.out.println("Failed to completely delete Order " + id);
            e.printStackTrace();
        }
        return;
    }

}