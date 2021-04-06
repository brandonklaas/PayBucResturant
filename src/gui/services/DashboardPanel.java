/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.services;

import core.database.DatabaseAccessObject;
import core.general.Person;
import core.utilities.Session;
import gui.cards.Card;
import gui.desktop.LiveWaitlist;
import gui.dialoguePanels.ClientsDialogue;
import gui.dialoguePanels.Dialogue;
import gui.dialoguePanels.OkayDialogue;
import gui.dialoguePanels.ServiceTransactionDialogue;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brand
 */
public class DashboardPanel extends javax.swing.JPanel {

    private static DecimalFormat df2 = new DecimalFormat("0.00");
    private Session session;
    private Card transactionsSumCard = new Card(" Daily Sales");
    private Card productSumCard = new Card(" Daily Product Sales");
    private Card serviceSumCard = new Card(" Daily Service Sales");
    private Card dailyCardSales = new Card(" Daily Card Sales");
    private Card dailyCashSales = new Card(" Daily Cash Sales");
    private Card totalServices = new Card(" Available Services");
    private Card totalProducts = new Card(" Available Products");
    private DatabaseAccessObject database;
    private ArrayList<Person> waitlistArray;
    private LiveWaitlist liveWaitlist = null;
    private DefaultTableModel waitlistModel;
    
    /**
     * Creates new form Services
     */
    public DashboardPanel(Session session) {
        
        this.session = session;
        this.database = session.getDatabase();
        
        waitlistArray = new ArrayList<Person>();
        
        initComponents();
        setDefaults();
        refreshCards();
        
        waitlistModel = new DefaultTableModel();
        waitlistModel.addColumn("Name & Surname");
        waitlistModel.addColumn("Number");
        waitlistTable.setModel(waitlistModel);
        
    }

    public void addToWaitList(Person person) {
        waitlistArray.add(person);

        
        if (waitlistArray.size() > 0) {
            
            waitlistModel = new DefaultTableModel();
            waitlistModel.addColumn("Name & Surname");
            waitlistModel.addColumn("Number");
            
            for (Person pers : waitlistArray) {
                waitlistModel.addRow(new Object[]{pers.getFirstname() + " " + pers.getLastname(), pers.getCellNo()});
            }

            waitlistTable.setModel(waitlistModel);
            waitlistTable.repaint();
            waitlistTable.validate();
            
        }

        if (liveWaitlist != null) {
            liveWaitlist.refresh(waitlistArray);
        }
    }

    public void removeFromWaitList(int index) {
        waitlistArray.remove(index);

        waitlistModel = new DefaultTableModel();
        waitlistModel.addColumn("Name & Surname");
        waitlistModel.addColumn("Number");

        for (Person pers : waitlistArray) {
            waitlistModel.addRow(new Object[]{pers.getFirstname() + " " + pers.getLastname(), pers.getCellNo()});
        }

        waitlistTable.setModel(waitlistModel);
        waitlistTable.repaint();
        waitlistTable.validate();

        if (liveWaitlist != null) {
            liveWaitlist.refresh(waitlistArray);
        }
    }

    public void setDefaults(){
        cardsPanel.add(transactionsSumCard);
        cardsPanel.add(productSumCard);
        cardsPanel.add(serviceSumCard);
        cardsPanel.add(dailyCardSales);
        cardsPanel.add(dailyCashSales);
        cardsPanel.add(totalServices);
        cardsPanel.add(totalProducts);
        
        
    }
    
    public void refreshCards(){
        if (session.getBranch() != null) {
            transactionsSumCard.setAmountValue("  R" + df2.format(database.getTransactionSum(session.getBranch().getId(), Calendar.getInstance().getTime())));
            productSumCard.setAmountValue("  R" + df2.format(database.getTransactionDailySum("Product", session.getBranch().getId(), Calendar.getInstance().getTime())));
            serviceSumCard.setAmountValue("  R" + df2.format(database.getTransactionDailySum("Service", session.getBranch().getId(), Calendar.getInstance().getTime())));
            dailyCardSales.setAmountValue("  R" + df2.format(database.getTransactionCardSum(session.getBranch().getId(), Calendar.getInstance().getTime())));
            dailyCashSales.setAmountValue("  R" + df2.format(database.getTransactionCashSum(session.getBranch().getId(), Calendar.getInstance().getTime())));
            totalServices.setAmountValue( "  "+database.getServices().size()+"");
            totalProducts.setAmountValue( "  "+database.getProducts().size()+"");
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        bottomButtonsPanel = new javax.swing.JPanel();
        payBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        waitlistBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainCenter = new javax.swing.JPanel();
        top = new javax.swing.JPanel();
        cardsPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        displayBtn = new javax.swing.JButton();
        bottom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        waitlistTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1058, 720));
        setLayout(new java.awt.BorderLayout());

        main.setLayout(new java.awt.BorderLayout());

        bottomButtonsPanel.setBackground(new java.awt.Color(227, 227, 227));
        bottomButtonsPanel.setPreferredSize(new java.awt.Dimension(1058, 60));
        bottomButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 10));

        payBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pay.png"))); // NOI18N
        payBtn.setBorder(null);
        payBtn.setBorderPainted(false);
        payBtn.setContentAreaFilled(false);
        payBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pay-pressed.png"))); // NOI18N
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });
        bottomButtonsPanel.add(payBtn);

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
        bottomButtonsPanel.add(deleteBtn);

        waitlistBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/waitlist.png"))); // NOI18N
        waitlistBtn.setBorder(null);
        waitlistBtn.setBorderPainted(false);
        waitlistBtn.setContentAreaFilled(false);
        waitlistBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/waitlist-pressed.png"))); // NOI18N
        waitlistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waitlistBtnActionPerformed(evt);
            }
        });
        bottomButtonsPanel.add(waitlistBtn);

        main.add(bottomButtonsPanel, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBackground(new java.awt.Color(227, 227, 227));
        jPanel1.setPreferredSize(new java.awt.Dimension(20, 589));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );

        main.add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel2.setBackground(new java.awt.Color(227, 227, 227));
        jPanel2.setPreferredSize(new java.awt.Dimension(20, 589));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );

        main.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(227, 227, 227));
        jPanel3.setPreferredSize(new java.awt.Dimension(959, 50));

        jLabel1.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Dashboard");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(815, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        mainCenter.setLayout(new java.awt.GridLayout(2, 0));

        top.setBackground(new java.awt.Color(255, 255, 255));
        top.setLayout(new java.awt.BorderLayout());

        cardsPanel.setBackground(new java.awt.Color(227, 227, 227));
        cardsPanel.setLayout(new java.awt.GridLayout(2, 4, 5, 5));

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-plus.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        cardsPanel.add(jButton2);

        top.add(cardsPanel, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(227, 227, 227));
        jPanel6.setPreferredSize(new java.awt.Dimension(1018, 50));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 10));

        jLabel2.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Service Waitlist");
        jPanel6.add(jLabel2);

        jLabel3.setText("   ");
        jPanel6.add(jLabel3);

        displayBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/display.png"))); // NOI18N
        displayBtn.setBorder(null);
        displayBtn.setBorderPainted(false);
        displayBtn.setContentAreaFilled(false);
        displayBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/display-pressed.png"))); // NOI18N
        displayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayBtnActionPerformed(evt);
            }
        });
        jPanel6.add(displayBtn);

        top.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        mainCenter.add(top);

        bottom.setBackground(new java.awt.Color(227, 227, 227));
        bottom.setLayout(new java.awt.BorderLayout());

        waitlistTable.setBackground(new java.awt.Color(255, 255, 255));
        waitlistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Customer Number"
            }
        ));
        jScrollPane1.setViewportView(waitlistTable);

        bottom.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        mainCenter.add(bottom);

        main.add(mainCenter, java.awt.BorderLayout.CENTER);

        add(main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void waitlistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waitlistBtnActionPerformed
        // TODO add your handling code here:
        
        new Dialogue(null, true, new ClientsDialogue(session, this), "Waitlist");
        
    }//GEN-LAST:event_waitlistBtnActionPerformed

    private void displayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayBtnActionPerformed
        // TODO add your handling code here:
        if (waitlistArray.size() == 0) {
            liveWaitlist = new LiveWaitlist(session, this);
        } else {
            liveWaitlist = new LiveWaitlist(session, this, waitlistArray);
        }
    }//GEN-LAST:event_displayBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        if(waitlistTable.getSelectedRow() > -1){
            removeFromWaitList(waitlistTable.getSelectedRow());
        } else {
            new OkayDialogue(null, true, "Please Select a client from the waitlist to delete.");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    public void payed() {
        removeFromWaitList(waitlistTable.getSelectedRow());
        refreshCards();
    }

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        // TODO add your handling code here:
        if (waitlistTable.getSelectedRow() > -1) {
            new Dialogue(null, true, new ServiceTransactionDialogue(waitlistArray.get(waitlistTable.getSelectedRow()).getFirstname() + " " + waitlistArray.get(waitlistTable.getSelectedRow()).getLastname(),
                    waitlistArray.get(waitlistTable.getSelectedRow()).getCellNo(), this, session), "Transaction Management");
        } else {
            new OkayDialogue(null, true, "Please Select a client from the waitlist to pay for service.");
        }
    }//GEN-LAST:event_payBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottom;
    private javax.swing.JPanel bottomButtonsPanel;
    private javax.swing.JPanel cardsPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton displayBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    private javax.swing.JPanel mainCenter;
    private javax.swing.JButton payBtn;
    private javax.swing.JPanel top;
    private javax.swing.JButton waitlistBtn;
    private javax.swing.JTable waitlistTable;
    // End of variables declaration//GEN-END:variables
}
