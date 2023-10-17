import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        
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

        // ResultSet test = conn.excessReport("2022-01-01 00:00:00");
        // System.out.println("ids");
        // while (test.next()) {
        //     int id = test.getInt("id");
        //     System.out.println(id);
        // }

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

        // testing update menu inventory junction
        // ArrayList<Integer> ids = new ArrayList<Integer>(Arrays.asList(11, 6, 16, 17));
        // conn.updateMenuItemInventoryItems(1, ids);

        // // testing update menu add on junction
        // ArrayList<Integer> addOnIds = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 11));
        // conn.updateMenuItemAddOns(1, addOnIds);

        ResultSet test = conn.menuItemsPopularity("2022/11/01", "2022/11/27", 10);
        while (test.next()) {
            int count = test.getInt("order_count");
            System.out.println(count);
        }

        conn.closeDatabase();
    }
}
