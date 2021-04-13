/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.services;

import core.database.DatabaseAccessObject;
import core.general.Employee;
import core.general.Product;
import core.general.Table;
import core.general.Transaction;
import core.pdf.TransactionReportPDF;
import core.utilities.Session;
import gui.desktop.ModernUI;
import gui.dialoguePanels.Dialogue;
import gui.dialoguePanels.FilterDialogue;
import gui.dialoguePanels.OkayDialogue;
import gui.dialoguePanels.YesNoDialogue;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brand
 */
public class TransactionsManagementPanel extends javax.swing.JPanel {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
    private static DecimalFormat df2 = new DecimalFormat("0.00");
    private Session session;
    private DefaultTableModel tableModel;
    private DatabaseAccessObject database;
    private ArrayList<Transaction> array;
    
    private ArrayList<Product> searchedProducts = new ArrayList<>(); 
    private ArrayList<Employee> searchedEmployees = new ArrayList<>(); 
    private ArrayList<Table> searchedTables = new ArrayList<>(); 
    
    private String filtrationStr = null;
    
    private boolean showAll = true;
    private  ModernUI desktop;
    
    /**
     * Creates new form Services
     */
    public TransactionsManagementPanel(Session session, ModernUI desktop) {
        this.session = session;
        this.desktop = desktop;
        this.database = session.getDatabase();
        
        initComponents();
        searchDatabase();
        refreshTable();
        setDefaults();
    }
    
    public void setDefaults(){
        deleteBtn.setVisible(session.getLoggedInUser().isDeleteTransactions());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        bottomButtonsPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        filterBtn = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();
        productBtn1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        transactionsTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1058, 720));
        setLayout(new java.awt.BorderLayout());

        main.setLayout(new java.awt.BorderLayout());

        bottomButtonsPanel.setBackground(new java.awt.Color(247, 249, 249));
        bottomButtonsPanel.setMinimumSize(new java.awt.Dimension(850, 30));
        bottomButtonsPanel.setPreferredSize(new java.awt.Dimension(1058, 40));
        bottomButtonsPanel.setLayout(new java.awt.GridLayout(1, 2));

        jPanel5.setBackground(new java.awt.Color(247, 249, 249));
        jPanel5.setMinimumSize(new java.awt.Dimension(380, 35));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 4));

        filterBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filter.png"))); // NOI18N
        filterBtn.setBorder(null);
        filterBtn.setBorderPainted(false);
        filterBtn.setContentAreaFilled(false);
        filterBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filter-pressed.png"))); // NOI18N
        filterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBtnActionPerformed(evt);
            }
        });
        jPanel5.add(filterBtn);

        reportBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.png"))); // NOI18N
        reportBtn.setBorder(null);
        reportBtn.setBorderPainted(false);
        reportBtn.setContentAreaFilled(false);
        reportBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report-pressed.png"))); // NOI18N
        reportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBtnActionPerformed(evt);
            }
        });
        jPanel5.add(reportBtn);

        productBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/all.png"))); // NOI18N
        productBtn1.setBorder(null);
        productBtn1.setBorderPainted(false);
        productBtn1.setContentAreaFilled(false);
        productBtn1.setPreferredSize(new java.awt.Dimension(130, 35));
        productBtn1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/all-pressed.png"))); // NOI18N
        productBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productBtn1ActionPerformed(evt);
            }
        });
        jPanel5.add(productBtn1);

        bottomButtonsPanel.add(jPanel5);

        jPanel4.setBackground(new java.awt.Color(247, 249, 249));
        jPanel4.setPreferredSize(new java.awt.Dimension(425, 35));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 4));

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        deleteBtn.setBorder(null);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setContentAreaFilled(false);
        deleteBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete-pressed.png"))); // NOI18N
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel4.add(deleteBtn);

        bottomButtonsPanel.add(jPanel4);

        main.add(bottomButtonsPanel, java.awt.BorderLayout.PAGE_END);

        transactionsTable.setBackground(new java.awt.Color(255, 255, 255));
        transactionsTable.setForeground(new java.awt.Color(0, 0, 0));
        transactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Description", "Employee", "Client Name", "Client Cell", "Payment", "Price"
            }
        ));
        transactionsTable.setFillsViewportHeight(true);
        transactionsTable.setGridColor(new java.awt.Color(204, 204, 204));
        transactionsTable.setRowHeight(30);
        transactionsTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        transactionsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(transactionsTable);

        main.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(247, 249, 249));
        jPanel1.setPreferredSize(new java.awt.Dimension(20, 589));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        main.add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel2.setBackground(new java.awt.Color(247, 249, 249));
        jPanel2.setPreferredSize(new java.awt.Dimension(20, 589));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        main.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(247, 249, 249));
        jPanel3.setPreferredSize(new java.awt.Dimension(959, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        main.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        add(main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void filterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBtnActionPerformed
        // TODO add your handling code here:
        desktop.dim(true);
        new Dialogue(null, true, new FilterDialogue(session, this), "Filter");
        desktop.dim(false);
    }//GEN-LAST:event_filterBtnActionPerformed

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        // TODO add your handling code here:
        TransactionReportPDF reportPDF = new TransactionReportPDF((showAll) ? "" : filtrationStr, array, session, showAll);

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(reportPDF.getPath());
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_reportBtnActionPerformed

    private void productBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productBtn1ActionPerformed
        // TODO add your handling code here:
        refreshTable();
    }//GEN-LAST:event_productBtn1ActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        if(transactionsTable.getSelectedRow() > -1) {
            new YesNoDialogue(null, true, "Are you sure you'd like to delete this Transaction", "Delete", this);
        } else {
            new OkayDialogue(null, true, "Select Transaction to Delete");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    
    public void delete() {
        database.delete(array.get(transactionsTable.getSelectedRow()));
        if (showAll) {
            refreshTable();
            new OkayDialogue(null, true, "Transaction Successfuly Deleted");
        } else {
            array.remove(transactionsTable.getSelectedRow());
            
            tableModel = new DefaultTableModel();
            tableModel.addColumn("Date");
            tableModel.addColumn("OrderNumber");
            tableModel.addColumn("Table");
            tableModel.addColumn("Waiter/Emp");
            tableModel.addColumn("Payment");
            tableModel.addColumn("Tip");
            tableModel.addColumn("Total Price");

            if (array.size() > 0) {
                for (Transaction transaction : array) {
                    tableModel.addRow(new Object[]{simpleDateFormat.format(transaction.getDate()), transaction.getOrderNumber(), getTable(transaction.getTableID()).getTableName(),
                        getEmployee(transaction.getEmployeeID()).getFirstname() + " " + getEmployee(transaction.getEmployeeID()).getLastname(), transaction.getPayment(), df2.format(transaction.getTip()), df2.format(transaction.getPrice())});
                }
            }

            transactionsTable.setModel(tableModel);
            transactionsTable.repaint();
            transactionsTable.validate();
            new OkayDialogue(null, true, "Transaction Successfuly Deleted");
        }
    }

    public void searchDatabase(){
        searchedEmployees = database.getEmployees();
        searchedTables = database.getTables();
        searchedProducts = database.getProducts();
    }
    
    public void refreshTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("OrderNumber");
        tableModel.addColumn("Table");
        tableModel.addColumn("Waiter/Emp");
        tableModel.addColumn("Payment"); 
        tableModel.addColumn("Tip");
        tableModel.addColumn("Total Price");

        array = database.getTransactions(); 
        
        if (array.size() > 0) {
            for (Transaction transaction : array) {
                tableModel.addRow(new Object[]{simpleDateFormat.format(transaction.getDate()), transaction.getOrderNumber(), getTable(transaction.getTableID()).getTableName(),
                getEmployee(transaction.getEmployeeID()).getFirstname()+" "+getEmployee(transaction.getEmployeeID()).getLastname() , transaction.getPayment(), df2.format(transaction.getTip()), df2.format(transaction.getPrice())});
            }
        }

        transactionsTable.setModel(tableModel);
        transactionsTable.repaint();
        transactionsTable.validate();
        
        showAll = true;
    }
    
    public void refreshFilteredTable(ArrayList<Transaction> array, String filtrationStr) {
        this.filtrationStr = filtrationStr;

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("OrderNumber");
        tableModel.addColumn("Table");
        tableModel.addColumn("Waiter/Emp");
        tableModel.addColumn("Payment");
        tableModel.addColumn("Tip");
        tableModel.addColumn("Total Price");

        if (array.size() > 0) {
            for (Transaction transaction : array) {
                tableModel.addRow(new Object[]{simpleDateFormat.format(transaction.getDate()), transaction.getOrderNumber(), getTable(transaction.getTableID()).getTableName(),
                    getEmployee(transaction.getEmployeeID()).getFirstname() + " " + getEmployee(transaction.getEmployeeID()).getLastname(), transaction.getPayment(), df2.format(transaction.getTip()), df2.format(transaction.getPrice())});
            }
        }

        transactionsTable.setModel(tableModel);
        transactionsTable.repaint();
        transactionsTable.validate();
        
        showAll = false;
    }
    
    
    
    public Table getTable(int id){
        for(Table table : searchedTables){
            if(table.getId() == id){
                return table;
            }
        }
        return null;
    }
    
    public Employee getEmployee(int id){
        for(Employee employee : searchedEmployees){
            if(employee.getId() == id){
                return employee;
            }
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomButtonsPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton filterBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    private javax.swing.JButton productBtn1;
    private javax.swing.JButton reportBtn;
    private javax.swing.JTable transactionsTable;
    // End of variables declaration//GEN-END:variables

}
