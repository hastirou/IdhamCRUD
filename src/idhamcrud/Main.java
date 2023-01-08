/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idhamcrud;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI GAMING
 */
public class Main extends javax.swing.JFrame {

    DefaultTableModel dtModel = new DefaultTableModel();
    Connection kon;
    Statement stm;
    ResultSet res;
    String id;
    Boolean isUbah;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();

        try {
            this.isUbah = false;
            this.dtModel = new DefaultTableModel();
            tbData.setModel(this.dtModel);
            this.dtModel.addColumn("Id");
            this.dtModel.addColumn("Username");
            this.dtModel.addColumn("Class");
            this.dtModel.addColumn("Level");
            this.kon = Koneksi.koneksis();
            this.stm = this.kon.createStatement();
            btnSimpan.setEnabled(false);
            btnUbah.setEnabled(false);
            btnHapus.setEnabled(false);
            btnClear.setEnabled(false);
            read();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassCastException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reset() {
        this.isUbah = false;
        etUser.setText("");
        etLevel.setText("");
        cbClass.setSelectedIndex(0);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnClear.setEnabled(false);
    }

    public void create() {
        try {
            if (etLevel.getText().length() > 4) {
                JOptionPane.showMessageDialog(null, "Maksimal panjang angka level ialah 999");
            } else {
                PreparedStatement p1 = kon.prepareStatement("INSERT INTO `member` (`id`, `username`, `class`, `level`) VALUES (NULL, '" + etUser.getText().toString() + "', '" + cbClass.getSelectedItem().toString() + "', '" + Integer.valueOf(etLevel.getText()) + "')");
                p1.execute();

                reset();
                read();

                JOptionPane.showMessageDialog(null, "Berhasil menambahkan data");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data");
        }
    }

    public void update() {
        try {
            PreparedStatement p1 = kon.prepareStatement("UPDATE `member` SET `username` = '" + etUser.getText().toString() + "', `class` = '" + cbClass.getSelectedItem().toString() + "', `level` = '" + Integer.valueOf(etLevel.getText()) + "' WHERE `member`.`id` = " + this.id);
            p1.execute();

            reset();
            read();

            btnUbah.setEnabled(false);
            btnHapus.setEnabled(false);

            JOptionPane.showMessageDialog(null, "Berhasil mengubah data");
        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Gagal mengubah data");
        }
    }

    public void delete() {
        try {
            PreparedStatement p1 = kon.prepareStatement("DELETE FROM `member` WHERE `id` = " + this.id);
            p1.execute();

            reset();
            read();

            btnUbah.setEnabled(false);
            btnHapus.setEnabled(false);

            JOptionPane.showMessageDialog(null, "Berhasil menghapus data");
        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
        }
    }

    public void search() {
        try {
            dtModel.getDataVector().removeAllElements();
            dtModel.fireTableDataChanged();

            Statement stm = Koneksi.koneksis().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM `member` WHERE `id` LIKE '%" + etCari.getText().toString() + "%'");

            while (res.next()) {
                Object[] data = new Object[5];
                data[0] = res.getInt("id");
                data[1] = res.getString("username");
                data[2] = res.getString("class");
                data[3] = res.getInt("level");

                dtModel.addRow(data);
            }
            res.close();
            stm.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
        }
    }

    public void read() {
        try {
            dtModel.getDataVector().removeAllElements();
            dtModel.fireTableDataChanged();

            Statement stm = Koneksi.koneksis().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM member");

            while (res.next()) {
                Object[] data = new Object[5];
                data[0] = res.getInt("id");
                data[1] = res.getString("username");
                data[2] = res.getString("class");
                data[3] = res.getInt("level");

                dtModel.addRow(data);
            }
            res.close();
            stm.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void clicked() {
        this.isUbah = true;
        int i = tbData.getSelectedRow();

        if (i == -1) {
            return;
        }

        this.id = this.dtModel.getValueAt(i, 0).toString();

        String username = this.dtModel.getValueAt(i, 1).toString();
        etUser.setText(username);

        String clas = this.dtModel.getValueAt(i, 2).toString();
        cbClass.setSelectedItem(clas);

        String level = this.dtModel.getValueAt(i, 3).toString();
        etLevel.setText(level);

        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        etUser = new javax.swing.JTextField();
        etLevel = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        btnCari = new javax.swing.JButton();
        cbClass = new javax.swing.JComboBox<>();
        etCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Class");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Level");

        etUser.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        etUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                etUserMouseExited(evt);
            }
        });

        etLevel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        etLevel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                etLevelMouseExited(evt);
            }
        });
        etLevel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                etLevelKeyPressed(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(255, 255, 255));
        btnSimpan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setBackground(new java.awt.Color(255, 255, 255));
        btnUbah.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(255, 255, 255));
        btnHapus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 255, 255));
        btnClear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        tbData.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Username", "Class", "Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbData);

        btnCari.setBackground(new java.awt.Color(255, 255, 255));
        btnCari.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        cbClass.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Mage", "Assasin", "Tank", "Support" }));
        cbClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbClassMouseExited(evt);
            }
        });

        etCari.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnUbah)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etUser)
                            .addComponent(etLevel)
                            .addComponent(cbClass, 0, 254, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(etCari)
                        .addGap(18, 18, 18)
                        .addComponent(btnCari))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(etUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(etCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(etLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnUbah)
                            .addComponent(btnHapus)
                            .addComponent(btnClear))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_btnCariActionPerformed

    private void tbDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataMouseClicked
        // TODO add your handling code here:
        clicked();
    }//GEN-LAST:event_tbDataMouseClicked

    private void etUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etUserMouseExited
        // TODO add your handling code here:
        if (!isUbah) {
            if (etUser.getText().length() <= 0 || cbClass.getSelectedItem().equals("-") || etLevel.getText().length() <= 0) {
                btnSimpan.setEnabled(false);
            } else {
                btnSimpan.setEnabled(true);
            }
        }
    }//GEN-LAST:event_etUserMouseExited

    private void cbClassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbClassMouseExited
        // TODO add your handling code here:
        if (!isUbah) {
            if (etUser.getText().length() <= 0 || cbClass.getSelectedItem().equals("-") || etLevel.getText().length() <= 0) {
                btnSimpan.setEnabled(false);
            } else {
                btnSimpan.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cbClassMouseExited

    private void etLevelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etLevelMouseExited
        // TODO add your handling code here:
        if (!isUbah) {
            if (etUser.getText().length() <= 0 || cbClass.getSelectedItem().equals("-") || etLevel.getText().length() <= 0) {
                btnSimpan.setEnabled(false);
            } else {
                btnSimpan.setEnabled(true);
            }
        }
    }//GEN-LAST:event_etLevelMouseExited

    private void etLevelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etLevelKeyPressed
        // TODO add your handling code here:
        if (etLevel.getText().length() < 3 && evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || evt.getKeyChar() == evt.VK_BACK_SPACE) {
            etLevel.setEditable(true);
        } else {
            etLevel.setEditable(false);
        }
    }//GEN-LAST:event_etLevelKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbClass;
    private javax.swing.JTextField etCari;
    private javax.swing.JTextField etLevel;
    private javax.swing.JTextField etUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbData;
    // End of variables declaration//GEN-END:variables
}
