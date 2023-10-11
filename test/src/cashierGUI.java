import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;

/**
 * @author Spencer Logan
 */
public class cashierGUI {
    public static void main(String[] args) {

        // Create frame
        JFrame frame = new JFrame("Cashier Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(1, 2));

        // Create panels
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(Color.white);
        orderPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 10, Color.black));

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.lightGray);

        // Create labels
        JLabel orderLabel = new JLabel("Order");
        orderLabel.setFont(orderLabel.getFont().deriveFont(100.0f));

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setFont(menuLabel.getFont().deriveFont(100.0f));

        // Create menu buttons for 20 menu items
        JButton menuButton1 = new JButton("Mango LuLu");
        JButton menuButton2 = new JButton("Strawberry LuLu");
        JButton menuButton3 = new JButton("Brown Sugar Deerioca Milk");
        JButton menuButton4 = new JButton("Peach Oolong Tea");
        JButton menuButton5 = new JButton("Jasmine Green Tea");
        JButton menuButton6 = new JButton("Snow Velvet Royal No 9");
        JButton menuButton7 = new JButton("Royal No. 9 Milk Tea");
        JButton menuButton8 = new JButton("The Alley Assam Milk Tea");
        JButton menuButton9 = new JButton("The Alley Trio");
        JButton menuButton10 = new JButton("Lychee Green Tea");
        JButton menuButton11 = new JButton("Passion Fruit Green Tea");
        JButton menuButton12 = new JButton("Original Yogurt Purple Rice");
        JButton menuButton13 = new JButton("Matcha Purple Rice Yogurt");
        JButton menuButton14 = new JButton("Peach Oolong Purple Rice Yogurt");
        JButton menuButton15 = new JButton("Taro Milk Tea");
        JButton menuButton16 = new JButton("Taro Smoothie");
        JButton menuButton17 = new JButton("Mango Frappe");
        JButton menuButton18 = new JButton("Cocoa Cream Cold Brew");
        JButton menuButton19 = new JButton("Milk Tea Cold Brew");
        JButton menuButton20 = new JButton("Orange LuLu");

        // Add labels to panels
        orderPanel.add(orderLabel);
        menuPanel.add(menuLabel);

        // Add menu buttons to menu panel
        menuPanel.add(menuButton1); menuPanel.add(menuButton2); menuPanel.add(menuButton3); menuPanel.add(menuButton4);
        menuPanel.add(menuButton5); menuPanel.add(menuButton6); menuPanel.add(menuButton7); menuPanel.add(menuButton8);
        menuPanel.add(menuButton9); menuPanel.add(menuButton10); menuPanel.add(menuButton11); menuPanel.add(menuButton12);
        menuPanel.add(menuButton13); menuPanel.add(menuButton14); menuPanel.add(menuButton15); menuPanel.add(menuButton16);
        menuPanel.add(menuButton17); menuPanel.add(menuButton18); menuPanel.add(menuButton19); menuPanel.add(menuButton20);

        // Add panels to frame
        frame.add(orderPanel);
        frame.add(menuPanel);
    }
}