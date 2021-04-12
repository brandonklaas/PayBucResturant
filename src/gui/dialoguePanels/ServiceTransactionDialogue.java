/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.database.DatabaseAccessObject;
import core.general.Employee;
import core.general.Service;
import core.general.Transaction;
import core.utilities.Session;
import gui.services.TransactionsManagementPanel;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author brand
 */
public class ServiceTransactionDialogue extends javax.swing.JPanel {

    private Session session;
    private Dialogue diag;
    private TransactionsManagementPanel panel;
    private DatabaseAccessObject database;
    private Transaction selectedTransaction;
    private DefaultComboBoxModel serviceModel;
    private DefaultComboBoxModel employeeModel;
    
    private ArrayList<Service> servicesArray;
    private ArrayList<Employee> employeeArray;
    
    /**
     * Creates new form TransactionDialogue
     */
    public ServiceTransactionDialogue(Session session, TransactionsManagementPanel panel) {
        this.session = session;
        this.database = session.getDatabase();
        this.panel = panel;
        initComponents();
        setDefaults();
        clearPanels();
        updateBtn.setVisible(false);
    }

    public ServiceTransactionDialogue(Session session, TransactionsManagementPanel panel, Transaction selectedService) {
        this.session = session;
        this.database = session.getDatabase();
        this.panel = panel;
        this.selectedTransaction = selectedService;
        initComponents();
        setDefaults();
        fillPanel();
        clearPanels();
        saveBtn.setVisible(false);
    }

    public void clearPanels() {
        serviceCB.setSelectedIndex(0);
        descriptionTf.setText("");
        stylistCB.setSelectedIndex(0);
        cashBtn.setSelected(true); 
        priceTf.setText("");
        
        customerNameTf.setText("");
        customerCell.setText("");
    }

    public void fillPanel() {
//        serviceCB.setSelectedItem(selectedTransaction.getTitle());
//        descriptionTf.setText(servicesArray.get(serviceCB.getSelectedIndex()).getDescription());
//        stylistCB.setSelectedItem(selectedTransaction.getEmployee());
//        
//        if ((selectedTransaction.getPayment().equals("Cash"))) {
//            cashBtn.setSelected(true); 
//        } else {
//            cardBtn.setSelected(true);
//        }
//        priceTf.setText(selectedTransaction.getPrice()+"");
//        
//        customerNameTf.setText(selectedTransaction.getCustomerName());
//        customerCell.setText(selectedTransaction.getCustomerNumber());
    }

    private void setDefaults() {
        ButtonGroup bg = new ButtonGroup();
        bg.add(cashBtn);
        bg.add(cardBtn);
        
        dateChooser.setDate(Calendar.getInstance().getTime());
        timeChooser.setTime(Calendar.getInstance().getTime());
        
        refreshEmployees();
        refreshServices();
    }
    
    public void refreshServices(){
        servicesArray = database.getServices();
        if(servicesArray.size() > 0){
            Object[] tmp = new Object[servicesArray.size()+1];
            
            tmp[0] = "<Select Service>";
            
            for(int i = 1; i <= servicesArray.size(); i++){
               tmp[i] = servicesArray.get(i-1).getName();
            }
            
            serviceModel = new DefaultComboBoxModel(tmp);
            serviceCB.setModel(serviceModel);
            serviceCB.repaint();
            serviceCB.validate();
            
        } else {
            Object[] tmp = new Object[]{"<None>"};
            serviceModel = new DefaultComboBoxModel(tmp);
            serviceCB.setModel(serviceModel);
            serviceCB.repaint();
            serviceCB.validate();
        }
    }

    public void refreshEmployees(){
        employeeArray = database.getEmployeesByOccupation("Stylist");
        if(employeeArray.size() > 0){
            Object[] tmp = new Object[employeeArray.size()+1];
            
            tmp[0] = "<Select Employee>";
            
            for(int i = 1; i <= employeeArray.size(); i++){
               tmp[i] = employeeArray.get(i-1).getFirstname()+" "+employeeArray.get(i-1).getLastname();
            }
            employeeModel = new DefaultComboBoxModel(tmp);
            stylistCB.setModel(employeeModel);
            stylistCB.repaint();
            stylistCB.validate();
        
        } else {
            Object[] tmp = new Object[]{"<none>"};
            employeeModel = new DefaultComboBoxModel(tmp);
            stylistCB.setModel(employeeModel);
            stylistCB.repaint();
            stylistCB.validate();
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
        jLabel9 = new javax.swing.JLabel();
        customerNameTf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        serviceCB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        stylistCB = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        customerCell = new javax.swing.JTextField();
        cashBtn = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        priceTf = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        descriptionTf = new javax.swing.JTextField();
        cardBtn = new javax.swing.JToggleButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        timeChooser = new lu.tudor.santec.jtimechooser.JTimeChooser();
        buttonsPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
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
        jLabel9.setBounds(40, 280, 100, 16);
        jPanel1.add(customerNameTf);
        customerNameTf.setBounds(150, 390, 270, 24);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Customer No :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(40, 440, 100, 16);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Payment :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 230, 100, 16);

        serviceCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        serviceCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceCBActionPerformed(evt);
            }
        });
        jPanel1.add(serviceCB);
        serviceCB.setBounds(150, 100, 270, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Date & Time :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 70, 100, 16);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Customer Name :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(40, 400, 100, 16);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Customer Info");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 350, 290, 16);

        stylistCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(stylistCB);
        stylistCB.setBounds(150, 180, 270, 30);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 40, 600, 10);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Stylist :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(40, 190, 100, 16);
        jPanel1.add(customerCell);
        customerCell.setBounds(150, 430, 270, 24);

        cashBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cash.png"))); // NOI18N
        cashBtn.setBorder(null);
        cashBtn.setBorderPainted(false);
        cashBtn.setContentAreaFilled(false);
        cashBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cash-pressed.png"))); // NOI18N
        jPanel1.add(cashBtn);
        cashBtn.setBounds(150, 220, 130, 32);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Transaction Info");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(18, 16, 290, 16);
        jPanel1.add(priceTf);
        priceTf.setBounds(150, 270, 270, 24);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(20, 370, 600, 10);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Description :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 150, 100, 16);

        descriptionTf.setEditable(false);
        jPanel1.add(descriptionTf);
        descriptionTf.setBounds(150, 140, 270, 24);

        cardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/card.png"))); // NOI18N
        cardBtn.setBorder(null);
        cardBtn.setBorderPainted(false);
        cardBtn.setContentAreaFilled(false);
        cardBtn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/card-pressed.png"))); // NOI18N
        jPanel1.add(cardBtn);
        cardBtn.setBounds(290, 220, 130, 32);

        dateChooser.setEnabled(false);
        jPanel1.add(dateChooser);
        dateChooser.setBounds(240, 60, 180, 29);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Service :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(40, 110, 100, 16);

        timeChooser.setEnabled(false);
        timeChooser.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jPanel1.add(timeChooser);
        timeChooser.setBounds(150, 60, 80, 30);

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

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        cancelBtn.setBorder(null);
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel-pressed.png"))); // NOI18N
        buttonsPanel.add(cancelBtn);

        add(buttonsPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        if (priceTf.getText().isEmpty() == false && customerNameTf.getText().isEmpty() == false && customerCell.getText().isEmpty() == false
                && stylistCB.getSelectedIndex() > 0 
                && serviceCB.getSelectedIndex() > 0) {
            
//            Transaction transaction = new Transaction();
//            transaction.setId(selectedTransaction.getId());
//            transaction.setSite(session.getBranch().getId());
//            transaction.setTitle(serviceCB.getSelectedItem().toString());
//            transaction.setType("Service");
//            transaction.setTypeID(servicesArray.get(serviceCB.getSelectedIndex()-1).getId());
//            transaction.setPayment((cashBtn.isSelected()) ? "Cash" : "Card");
//            transaction.setPrice(Double.parseDouble(priceTf.getText()));
//            transaction.setEmployee(stylistCB.getSelectedItem().toString());
//            transaction.setCustomerName(customerNameTf.getText());
//            transaction.setCustomerNumber(customerCell.getText());
//            transaction.setDate(timeChooser.getCalendarWithTime(dateChooser.getDate()).getTime());
//
//            if (database.update(transaction)) {
//
//                diag.dispose();
//                panel.refreshTable();
//                new OkayDialogue(null, true, "Transaction updated successfully");
//
//            } else {
//                new OkayDialogue(null, true, "Failed to updated Transaction");
//            }
        } else {
                new OkayDialogue(null, true, "Fill in all the required fileds before updating");
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if (priceTf.getText().isEmpty() == false && customerNameTf.getText().isEmpty() == false
                && customerCell.getText().isEmpty() == false && stylistCB.getSelectedIndex() > 0 && serviceCB.getSelectedIndex() > 0) {

            if (session.getSettings().getDefaultBranch().equals("<None>") == false) {
//                Transaction transaction = new Transaction();
//                transaction.setSite(session.getBranch().getId());
//                transaction.setTitle(serviceCB.getSelectedItem().toString());
//                transaction.setType("Service");
//                transaction.setTypeID(servicesArray.get(serviceCB.getSelectedIndex() - 1).getId());
//                transaction.setPayment((cashBtn.isSelected()) ? "Cash" : "Card");
//                transaction.setPrice(Double.parseDouble(priceTf.getText()));
//                transaction.setEmployee(stylistCB.getSelectedItem().toString());
//                transaction.setCustomerName(customerNameTf.getText());
//                transaction.setCustomerNumber(customerCell.getText());
//                transaction.setDate(timeChooser.getCalendarWithTime(dateChooser.getDate()).getTime());

//                if (database.insert(transaction)) {
//
//                    diag.dispose();
//                    panel.refreshTable();
//                    new OkayDialogue(null, true, "Transaction save successfully");
//
//                } else {
//                    new OkayDialogue(null, true, "Failed to save Transaction");
//                }
            } else {
                new OkayDialogue(null, true, "Set deefault before saving Transaction. To set Default head to Settings > Default Branch");
            }

        } else {
                new OkayDialogue(null, true, "Fill in all the required fileds before saving");
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void serviceCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceCBActionPerformed
        // TODO add your handling code here:
        if(serviceCB.getSelectedIndex() > 0 && serviceCB.getSelectedItem().toString().equals("<none>") == false){
            descriptionTf.setText(servicesArray.get(serviceCB.getSelectedIndex()-1).getDescription());
            priceTf.setText(servicesArray.get(serviceCB.getSelectedIndex()-1).getPrice()+"");
        }
    }//GEN-LAST:event_serviceCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JToggleButton cardBtn;
    private javax.swing.JToggleButton cashBtn;
    private javax.swing.JTextField customerCell;
    private javax.swing.JTextField customerNameTf;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JTextField descriptionTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> serviceCB;
    private javax.swing.JComboBox<String> stylistCB;
    private lu.tudor.santec.jtimechooser.JTimeChooser timeChooser;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
