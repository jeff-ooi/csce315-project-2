package com.mycompany.managergui;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.spi.DirStateFactory.Result;

/**
 * @author Jeff Ooi, Suhu Lavu
 */
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
            // System.out.println("Database Connection Established");
        }
        catch (Exception e) {
            // // e.printStackTrace();
            // System.out.println(e.getClass().getName() + ": " + e.getMessage());
            // System.out.println("Databasae Connection Failed");
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
     * closes the connection to the database
     * @return boolean stating whether the database connection was successfully closed
     */
    public boolean closeDatabase() {
        if (conn != null) {
            try {
                conn.close();
                // System.out.println("Database Connection Closed");
                return true;
            }
            catch (Exception e) {
                // e.printStackTrace();
                // System.out.println("Database Connection NOT Closed");
            }
        }
        return false;
    }

    // EMPLOYEE SECTION

    /**
     * gets the information of a single Employee from the database
     * @param id the id of the Employee
     * @return ResultSet of the Employee data in the database or null
     */
    public ResultSet getSingleEmployee(int id) {
        ResultSet employee = null;
        try {
            employee = createStatement.executeQuery(
                "SELECT * FROM employee where id = " + id + ";"
            );
            // System.out.println("Got Employee " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Employee " + id);
            // e.printStackTrace();
        }
        return employee;
    }
    
    /**
     * gets all the Employees from the database
     * @return ResultSet containing all of the Employee data in the database or null
     */
    public ResultSet getEmployees() {
        ResultSet employees = null;
        try {
            employees = createStatement.executeQuery(
                "SELECT * FROM employee;"
            );
            // System.out.println("Got Employees");
        }
        catch (Exception e) {
            // System.out.println("Failed to get Employees");
            // e.printStackTrace();
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
     * @return boolean stating whether the Employee was successfully added
     */
    public boolean addEmployee(int id, String userName, String password, String name, String startDate, double salary, String position) {
        // ResultSet newEmployee = null;
        try {
            // why doesn't java have raw strings?????
            // newEmployee = createStatement.executeQuery(
            createStatement.execute(
                "INSERT INTO employee (id, username, password, name, start_date, salary, position) VALUES (" +
                id + ", \'" + userName + "\', \'" + password + "\', \'" + name + "\', \'" + startDate +
                "\', " + salary + ", \'" + position + "\');"
            );
            // System.out.println("Successfully added Employee " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to add Employee");
            // e.printStackTrace();
        }
        // return newEmployee;
        return false;
    }

    /**
     * attempts to delete an Employee
     * @param id the id of the Employee to be deleted
     * @return boolean stating whether the Employee was successfully deleted
     */
    public boolean deleteEmployee(int id) {
        try {
            createStatement.execute(
                "DELETE FROM employee WHERE id = " + id + ";"
            );
            // System.out.println("Successfully deleted Employee " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to delete Employee " + id);
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the username of an Employee
     * @param id the id of the Employee
     * @param newUsername the new username of the Employee
     * @return boolean stating whether the Employee's username was successfully updated
     */
    public boolean updateEmployeeUsername(int id, String newUsername) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET username = \'" + newUsername + "\' " +
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee " + id + " username");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee " + id + " username");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the password of an Employee
     * @param id the id of the Employee
     * @param newPassword the new password of the Employee
     * @return boolean stating whether the Employee's password was successfully updated
     */
    public boolean updateEmployeePassword(int id, String newPassword) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET password = \'" + newPassword + "\' " +
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee " + id + " password");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee " + id + " password");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the name of an Employee
     * @param id the id of the Employee
     * @param newName the new name of the Employee
     * @return boolean stating whether the Employee's name was successfully updated
     */
    public boolean updateEmployeeName(int id, String newName) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET name = \'" + newName + "\' " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee " + id + " name");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee " + id + " name");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the start date of an Employee
     * @param id the id of the Employee
     * @param newStartDate the new start date of the Employee in "YYYY-MM-DD" format
     * @return boolean stating whether the Employee's start date was successfully updated
     */
    public boolean updateEmployeeStartDate(int id, String newStartDate) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET start_date = \'" + newStartDate + "\' " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee " + id + " start_date");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee " + id + " start_date");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the salary of an Employee
     * @param id the id of the Employee
     * @param newSalary the new salary of the Employee
     * @return boolean stating whether the Employee's salary was successfully updated
     */
    public boolean updateEmployeeSalary(int id, double newSalary) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET salary = " + newSalary + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee " + id + " salary");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee " + id + " salary");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the position of an Employee
     * @param id the id of the Employee
     * @param newPosition the new salary of the Employee
     * @return boolean stating whether the Employee's position was successfully updated
     */
    public boolean updateEmployeePosition(int id, String newPosition) {
        try {
            createStatement.execute(
                "UPDATE employee " + 
                "SET position = \'" + newPosition + "\' " +
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee " + id + " position");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to updated Employee " + id + " salary");
            // e.printStackTrace();
        }
        return false;
    }

    // SHIFT SECTION

    /**
     * gets the information of a single Shift
     * @param id the id of the Shift
     * @return ResultSet with the Shift data or null
     */
    public ResultSet getSingleShift(int id) {
        ResultSet shift = null;
        try {
            shift = createStatement.executeQuery(
                "SELECT * FROM shifts WHERE id = " + id + ";"
            );
            // System.out.println("Successfully got Shift " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Shift " + id);
            // e.printStackTrace();
        }
        return shift;
    }

    /**
     * attempts to add a shift to the database
     * @param id the id of the new Shift
     * @param startTime the start time of the new Shift in 24-hour format (i.e. 1 PM is 13, 12 AM or midnight is 0)
     * @param endTime the end time of the new Shift in 24-format (i.e. 1 PM is 13, 12 AM or midnight is 24)
     * @return boolean stating whether the Shift was successfully added
     */
    public boolean addShift(int id, int startTime, int endTime) {
        try {
            createStatement.execute(
                "INSERT INTO shifts (id, start_time, end_time) VALUES(" +
                id + ", " + startTime + ", " + endTime + ");"
            );
            // System.out.println("Successfully added Shift " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to add Shift");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to delete a Shift from the database
     * @param id the id of the Shift to be deleted
     * @return boolean stating whether the Shift was successfully deleted
     */
    public boolean deleteShift (int id) {
        try {
            createStatement.execute(
                "DELETE FROM shifts WHERE id = " + id + ";"
            );
            // System.out.println("Successfully deleted Shift " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to delete Shift " + id);
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * updates the shift start time in 24-HOUR FORMAT (i.e. 1 PM is 13, 12 AM or midnight is 0)
     * @param id the id of the Shift
     * @param newStartTime the new start time of the Shift in 24-hour format (i.e. 1 PM is 13, 12 AM or midnight is 0)
     * @return boolean stating whether the Shift's start time was successfully updated
     */
    public boolean updateShiftStartTime(int id, int newStartTime) {
        try {
            createStatement.execute(
                "UPDATE shifts " +
                "SET start_time = " + newStartTime + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Shift " + id + " start_time");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Shift " + id +  " start_time");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * updates the shift end time in 24-HOUR FORMAT (i.e. 1 PM is 13, 12 AM or midnight is 24)
     * @param id the id of the Shift
     * @param newEndTime the new end time of the Shift in 24-hour format (i.e. 1 PM is 13, 12 AM or midnight is 24)
     * @return boolean stating whether the Shift's end time was successfully updated
     */
    public boolean updateShiftEndTime(int id, int newEndTime) {
        try {
            createStatement.execute(
                "UPDATE shifts " +
                "SET end_time = " + newEndTime + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Shift " + id + " end_time");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Shift " + id +  " end_time");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * gets all the Shifts from the database 
     * NOT the actual employee shifts
     * @return ResultSet with all the shift ids and times or null
     */
    public ResultSet getAllShifts() {
        ResultSet shifts = null;
        try {
            shifts = createStatement.executeQuery(
                "SELECT * FROM shifts;"
            );
            // System.out.println("Successfully got Shifts");
        }
        catch (Exception e) {
            // System.out.println("Failed to get Shifts");
            // e.printStackTrace();
        }
        return shifts;
    }

    // EMPLOYEE_SHIFTS JUNCTION TABLE SECTION

    /**
     * gets the Shifts of a single Employee
     * @param id the id of the Employee
     * @return ResultSet of the Employee Shifts for the Employee or null
     */
    public ResultSet getSingleEmployeeShifts(int id) {
        ResultSet employeeShift = null;
        try {
            employeeShift = createStatement.executeQuery(
                "SELECT * FROM employee_shift WHERE employee_id = " + id + ";"
            );
            // System.out.println("Successfully got Employee Shifts for Employee " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Employee Shifts for Employee " + id);
            // e.printStackTrace();
        }
        return employeeShift;
    }

    /**
     * gets all the shifts each Employee works 
     * not to be confused with getAllShifts which gets all the start and end times of the shifts
     * @return ResultSet of All the Employee Shifts or null
     */
    public ResultSet getAllEmployeeShifts() {
        ResultSet employeeShifts = null;
        try {
            employeeShifts = createStatement.executeQuery(
                "SELECT * FROM employee_shift;"
            );
            // System.out.println("Successfully got Employee Shifts");
        }
        catch (Exception e) {
            // System.out.println("Failed to get Employee Shifts");
            // e.printStackTrace();
        }
        return employeeShifts;
    }

    /**
     * attempts to add an Employee Shift to the database
     * @param id the id of the new Employee Shift
     * @param shiftId the id of the Shift
     * @param employeeId the id of the Employee
     * @param month the month that the Shift is in effect
     * @param dayOfWeek the day of the week that the Shift is in effect
     * @return boolean stating whether the Employee Shift was successfully added
     */
    public boolean addEmployeeShift(int id, int shiftId, int employeeId, int month, int dayOfWeek) {
        try {
            createStatement.execute(
                "INSERT INTO employee_shift (id, shift_id, employee_id, month, day_of_week) VALUES (" +
                id + ", " + shiftId + ", " + employeeId + ", " + month + ", " + dayOfWeek + ");"
            );
            // System.out.println("Successfully added Employee Shift");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to add Employee Shift");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to delete an Employee Shift
     * @param id the id of the Employee Shift to be deleted (not the Employee id or Shift id)
     * @return boolean stating whether the Employee Shift was successfully deleted
     */
    public boolean deleteEmployeeShift(int id) {
        try {
            createStatement.execute(
                "DELETE FROM employee_shift WHERE id = " + id + ";"
            );
            // System.out.println("Successfully deleted Employee Shift " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to delete Employee Shift " + id);
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the Shift that the Employee Shift is covering
     * @param id the id of the Employee Shift
     * @param newShiftId the id of the new Shift that the Employee is working
     * @return boolean stating whether the Employee Shift's Shift was successfully updated
     */
    public boolean updateShiftId(int id, int newShiftId) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET shift_id = " + newShiftId + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee Shift " + id + " shift_id");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee Shift " + id + " shift_id");
            // e.printStackTrace();
        }
        return false;
    }
    
    /**
     * attempts to update the Employee that is working the Employee Shift
     * @param id the id of the Employee Shift
     * @param newEmployeeId the id of the new Employee that is working this shift
     * @return boolean stating whether the Employee Shift's Employee was successfully updated
     */
    public boolean updateEmployeeId(int id, int newEmployeeId) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET employee_id = " + newEmployeeId + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee Shift " + id + " employee_id");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee Shift " + id + " employee_id");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the Month that the Employee Shift covers
     * @param id the id of the Employee Shift
     * @param newMonth the new Month that the Employee Shift covers
     * @return boolean stating whether the Employee Shift's Month was successfully updated
     */
    public boolean updateMonth(int id, int newMonth) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET month = " + newMonth + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee Shift " + id + " month");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee Shift " + id + " month");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the Day of Week that the Employee Shift covers
     * @param id the id of the Employee Shift
     * @param newDayOfWeek the new Day of Week that the Employee Shift will cover
     * @return boolean stating whether the Employee Shift's Day of Week was successfully updated
     */
    public boolean updateDayOfWeek(int id, int newDayOfWeek) {
        try {
            createStatement.execute(
                "UPDATE employee_shift " + 
                "SET day_of_week = " + newDayOfWeek + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Employee Shift " + id + " day_of_week");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Employee Shift " + id + " day_of_week");
            // e.printStackTrace();
        }
        return false;
    }

    // INVENTORY SECTION

    /**
     * gets the information of a single Inventory Item from the database
     * @param id the id of the Inventory Item
     * @return ResultSet containing the Inventory Item data or null
     */
    public ResultSet getSingleInventoryItem(int id) {
        ResultSet inventoryItem = null;
        try {
            inventoryItem = createStatement.executeQuery(
                "SELECT * FROM inventory where id = " + id + ";"
            );
            // System.out.println("Got Inventory Item " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Inventory Item " + id);
            // e.printStackTrace();
        }
        return inventoryItem;
    }

    /**
     * gets the information of All the Inventory Items from the database
     * @return ResultSet containing the data of All the Inventory Items or null
     */
    public ResultSet getInventory() {
        ResultSet inventory = null;
        try {
            inventory = createStatement.executeQuery(
                "SELECT * FROM inventory;"
            );
            // System.out.println("Got Inventory");
        }
        catch (Exception e) {
            // System.out.println("Failed to get Inventory");
            // e.printStackTrace();
        }
        return inventory;
    }

    /**
     * attempts to add an Inventory Item to the database
     * @param id the id of the new Inventory Item
     * @param name the name of the new Inventory Item
     * @param lastRestockDate the last Restock Date of the new Inventory Item in "YYYY-MM-DD" format
     * @param amountRemaining the amount remaining of the new Inventory Item
     * @param amountUsed the amount used of the new Inventory Item (usually 0)
     * @return boolean stating whether the new Inventory Item was successfully added to the database
     */
    public boolean addInventoryItem(int id, String name, String lastRestockDate, int amountRemaining, int amountUsed) {
        // ResultSet newInventoryItem = null;
        try {
            // newInventoryItem = createStatement.executeQuery(
            createStatement.execute(
                "INSERT INTO inventory (id, name, last_restock_date, amount_remaining, amount_used) VALUES (" +
                id + ", \'" + name + "\', \'" + lastRestockDate + "\', " + amountRemaining + ", " + amountUsed + ");"
            );
            // System.out.println("Successfully added Inventory Item " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to add Inventory Item");
            // e.printStackTrace();
        }
        return false;
        // return newInventoryItem;
    }

    /**
     * attempts to delete an Inventory Item from the database
     * @param id the id of the Inventory Item to be deleted
     * @return boolean stating whether the Inventory Item was successfully deleted
     */
    public boolean deleteInventoryItem(int id) {
        try {
            createStatement.execute(
                "DELETE FROM inventory WHERE id = " + id + ";"
            );
            // System.out.println("Successfully deleted Inventory Item " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to delete Inventory Item " + id);
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the amount remaining of an Inventory Item
     * @param id the id of the Inventory Item
     * @param newAmountRemaining the new amount remaining of the Inventory Item
     * @return boolean stating whether the Inventory Item's amount remaining was successfully updated
     */
    public boolean updateInventoryItemAmountRemaining(int id, int newAmountRemaining) {
        try {
            // ResultSet inventoryItem = createStatement.executeQuery(
            //     "SELECT * FROM inventory WHERE id = " + id + ";"
            // );
            // inventoryItem.next();
            // int currentAmountRemaining = inventoryItem.getInt("amount_remaining");
            // // System.out.println("Got amount_remaining");
            // int currentAmountUsed = inventoryItem.getInt("amount_used");
            // // System.out.println("Got amount_used");
            // // System.out.println(
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
            // // System.out.println("Successfully updated Inventory Item " + id + " amounts");
            // System.out.println("Successfully updated Inventory Item " + id + " amount remaining");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Inventory Item " + id + " Amount remaining");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the amount used of an Inventory Item
     * @param id the id of the Inventory Item
     * @param newAmountUsed the new amount used of the Inventory Item
     * @return boolean stating whether the Inventory Item's amount used was successfully updated
     */
    public boolean updateInventoryItemAmountUsed(int id, int newAmountUsed) {
        try {
            createStatement.execute(
                "UPDATE inventory " +
                "SET amount_used = " + newAmountUsed + " " +
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Inventory Item " + id + " amount used");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Inventory Item " + id + " Amount used");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update the name of an Inventory Item
     * @param id the id of the Inventory Item
     * @param newName the new name of the Inventory Item
     * @return boolean stating whether the Inventory Item's name was updated
     */
    public boolean updateInventoryItemName(int id, String newName) {
        try {
            createStatement.execute(
                "UPDATE inventory " +
                "SET name = \'" + newName + "\' " +
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Inventory Item " + id + " name");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Inventory Item " + id + " name");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to restock an Inventory Item
     * @param id the id of the Inventory Item
     * @param restockDate the date of the restock in "YYYY-MM-DD" format
     * @param restockAmount the amount restocked
     * @return boolean stating if the restock was successful
     */
    public boolean restockInventoryItem(int id, String restockDate, int restockAmount) {
        try {
            ResultSet inventoryItem = createStatement.executeQuery(
                "SELECT * FROM inventory WHERE id = "+ id + ";"
            );
            inventoryItem.next();
            int amountRemaining = inventoryItem.getInt("amount_remaining");
            createStatement.execute(
                "UPDATE inventory " + 
                "SET amount_remaining = " + (amountRemaining + restockAmount) + ", " + 
                "last_restock_date = \'" + restockDate + "\', " + 
                "amount_used = 0 " +    // amount_used is the amount used after the last restock
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully restocked Inventory Item " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to restock Inventory Item " + id);
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * helper function for addOrder
     * decreases an Inventory Item's amount remaining by 1
     * increases an Inventory Item's amount used by 1
     * @param id the id of the Inventory Item
     * @return boolean stating whether the update was successful
     */
    private boolean useInventoryItem(int id) {
        try {
            ResultSet inventoryItem = getSingleInventoryItem(id);
            inventoryItem.next();
            int amountRemaining = inventoryItem.getInt("amount_remaining");
            int amountUsed = inventoryItem.getInt("amount_used");
            amountRemaining--;
            amountUsed++;
            boolean updateAmountRemaining = updateInventoryItemAmountRemaining(id, amountRemaining);
            boolean updateAmountUsed = updateInventoryItemAmountUsed(id, amountUsed);
            return updateAmountRemaining && updateAmountUsed;
        }
        catch (Exception e) {
            // System.out.println("Failed to use Inventory Item");
            // e.printStackTrace();
        }
        return false;
    }

    // MENU SECTION

    /**
     * gets the information of a single Menu Item from the database
     * @param id the id of the Menu Item
     * @return ResultSet containing the Menu Item data or null
     */
    public ResultSet getSingleMenuItem(int id) {
        ResultSet menuItem = null;
        try {
            menuItem = createStatement.executeQuery(
                "SELECT * FROM menu where id = " + id + ";"
            );
            // System.out.println("Got Menu Item " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Menu Item " + id);
            // e.printStackTrace();
        }
        return menuItem;
    }

    /**
     * gets the information of All the Menu Items from the database
     * @return ResultSet containing the data of All the Menu Items or null
     */
    public ResultSet getMenu() {
        ResultSet menu = null;
        try {
            menu = createStatement.executeQuery(
                "SELECT * FROM menu;"
            );
            // System.out.println("Got Menu");
        }
        catch (Exception e) {
            // System.out.println("Failed to get Menu");
            // e.printStackTrace();
        }
        return menu;
    }

    /**
     * attempts to add a Menu Item to the database
     * @param id the id of the new Menu Item
     * @param name the name of the new Menu Item
     * @param price the price of the new Menu Item
     * @return boolean stating whether the Menu Item was successfully added
     */
    public boolean addMenuItem(int id, String name, double price) {
        // ResultSet newMenuItem = null;
        try {
            // newMenuItem = createStatement.executeQuery(
            createStatement.execute(
                "INSERT INTO menu (id, name, price) VALUES (" +
                id + ", \'" + name + "\', " + price + ");"
            );
            // System.out.println("Successfully added Menu Item " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to add Menu Item");
            // e.printStackTrace();
            return false;
        }
        // return newMenuItem;
    }

    /**
     * attempts to delete a Menu Item from the database
     * @param id the id of the Menu Item to be deleted
     * @return boolean stating whether the Menu Item was successfully deleted
     */
    public boolean deleteMenuItem(int id) {
        try {
            createStatement.execute(
                "DELETE FROM menu WHERE id = " + id + ";"
            );
            // System.out.println("Successfully deleted Menu Item " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to delete Menu Item " + id);
            // e.printStackTrace();
            return false;
        }
    }

    /**
     * attempts to update the price of a Menu Item
     * @param id the id of the Menu Item
     * @param newPrice the new price of the Menu Item
     * @return boolean stating whether the Menu Item was successfully deleted
     */
    public boolean updateMenuItemPrice(int id, double newPrice) {
        try {
            createStatement.execute(
                "UPDATE menu " +
                "SET price = " + newPrice + " " +
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Menu Item " + id + " price");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Faled to update Menu Item " + id + " price");
            // e.printStackTrace();
            return false;
        }
    }

    /**
     * attempts to update the name of a Menu Item
     * @param id the id of the Menu Item
     * @param newName the new name of the Menu Item
     * @return boolean stating whether the Menu Item's name was successfully updated
     */
    public boolean updateMenuItemName(int id, String newName) {
        try {
            createStatement.execute(
                "UPDATE menu " + 
                "SET name = \'" + newName + "\' " +
                "WHERE id = " + id + ";" 
            );
            // System.out.println("Successfully updated Menu Item " + id + " name");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Menu Item " + id + " name");
            // e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the inventory items for a menu item in the database
     * @param id (id of the menu item to update)
     * @param inventoryIds (new inventory ids of the menu item)
     */
    public boolean updateMenuItemInventoryItems(int id, ArrayList<Integer> inventoryIds) {
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
            // System.out.println("Successfully updated Menu Item " + id + "\'s inventory items");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Menu Item " + id + "\'s inventory items");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * Gets inventory item ids corresponsing to a menu item
     * @param id (id of the menu item)
     * @return result set of inventory item ids
     */
    public ResultSet getMenuItemInventoryItems(int id) {
        ResultSet invItems = null;
        try {
            invItems = createStatement.executeQuery(
                "SELECT inventory_id FROM menu_inventory WHERE menu_id = " + id + ";"
            );
            // System.out.println("Got inventory items for Menu Item " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get inventory items from Menu Item " + id);
            // e.printStackTrace();
        }
        return invItems;
    }

    /**
     * Gets add ons for a menu item
     * @param id (id of the mneu item)
     * @return result set of the add on ids
     */
    public ResultSet getMenuItemAddOns(int id) {
        ResultSet addOns = null;
        try {
            addOns = createStatement.executeQuery (
                "SELECT add_on_id FROM menu_add_on WHERE menu_id = " + id + ";"
            );
            // System.out.println("Got add ons for Menu Item" + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get add ons for Menu Item" + id);
            // e.printStackTrace();
        }
        return addOns;
    }

    /**
     * Updates possible add ons for a menu item
     * @param id (id of the menu item)
     * @param addOnIds (ids for new add ons)
     */
    public boolean updateMenuItemAddOns(int id, ArrayList<Integer> addOnIds) {
        try {
            createStatement.execute(
                "DELETE FROM menu_add_on WHERE menu_id = " + id + ";"
            );

            for (int addOnId : addOnIds) {
                createStatement.execute(
                    "INSERT INTO menu_add_on (menu_id, add_on_id) VALUES (" +
                    id + ", " + addOnId + ");"
                );
            }
            // System.out.println("Successfully updated Menu Item " + id + "\'s add ons");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Menu Item " + id + "\'s add ons");
            // e.printStackTrace();
        }
        return false;
    }

    // ADD-ONS SECTION

    /**
     * gets the information of a single Add-On in the database
     * @param id the id of the Add-On
     * @return ResultSet containing the single Add-On data or null
     */
    public ResultSet getSingleAddOn(int id) {
        ResultSet addOn = null;
        try {
            addOn = createStatement.executeQuery(
                "SELECT * FROM add_on WHERE id = " + id + ";"
            );
            // System.out.println("Successfully got Add-On " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Add-On " + id);
            // e.printStackTrace();
        }
        return addOn;
    }

    /**
     * gets the information of All the Add-Ons in the database
     * @return ResultSet containing the data of All the Add-Ons in the database
     */
    public ResultSet getAllAddOns() {
        ResultSet addOns = null;
        try {
            addOns = createStatement.executeQuery(
                "SELECT * FROM add_on;"
            );
            // System.out.println("Successfully got All Add-Ons");
        }
        catch (Exception e) {
            // System.out.println("Failed to get All Add-Ons");
            // e.printStackTrace();
        }
        return addOns;
    }

    /**
     * attempts to delete an Add-On from the database
     * @param id the id of the Add-On to be deleted
     * @return boolean containing whether the Add-On was successfully deleted
     */
    public boolean deleteAddOn(int id) {
        try {
            createStatement.execute(
                "DELETE FROM add_on WHERE id = " + id + ";"
            );
            // System.out.println("Successfully deleted Add-On " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to delete Add-On " + id);
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update an Add-On's name
     * @param id the id of the Add-On
     * @param newName the new name of the Add-On
     * @return boolean containing whether the Add-On's name was successfully updated
     */
    public boolean updateAddOnName(int id, String newName) {
        try {
            createStatement.execute(
                "UPDATE add_on " + 
                "SET name = \'" + newName + "\' " +
                "WHERE id = " + id + ";" 
            );
            // System.out.println("Successfully updated Add-On " + id + " name");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Add-On " + id + " name");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update an Add-On's price
     * @param id the id of the Add-On
     * @param newPrice the new price of the Add-On
     * @return boolean containing whether the Add-On's price was successfully updated
     */
    public boolean updateAddOnPrice(int id, double newPrice) {
        try {
            createStatement.execute(
                "UPDATE add_on " + 
                "SET price = " + newPrice + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Add-On " + id + " price");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Add-On " + id + " price");
            // e.printStackTrace();
        }
        return false;
    }

    /**
     * attempts to update an Add-On's inventory id
     * @param id the id of the Add-On
     * @param newInventoryId the new inventory id of the Add-On
     * @return boolean containing whether the Add-On's inventory id was successfully updated
     */
    public boolean updateAddOnInventoryId(int id, int newInventoryId) {
        try {
            createStatement.execute(
                "UPDATE add_on " + 
                "SET inventory_id = " + newInventoryId + " " + 
                "WHERE id = " + id + ";"
            );
            // System.out.println("Successfully updated Add-On " + id + " inventory id");
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to update Add-On " + id + " inventory id");
            // e.printStackTrace();
        }
        return false;
    }

    // ORDERS SECTION

    /**
     * gets the information of a single Order from the database
     * @param id the id of the Order
     * @return ResultSet containing the Order data or null
     */
    public ResultSet getSingleOrder(int id) {
        ResultSet order = null;
        try {
            order = createStatement.executeQuery(
                "SELECT * FROM orders where id = " + id + ";"
            );
            // System.out.println("Got Order " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Order " + id);
            // e.printStackTrace();
        }
        return order;
    }

    /**
     * gets the information of All the Orders from the database
     * @return ResultSet containing the data of All the Orders or null
     */
    public ResultSet getAllOrders() {
        ResultSet orders = null;
        try {
            orders = createStatement.executeQuery(
                "SELECT * FROM orders;"
            );
            // System.out.println("Got Orders");
        }
        catch (Exception e) {
            // System.out.println("Failed to get Orders");
            // e.printStackTrace();
        }
        return orders;
    }

    /**
     * gets the Menu Item information for an Order
     * @param id the id of the Order
     * @return ResultSet containing the Order's Menu Item data or null
     */
    public ResultSet getMenuItemsFromOrder(int id) {
        ResultSet orderItems = null;
        try {
            orderItems = createStatement.executeQuery(
                "SELECT * FROM order_menu WHERE order_id = " + id + ";"
            );
            // System.out.println("Got Menu Items for Order " + id);
        }
        catch (Exception e) {
            // System.out.println("Failed to get Menu Items for Order " + id);
        }
        return orderItems;
    }

    /**
     * gets the Add-Ons for a Menu Item in an Order
     * @param orderId the id of the Order
     * @param menuItemId the id of the Menu Item
     * @return ResultSet containing the Order's Menu Item's Add-Ons data or null
     */
    public ResultSet getAddOnsForMenuItemInOrder(int orderId, int menuItemId) {
        ResultSet addOns = null;
        try {
            addOns = createStatement.executeQuery(
                "SELECT * FROM order_add_ons WHERE order_menu_junction id = (" + 
                "SELECT id FROM order_menu WHERE order_id = " + orderId + " AND menu_id = " + menuItemId + ");"
            );
        }
        catch (Exception e) {
            // System.out.println("Failed to get Add-Ons for Menu Item in an Order");
        }
        return addOns;
    }

    /**
     * Adds an order to the database and updates respective junction tables
     * Updates the counts of the Inventory
     * @param price (price of the new order)
     * @param dateTime (date and time of the new order)
     * @param menuItemIds (menu ids of items in the order)
     * @param addOnIdsForEachMenuItem (corresponding ids for add ons for each menu item)
    */
    public boolean addOrder(double price, java.util.Date dateTime, ArrayList<Integer> menuItemIds, ArrayList<ArrayList<Integer>> addOnIdsForEachMenuItem) {        
        ResultSet idSet = null;
        int id = -1;
        try {
            idSet = createStatement.executeQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1;");
            idSet.next();
            id = idSet.getInt("id") + 1;
        }
        catch (Exception e) {
            // System.out.println("Failed to get new Order id");
            // e.printStackTrace();
            return false;
        }
        try {
            createStatement.execute(
                "INSERT INTO orders (id, price, date_time) VALUES (" +
                id + ", " + price + ", " + dateTime + ");"
            );

            // update order menu junction table
            ResultSet maxIdSet = null;
            maxIdSet = createStatement.executeQuery (
                "SELECT id FROM order_menu ORDER BY id DESC LIMIT 1;"
            );
            maxIdSet.next();
            int maxId = maxIdSet.getInt("id");

            for (int i = 0; i < menuItemIds.size(); i++) {
                maxId += 1;

                createStatement.execute(
                    "INSERT INTO order_menu (id, order_id, menu_id) VALUES (" +
                    maxId + ", " + id + ", " + menuItemIds.get(i) + ");"
                );

                // updates the inventory count
                ResultSet menuItemInventoryItems = getMenuItemInventoryItems(menuItemIds.get(i));
                while (menuItemInventoryItems.next()) {
                    int inventoryItemId = menuItemInventoryItems.getInt("inventory_id");
                    useInventoryItem(inventoryItemId);
                }

                // update order add_on junction table
                for (int addOnId : addOnIdsForEachMenuItem.get(i)) {
                    createStatement.execute(
                        "INSERT INTO order_add_ons (order_menu_junction_id, add_on_id) VALUES (" +
                        maxId + ", " + addOnId + ");"
                    );
                    // updates the inventory count
                    useInventoryItem(addOnId);
                }
            }
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to add Order");
            // e.printStackTrace();
        }
        return false;
    }

    public boolean deleteOrder(int id) {
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
                    // e.printStackTrace();
                }
                createStatement.executeQuery(
                    "DELETE FROM order_menu WHERE id = " + orderMenuJunctionId + ";"
                );
            }
            createStatement.executeQuery(
                "DELETE FROM orders WHERE id = " + id + ";"
            );
            // System.out.println("Successfully deleted Order " + id);
            return true;
        }
        catch (Exception e) {
            // System.out.println("Failed to completely delete Order " + id);
            // e.printStackTrace();
        }
        return false;
    }

    // REPORTS SECTION

    public ResultSet salesReport(String startDateTime, String endDateTime) {
        ResultSet report = null;
        return report;
    }

    /**
     * generates the Excess Report for Inventory Items given a starting datetime,
     * assuming there are no restocks between the starting datetime and the current
     * time
     * An inventory Item is in excess when it sells less than 10% of its inventory
     * between the starting datetime and the current time
     * 
     * @param timeStamp the datetime to start from in "yyyy-MM-dd HH:mm:ss" format
     * @return ResultSet with the Inventory Item Ids that are in excess
     */
    public ResultSet excessReport(String timeStamp) {
        ResultSet report = null;
        try {
            ResultSet inventoryItems = getInventory();
            // Holds the Inventory Items that sold less than 10% of their inventories during
            // the timeframe
            ArrayList<Integer> excessInventoryIds = new ArrayList<Integer>();
            // Holds the total inventory of each Inventory Item assuming no restocks
            // occurred during the timeframe
            HashMap<Integer, Integer> totalInventory = new HashMap<Integer, Integer>();
            // Holds the amount each Inventory Item is was ordered during the timeframe
            HashMap<Integer, Integer> totalUsed = new HashMap<Integer, Integer>();

            // for every Inventory Item
            while (inventoryItems.next()) {
                int inventoryItemId = inventoryItems.getInt("id");
                int total = inventoryItems.getInt("amount_remaining") + inventoryItems.getInt("amount_used");
                // adds the total inventory of the Inventory Item to the totalInventory HashMap
                totalInventory.put(inventoryItemId, total);
            }

            // new createStatement to not close the orderMenuCount ResultSet when calling
            // getMenuItemInventoryItems in the while loop
            Statement createStatement2 = conn.createStatement();

            // gets every Order that happened between the timeStamp and the current
            // timestamp
            // then gets every Order-Menu Junction associated with those Orders
            // then groups them by Menu Item
            // then counts each Order-Menu Junction associated with each Menu Item
            // then returns those counts along with their associated Menu Items
            ResultSet orderMenuCount = createStatement2.executeQuery(
                    "SELECT COUNT(*),menu_id FROM order_menu WHERE order_id in (" +
                            "SELECT id FROM orders WHERE date_time BETWEEN \'" + timeStamp + "\' AND LOCALTIMESTAMP" +
                            ") GROUP BY menu_id;");

            // for every Menu Item ordered in the timeframe
            while (orderMenuCount.next()) {
                int menuItemId = orderMenuCount.getInt("menu_id");
                int count = orderMenuCount.getInt("count");
                ResultSet menuInventory = getMenuItemInventoryItems(menuItemId);

                // for every Inventory Item used to make the Menu Item
                while (menuInventory.next()) {
                    int inventoryItemId = menuInventory.getInt("inventory_id");
                    // puts count or (count + the value already in the map) into the totalUsed
                    // HashMap
                    totalUsed.merge(inventoryItemId, count, (a, b) -> a + b);
                }
            }

            // gets every Order that happened between the timeStamp and the current
            // timestamp
            // then gets every Order-Menu Junction associated with those Orders
            // then gets every Order-Add-On Junction associated with those Order-Menu
            // Junctions
            // the groups them by Add-On
            // then counts each Order-Add-On Junction associated with each Add-On
            // then returns those counts along with their associated Add-On
            ResultSet orderAddOnsCount = createStatement.executeQuery(
                    "SELECT COUNT(*),add_on_id FROM order_add_ons WHERE order_menu_junction_id in (" +
                            "SELECT id FROM order_menu WHERE order_id in " +
                            "(SELECT id FROM orders WHERE date_time BETWEEN \'" + timeStamp + "\' AND LOCALTIMESTAMP)" +
                            ") GROUP BY add_on_id;");

            // for every Add-On ordered in the timeframe
            while (orderAddOnsCount.next()) {
                int addOnId = orderAddOnsCount.getInt("add_on_id");
                int count = orderAddOnsCount.getInt("count");
                // adds the count to the totalUsed HashMap
                totalUsed.put(addOnId, count);
            }

            for (HashMap.Entry<Integer, Integer> entry : totalUsed.entrySet()) {
                int inventoryId = entry.getKey();
                int amountUsed = entry.getValue();
                System.out.println(inventoryId + " " + amountUsed + " " + (totalInventory.get(inventoryId) * 0.1));
                if (totalInventory.get(inventoryId) * 0.1 > amountUsed) {
                    excessInventoryIds.add(inventoryId);
                }
            }
            if (!excessInventoryIds.isEmpty()) {
                String queryStatement = "SELECT id FROM inventory WHERE id in (";
                for (int i = 0; i < excessInventoryIds.size(); i++) {
                    queryStatement += excessInventoryIds.get(i);
                    if (i < excessInventoryIds.size() - 1) {
                        queryStatement += ",";
                    }
                }
                queryStatement += ");";
                report = createStatement.executeQuery(queryStatement);
            }
            System.out.println("Excess Report generated successfully");
        } catch (Exception e) {
            System.out.println("Failed to generate excess report");
            e.printStackTrace();
        }
        return report;
    }

    public ResultSet restockReport() {
        ResultSet report = null;
        return report;
    }

    public ResultSet menuItemsPopularity(String startDateTime, String endDateTime) {
        ResultSet report = null;
        return report;
    }

}