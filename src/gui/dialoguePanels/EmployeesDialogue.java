/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.database.DatabaseAccessObject;
import core.enums.Gender;
import core.general.Employee;
import core.general.Occupation;
import core.utilities.Session;
import gui.services.EmployeesManagementPanel;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author brand
 */
public class EmployeesDialogue extends javax.swing.JPanel {

    private Session session;
    private Dialogue diag;
    private DatabaseAccessObject database;
    private EmployeesManagementPanel panel;
    private Employee selectedEmployee;
    private DefaultComboBoxModel occupationModel;
    private ArrayList<Occupation> occArray;
    
    
    /**
     * Creates new form TransactionDialogue
     */
    public EmployeesDialogue(Session session , EmployeesManagementPanel panel) {
        this.session = session;
        this.database = session.getDatabase();
        this.panel = panel;
        
        initComponents();
        setDefaults();
        updateBtn.setVisible(false);
    }
    
    
    public EmployeesDialogue(Session session , EmployeesManagementPanel panel, Employee selectedEmployee) {
        this.session = session;
        this.database = session.getDatabase();
        this.panel = panel;
        this.selectedEmployee = selectedEmployee;
        
        initComponents();
        setDefaults();
        saveBtn.setVisible(false);
        fillPanel();
    }
    
    private void setDefaults() {
        occArray = database.getOccupations();
        if(occArray.size() > 0){
            Object[] tmp = new Object[occArray.size()];
            
            for(int i = 0; i < occArray.size(); i++){
               tmp[i] = occArray.get(i).getTitle();
            }
            occupationModel = new DefaultComboBoxModel(tmp);
            occupationCb.setModel(occupationModel);
            occupationCb.repaint();
            occupationCb.validate();
        }
    }

    
    
    void setDialogue(Dialogue dialogue) {
        diag = dialogue;
    }
    
    public void clearPanels(){
        nameTf.setText("");
        surnameTf.setText("");
        idNumberTf.setText("");
        cellTf.setText("");
        emailTF.setText("");
        employeeNumTf.setText("");
        addressTf.setText("");
        genderCb.setSelectedIndex(0);
    }

    
    public void fillPanel(){
        nameTf.setText(selectedEmployee.getFirstname());
        surnameTf.setText(selectedEmployee.getLastname());
        idNumberTf.setText(selectedEmployee.getIDNumber());
        cellTf.setText(selectedEmployee.getCellNo());
        emailTF.setText(selectedEmployee.getEmail());
        employeeNumTf.setText(selectedEmployee.getEmployeeNo());
        addressTf.setText(selectedEmployee.getHomeAddress());
        genderCb.setSelectedItem(selectedEmployee.getGender().toString());
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
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameTf = new javax.swing.JTextField();
        cellTf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressTf = new javax.swing.JTextArea();
        surnameTf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        idNumberTf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        employeeNumTf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        genderCb = new javax.swing.JComboBox<>();
        occupationCb = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
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
        jLabel9.setText("Address :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(40, 380, 100, 20);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText(" Name :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 70, 100, 16);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 40, 600, 10);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Employee");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(28, 16, 280, 16);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Cell :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 310, 100, 16);
        jPanel1.add(nameTf);
        nameTf.setBounds(150, 60, 270, 24);
        jPanel1.add(cellTf);
        cellTf.setBounds(150, 300, 270, 24);

        addressTf.setColumns(20);
        addressTf.setRows(5);
        jScrollPane1.setViewportView(addressTf);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(150, 380, 270, 83);
        jPanel1.add(surnameTf);
        surnameTf.setBounds(150, 100, 270, 24);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText(" Surname :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(40, 110, 100, 16);
        jPanel1.add(idNumberTf);
        idNumberTf.setBounds(150, 140, 270, 24);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText(" ID Number :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(40, 150, 100, 16);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Employee No :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(40, 270, 100, 16);
        jPanel1.add(employeeNumTf);
        employeeNumTf.setBounds(150, 260, 270, 24);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Email :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(40, 350, 100, 16);
        jPanel1.add(emailTF);
        emailTF.setBounds(150, 340, 270, 24);

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Gender :");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(40, 190, 100, 16);

        genderCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        jPanel1.add(genderCb);
        genderCb.setBounds(150, 180, 270, 26);

        jPanel1.add(occupationCb);
        occupationCb.setBounds(150, 220, 270, 26);

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Occupation ;");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(40, 230, 100, 16);

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
        if (nameTf.getText().isEmpty() == false && surnameTf.getText().isEmpty() == false && cellTf.getText().isEmpty() == false && idNumberTf.getText().isEmpty() == false) {

            Employee employee = new Employee();
            employee.setId(selectedEmployee.getId());
            employee.setFirstname(nameTf.getText());
            employee.setLastname(surnameTf.getText());
            employee.setEmail(emailTF.getText());
            employee.setCellNo(cellTf.getText());
            employee.setIDNumber(idNumberTf.getText());
            employee.setHomeAddress(addressTf.getText());
            employee.setEmployeeNo(employeeNumTf.getText());
            employee.setGender(Gender.fromString(genderCb.getSelectedItem().toString()));
            if(occupationCb.getSelectedIndex() > 0){
                employee.setOccupation(occupationCb.getSelectedItem().toString());
            }

            if (database.update(employee)) {
                diag.dispose();
                panel.refreshTable();
                new OkayDialogue(null, true, "Employee updated successfully");
            } else {
                new OkayDialogue(null, true, "Failed to update Employee");
            }
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if (nameTf.getText().isEmpty() == false && surnameTf.getText().isEmpty() == false && cellTf.getText().isEmpty() == false && idNumberTf.getText().isEmpty() == false) {

            Employee employee = new Employee();
            employee.setFirstname(nameTf.getText());
            employee.setLastname(surnameTf.getText());
            employee.setEmail(emailTF.getText());
            employee.setCellNo(cellTf.getText());
            employee.setIDNumber(idNumberTf.getText());
            employee.setHomeAddress(addressTf.getText());
            employee.setEmployeeNo(employeeNumTf.getText());
            employee.setGender(Gender.fromString(genderCb.getSelectedItem().toString()));
            if(occupationCb.getSelectedIndex() > -1){
                employee.setOccupation(occupationCb.getSelectedItem().toString());
            }

            if (database.insert(employee)) {
                clearPanels();
                panel.refreshTable();
                diag.setVisible(false);
                new OkayDialogue(null, true, "Employee saved successfully");
                diag.dispose();
            } else {
                new OkayDialogue(null, true, "Failed to save Employee");
            }
        }
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addressTf;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField cellTf;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField employeeNumTf;
    private javax.swing.JComboBox<String> genderCb;
    private javax.swing.JTextField idNumberTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nameTf;
    private javax.swing.JComboBox<String> occupationCb;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField surnameTf;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
