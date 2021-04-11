/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.services;

import core.database.DatabaseAccessObject;
import core.general.Table;
import core.utilities.Session;
import gui.dialoguePanels.Dialogue;
import gui.dialoguePanels.OkayDialogue; 
import gui.dialoguePanels.TablesDialogue;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brand
 */
public class TablesManagementPanel extends javax.swing.JPanel {

    private Session session;
    private DatabaseAccessObject database;
    private DefaultTableModel tableModel;
    private ArrayList<Table> array;
    
    /**
     * Creates new form Services
     */
    public TablesManagementPanel(Session session) {
        this.session = session;
        this.database = session.getDatabase();
        
        initComponents();
        refreshTable();
    }
    
    public void refreshTable(){
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Table Title");
        tableModel.addColumn("Table Status");
        
        array = database.getTables();
        
        if(array.size() > 0){
            for(Table table : array){
                tableModel.addRow(new Object[]{table.getTableName(), table.getTableStatus()});
            }
        }
        
        tablesTable.setModel(tableModel);
        tablesTable.repaint();
        tablesTable.validate();
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
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablesTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1058, 720));
        setLayout(new java.awt.BorderLayout());

        main.setLayout(new java.awt.BorderLayout());

        bottomButtonsPanel.setBackground(new java.awt.Color(247, 249, 249));
        bottomButtonsPanel.setPreferredSize(new java.awt.Dimension(1058, 50));
        bottomButtonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 10));

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

        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        editBtn.setBorder(null);
        editBtn.setBorderPainted(false);
        editBtn.setContentAreaFilled(false);
        editBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit-pressed.png"))); // NOI18N
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        bottomButtonsPanel.add(editBtn);

        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        addBtn.setBorder(null);
        addBtn.setBorderPainted(false);
        addBtn.setContentAreaFilled(false);
        addBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-pressed.png"))); // NOI18N
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        bottomButtonsPanel.add(addBtn);

        main.add(bottomButtonsPanel, java.awt.BorderLayout.PAGE_END);

        tablesTable.setBackground(new java.awt.Color(255, 255, 255));
        tablesTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tablesTable.setForeground(new java.awt.Color(51, 51, 51));
        tablesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Description", "Price"
            }
        ));
        tablesTable.setGridColor(new java.awt.Color(204, 204, 204));
        tablesTable.setOpaque(false);
        jScrollPane1.setViewportView(tablesTable);

        main.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(247, 249, 249));
        jPanel1.setPreferredSize(new java.awt.Dimension(20, 589));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        main.add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel2.setBackground(new java.awt.Color(247, 249, 249));
        jPanel2.setPreferredSize(new java.awt.Dimension(20, 589));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        main.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(247, 249, 249));
        jPanel3.setPreferredSize(new java.awt.Dimension(959, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1058, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        main.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        add(main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        new Dialogue(null, true, new TablesDialogue(session, this), "Tables Management");
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        if(tablesTable.getSelectedRow() > -1){
            if(database.delete(array.get(tablesTable.getSelectedRow()))){
                new OkayDialogue(null, true, "Occupation Deleleted Successfully");
                refreshTable();
            } else {
                new OkayDialogue(null, true, "Failed to Occupation");
            }
        } else {
            new OkayDialogue(null, true, "Select Occupation to delete");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        if(tablesTable.getSelectedRow() > -1){
            new Dialogue(null, true, new TablesDialogue(session, this, array.get(tablesTable.getSelectedRow())), "Tables Management");
        }
        
    }//GEN-LAST:event_editBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel bottomButtonsPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    private javax.swing.JTable tablesTable;
    // End of variables declaration//GEN-END:variables
}