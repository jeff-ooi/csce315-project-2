package cashierpos;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * @author Spencer Logan
 */
public class OrderMenu extends javax.swing.JFrame {
    private ArrayList<item> items = new ArrayList<item>();
    private double total = 0.00;
    Database database = new Database("csce315_971_spencertlogan", "password");
    private ArrayList<Integer> menuIDList = new ArrayList<Integer>();
    private ArrayList<String> menuNameList = new ArrayList<String>();

    /**
     * Creates new form NewJFrame
     */
    public OrderMenu() {
        initComponents();
    }

    /**
     * Gets the new item from newitem.dat and adds it to the order text
     */
    public void getNewItem() {
        try {
            FileInputStream file = new FileInputStream("newitem.dat");
            ObjectInputStream fin = new ObjectInputStream(file);

            try {
                items.add((item) fin.readObject());
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                fin.close();
                file.close();
                updateOrderText();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Updates the order text and subtotal, tax, and total text fields
     */
    private void updateOrderText() {
        String orderText = "";
        double subtotal = 0;
        double tax = 0;
        total = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        for (item i : items) {
            orderText += i.getName() + "\t\t$" + i.getPrice() + "\n";
            for (int j = 0; j < i.getAddOns().size(); j++) {
                ResultSet rs = database.getSingleAddOn(i.getAddOns().get(j));
                try {
                    rs.next();
                    orderText += "\t" + i.getAddOnAmount(j) + " " + rs.getString("name") + "\t\t$"
                            + df.format(rs.getDouble("price"))
                            + "\n";
                    subtotal += rs.getDouble("price");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            subtotal += i.getPrice();
        }

        tax = subtotal * 0.0825;
        total = subtotal + tax;

        order_text.setText(orderText);
        subtotal_text.setText("$" + df.format(subtotal));
        tax_text.setText("$" + df.format(tax));
        total_text.setText("$" + df.format(total));
    }

    @SuppressWarnings("unchecked")
    // GEN-BEGIN:initComponents
    private void initComponents() {

        try {
            ResultSet rs = database.getMenu();
            while (rs.next()) {
                menuIDList.add(rs.getInt("id"));
                menuNameList.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        order_text = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        subtotal_text = new javax.swing.JTextField();
        tax_text = new javax.swing.JTextField();
        total_text = new javax.swing.JTextField();
        checkout_button = new javax.swing.JButton();
        loadItem = new javax.swing.JButton();
        clearOrder = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(new JTableButtonModel());
        jTable1.setDefaultRenderer(Object.class, new JTableButtonRenderer());
        JScrollPane scrollPane = new JScrollPane(jTable1);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 86)); // NOI18N
        jLabel1.setText("Menu");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 86)); // NOI18N
        jLabel2.setText("Order");

        order_text.setColumns(20);
        order_text.setRows(5);
        jScrollPane1.setViewportView(order_text);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Subtotal");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Tax");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Total");

        subtotal_text.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        subtotal_text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subtotal_text.setText("$0.00");
        subtotal_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtotal_textActionPerformed(evt);
            }
        });

        tax_text.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tax_text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tax_text.setText("$0.00");

        total_text.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        total_text.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        total_text.setText("$0.00");

        checkout_button.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        checkout_button.setText("Checkout");
        checkout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkout_buttonActionPerformed(evt);
            }
        });

        loadItem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loadItem.setText("Load Item");
        loadItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadItemActionPerformed(evt);
            }
        });

        clearOrder.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearOrder.setForeground(new java.awt.Color(255, 51, 51));
        clearOrder.setText("Clear Order");
        clearOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearOrderActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jTable1.setModel(new JTableButtonModel());

        DefaultTableModel model = new DefaultTableModel(new String[] { "Menu Item" }, 0);
        jTable1.setModel(model);

        jTable1.getColumnModel().getColumn(0).setCellRenderer(new JTableButtonRenderer());
        jTable1.getColumnModel().getColumn(0).setCellEditor(new ButtonCellEditor());
        // add rows to button table
        for (int i = 0; i < menuIDList.size(); i++) {
            JButton button = new JButton(menuNameList.get(i));
            button.setFont(new java.awt.Font("Segoe UI", 1, 18));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new AddOnMenu(menuIDList.get(jTable1.getSelectedRow())).setVisible(true);
                }
            });
            // add new button row
            model.addRow(new Object[] { button });
        }
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.setRowHeight(50);

        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            // jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(201, 201, 201))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(634, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(checkout_button,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 358,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(57, 57, 57)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                        .addComponent(jLabel5,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(jLabel4,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(jLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                110,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(subtotal_text)
                                                                        .addComponent(total_text)
                                                                        .addComponent(tax_text,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                100,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addComponent(loadItem,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 182,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(207, 207, 207)
                                                .addComponent(clearOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 182,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(loadItem, javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(4, 4, 4)
                                                                .addComponent(subtotal_text,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(tax_text, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(total_text,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(56, 56, 56)
                                                .addComponent(checkout_button, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(clearOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 571,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(16, Short.MAX_VALUE)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadItemActionPerformed
        getNewItem();
    }// GEN-LAST:event_loadItemActionPerformed

    private void clearOrderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clearOrderActionPerformed
        items.clear();
        updateOrderText();
    }// GEN-LAST:event_clearOrderActionPerformed

    private void subtotal_textActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_subtotal_textActionPerformed
    }// GEN-LAST:event_subtotal_textActionPerformed

    /**
     * Adds the order to the database and clears the order text field
     * 
     * @param evt the button click that triggered this action
     */
    private void checkout_buttonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_checkout_buttonActionPerformed
        ArrayList<Integer> item_ids = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> add_on_ids = new ArrayList<ArrayList<Integer>>();
        for (item i : items) {
            item_ids.add(i.getId());
            add_on_ids.add(i.getAddOns());
        }
        try {
            DecimalFormat df = new DecimalFormat("#.00");
            database.addOrder(Double.parseDouble(df.format(total)), new Timestamp(System.currentTimeMillis()), item_ids,
                    add_on_ids);
        } catch (Exception e) {
            System.out.println(e);
        }
        items.clear();
        updateOrderText();
    }// GEN-LAST:event_checkout_buttonActionPerformed

    /**
     * Custom cell editor for the menu buttons
     */
    class ButtonCellEditor extends DefaultCellEditor {
        private JButton button;
        private Object clickedValue;

        public ButtonCellEditor() {
            super(new JCheckBox());
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    // Handle the button click action here.
                    // For example, open the AddOnMenu.
                    int row = jTable1.getSelectedRow();
                    new AddOnMenu(menuIDList.get(row)).setVisible(true);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            clickedValue = value;
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return clickedValue;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkout_button;
    private javax.swing.JButton clearOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton loadItem;
    private javax.swing.JTextArea order_text;
    private javax.swing.JTextField subtotal_text;
    private javax.swing.JTextField tax_text;
    private javax.swing.JTextField total_text;
    // End of variables declaration//GEN-END:variables
}

/**
 * Custom table model for the menu buttons
 */
class JTableButtonModel extends AbstractTableModel {
    private Object[][] rows = { { "Button1", new JButton("Button1") }, { "Button2", new JButton("Button2") },
            { "Button3", new JButton("Button3") }, { "Button4", new JButton("Button4") } };
    private String[] columns = { "Count", "Buttons" };

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int row, int column) {
        return rows[row][column];
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}

/**
 * Custom table cell renderer for the menu buttons
 */
class JTableButtonRenderer extends DefaultTableCellRenderer {
    public JTableButtonRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (value instanceof JButton) {
            JButton button = (JButton) value;
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}