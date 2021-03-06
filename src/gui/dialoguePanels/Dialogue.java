/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import gui.services.AccountsManagementPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author brand
 */
public class Dialogue extends javax.swing.JDialog {
 
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    /**
     * Creates new form Dialogue
     */
    public Dialogue(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Dialogue(JFrame parent, boolean modal, JPanel panel, String title, String size) {
        super(parent, modal);

        initComponents();
        
        if (panel instanceof ProductDialogue) {
            ((ProductDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ServiceDialogue) {
            ((ServiceDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof EmployeesDialogue) {
            ((EmployeesDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ServiceTransactionDialogue) {
            ((ServiceTransactionDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ProductsTransactionDialogue) {
            ((ProductsTransactionDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ClientsDialogue) {
            ((ClientsDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof OccupationDialogue) {
            ((OccupationDialogue) panel).setDialogue(this);
        
        } else if (panel instanceof AccountDialogue) {
            ((AccountDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof BranchDialogue) {
            ((BranchDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof PreCartDialogue) {
            ((PreCartDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof OrderDialogue) {
            ((OrderDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof TablesDialogue) {
            ((TablesDialogue) panel).setDialogue(this);
        
        } else if (panel instanceof OrderCheckoutDialogue) {
            ((OrderCheckoutDialogue) panel).setDialogue(this);
         
        } else if (panel instanceof TransactionDialogue) {
            ((TransactionDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof FilterDialogue) {
            ((FilterDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof SettingsManagement) {
            ((SettingsManagement) panel).setDialogue(this);
            
        } else if (panel instanceof AccountsManagementPanel) {
            ((AccountsManagementPanel) panel).setDialogue(this);
        }   
 

        jLabel1.setText(title);

        centerPanel.add(panel, BorderLayout.CENTER);
        centerPanel.repaint();
        centerPanel.validate();
        
        this.setSize(750, 880);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        
    }
    
    public Dialogue(JFrame parent, boolean modal, JPanel panel, String title) {
        super(parent, modal);

        initComponents();
        
        if (panel instanceof ProductDialogue) {
            ((ProductDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ServiceDialogue) {
            ((ServiceDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof EmployeesDialogue) {
            ((EmployeesDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ServiceTransactionDialogue) {
            ((ServiceTransactionDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ProductsTransactionDialogue) {
            ((ProductsTransactionDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof ClientsDialogue) {
            ((ClientsDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof OccupationDialogue) {
            ((OccupationDialogue) panel).setDialogue(this);
        
        } else if (panel instanceof AccountDialogue) {
            ((AccountDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof BranchDialogue) {
            ((BranchDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof PreCartDialogue) {
            ((PreCartDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof OrderDialogue) {
            ((OrderDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof TablesDialogue) {
            ((TablesDialogue) panel).setDialogue(this);
        
        } else if (panel instanceof OrderCheckoutDialogue) {
            ((OrderCheckoutDialogue) panel).setDialogue(this);
          
        } else if (panel instanceof TransactionDialogue) {
            ((TransactionDialogue) panel).setDialogue(this);
        
        } else if (panel instanceof FilterDialogue) {
            ((FilterDialogue) panel).setDialogue(this);
            
        } else if (panel instanceof SettingsManagement) {
            ((SettingsManagement) panel).setDialogue(this);
        }   

        jLabel1.setText(title);

        centerPanel.add(panel, BorderLayout.CENTER);
        centerPanel.repaint();
        centerPanel.validate();
        
        this.setSize(750, 680);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        closeButtonsPanel = new javax.swing.JPanel();
        closeBtn = new javax.swing.JButton();
        titlePanle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(102, 102, 102)));
        topPanel.setPreferredSize(new java.awt.Dimension(929, 45));
        topPanel.setLayout(new java.awt.BorderLayout());

        closeButtonsPanel.setBackground(new java.awt.Color(255, 255, 255));
        closeButtonsPanel.setForeground(new java.awt.Color(255, 255, 255));
        closeButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 8));

        closeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        closeBtn.setBorder(null);
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        closeButtonsPanel.add(closeBtn);

        topPanel.add(closeButtonsPanel, java.awt.BorderLayout.LINE_END);

        titlePanle.setBackground(new java.awt.Color(255, 255, 255));
        titlePanle.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Title Holder");
        titlePanle.add(jLabel1);

        topPanel.add(titlePanle, java.awt.BorderLayout.CENTER);

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        centerPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        centerPanel.setMaximumSize(new java.awt.Dimension(929, 825));
        centerPanel.setMinimumSize(new java.awt.Dimension(929, 825));
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel closeButtonsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel titlePanle;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

}
