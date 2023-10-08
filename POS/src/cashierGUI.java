import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

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

        // Add labels to panels
        orderPanel.add(orderLabel);
        menuPanel.add(menuLabel);

        // Add panels to frame
        frame.add(orderPanel);
        frame.add(menuPanel);
    }
}