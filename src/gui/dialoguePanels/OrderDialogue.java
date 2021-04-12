/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.database.DatabaseAccessObject;
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
import javax.swing.ButtonGroup;
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
        ButtonGroup bg = new ButtonGroup();
        bg.add(sitInBtn);
        bg.add(takeawayBtn);
        
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
        jLabel2 = new javax.swing.JLabel();
        takeawayBtn = new javax.swing.JToggleButton();
        sitInBtn = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Order Data");

        orderNumberTF.setEditable(false);
        orderNumberTF.setBackground(new java.awt.Color(255, 255, 255));
        orderNumberTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Order Number :");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Waiter / Employee :");

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Table :");

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Order Details");

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

        notesTf.setBackground(new java.awt.Color(255, 255, 255));
        notesTf.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        notesTf.setOpaque(false);
        jScrollPane4.setViewportView(notesTf);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Order Notes  :");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Product Desc  :");

        productDescTF.setEditable(false);
        productDescTF.setBackground(new java.awt.Color(255, 255, 255));
        productDescTF.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        productDescTF.setOpaque(false);
        jScrollPane2.setViewportView(productDescTF);

        productNameTF.setEditable(false);
        productNameTF.setBackground(new java.awt.Color(255, 255, 255));
        productNameTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Product Name :");

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Order Data");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Order Type :");

        takeawayBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/takeaway.png"))); // NOI18N
        takeawayBtn.setBorder(null);
        takeawayBtn.setBorderPainted(false);
        takeawayBtn.setContentAreaFilled(false);
        takeawayBtn.setMaximumSize(new java.awt.Dimension(130, 33));
        takeawayBtn.setMinimumSize(new java.awt.Dimension(130, 33));
        takeawayBtn.setPreferredSize(new java.awt.Dimension(32, 130));
        takeawayBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/takeaway-pressed.png"))); // NOI18N
        takeawayBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/takeaway-pressed.png"))); // NOI18N
        takeawayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeawayBtnActionPerformed(evt);
            }
        });

        sitInBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sit.png"))); // NOI18N
        sitInBtn.setSelected(true);
        sitInBtn.setBorder(null);
        sitInBtn.setBorderPainted(false);
        sitInBtn.setContentAreaFilled(false);
        sitInBtn.setMaximumSize(new java.awt.Dimension(130, 33));
        sitInBtn.setMinimumSize(new java.awt.Dimension(130, 33));
        sitInBtn.setPreferredSize(new java.awt.Dimension(32, 130));
        sitInBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sit-pressed.png"))); // NOI18N
        sitInBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sit-pressed.png"))); // NOI18N
        sitInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sitInBtnActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Side :");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Option :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(orderNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(waiterCB, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(sitInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(takeawayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(tableCB, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(productNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(optionsCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sideCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel11)
                .addGap(4, 4, 4)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(orderNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(waiterCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(sitInBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(takeawayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(tableCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel17)
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(productNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(optionsCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sideCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
        if(products.size() > 0 && waiterCB.getSelectedIndex() > 0){
            currentOrder.setEmployeeID(searchedEmployees.get(waiterCB.getSelectedIndex()-1).getId());
            currentOrder.setTableID(searchedTables.get(tableCB.getSelectedIndex()-1).getId());
            currentOrder.setProducts(products);
            
            for(OrderedProducts ordered : currentOrder.getProducts()){
                ordered.setOrderNumber(currentOrder.getOrderNumber());
            }
            
            if(database.insert(currentOrder)){
                new OkayDialogue(desktop, true, "Order recored successfully");
                diag.dispose();
            } else {
                new OkayDialogue(desktop, true, "Order not recored");
            }
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void sitInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sitInBtnActionPerformed
        // TODO add your handling code here:
        if(sitInBtn.isSelected()){
            tableCB.setEnabled(true);
        }
    }//GEN-LAST:event_sitInBtnActionPerformed

    private void takeawayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takeawayBtnActionPerformed
        // TODO add your handling code here: 
        if(takeawayBtn.isSelected()){
            tableCB.setEnabled(false);
            tableCB.setSelectedIndex(0);
        }
    }//GEN-LAST:event_takeawayBtnActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JToggleButton sitInBtn;
    private javax.swing.JComboBox<String> tableCB;
    private javax.swing.JToggleButton takeawayBtn;
    private javax.swing.JButton updateBtn;
    private javax.swing.JComboBox<String> waiterCB;
    // End of variables declaration//GEN-END:variables

}
