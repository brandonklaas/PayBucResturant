/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.enums.ProductStatus;
import core.enums.ProductType;
import core.general.OrderedProducts;
import core.general.Product;
import core.utilities.Session;
import gui.desktop.ModernUI;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brand
 */
public class PreCartDialogue extends javax.swing.JPanel {

    private Session session;
    private Dialogue diag;
    private ArrayList<OrderedProducts> products = new ArrayList<>();
    private ArrayList<Product> searchedProducts = new ArrayList<>();
    private DefaultTableModel tableModel = new DefaultTableModel(); 
    private ModernUI desktop;
//    private ComboBoxModel optionalModel;
//    private ComboBoxModel sidesModel;
//    private ComboBoxModel drinksModel;
    
    /**
     * Creates new form TransactionDialogue
     */
    public PreCartDialogue(Session session) {
        this.session = session;
        initComponents();
        setDefaults();
    }

    public PreCartDialogue( ModernUI desktop, Product product, int quantity) { 
        this.session = session;
        this.desktop = desktop;
        initComponents();
        setDefaults();
        createOrderedProducts(product, quantity); 
    } 
    
    public void createOrderedProducts(Product product, int quantity){
        for(int i = 0; i < quantity; i++){
            OrderedProducts ordered = new OrderedProducts();
            ordered.setProductID(product.getId());
            ordered.setProductName(product.getName());
            ordered.setProductDescription(product.getDescription());
            ordered.setProductPrice(product.getPrice()); 
            ordered.setTaxable(product.isTaxable());  
            
            ordered.setProductStatus(ProductStatus.PENDING.getID());
            ordered.setDrink(-1);
            ordered.setOptional(-1);
            ordered.setSide(-1);
            ordered.setNotes("--"); 
            
            products.add(ordered);
            
            tableModel.insertRow(tableModel.getRowCount(), new Object[]{product.getName(), "<None>","<None>","<None>", "<None>", product.getPrice() });
        }
    }
    
    public void clearTable(){ 
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Side");
        tableModel.addColumn("Drink");
        tableModel.addColumn("Optional");
        tableModel.addColumn("Notes");
        tableModel.addColumn("Price");
        
        preCartTable.setModel(tableModel);
    }
    
    public void clearPanel(){
        productNameTF.setText("");
        productDescTF.setText("");
        notesTf.setText("");
        drinkCB.setSelectedIndex(-1);
        sideCB.setSelectedIndex(-1);
        optionsCB.setSelectedIndex(-1);
    }
    
    public void updateTable(){
        clearTable();
        for (OrderedProducts product : products) {
            tableModel.insertRow(tableModel.getRowCount(), new Object[]{product.getProductName(), (product.getSide() != -1) ? getProductName(product.getSide()) : "<None>",
                 (product.getDrink() != -1) ? getProductName(product.getDrink()) : "<None>",
                (product.getOptional() != -1) ? getProductName(product.getOptional()) : "<None>",
                product.getNotes(), product.getProductPrice()});
        }
        clearPanel();
    }
    
    private void setDefaults() {
        clearTable();
        
        searchedProducts.add(new Product(1, "CocaCola", "Food", 95.50, false,"/icons/Food/w1.png", ProductType.DRINK));
        searchedProducts.add(new Product(2,"Fanta", "Food", 95.50, false,"/icons/Food/w1.png", ProductType.DRINK));
        searchedProducts.add(new Product(3,"Sprite", "Food", 95.50, false,"/icons/Food/w1.png", ProductType.DRINK));
        searchedProducts.add(new Product(4,"Fries", "Food", 95.50, false, "/icons/Food/w1.png", ProductType.SIDE));
        searchedProducts.add(new Product(5,"Roll", "Food", 95.50, false, "/icons/Food/w1.png", ProductType.OPTIONAL));

        drinkCB.addItem("<Select Drink>");
        sideCB.addItem("<Select Side>");
        optionsCB.addItem("<Select Option>");

        for (Product product : searchedProducts) {
            switch (product.getType()) {
                case DRINK:
                    drinkCB.addItem(product.getName());
                    break;
                case SIDE:
                    sideCB.addItem(product.getName());
                    break;
                case OPTIONAL:
                    optionsCB.addItem(product.getName());
                    break;
            }
        }
    }
    
    void setDialogue(Dialogue dialogue) {
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
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        preCartTable = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        productNameTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        productDescTF = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        drinkCB = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        optionsCB = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        sideCB = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        notesTf = new javax.swing.JTextPane();
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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Order Data");

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
        preCartTable.setRowHeight(20);
        preCartTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        preCartTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        preCartTable.setShowVerticalLines(false);
        preCartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preCartTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(preCartTable);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Order Data");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Drink / Beverage :");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Product Desc  :");

        productNameTF.setEditable(false);
        productNameTF.setBackground(new java.awt.Color(255, 255, 255));
        productNameTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        productDescTF.setEditable(false);
        productDescTF.setBackground(new java.awt.Color(255, 255, 255));
        productDescTF.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        productDescTF.setOpaque(false);
        jScrollPane2.setViewportView(productDescTF);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Order Notes  :");

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Product Name :");

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Option :");

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Side :");

        notesTf.setBackground(new java.awt.Color(255, 255, 255));
        notesTf.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        notesTf.setOpaque(false);
        jScrollPane4.setViewportView(notesTf);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(productNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionsCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sideCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(drinkCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel10)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(productNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(drinkCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(optionsCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sideCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void preCartTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preCartTableMouseClicked
        // TODO add your handling code here:
        if(preCartTable.getSelectedRow() > -1){
            productNameTF.setText(products.get(preCartTable.getSelectedRow()).getProductName());
            productDescTF.setText(products.get(preCartTable.getSelectedRow()).getProductDescription());
            notesTf.setText(products.get(preCartTable.getSelectedRow()).getNotes());
            drinkCB.setSelectedItem((products.get(preCartTable.getSelectedRow()).getDrink() > -1) ? 
                                     getProductName(products.get(preCartTable.getSelectedRow()).getDrink()) : "<Select Drink>");
            sideCB.setSelectedItem((products.get(preCartTable.getSelectedRow()).getSide()> -1) ? 
                                     getProductName(products.get(preCartTable.getSelectedRow()).getSide()) : "<Select Side>");
            optionsCB.setSelectedItem((products.get(preCartTable.getSelectedRow()).getOptional() > -1) ?
                                      getProductName(products.get(preCartTable.getSelectedRow()).getOptional()) : "<Select Option>");
        }
    }//GEN-LAST:event_preCartTableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        if(preCartTable.getSelectedRow() > -1){ 
            products.get(preCartTable.getSelectedRow()).setNotes(notesTf.getText());
            products.get(preCartTable.getSelectedRow()).setOptional((optionsCB.getSelectedIndex() > 0) ? getProductID(optionsCB.getSelectedItem().toString()) : -1);
            products.get(preCartTable.getSelectedRow()).setDrink((drinkCB.getSelectedIndex() > 0) ? getProductID(drinkCB.getSelectedItem().toString()) : -1);
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
    private javax.swing.JComboBox<String> drinkCB;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextPane notesTf;
    private javax.swing.JComboBox<String> optionsCB;
    private javax.swing.JTable preCartTable;
    private javax.swing.JTextPane productDescTF;
    private javax.swing.JTextField productNameTF;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> sideCB;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
