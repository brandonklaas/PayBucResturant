/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.database.DatabaseAccessObject;
import core.enums.ProductType;
import core.general.Employee;
import core.general.Order;
import core.general.OrderedProducts;
import core.general.Product;
import core.general.Table;
import core.utilities.Session;
import gui.desktop.ModernUI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brand
 */
public class OrderDialogue extends javax.swing.JPanel {

    private Session session;
    private DatabaseAccessObject database;
    private Dialogue diag;
    private ArrayList<OrderedProducts> products = new ArrayList<>(); 
    
    
    private DateTimeFormatter orderNumberFormat = DateTimeFormatter.ofPattern("yyMMddHHmmss"); 
    
    private ArrayList<Product> searchedProducts = new ArrayList<>(); 
    private ArrayList<Employee> searchedEmployees = new ArrayList<>(); 
    private ArrayList<Table> searchedTables = new ArrayList<>(); 
    
    private DefaultTableModel tableModel = new DefaultTableModel(); 
    private ModernUI desktop;
    
    private Order currentOrder;
//    private ComboBoxModel optionalModel;
//    private ComboBoxModel sidesModel;
//    private ComboBoxModel drinksModel;
    
    /**
     * Creates new form TransactionDialogue
     */
    public OrderDialogue(Session session) {
        this.session = session;
        initComponents();
        setDefaults();
    }

    public OrderDialogue( ModernUI desktop, ArrayList<OrderedProducts> products, Session session) { 
        this.session = session;
        this.database = session.getDatabase();
        this.desktop = desktop;
        this.products = products;
        initComponents();
        createOrder();
        searchDatabase();
        setDefaults();
        fillTable();
    } 
    
    public void createOrder(){
        currentOrder = new Order();
        currentOrder.setOrderNumber(orderNumberFormat.format(LocalDateTime.now()));
        orderNumberTF.setText(currentOrder.getOrderNumber());
    }
    
    public void fillTable(){
        for(int i = 0; i < products.size(); i++){  
            tableModel.insertRow(tableModel.getRowCount(), new Object[]{products.get(i).getProductName(), (products.get(i).getSide() != -1) ? getProductName(products.get(i).getSide()) : "<None>", 
                (products.get(i).getOptional() != -1) ? getProductName(products.get(i).getOptional()) : "<None>",
                products.get(i).getNotes(), products.get(i).getProductPrice()});
        }
    }
    
    public void searchDatabase(){
        searchedEmployees = database.getEmployees();
        searchedTables = database.getTables();
        searchedProducts = database.getProducts();
    }
    
    public void clearTable(){ 
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Side"); 
        tableModel.addColumn("Optional");
        tableModel.addColumn("Notes");
        tableModel.addColumn("Price");
        
        preCartTable.setModel(tableModel);
    }
    
    public void clearPanel(){
        orderNumberTF.setText("");
        productDescTF.setText("");
        notesTf.setText(""); 
        sideCB.setSelectedIndex(-1);
        optionsCB.setSelectedIndex(-1);
    }
    
    public void updateTable(){
        clearTable();
        for (OrderedProducts product : products) {
            tableModel.insertRow(tableModel.getRowCount(), new Object[]{product.getProductName(), (product.getSide() != -1) ? getProductName(product.getSide()) : "<None>", 
                (product.getOptional() != -1) ? getProductName(product.getOptional()) : "<None>",
                product.getNotes(), product.getProductPrice()});
        }
        clearPanel();
    }
    
    private void setDefaults() {
        clearTable();
        
        sideCB.addItem("<Select Side>");
        optionsCB.addItem("<Select Option>");
        waiterCB.addItem("<Select Waiter/Waitress>");
        tableCB.addItem("<Select Table>");

        for (Product product : searchedProducts) {
            switch (product.getType()) { 
                case SIDE:
                    sideCB.addItem(product.getName());
                    break;
                case OPTIONAL:
                    optionsCB.addItem(product.getName());
                    break;
            }
        }
        
        
        for (Employee employee : searchedEmployees) {
            switch (employee.getOccupation()) { 
                case "Waiter":
                    waiterCB.addItem(employee.getFirstname()+" , "+ employee.getLastname());
                    break;
            }
        }
        
        for (Table table : searchedTables) { 
            tableCB.addItem(table.getTableName()); 
        }
    }
    
    public void setDialogue(Dialogue dialogue) {
        diag = dialogue;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        orderNumberTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        waiterCB = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        tableCB = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        preCartTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        notesTf = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        productDescTF = new javax.swing.JTextPane();
        sideCB = new javax.swing.JComboBox<>();
        optionsCB = new javax.swing.JComboBox<>();
        productNameTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        buttonsPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);
        jPanel1.add(jSeparator3);
        jSeparator3.setBounds(20, 43, 680, 10);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Order Data");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(20, 23, 290, 16);

        orderNumberTF.setEditable(false);
        orderNumberTF.setBackground(new java.awt.Color(255, 255, 255));
        orderNumberTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        jPanel1.add(orderNumberTF);
        orderNumberTF.setBounds(168, 54, 190, 24);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Order Number :");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 54, 120, 16);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Waiter / Employee :");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(30, 90, 120, 16);

        jPanel1.add(waiterCB);
        waiterCB.setBounds(170, 90, 190, 26);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Table :");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(30, 130, 120, 16);

        jPanel1.add(tableCB);
        tableCB.setBounds(170, 130, 190, 26);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Order Details");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 180, 290, 16);
        jPanel1.add(jSeparator4);
        jSeparator4.setBounds(20, 200, 680, 10);

        preCartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        preCartTable.setFillsViewportHeight(true);
        preCartTable.setGridColor(new java.awt.Color(102, 102, 102));
        preCartTable.setMaximumSize(new java.awt.Dimension(1000, 64));
        preCartTable.setRowHeight(20);
        preCartTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        preCartTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        preCartTable.setShowVerticalLines(false);
        jScrollPane1.setViewportView(preCartTable);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 220, 660, 260);

        notesTf.setBackground(new java.awt.Color(255, 255, 255));
        notesTf.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        notesTf.setOpaque(false);
        jScrollPane4.setViewportView(notesTf);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(170, 640, 190, 70);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Order Notes  :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(30, 640, 120, 16);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Product Desc  :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, 560, 120, 16);

        productDescTF.setEditable(false);
        productDescTF.setBackground(new java.awt.Color(255, 255, 255));
        productDescTF.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        productDescTF.setOpaque(false);
        jScrollPane2.setViewportView(productDescTF);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(170, 560, 190, 70);

        jPanel1.add(sideCB);
        sideCB.setBounds(500, 570, 190, 26);

        jPanel1.add(optionsCB);
        optionsCB.setBounds(500, 530, 190, 26);

        productNameTF.setEditable(false);
        productNameTF.setBackground(new java.awt.Color(255, 255, 255));
        productNameTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        jPanel1.add(productNameTF);
        productNameTF.setBounds(170, 530, 190, 24);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Product Name :");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(30, 530, 120, 16);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Order Data");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(20, 500, 290, 16);
        jPanel1.add(jSeparator5);
        jSeparator5.setBounds(20, 520, 680, 10);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(10, 45));
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        saveBtn.setBorder(null);
        saveBtn.setBorderPainted(false);
        saveBtn.setContentAreaFilled(false);
        saveBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-pressed.png"))); // NOI18N
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        buttonsPanel.add(saveBtn);

        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        updateBtn.setBorder(null);
        updateBtn.setBorderPainted(false);
        updateBtn.setContentAreaFilled(false);
        updateBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update-pressed.png"))); // NOI18N
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        buttonsPanel.add(updateBtn);

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
        buttonsPanel.add(deleteBtn);

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        cancelBtn.setBorder(null);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel-pressed.png"))); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelBtn);

        add(buttonsPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        diag.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        if(preCartTable.getSelectedRow() > -1){ 
            products.get(preCartTable.getSelectedRow()).setNotes(notesTf.getText());
            products.get(preCartTable.getSelectedRow()).setOptional((optionsCB.getSelectedIndex() > 0) ? getProductID(optionsCB.getSelectedItem().toString()) : -1);
            products.get(preCartTable.getSelectedRow()).setSide((sideCB.getSelectedIndex() > 0) ? getProductID(sideCB.getSelectedItem().toString()) : -1);
            updateTable();
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        desktop.addToOrder(products);
        diag.dispose();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBtnActionPerformed

    public int getProductID(String productName){
        
        for(int i = 0; i < searchedProducts.size(); i++){
            if(searchedProducts.get(i).getName().equals(productName)){
                return searchedProducts.get(i).getId();
            }
        }
        return -1;
    }
    
    public String getProductName(int productID){
        for(Product product : searchedProducts){
            if(product.getId() == productID){
                return product.getName();
            } 
        }
        return "<None>";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextPane notesTf;
    private javax.swing.JComboBox<String> optionsCB;
    private javax.swing.JTextField orderNumberTF;
    private javax.swing.JTable preCartTable;
    private javax.swing.JTextPane productDescTF;
    private javax.swing.JTextField productNameTF;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> sideCB;
    private javax.swing.JComboBox<String> tableCB;
    private javax.swing.JButton updateBtn;
    private javax.swing.JComboBox<String> waiterCB;
    // End of variables declaration//GEN-END:variables

}
