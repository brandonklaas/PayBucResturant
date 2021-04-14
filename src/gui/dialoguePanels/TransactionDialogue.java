/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import com.sun.glass.events.KeyEvent;
import core.database.DatabaseAccessObject;
import core.enums.OrderStatus;
import core.enums.PaymentType;
import core.enums.ProductStatus;
import core.general.Employee;
import core.general.Order;
import core.general.OrderedProducts;
import core.general.Product;
import core.general.Table;
import core.general.Transaction;
import core.utilities.Session;
import gui.desktop.ModernUI;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brand
 */
public class TransactionDialogue extends javax.swing.JPanel {

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
     
    private static DecimalFormat df2 = new DecimalFormat("0.00");
    
    private Order currentOrder;
//    private ComboBoxModel optionalModel;
//    private ComboBoxModel sidesModel;
//    private ComboBoxModel drinksModel;
    
    
    private double subTotal = 0.00;
    private double grandTotal = 0.00;
    private double tipTotal = 0.00;
    private int vat;
    
    /**
     * Creates new form TransactionDialogue
     */
    public TransactionDialogue(Session session) {
        this.session = session;
        initComponents();
        setDefaults();
    }

    public TransactionDialogue( ModernUI desktop, Order order, Session session) { 
        this.session = session;
        this.database = session.getDatabase();
        this.desktop = desktop;
        this.products = order.getProducts();
        this.currentOrder = order;
        this.vat = Integer.parseInt(session.getSettings().getTax());
        
        initComponents(); 
        searchDatabase();
        setDefaults();
        fillTable();
        fillOrder();
    } 
    
    public void fillOrder(){
        orderNumberTF.setText(currentOrder.getOrderNumber());
        Employee temp = getEmployee(currentOrder.getEmployeeID());
        waiterCB.setSelectedItem(temp.getFirstname()+" , "+temp.getLastname());
        tableCB.setSelectedItem(getTable(currentOrder.getTableID()).getTableName());
        
        for(OrderedProducts prod : products){
            subTotal+=prod.getProductPrice();
        }
        subTotalTf.setText(df2.format(subTotal));
        vatTf.setText(vat+" %");
        tipTF.setText("0.00");
        grandTotalTF.setText(""+((vat > 0) ? df2.format(( (subTotal * vat) / 100) + subTotal) : df2.format(subTotal)));
        
    }
    
    public void fillTable(){
        for(int i = 0; i < products.size(); i++){  
            tableModel.insertRow(tableModel.getRowCount(), new Object[]{products.get(i).getProductName(), (products.get(i).getSide() != -1) ? getProductName(products.get(i).getSide()) : "<None>", 
                (products.get(i).getOptional() != -1) ? getProductName(products.get(i).getOptional()) : "<None>",
                 products.get(i).getProductPrice(), ProductStatus.fromId(products.get(i).getProductStatus()) });
        }
    }
    
    public void searchDatabase(){
        searchedEmployees = database.getEmployees();
        searchedTables = database.getTables();
        searchedProducts = database.getProducts();
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
    
    public void clearTable(){ 
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Side"); 
        tableModel.addColumn("Optional");
        tableModel.addColumn("Price");
        tableModel.addColumn("Status");
        
        preCartTable.setModel(tableModel);
    }
    
    public void clearPanel(){
    }
    
    public void updateTable(){
        clearTable();
        searchDatabase();
        for (OrderedProducts product : products) {
            tableModel.insertRow(tableModel.getRowCount(), new Object[]{product.getProductName(), (product.getSide() != -1) ? getProductName(product.getSide()) : "<None>", 
                (product.getOptional() != -1) ? getProductName(product.getOptional()) : "<None>",
                 product.getProductPrice(), ProductStatus.fromId(product.getProductStatus())});
        }
        clearPanel();
    }
    
    private void setDefaults() {
        ButtonGroup bg = new ButtonGroup();
        bg.add(cashBtn);
        bg.add(cardBtn);
        
        clearTable();
        
        waiterCB.addItem("<Select Waiter/Waitress>");
      
       
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
        subTotalTf = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cashBtn = new javax.swing.JToggleButton();
        cardBtn = new javax.swing.JToggleButton();
        jLabel18 = new javax.swing.JLabel();
        vatTf = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tipTF = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        grandTotalTF = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser(Calendar.getInstance().getTime());
        timeTF = new lu.tudor.santec.jtimechooser.JTimeChooser();
        buttonsPanel = new javax.swing.JPanel();
        payBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Transaction Details");

        orderNumberTF.setEditable(false);
        orderNumberTF.setBackground(new java.awt.Color(255, 255, 255));
        orderNumberTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Order Number :");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Waiter / Employee :");

        waiterCB.setEnabled(false);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Table :");

        tableCB.setEditable(true);
        tableCB.setEnabled(false);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Order Details");

        preCartTable.setBackground(new java.awt.Color(255, 255, 255));
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
        preCartTable.setRowHeight(30);
        preCartTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        preCartTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        preCartTable.setShowVerticalLines(false);
        preCartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preCartTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(preCartTable);

        subTotalTf.setEditable(false);
        subTotalTf.setBackground(new java.awt.Color(255, 255, 255));
        subTotalTf.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Sub Total :");

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Transaction Cost");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Payment Type :");

        cashBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cash.png"))); // NOI18N
        cashBtn.setSelected(true);
        cashBtn.setBorder(null);
        cashBtn.setBorderPainted(false);
        cashBtn.setContentAreaFilled(false);
        cashBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cash-pressed.png"))); // NOI18N

        cardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/card.png"))); // NOI18N
        cardBtn.setBorder(null);
        cardBtn.setBorderPainted(false);
        cardBtn.setContentAreaFilled(false);
        cardBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/card-pressed.png"))); // NOI18N

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("VAT :");

        vatTf.setEditable(false);
        vatTf.setBackground(new java.awt.Color(255, 255, 255));
        vatTf.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tip :");

        tipTF.setBackground(new java.awt.Color(255, 255, 255));
        tipTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        tipTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tipTFKeyTyped(evt);
            }
        });

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Grand Total :");

        grandTotalTF.setEditable(false);
        grandTotalTF.setBackground(new java.awt.Color(255, 255, 255));
        grandTotalTF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Date :");

        timeTF.setTime(Calendar.getInstance().getTime());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(waiterCB, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cashBtn)
                        .addGap(10, 10, 10)
                        .addComponent(cardBtn))
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
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(vatTf, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(subTotalTf, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tipTF, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(grandTotalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(timeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(orderNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel21))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(orderNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(waiterCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cashBtn)
                            .addComponent(cardBtn))))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(tableCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subTotalTf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vatTf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipTF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grandTotalTF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(10, 45));
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        payBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pay.png"))); // NOI18N
        payBtn.setBorder(null);
        payBtn.setContentAreaFilled(false);
        payBtn.setDefaultCapable(false);
        payBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pay-pressed.png"))); // NOI18N
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });
        buttonsPanel.add(payBtn);

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
        
    }//GEN-LAST:event_preCartTableMouseClicked

    private void tipTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipTFKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyCode() != KeyEvent.VK_0 && evt.getKeyCode() != KeyEvent.VK_1 && evt.getKeyCode() != KeyEvent.VK_2 && evt.getKeyCode() != KeyEvent.VK_3 && 
           evt.getKeyCode() != KeyEvent.VK_4 && evt.getKeyCode() != KeyEvent.VK_5 && evt.getKeyCode() != KeyEvent.VK_6 && evt.getKeyCode() != KeyEvent.VK_7 && 
           evt.getKeyCode() != KeyEvent.VK_8 && evt.getKeyCode() != KeyEvent.VK_9 && evt.getKeyCode() != KeyEvent.VK_PERIOD && evt.getKeyCode() != KeyEvent.VK_BACKSPACE &&
           evt.getKeyCode() != KeyEvent.VK_DELETE){
            if(tipTF.getText().length() > 0){
                tipTF.setText(tipTF.getText().substring(0, tipTF.getText().length()-1));
            }
            new OkayDialogue(desktop, true, "Only Digits and '.' allowed.");
        } else {
            subTotalTf.setText(df2.format(subTotal)); 
            grandTotalTF.setText("" + ((vat > 0) ? df2.format((((subTotal * vat) / 100) + subTotal)+Double.parseDouble(tipTF.getText())) : df2.format(subTotal+Double.parseDouble(tipTF.getText()))));
        }
    }//GEN-LAST:event_tipTFKeyTyped

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        // TODO add your handling code here:
        Transaction transaction = new Transaction();
        transaction.setDate(timeTF.getCalendarWithTime(date.getDate()).getTime());
        transaction.setEmployeeID(currentOrder.getEmployeeID());
        transaction.setOrderID(currentOrder.getId());
        transaction.setOrderNumber(currentOrder.getOrderNumber());
        transaction.setPayment((cashBtn.isSelected()) ? PaymentType.CASH : PaymentType.CARD );
        transaction.setPrice(Double.parseDouble(grandTotalTF.getText()));
        transaction.setSite((session.getBranch() != null) ? session.getBranch().getId() : -1);
        transaction.setTableID(currentOrder.getTableID());
        transaction.setTip(Double.parseDouble(tipTF.getText()));
        transaction.setVat(vat);
        
        currentOrder.setOrderStatus(OrderStatus.PAID);
        
        if(database.insert(transaction) && database.update(currentOrder)){ 
            new OkayDialogue(desktop, true, "Transaction saved Succesfully");
            updateTable();
            diag.dispose();
        } else {
            new OkayDialogue(desktop, true, "Failed to save Transaction");
        }
    }//GEN-LAST:event_payBtnActionPerformed

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
    private javax.swing.JToggleButton cardBtn;
    private javax.swing.JToggleButton cashBtn;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField grandTotalTF;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField orderNumberTF;
    private javax.swing.JButton payBtn;
    private javax.swing.JTable preCartTable;
    private javax.swing.JTextField subTotalTf;
    private javax.swing.JComboBox<String> tableCB;
    private lu.tudor.santec.jtimechooser.JTimeChooser timeTF;
    private javax.swing.JTextField tipTF;
    private javax.swing.JTextField vatTf;
    private javax.swing.JComboBox<String> waiterCB;
    // End of variables declaration//GEN-END:variables

}
