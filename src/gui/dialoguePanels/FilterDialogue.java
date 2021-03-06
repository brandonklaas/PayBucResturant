/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.database.DatabaseAccessObject; 
import core.utilities.Session; 
import gui.services.TransactionsManagementPanel;
import java.text.SimpleDateFormat; 
import javax.swing.ButtonGroup;

/**
 *
 * @author brand
 */
public class FilterDialogue extends javax.swing.JPanel {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
    
    private Session session;
    private Dialogue diag;
    private TransactionsManagementPanel panel;
    private DatabaseAccessObject database;
    
    
    /**
     * Creates new form TransactionDialogue
     */
    public FilterDialogue(Session session, TransactionsManagementPanel panel) {
        this.session = session;
        this.database = session.getDatabase();
        this.panel = panel;
        initComponents();
        setDefaults();
    }
    
    private void setDefaults() {
        ButtonGroup bg = new ButtonGroup();
        bg.add(allRB);
        bg.add(cashRB);
        bg.add(cardRB);
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
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        toChooser = new com.toedter.calendar.JDateChooser();
        fromChooser = new com.toedter.calendar.JDateChooser();
        cardRB = new javax.swing.JRadioButton();
        allRB = new javax.swing.JRadioButton();
        cashRB = new javax.swing.JRadioButton();
        buttonsPanel = new javax.swing.JPanel();
        filterBTN = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Payment :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 70, 100, 16);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 40, 600, 10);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Clients");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(18, 16, 290, 16);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("To :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 150, 100, 16);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("From :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(40, 110, 100, 16);
        jPanel1.add(toChooser);
        toChooser.setBounds(150, 140, 270, 32);
        jPanel1.add(fromChooser);
        fromChooser.setBounds(150, 100, 270, 32);

        cardRB.setText("Card");
        jPanel1.add(cardRB);
        cardRB.setBounds(340, 60, 80, 28);

        allRB.setSelected(true);
        allRB.setText("All");
        jPanel1.add(allRB);
        allRB.setBounds(150, 60, 60, 28);

        cashRB.setText("Cash");
        jPanel1.add(cashRB);
        cashRB.setBounds(230, 60, 90, 28);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        buttonsPanel.setPreferredSize(new java.awt.Dimension(10, 45));
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        filterBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filter.png"))); // NOI18N
        filterBTN.setBorder(null);
        filterBTN.setBorderPainted(false);
        filterBTN.setContentAreaFilled(false);
        filterBTN.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filter-pressed.png"))); // NOI18N
        filterBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBTNActionPerformed(evt);
            }
        });
        buttonsPanel.add(filterBTN);

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

    private void filterBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBTNActionPerformed
        // TODO add your handling code here:
        if (fromChooser.getDate() != null && toChooser.getDate() != null) {
            diag.setVisible(false);
            panel.refreshFilteredTable(database.getFilteredTransactions(fromChooser.getDate(), toChooser.getDate(), allRB.isSelected(), cardRB.isSelected(), cashRB.isSelected()), 
                    simpleDateFormat.format(fromChooser.getDate())+" - "+simpleDateFormat.format(toChooser.getDate()));
            diag.dispose();
        } else {
            new OkayDialogue(null, true, "Select From Date and To Date for filtering");
        }

    }//GEN-LAST:event_filterBTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allRB;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JRadioButton cardRB;
    private javax.swing.JRadioButton cashRB;
    private javax.swing.JButton filterBTN;
    private com.toedter.calendar.JDateChooser fromChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser toChooser;
    // End of variables declaration//GEN-END:variables

}
