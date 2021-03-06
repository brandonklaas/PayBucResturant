/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.utilities.Session;
import javax.swing.ButtonGroup;

/**
 *
 * @author brand
 */
public class ProductsTransactionDialogue extends javax.swing.JPanel {

    private Session session;
    private Dialogue diag;
    
    /**
     * Creates new form TransactionDialogue
     */
    public ProductsTransactionDialogue(Session session) {
        this.session = session;
        initComponents();
        setDefaults();
    }
    
    
    
    void setDialogue(Dialogue dialogue) {
        diag = dialogue;
    }
    
    
    private void setDefaults() {
        ButtonGroup bg = new ButtonGroup();
        bg.add(cashBtn);
        bg.add(cardBtn);
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
        jLabel9 = new javax.swing.JLabel();
        customerName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productsCB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        customerNumberTf = new javax.swing.JTextField();
        cashBtn = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        priceTf = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        descriptionTf = new javax.swing.JTextField();
        cardBtn = new javax.swing.JToggleButton();
        buttonsPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Price :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(40, 200, 100, 16);
        jPanel1.add(customerName);
        customerName.setBounds(150, 300, 270, 24);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Customer No :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(40, 350, 100, 16);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Payment :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 150, 100, 16);

        productsCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(productsCB);
        productsCB.setBounds(150, 60, 270, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Product :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 70, 100, 16);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Customer Name :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(40, 310, 100, 16);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Customer Info");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 260, 290, 16);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 40, 600, 10);
        jPanel1.add(customerNumberTf);
        customerNumberTf.setBounds(150, 340, 270, 24);

        cashBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cash.png"))); // NOI18N
        cashBtn.setBorder(null);
        cashBtn.setBorderPainted(false);
        cashBtn.setContentAreaFilled(false);
        cashBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cash-pressed.png"))); // NOI18N
        jPanel1.add(cashBtn);
        cashBtn.setBounds(150, 140, 130, 32);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Transaction Info");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(18, 16, 290, 16);
        jPanel1.add(priceTf);
        priceTf.setBounds(150, 190, 270, 24);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(20, 280, 600, 10);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Description :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 110, 100, 16);
        jPanel1.add(descriptionTf);
        descriptionTf.setBounds(150, 100, 270, 24);

        cardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/card.png"))); // NOI18N
        cardBtn.setBorder(null);
        cardBtn.setBorderPainted(false);
        cardBtn.setContentAreaFilled(false);
        cardBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/card-pressed.png"))); // NOI18N
        jPanel1.add(cardBtn);
        cardBtn.setBounds(290, 140, 130, 32);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(10, 45));
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        saveBtn.setBorder(null);
        saveBtn.setBorderPainted(false);
        saveBtn.setContentAreaFilled(false);
        saveBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save-pressed.png"))); // NOI18N
        buttonsPanel.add(saveBtn);

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        cancelBtn.setBorder(null);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel-pressed.png"))); // NOI18N
        buttonsPanel.add(cancelBtn);

        add(buttonsPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JToggleButton cardBtn;
    private javax.swing.JToggleButton cashBtn;
    private javax.swing.JTextField customerName;
    private javax.swing.JTextField customerNumberTf;
    private javax.swing.JTextField descriptionTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField priceTf;
    private javax.swing.JComboBox<String> productsCB;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables

}
