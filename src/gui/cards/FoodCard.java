/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cards;

import core.general.Product;
import core.utilities.Session;
import gui.desktop.ModernUI;
import gui.dialoguePanels.Dialogue;
import gui.dialoguePanels.OkayDialogue;
import gui.dialoguePanels.PreCartDialogue;
import java.io.File;

/**
 *
 * @author brand
 */
public class FoodCard extends javax.swing.JPanel {

    private int quantity = 0;
    private Product product;
    private ModernUI desktop;  
    private Session session;
    
    /**
     * Creates new form FoodCard
     */
    public FoodCard() {
        initComponents();
    }
    
    public FoodCard(Product product, ModernUI desktop, Session session) { 
        this.session = session;
        this.product = product;
        this.desktop = desktop;
        initComponents();
        productName.setText(product.getName());
        imageLbl.setIcon(new javax.swing.ImageIcon(new File("").getAbsolutePath()+product.getImagePath()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productName = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        minusBtn = new javax.swing.JButton();
        qtyLbl = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        cartBtn = new javax.swing.JButton();
        imageLbl = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(220, 260));
        setPreferredSize(new java.awt.Dimension(220, 260));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        productName.setBackground(new java.awt.Color(204, 204, 204));
        productName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        productName.setForeground(new java.awt.Color(0, 0, 0));
        productName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productName.setText("jLabel1");
        productName.setOpaque(true);
        productName.setPreferredSize(new java.awt.Dimension(41, 30));
        add(productName, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 65));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 2));

        minusBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        minusBtn.setBorder(null);
        minusBtn.setBorderPainted(false);
        minusBtn.setContentAreaFilled(false);
        minusBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus-pressed.png"))); // NOI18N
        minusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusBtnActionPerformed(evt);
            }
        });
        jPanel1.add(minusBtn);

        qtyLbl.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        qtyLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        qtyLbl.setText("0");
        qtyLbl.setPreferredSize(new java.awt.Dimension(45, 45));
        qtyLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qtyLblMouseClicked(evt);
            }
        });
        jPanel1.add(qtyLbl);

        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        addBtn.setBorder(null);
        addBtn.setBorderPainted(false);
        addBtn.setContentAreaFilled(false);
        addBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus-pressed.png"))); // NOI18N
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn);

        cartBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cart.png"))); // NOI18N
        cartBtn.setBorder(null);
        cartBtn.setBorderPainted(false);
        cartBtn.setContentAreaFilled(false);
        cartBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cart-pressed.png"))); // NOI18N
        cartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cartBtn);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        imageLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Food/b1.png"))); // NOI18N
        add(imageLbl, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        if (quantity < 99) {
            quantity++;
            qtyLbl.setText(quantity + "");
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void minusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusBtnActionPerformed
        // TODO add your handling code here:
        if (quantity > 0) {
            quantity--;
            qtyLbl.setText(quantity + "");
        }
    }//GEN-LAST:event_minusBtnActionPerformed

    private void cartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartBtnActionPerformed
        // TODO add your handling code here:
        if(quantity > 0){
            desktop.dim(true);
            new Dialogue( desktop, true, new PreCartDialogue(desktop, product, quantity, session), "Pre Cart Dialogue");
            desktop.dim(false);
            quantity = 0;
            qtyLbl.setText(quantity + "");
            
        } else {
            desktop.dim(true);
            new OkayDialogue(desktop, true, "Add Number of Products to order", desktop.getSize());
            desktop.dim(false);
        }
        
    }//GEN-LAST:event_cartBtnActionPerformed

    private void qtyLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyLblMouseClicked
        // TODO add your handling code here:
        quantity = 0;
        qtyLbl.setText( quantity +"");
    }//GEN-LAST:event_qtyLblMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cartBtn;
    private javax.swing.JLabel imageLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton minusBtn;
    private javax.swing.JLabel productName;
    private javax.swing.JLabel qtyLbl;
    // End of variables declaration//GEN-END:variables
}
