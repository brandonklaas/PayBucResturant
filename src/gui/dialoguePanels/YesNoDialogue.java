/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import gui.desktop.DesktopFrame;
import gui.desktop.LoginFrame;
import gui.services.TransactionsManagementPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author brand
 */
public class YesNoDialogue extends javax.swing.JDialog {
 
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private String function;
    private DesktopFrame frame;
    private TransactionsManagementPanel panel;

    public YesNoDialogue(DesktopFrame parent, boolean modal, String title, String function) {
        super(parent, modal);
        this.frame = parent;
        
        initComponents();
                
        message.setText("<html>"+title+"</html>");
        this.setSize(250, 180);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.function = function;
        this.setVisible(true);
    }
    
     public YesNoDialogue(DesktopFrame parent, boolean modal, String title, String function, TransactionsManagementPanel panel) {
        super(parent, modal);
        this.frame = parent;
        this.panel = panel;
        
        initComponents();
                
        message.setText("<html><center>"+title+"</center></html>");
        this.setSize(250, 180);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.function = function;
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
        message = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        yesBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(929, 75));

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(102, 102, 102)));
        topPanel.setPreferredSize(new java.awt.Dimension(929, 36));
        topPanel.setLayout(new java.awt.BorderLayout());

        closeButtonsPanel.setBackground(new java.awt.Color(255, 255, 255));
        closeButtonsPanel.setForeground(new java.awt.Color(255, 255, 255));
        closeButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

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
        titlePanle.setPreferredSize(new java.awt.Dimension(150, 36));
        titlePanle.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Infomation Dialogue");
        titlePanle.add(jLabel1);

        topPanel.add(titlePanle, java.awt.BorderLayout.CENTER);

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        message.setBackground(new java.awt.Color(255, 255, 255));
        message.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        message.setText("message");
        message.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(51, 51, 51)));
        message.setOpaque(true);
        getContentPane().add(message, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        jPanel1.setPreferredSize(new java.awt.Dimension(10, 45));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        yesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/yes.png"))); // NOI18N
        yesBtn.setBorder(null);
        yesBtn.setBorderPainted(false);
        yesBtn.setContentAreaFilled(false);
        yesBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/yes-pressed.png"))); // NOI18N
        yesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesBtnActionPerformed(evt);
            }
        });
        jPanel1.add(yesBtn);

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/no.png"))); // NOI18N
        cancelBtn.setBorder(null);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/no-pressed.png"))); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelBtn);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void yesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesBtnActionPerformed
        // TODO add your handling code here:
        if(function.equals("LogOut")){
            frame.dispose();
            frame.closeDatabase();
            new LoginFrame();
        } else if(function.equals("Exit")){
            frame.closeDatabase();
            System.exit(0);
            
        } else if(function.equals("Delete")){
            this.dispose();
            panel.delete();
        }
    }//GEN-LAST:event_yesBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel closeButtonsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel message;
    private javax.swing.JPanel titlePanle;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton yesBtn;
    // End of variables declaration//GEN-END:variables
}