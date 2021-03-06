/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.desktop;

import core.utilities.Session;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.swing.ImageIcon;

/**
 *
 * @author brand
 */
public class Loading extends javax.swing.JDialog {

    private Session session;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private Thread thread;

    /**
     * Creates new form Loading
     */
    public Loading(java.awt.Frame parent, boolean modal, Session session) {
        super(parent, modal);
        this.session = session;
        initComponents();
        this.setSize(650, 165);
        this.setIconImage(new ImageIcon(getClass().getResource("/icons/round-logo.png")).getImage());
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    public void startThread() {
        setVisible(true);
        thread = new Thread(loadEnvironment);
        thread.start();

    }
    
    public void close(){
        this.dispose();
    }

    Runnable loadEnvironment = new Runnable() {
        public void run() {
            ModernUI frame = null;

            int count = 0;

            while (count < 100) {
                try {
                    Thread.sleep(70L);
                    
                    if(count == 63){
                        frame = new ModernUI(dim, session);
                    }
                    
                    if(count < 63){
                        loadingLbl.setText("Loading Database [ "+count+"% ]");
                    } else {
                        loadingLbl.setText("Loading Database [ "+count+"% ]  |  Initializing Environment...");
                    }    
                    count++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            close();
            frame.setVisible(true);
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loadingLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 150));
        jPanel1.setLayout(null);

        loadingLbl.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        loadingLbl.setForeground(new java.awt.Color(102, 102, 102));
        loadingLbl.setText("Loading Database.");
        jPanel1.add(loadingLbl);
        loadingLbl.setBounds(140, 110, 360, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loadingText.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 465, 104);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loading.gif"))); // NOI18N
        jLabel2.setToolTipText("");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(510, 23, 123, 104);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loadingLbl;
    // End of variables declaration//GEN-END:variables
}
