/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialoguePanels;

import core.database.DatabaseAccessObject;
import core.enums.ProductType;
import core.general.Product;
import core.utilities.Session;
import gui.services.ProductManagementPanel;
import java.awt.Image;
import java.io.File;  
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser; 
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;


/**
 * @author brand
 */
public class ProductDialogue extends javax.swing.JPanel {
    private Session session ;
    private DatabaseAccessObject database;
    private ProductManagementPanel panel;
    private Dialogue diag;
    private Product selectedProduct = null;
    private String imagePath = "\\bin\\ProductImages\\no-image.png";
    
    /**
     * Creates new form TransactionDialogue
     */
    public ProductDialogue(Session session, ProductManagementPanel panel) {
        this.session = session;
        this.database = session.getDatabase();
        this.panel = panel;
        
        initComponents();
        setDefaults();
        updateBtn.setVisible(false);
    }
    
    /**
     * Creates new form TransactionDialogue
     */
    public ProductDialogue() {
        initComponents();
        setDefaults();
        updateBtn.setVisible(false);
    }

    public ProductDialogue(Session session, ProductManagementPanel panel, Product product) {
        this.session = session;
        this.database = session.getDatabase();
        this.panel = panel;
        this.selectedProduct = product;
        
        initComponents();
        setDefaults();
        fillPanel();
        saveBtn.setVisible(false);
    }
    
    public ImageIcon scaleImage(ImageIcon image) {
        Image imageScaled = image.getImage().getScaledInstance(220, 170, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(imageScaled);
    }
    
    public void fillPanel(){
        productImage.setText("");
        
        productImage.setIcon(scaleImage(new ImageIcon(selectedProduct.getImagePath())));
        productTf.setText(selectedProduct.getName());
        descriptionTf.setText(selectedProduct.getDescription());
        priceTf.setText(selectedProduct.getPrice().toString());
        taxableCk.setSelected(selectedProduct.isTaxable());
    }
    
    
    void setDialogue(Dialogue dialogue) {
        diag = dialogue;
    }
    
    private void setDefaults() {
    }

    public void clearPanels(){
        productTf.setText("");
        descriptionTf.setText("");
        priceTf.setText("");
        taxableCk.setSelected(false);
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
        priceTf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        productTf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        taxableCk = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        productTypeCB = new javax.swing.JComboBox<>(ProductType.values());
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTf = new javax.swing.JTextArea();
        browseBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        productImage = new javax.swing.JLabel();
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
        jLabel9.setText("Taxable :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(40, 470, 100, 20);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("<html>Image :     <><p><i>(220x170)</i></html>");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(40, 60, 100, 50);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 40, 600, 10);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Product Info");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(18, 16, 290, 16);
        jPanel1.add(priceTf);
        priceTf.setBounds(150, 390, 270, 24);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Description :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 290, 100, 16);
        jPanel1.add(productTf);
        productTf.setBounds(150, 250, 270, 24);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Product Type :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(40, 440, 100, 16);
        jPanel1.add(taxableCk);
        taxableCk.setBounds(150, 470, 24, 30);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Product :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(40, 260, 100, 16);

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Price :");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(40, 400, 100, 16);

        jPanel1.add(productTypeCB);
        productTypeCB.setBounds(150, 430, 270, 26);

        descriptionTf.setColumns(20);
        descriptionTf.setRows(5);
        jScrollPane1.setViewportView(descriptionTf);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(150, 290, 270, 83);

        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });
        jPanel1.add(browseBtn);
        browseBtn.setBounds(30, 110, 80, 32);

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn);
        clearBtn.setBounds(30, 150, 80, 32);

        productImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Food/no-image.png"))); // NOI18N
        productImage.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        jPanel1.add(productImage);
        productImage.setBounds(160, 60, 230, 170);

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
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelBtn);

        add(buttonsPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if(productTf.getText().isEmpty() == false && priceTf.getText().isEmpty() == false){
            Product product = new Product();
            product.setName(productTf.getText());
            product.setDescription(descriptionTf.getText());
            product.setImagePath(imagePath);
            product.setPrice(Double.parseDouble(priceTf.getText()));
            product.setTaxable(taxableCk.isSelected());
            product.setType(ProductType.fromString(productTypeCB.getSelectedItem().toString()));
            
            if(database.insert(product)){
                clearPanels();
                panel.refreshTable();
                diag.setVisible(false);
                new OkayDialogue(null, true, "Product saved successfully");
                diag.dispose();
            } else {
                new OkayDialogue(null, true, "Failed to save Product");
            }
            
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        if (productTf.getText().isEmpty() == false && priceTf.getText().isEmpty() == false) {
            Product product = new Product();
            product.setId(selectedProduct.getId());
            product.setName(productTf.getText());
            product.setDescription(descriptionTf.getText());
            product.setImagePath(imagePath );
            product.setPrice(Double.parseDouble(priceTf.getText()));
            product.setTaxable(taxableCk.isSelected());
            product.setType(ProductType.fromString(productTypeCB.getSelectedItem().toString()));

            if (database.update(product)) {
                diag.dispose();
                panel.refreshTable();
                new OkayDialogue(null, true, "Product updated successfully");
                
            } else {
                new OkayDialogue(null, true, "Failed to updated Product");
            }

        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        this.diag.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        // TODO add your handling code here:
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(imageFilter);
        
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                File dest = new File(new File("").getAbsolutePath()+"\\bin\\ProductImages\\"+file.getName());
                FileUtils.copyFile(file, dest);
                imagePath = "\\bin\\ProductImages\\"+file.getName();
                productImage.setText("");
                productImage.setIcon(new javax.swing.ImageIcon(imagePath));
                jPanel1.validate();
                jPanel1.repaint();
            } catch (IOException ex) {
                Logger.getLogger(ProductDialogue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_browseBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        imagePath = "\\bin\\ProductImages\\no-image.png";
        productImage.setIcon(new ImageIcon("\\bin\\ProductImages\\no-image.png"));
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_clearBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseBtn;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextArea descriptionTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField priceTf;
    private javax.swing.JLabel productImage;
    private javax.swing.JTextField productTf;
    private javax.swing.JComboBox<ProductType> productTypeCB;
    private javax.swing.JButton saveBtn;
    private javax.swing.JCheckBox taxableCk;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
