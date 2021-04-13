/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.desktop;

import core.database.DatabaseAccessObject;
import core.enums.ProductType;
import core.general.Order; 
import core.general.OrderedProducts;
import core.general.Product;
import core.utilities.Session;
import gui.cards.FoodCard; 
import gui.dialoguePanels.BranchDialogue;
import gui.dialoguePanels.Dialogue;
import gui.dialoguePanels.OrderDialogue;
import gui.dialoguePanels.SettingsManagement;
import gui.services.AccountsManagementPanel;
import gui.services.BranchManagementPanel;
import gui.services.EmployeesManagementPanel;
import gui.services.OrdersManagementPanel;
import gui.services.ProductManagementPanel;
import gui.services.ServiceManagementPanel;
import gui.services.SettingsManagementPanel;
import gui.services.TablesManagementPanel;
import gui.services.TransactionsManagementPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer; 
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author brand
 */
public class ModernUI extends javax.swing.JFrame {

    private Dimension dim;
    private Order currentOrder;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private ArrayList<OrderedProducts> products = new ArrayList<>();
    private static DecimalFormat df2 = new DecimalFormat("#.00");
    
    private static final int ALPHA = 175; // how much see-thru. 0 to 255
    private static final Color GP_BG = new Color(0, 0, 0, ALPHA);
    private JPanel glassPane;
    private Session session;
    private DatabaseAccessObject database;
    
    private ServiceManagementPanel servicePanel;
    private ProductManagementPanel productsPanel;
    private TransactionsManagementPanel transactionsPanel;
    private EmployeesManagementPanel employeesPanel;
    private TablesManagementPanel tablesPanel; 
    private OrdersManagementPanel ordersPanel; 
    
    private ArrayList<Product> searchedProducts;
    private ArrayList<OrderedProducts> orderedProducts = new ArrayList<>();
    private int tax;
    
    /**
     * Creates new form ModernUI
     */
    public ModernUI() {
        this.session = new Session();
        this.database = session.getDatabase();
        this.tax = Integer.parseInt(session.getSettings().getTax());
        this.setIconImage(new ImageIcon(getClass().getResource("/icons/round-logo.png")).getImage());
        
        initComponents();
        defaults();
        createGlassPanel();
        
         
        productsPanel = new ProductManagementPanel(session, this);
        transactionsPanel = new TransactionsManagementPanel(session, this);
        employeesPanel = new EmployeesManagementPanel(session, this); 
        tablesPanel = new TablesManagementPanel(session, this);
        ordersPanel = new OrdersManagementPanel(session, this);
        
        foodBtnActionPerformed(null);
    }
    
    
    public ModernUI(Dimension dimension, Session session) {
        this.dim = dimension; 
        this.session = session;
        this.database = session.getDatabase();
        this.setVisible(false);
        
        initComponents();
        defaults();
        createGlassPanel();
         
        productsPanel = new ProductManagementPanel(session, this);
        transactionsPanel = new TransactionsManagementPanel(session, this);
        employeesPanel = new EmployeesManagementPanel(session, this); 
        tablesPanel = new TablesManagementPanel(session, this);
        ordersPanel = new OrdersManagementPanel(session,this);
        
        foodBtnActionPerformed(null);
    }
    
    public void fillProductDash(ProductType type){
        searchedProducts = database.getProducts();
        scrollOrderJPanel.removeAll();
        
        for(Product product : searchedProducts){
            if(product.getType() == type){
                scrollOrderJPanel.add(new FoodCard(product, this, session));
            }
        }
    }
    
    public void defaults() {
        
        //====================================================================================================================
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(foodBtn);
        bg.add(dessertBtn);
        bg.add(drinksBtn);
        
        bg.add(ordersBtn);
        bg.add(tableBtn);
        bg.add(reportsBtn);
        bg.add(productsBtn);
        bg.add(employeesBtn);
         
        fillProductDash(ProductType.SINGLE);
        
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Description");
        tableModel.addColumn("Qty");
        tableModel.addColumn("Price");
        
        receiptTable.setModel(tableModel);
        
        taxLbl.setText(tax+"");        
        
        int[] columnsWidth = {
            140, 33, 60
        };
  
        // Configures table's column width.
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = receiptTable.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
    } 
    
    public void createGlassPanel() {
        // create our glass pane
        glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // magic to make it dark without side-effects
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        // more magic below
        glassPane.setOpaque(false);
        glassPane.setBackground(GP_BG);

        // get the rootpane container, here the JFrame, that holds the JButton
        RootPaneContainer win = (RootPaneContainer) this; 
        win.setGlassPane(glassPane);  // set the glass pan
    }
    
    public void dim(boolean dim){
        glassPane.setVisible(dim); 
    }
    
    public void addToOrder(ArrayList<OrderedProducts> product){
        
        tableModel.insertRow(tableModel.getRowCount(), new Object[]{product.get(0).getProductName(), product.size(),
                             df2.format(product.size()*product.get(0).getProductPrice()) });
        
        for(OrderedProducts prod : product){
            products.add(prod);
        }
        
        calculateTotal();
        
    }
    
    public void clearTable(){
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Description");
        tableModel.addColumn("Qty");
        tableModel.addColumn("Price");
        
        receiptTable.setModel(tableModel);
        
        int[] columnsWidth = {
            140, 33, 60
        };
  
        // Configures table's column width.
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = receiptTable.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
    }
     
    public void closeDatabase(){
        session.getDatabase().close();
    }
    
    public void calculateTotal(){
        double tempTotal = 0.0; 
        
        for(int i = 0; i < products.size(); i++){
            tempTotal = tempTotal + products.get(i).getProductPrice();
        }
        
        subTotal.setText("R "+ df2.format(tempTotal));
        taxInRandsLbl.setText( (tax == 0) ? "R 0.00" : "R "+ df2.format((tempTotal * tax) / 100));
        totalLbl.setText((tax == 0) ? df2.format(tempTotal) : "R "+ df2.format(( (tempTotal * tax) / 100) + tempTotal)  ) ;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        MainHeader = new javax.swing.JPanel();
        headerLeftPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        headerRightPanel = new javax.swing.JPanel();
        accountsBtn = new javax.swing.JButton();
        branchBtn = new javax.swing.JButton();
        headerCenterMain = new javax.swing.JPanel();
        panelSearchPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        textboxCenterPanel = new javax.swing.JPanel();
        universalSearchTf = new javax.swing.JTextField();
        MainLeftPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        foodBtn = new javax.swing.JToggleButton();
        drinksBtn = new javax.swing.JToggleButton();
        dessertBtn = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        ordersBtn = new javax.swing.JToggleButton();
        tableBtn = new javax.swing.JToggleButton();
        employeesBtn = new javax.swing.JToggleButton();
        reportsBtn = new javax.swing.JToggleButton();
        productsBtn = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        centerRightPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        subTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        taxLbl = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        taxInRandsLbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        totalLbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        payBillBtn = new javax.swing.JButton();
        addToTableBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiptTable = new javax.swing.JTable();
        centerPanel = new javax.swing.JPanel();
        bottomButtons = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        quickOrderPanel = new javax.swing.JPanel();
        quickOrderScroll = new javax.swing.JScrollPane();
        scrollOrderJPanel = new javax.swing.JPanel();
        QuickPanelHeader = new javax.swing.JPanel();
        quickHeaderLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1278, 720));
        setSize(new java.awt.Dimension(0, 0));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMaximumSize(new java.awt.Dimension(1920, 1080));
        mainPanel.setMinimumSize(new java.awt.Dimension(1278, 720));
        mainPanel.setPreferredSize(new java.awt.Dimension(1366, 728));
        mainPanel.setLayout(new java.awt.BorderLayout());

        MainHeader.setBackground(new java.awt.Color(52, 188, 183));
        MainHeader.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(233, 234, 236)));
        MainHeader.setPreferredSize(new java.awt.Dimension(1497, 80));
        MainHeader.setLayout(new java.awt.BorderLayout());

        headerLeftPanel.setBackground(new java.awt.Color(52, 188, 183));
        headerLeftPanel.setPreferredSize(new java.awt.Dimension(370, 78));
        headerLeftPanel.setLayout(new java.awt.BorderLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/header.png"))); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(330, 78));
        headerLeftPanel.add(jLabel7, java.awt.BorderLayout.CENTER);

        MainHeader.add(headerLeftPanel, java.awt.BorderLayout.LINE_START);

        headerRightPanel.setBackground(new java.awt.Color(52, 188, 183));
        headerRightPanel.setPreferredSize(new java.awt.Dimension(400, 78));

        accountsBtn.setText("Accounts");
        accountsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountsBtnActionPerformed(evt);
            }
        });
        headerRightPanel.add(accountsBtn);

        branchBtn.setText("Branches");
        branchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchBtnActionPerformed(evt);
            }
        });
        headerRightPanel.add(branchBtn);

        MainHeader.add(headerRightPanel, java.awt.BorderLayout.LINE_END);

        headerCenterMain.setBackground(new java.awt.Color(52, 188, 183));
        headerCenterMain.setLayout(new java.awt.BorderLayout());

        panelSearchPanel.setBackground(new java.awt.Color(52, 188, 183));
        panelSearchPanel.setPreferredSize(new java.awt.Dimension(80, 80));
        panelSearchPanel.setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchBtn.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        panelSearchPanel.add(jButton1, java.awt.BorderLayout.CENTER);

        headerCenterMain.add(panelSearchPanel, java.awt.BorderLayout.LINE_END);

        textboxCenterPanel.setBackground(new java.awt.Color(52, 188, 183));
        textboxCenterPanel.setLayout(new java.awt.GridLayout(1, 1));

        universalSearchTf.setBackground(new java.awt.Color(61, 161, 158));
        universalSearchTf.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        universalSearchTf.setForeground(new java.awt.Color(255, 255, 255));
        universalSearchTf.setBorder(javax.swing.BorderFactory.createMatteBorder(14, 0, 14, 0, new java.awt.Color(52, 188, 183)));
        universalSearchTf.setPreferredSize(new java.awt.Dimension(14, 35));
        universalSearchTf.setSelectionColor(new java.awt.Color(102, 102, 102));
        universalSearchTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                universalSearchTfActionPerformed(evt);
            }
        });
        textboxCenterPanel.add(universalSearchTf);

        headerCenterMain.add(textboxCenterPanel, java.awt.BorderLayout.CENTER);

        MainHeader.add(headerCenterMain, java.awt.BorderLayout.CENTER);

        mainPanel.add(MainHeader, java.awt.BorderLayout.PAGE_START);

        MainLeftPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainLeftPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        MainLeftPanel.setPreferredSize(new java.awt.Dimension(280, 926));
        MainLeftPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 20));

        jLabel1.setFont(new java.awt.Font("Poppins Light", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Categories                  ");
        MainLeftPanel.add(jLabel1);

        foodBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/food.png"))); // NOI18N
        foodBtn.setSelected(true);
        foodBtn.setBorder(null);
        foodBtn.setContentAreaFilled(false);
        foodBtn.setFocusPainted(false);
        foodBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/food-pressed.png"))); // NOI18N
        foodBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/food-pressed.png"))); // NOI18N
        foodBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(foodBtn);

        drinksBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/drinks.png"))); // NOI18N
        drinksBtn.setBorder(null);
        drinksBtn.setContentAreaFilled(false);
        drinksBtn.setFocusPainted(false);
        drinksBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/drinks-drinks.png"))); // NOI18N
        drinksBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/drinks-drinks.png"))); // NOI18N
        drinksBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinksBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(drinksBtn);

        dessertBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/dessert.png"))); // NOI18N
        dessertBtn.setBorder(null);
        dessertBtn.setContentAreaFilled(false);
        dessertBtn.setFocusPainted(false);
        dessertBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/desert-pressed.png"))); // NOI18N
        dessertBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/catergories/desert-pressed.png"))); // NOI18N
        dessertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dessertBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(dessertBtn);

        jLabel2.setFont(new java.awt.Font("Poppins Light", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Admin                          ");
        MainLeftPanel.add(jLabel2);

        ordersBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/orders.png"))); // NOI18N
        ordersBtn.setBorder(null);
        ordersBtn.setContentAreaFilled(false);
        ordersBtn.setFocusPainted(false);
        ordersBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/orders-pressed.png"))); // NOI18N
        ordersBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/orders-pressed.png"))); // NOI18N
        ordersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(ordersBtn);

        tableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tables.png"))); // NOI18N
        tableBtn.setBorder(null);
        tableBtn.setContentAreaFilled(false);
        tableBtn.setFocusPainted(false);
        tableBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tables-pressed.png"))); // NOI18N
        tableBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tables-pressed.png"))); // NOI18N
        tableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(tableBtn);

        employeesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/employees.png"))); // NOI18N
        employeesBtn.setBorder(null);
        employeesBtn.setContentAreaFilled(false);
        employeesBtn.setFocusPainted(false);
        employeesBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/employees-pressed.png"))); // NOI18N
        employeesBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/employees-pressed.png"))); // NOI18N
        employeesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeesBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(employeesBtn);

        reportsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/transdaction.png"))); // NOI18N
        reportsBtn.setBorder(null);
        reportsBtn.setContentAreaFilled(false);
        reportsBtn.setFocusPainted(false);
        reportsBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/transaction-pressed.png"))); // NOI18N
        reportsBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/transaction-pressed.png"))); // NOI18N
        reportsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(reportsBtn);

        productsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/products.png"))); // NOI18N
        productsBtn.setBorder(null);
        productsBtn.setContentAreaFilled(false);
        productsBtn.setFocusPainted(false);
        productsBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/products-pressed.png"))); // NOI18N
        productsBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/products-pressed.png"))); // NOI18N
        productsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productsBtnActionPerformed(evt);
            }
        });
        MainLeftPanel.add(productsBtn);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings-pressed.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        MainLeftPanel.add(jButton2);

        mainPanel.add(MainLeftPanel, java.awt.BorderLayout.LINE_START);

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));
        rightPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        rightPanel.setMinimumSize(new java.awt.Dimension(280, 100));
        rightPanel.setPreferredSize(new java.awt.Dimension(280, 926));
        rightPanel.setLayout(new java.awt.BorderLayout());

        centerRightPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerRightPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(255, 255, 255)));
        centerRightPanel.setPreferredSize(new java.awt.Dimension(400, 743));
        centerRightPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(52, 188, 183)));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 360));

        jLabel12.setText("         ");
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 16));
        jPanel1.add(jLabel12);

        jLabel5.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Subtotal ");
        jLabel5.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(jLabel5);

        subTotal.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        subTotal.setForeground(new java.awt.Color(0, 0, 0));
        subTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subTotal.setText("R 0.00");
        subTotal.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(subTotal);

        jLabel9.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tax (%)");
        jLabel9.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(jLabel9);

        taxLbl.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        taxLbl.setForeground(new java.awt.Color(0, 0, 0));
        taxLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        taxLbl.setText("15");
        taxLbl.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(taxLbl);

        jLabel8.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tax (Rand)");
        jLabel8.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(jLabel8);

        taxInRandsLbl.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        taxInRandsLbl.setForeground(new java.awt.Color(0, 0, 0));
        taxInRandsLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        taxInRandsLbl.setText("R 0.00");
        taxInRandsLbl.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(taxInRandsLbl);

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("         ");
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(250, 1));
        jPanel1.add(jLabel4);

        jLabel11.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Total");
        jLabel11.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(jLabel11);

        totalLbl.setFont(new java.awt.Font("Poppins Light", 1, 14)); // NOI18N
        totalLbl.setForeground(new java.awt.Color(0, 0, 0));
        totalLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalLbl.setText("R 0.00");
        totalLbl.setPreferredSize(new java.awt.Dimension(110, 20));
        jPanel1.add(totalLbl);

        jLabel6.setText("         ");
        jLabel6.setPreferredSize(new java.awt.Dimension(200, 55));
        jPanel1.add(jLabel6);

        payBillBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/payBill.png"))); // NOI18N
        payBillBtn.setBorder(null);
        payBillBtn.setBorderPainted(false);
        payBillBtn.setContentAreaFilled(false);
        payBillBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/payBill-pressed.png"))); // NOI18N
        jPanel1.add(payBillBtn);

        addToTableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add2table.png"))); // NOI18N
        addToTableBtn.setBorder(null);
        addToTableBtn.setBorderPainted(false);
        addToTableBtn.setContentAreaFilled(false);
        addToTableBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add2table-pressed.png"))); // NOI18N
        addToTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToTableBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addToTableBtn);

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancelReceipt.png"))); // NOI18N
        cancelBtn.setBorder(null);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancelReceipt-pressed.png"))); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelBtn);

        centerRightPanel.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 153, 153)));
        jPanel2.setPreferredSize(new java.awt.Dimension(319, 40));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jLabel3.setFont(new java.awt.Font("Poppins Light", 0, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Receipt");
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 29));
        jPanel2.add(jLabel3);

        centerRightPanel.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        receiptTable.setBackground(new java.awt.Color(255, 255, 255));
        receiptTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        receiptTable.setFont(new java.awt.Font("Poppins Light", 0, 13)); // NOI18N
        receiptTable.setForeground(new java.awt.Color(51, 51, 51));
        receiptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Description", "Qty", "Price"
            }
        ));
        receiptTable.setFillsViewportHeight(true);
        receiptTable.setGridColor(new java.awt.Color(204, 204, 204));
        receiptTable.setRowHeight(20);
        receiptTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        receiptTable.setShowVerticalLines(false);
        receiptTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(receiptTable);

        centerRightPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        rightPanel.add(centerRightPanel, java.awt.BorderLayout.CENTER);

        mainPanel.add(rightPanel, java.awt.BorderLayout.LINE_END);

        centerPanel.setBackground(new java.awt.Color(247, 249, 249));
        centerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 20, 20, 20, new java.awt.Color(247, 249, 249)));
        centerPanel.setMaximumSize(new java.awt.Dimension(1920, 2147483647));
        centerPanel.setPreferredSize(new java.awt.Dimension(1920, 185));
        centerPanel.setLayout(new java.awt.BorderLayout());

        bottomButtons.setBackground(new java.awt.Color(247, 249, 249));
        bottomButtons.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        bottomButtons.setPreferredSize(new java.awt.Dimension(1516, 30));
        bottomButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("<html><center>Copyright © 2021 PayBuc (PTY) Ltd. All rights reserved\n</center><html>");
        bottomButtons.add(jLabel10);

        centerPanel.add(bottomButtons, java.awt.BorderLayout.PAGE_END);

        quickOrderPanel.setBackground(new java.awt.Color(247, 249, 249));
        quickOrderPanel.setMaximumSize(new java.awt.Dimension(1000, 32000));
        quickOrderPanel.setPreferredSize(new java.awt.Dimension(1000, 40));
        quickOrderPanel.setLayout(new java.awt.BorderLayout());

        quickOrderScroll.setBackground(new java.awt.Color(247, 249, 249));
        quickOrderScroll.setBorder(null);
        quickOrderScroll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        quickOrderScroll.setHorizontalScrollBar(null);
        quickOrderScroll.setMaximumSize(new java.awt.Dimension(900, 950));
        quickOrderScroll.setPreferredSize(new java.awt.Dimension(900, 40));

        scrollOrderJPanel.setBackground(new java.awt.Color(247, 249, 249));
        scrollOrderJPanel.setMaximumSize(new java.awt.Dimension(900, 950));
        scrollOrderJPanel.setPreferredSize(new java.awt.Dimension(900, 950));
        scrollOrderJPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 20, 20));
        quickOrderScroll.setViewportView(scrollOrderJPanel);

        quickOrderPanel.add(quickOrderScroll, java.awt.BorderLayout.CENTER);

        centerPanel.add(quickOrderPanel, java.awt.BorderLayout.CENTER);

        QuickPanelHeader.setBackground(new java.awt.Color(247, 249, 249));
        QuickPanelHeader.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        QuickPanelHeader.setPreferredSize(new java.awt.Dimension(1516, 40));

        quickHeaderLbl.setFont(new java.awt.Font("Poppins Light", 0, 20)); // NOI18N
        quickHeaderLbl.setForeground(new java.awt.Color(102, 102, 102));
        quickHeaderLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quickHeaderLbl.setText("Quick Dashboard");
        QuickPanelHeader.add(quickHeaderLbl);

        centerPanel.add(QuickPanelHeader, java.awt.BorderLayout.PAGE_START);

        mainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void universalSearchTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_universalSearchTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_universalSearchTfActionPerformed

    private void productsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productsBtnActionPerformed
        // TODO add your handling code here:
        if(centerPanel.getComponent(1) instanceof ProductManagementPanel == false){
            quickHeaderLbl.setText("Products Management");
            centerPanel.remove(1);
            centerPanel.add(productsPanel, 1);
            rightPanel.setVisible(false);
            bottomButtons.setVisible(false);
            centerPanel.validate();
            centerPanel.repaint();
        }
    }//GEN-LAST:event_productsBtnActionPerformed

    private void reportsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsBtnActionPerformed
        // TODO add your handling code here:
        if(centerPanel.getComponent(1) instanceof TransactionsManagementPanel == false){
            quickHeaderLbl.setText("Reports Management");
            centerPanel.remove(1);
            centerPanel.add(transactionsPanel, 1);
            transactionsPanel.refreshTable();
            rightPanel.setVisible(false);
            bottomButtons.setVisible(false);
            centerPanel.validate();
            centerPanel.repaint();
        }
    }//GEN-LAST:event_reportsBtnActionPerformed

    private void employeesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeesBtnActionPerformed
        // TODO add your handling code here:
        if(centerPanel.getComponent(1) instanceof EmployeesManagementPanel == false){
            quickHeaderLbl.setText("Employees Management");
            centerPanel.remove(1);
            centerPanel.add(employeesPanel, 1);
            rightPanel.setVisible(false);
            bottomButtons.setVisible(false);
            centerPanel.validate();
            centerPanel.repaint();
        }
        
    }//GEN-LAST:event_employeesBtnActionPerformed

    private void tableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableBtnActionPerformed
        // TODO add your handling code here:
        
        if(centerPanel.getComponent(1) instanceof TablesManagementPanel == false){
            quickHeaderLbl.setText("Tables Management");
            centerPanel.remove(1);
            centerPanel.add(tablesPanel, 1);
            rightPanel.setVisible(false);
            bottomButtons.setVisible(false);
            centerPanel.validate();
            centerPanel.repaint();
        }
    }//GEN-LAST:event_tableBtnActionPerformed

    private void foodBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodBtnActionPerformed
        // TODO add your handling code here:
        if(centerPanel.getComponent(1) != quickOrderPanel || quickHeaderLbl.getText().equals("Quick Food Dashboard") == false){
            quickHeaderLbl.setText("Quick Food Dashboard");
            centerPanel.remove(1);
            centerPanel.add(quickOrderPanel, 1);
            fillProductDash(ProductType.SINGLE);
            rightPanel.setVisible(true);
            bottomButtons.setVisible(true);
            centerPanel.validate();
            centerPanel.repaint();
        }
    }//GEN-LAST:event_foodBtnActionPerformed

    private void drinksBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drinksBtnActionPerformed
        // TODO add your handling code here:
        
        if(centerPanel.getComponent(1) != quickOrderPanel || quickHeaderLbl.getText().equals("Quick Drinks Dashboard") == false){
            quickHeaderLbl.setText("Quick Drinks Dashboard");
            centerPanel.remove(1);
            centerPanel.add(quickOrderPanel, 1);
            fillProductDash(ProductType.DRINK);
            rightPanel.setVisible(true);
            bottomButtons.setVisible(true);
            centerPanel.validate();
            centerPanel.repaint();
        } 
    }//GEN-LAST:event_drinksBtnActionPerformed

    private void dessertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dessertBtnActionPerformed
        // TODO add your handling code here:
        if(centerPanel.getComponent(1) != quickOrderPanel || quickHeaderLbl.getText().equals("Quick Dessert Dashboard") == false){
            quickHeaderLbl.setText("Quick Dessert Dashboard");
            centerPanel.remove(1);
            centerPanel.add(quickOrderPanel, 1);
            fillProductDash(ProductType.DESSERT);
            rightPanel.setVisible(true);
            bottomButtons.setVisible(true);
            centerPanel.validate();
            centerPanel.repaint();
        } 
    }//GEN-LAST:event_dessertBtnActionPerformed

    private void addToTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToTableBtnActionPerformed
        // TODO add your handling code here:
        if(products.size() > 0){
            dim(true);
            new Dialogue( this, true, new OrderDialogue(this, products, session), "Order Dialogue", "Larger");
            dim(false);
        }
    }//GEN-LAST:event_addToTableBtnActionPerformed

    private void ordersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersBtnActionPerformed
        // TODO add your handling code here:
        if(centerPanel.getComponent(1) instanceof OrdersManagementPanel == false){
            quickHeaderLbl.setText("Order Management");
            centerPanel.remove(1);
            centerPanel.add(ordersPanel, 1);
            ordersPanel.refreshTable();
            rightPanel.setVisible(false);
            bottomButtons.setVisible(false);
            centerPanel.validate();
            centerPanel.repaint();
        } 
    }//GEN-LAST:event_ordersBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dim(true);
        new Dialogue(this, true, new SettingsManagement(session), "Settings");
        dim(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        clearReceipt();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void accountsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountsBtnActionPerformed
        // TODO add your handling code here:
        dim(true);
        new Dialogue(this, true, new AccountsManagementPanel(session), "Accounts Management");
        dim(false);
    }//GEN-LAST:event_accountsBtnActionPerformed

    private void branchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchBtnActionPerformed
        // TODO add your handling code here:
        dim(true);
        new Dialogue(this, true, new BranchManagementPanel(session), "Branch Management");
        dim(false);
    }//GEN-LAST:event_branchBtnActionPerformed

    public void clearReceipt(){
        clearTable();
        products = new ArrayList<>();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModernUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModernUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModernUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModernUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ModernUI().setVisible(true);
//            }
//        });
//        
        new ModernUI().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainHeader;
    private javax.swing.JPanel MainLeftPanel;
    private javax.swing.JPanel QuickPanelHeader;
    private javax.swing.JButton accountsBtn;
    private javax.swing.JButton addToTableBtn;
    private javax.swing.JPanel bottomButtons;
    private javax.swing.JButton branchBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel centerRightPanel;
    private javax.swing.JToggleButton dessertBtn;
    private javax.swing.JToggleButton drinksBtn;
    private javax.swing.JToggleButton employeesBtn;
    private javax.swing.JToggleButton foodBtn;
    private javax.swing.JPanel headerCenterMain;
    private javax.swing.JPanel headerLeftPanel;
    private javax.swing.JPanel headerRightPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToggleButton ordersBtn;
    private javax.swing.JPanel panelSearchPanel;
    private javax.swing.JButton payBillBtn;
    private javax.swing.JToggleButton productsBtn;
    private javax.swing.JLabel quickHeaderLbl;
    private javax.swing.JPanel quickOrderPanel;
    private javax.swing.JScrollPane quickOrderScroll;
    private javax.swing.JTable receiptTable;
    private javax.swing.JToggleButton reportsBtn;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel scrollOrderJPanel;
    private javax.swing.JLabel subTotal;
    private javax.swing.JToggleButton tableBtn;
    private javax.swing.JLabel taxInRandsLbl;
    private javax.swing.JLabel taxLbl;
    private javax.swing.JPanel textboxCenterPanel;
    private javax.swing.JLabel totalLbl;
    private javax.swing.JTextField universalSearchTf;
    // End of variables declaration//GEN-END:variables
}
