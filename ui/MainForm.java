/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import controller.Controller;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Rental;
/**
 *
 * @author USER
 */
public class MainForm extends JFrame {
    private final Controller controller;

    private final JTextField nameField;
    private final JTextField contactField;
    private final JTextField dateStartedField;
    private final JTextField roomNumberField;
    private final JTextField monthlyBillField;
    private final JTextField emergencyContactField;
    private final JTable boarderTable;
    private final DefaultTableModel tableModel;
    

  public MainForm(Controller controller) {
    this.controller = controller;

    initComponents();

    setTitle("Boarding House Management System");
    setLocationRelativeTo(null);

    // Connect helper variables to the actual NetBeans components
    nameField = jTextField1Name;
    contactField = jTextField2ContactNumber;
    dateStartedField = jTextField3DateStarted;
    roomNumberField = jTextField2RoomNumber;
    monthlyBillField = jTextField2MonthlyBill;
    emergencyContactField = jTextField1;
    boarderTable = jTable1;

    // Initialize table model
    tableModel = (DefaultTableModel) boarderTable.getModel();

    // Set default date
    dateStartedField.setText(LocalDate.now().toString());

    // Button functions
    jButton1.addActionListener(e -> addBoarder());
    jButton2.addActionListener(e -> clearFields());
    jButton3.addActionListener(e -> markSelectedAsPaid());
    jButton4.addActionListener(e -> deleteSelectedBoarder());

    // Load saved records
    refreshTable();
}

    

    private void addField(JPanel panel, String label, JTextField field, int column, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = column;
        gbc.gridy = row * 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(7, 8, 3, 8);
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        panel.add(jLabel, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 8, 7, 8);
        panel.add(field, gbc);
    }

    private void addBoarder() {
        try {
            controller.addBoarder(
                    nameField.getText(),
                    contactField.getText(),
                    dateStartedField.getText(),
                    roomNumberField.getText(),
                    monthlyBillField.getText(),
                    emergencyContactField.getText()
            );
            refreshTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Boarder added successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Unable to add boarder", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void markSelectedAsPaid() {
        int row = getSelectedModelRow();
        if (row < 0) {
            showSelectMessage();
            return;
        }
        if (controller.markAsPaid(row)) {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Payment status updated to Paid.");
        }
    }

    private void deleteSelectedBoarder() {
        int row = getSelectedModelRow();
        if (row < 0) {
            showSelectMessage();
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this,
                "Delete the selected boarder?", "Confirm Delete",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION && controller.deleteBoarder(row)) {
            refreshTable();
        }
    }

    private int getSelectedModelRow() {
    if (boarderTable == null) {
        return -1;
    }

    int selected = boarderTable.getSelectedRow();

    return selected < 0
            ? -1
            : boarderTable.convertRowIndexToModel(selected);
}

   private void refreshTable() {

    List<Rental> rentals =
            controller.getBoardersSortedByDate();

    tableModel.setRowCount(0);

    for (Rental rental : rentals) {

        Object[] row = {
            rental.getName(),
            rental.getRoomNumber(),
            rental.getContactNo(),
            rental.getEmergencyContactNo(),
            rental.getFormattedDateStarted(),
            rental.getFormattedDueDate(),
            rental.getPaymentStatus()
        };

        tableModel.addRow(row);
    }
}
    private void clearFields() {
        nameField.setText("");
        contactField.setText("");
        dateStartedField.setText(LocalDate.now().toString());
        roomNumberField.setText("");
        monthlyBillField.setText("");
        emergencyContactField.setText("");
        nameField.requestFocusInWindow();
        boarderTable.clearSelection();
    }

    private void showSelectMessage() {
        JOptionPane.showMessageDialog(this, "Please select a boarder from the table first.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1Name = new javax.swing.JLabel();
        jTextField1Name = new javax.swing.JTextField();
        jLabel2ContactNumber = new javax.swing.JLabel();
        jTextField2ContactNumber = new javax.swing.JTextField();
        jLabel3DateStarted = new javax.swing.JLabel();
        jTextField3DateStarted = new javax.swing.JTextField();
        jLabel5RoomNumber = new javax.swing.JLabel();
        jTextField2RoomNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2MonthlyBill = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel4.setText("Boarding House Management System");

        jLabel1Name.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1Name.setText("Name: ");

        jLabel2ContactNumber.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2ContactNumber.setText("Contact No.:");

        jTextField2ContactNumber.addActionListener(this::jTextField2ContactNumberActionPerformed);

        jLabel3DateStarted.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3DateStarted.setText("Date Started:");

        jLabel5RoomNumber.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5RoomNumber.setText("Room Number:");

        jTextField2RoomNumber.addActionListener(this::jTextField2RoomNumberActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Monthly Bill:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Emergency Contact No.:");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Add Boarder");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Cancel");

        jLayeredPane1.setLayer(jLabel1Name, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField1Name, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2ContactNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField2ContactNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3DateStarted, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField3DateStarted, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5RoomNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField2RoomNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField2MonthlyBill, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2ContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2ContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1Name)
                    .addComponent(jLabel3DateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3DateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField2MonthlyBill, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5RoomNumber)
                    .addComponent(jLabel1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2ContactNumber)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2ContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2MonthlyBill, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3DateStarted)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3DateStarted, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Boarder Name", "Assigned Room", "Contact Number", "Emergency Contact No.", "Date Started", "Due Date", "Payment Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Mark as Paid");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("DELETE Tenant");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLayeredPane1)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2RoomNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2RoomNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2RoomNumberActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ContactNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ContactNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ContactNumberActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1Name;
    private javax.swing.JLabel jLabel2ContactNumber;
    private javax.swing.JLabel jLabel3DateStarted;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel5RoomNumber;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField1Name;
    private javax.swing.JTextField jTextField2ContactNumber;
    private javax.swing.JTextField jTextField2MonthlyBill;
    private javax.swing.JTextField jTextField2RoomNumber;
    private javax.swing.JTextField jTextField3DateStarted;
    // End of variables declaration//GEN-END:variables
}
