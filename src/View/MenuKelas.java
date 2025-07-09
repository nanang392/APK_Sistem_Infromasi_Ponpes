package View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class MenuKelas extends javax.swing.JPanel {
    private int totalPages;
    
    private final Connection conn;
    public MenuKelas() {
        initComponents();
        
        conn = koneksi.getConnection();
        setTabelModel();
        loadData();
    }

    
    private void loadData() {
        getData((DefaultTableModel) tbkelas.getModel());
        bthapus.setVisible(false);
        bBatal.setVisible(false);
    }
    
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new MenuKelas());
        panelMain.repaint();
        panelMain.revalidate();
    }
    private void setTabelModel() {
        DefaultTableModel model = (DefaultTableModel)tbkelas.getModel();
        model.addColumn("NO");
        model.addColumn("KELAS");
        model.addColumn("NO.Ruangan");
        model.addColumn("Wali Kelas");
     
    }

    private void getData(DefaultTableModel model) {
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM kelas";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
            String NOKelas = rs.getString("no");
            String Kelas = rs.getString("kelas");
            String NoRuangan = rs.getString("noruangan");
            String WaliKelas = rs.getString("walikelas");
        
            
            Object[] Baris ={NOKelas,Kelas,NoRuangan,WaliKelas};
            model.addRow(Baris);
        }
       }
     } catch (SQLException e){
            Logger.getLogger(MenuKelas.class.getName()).log(Level.SEVERE,null,e);
        }
    }    

    private void insertData() {
        String IDKelas = txtId.getText();
        String Kelas = txtkelas.getText();
        String NoRuangan = txtNoruangan.getText();
        String Walas = txtWalas.getText();
       
        
        if(IDKelas.isEmpty() || Kelas.isEmpty() || NoRuangan.isEmpty() || Walas.isEmpty()){
                JOptionPane.showMessageDialog(this, "semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
        }
        try{
            String sql = "INSERT INTO kelas (no,kelas,noruangan,walikelas) VALUES(?,?,?,?)";
            try(PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1,IDKelas);
                st.setString(2,Kelas);
                st.setString(3,NoRuangan);
                st.setString(4,Walas);
           
               
                int rowInserted = st.executeUpdate();
                if(rowInserted > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil di Dimpan");
                    resetForm();
                    loadData();
                    showPanel();
                   
                }
            }
        }catch(SQLException e){
           Logger.getLogger(MenuKelas.class.getName()).log(Level.SEVERE,null,e); 
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtkelas.setText("");
        txtNoruangan.setText("");
        txtWalas.setText("");

    }
    private void dataTabel() {
        panelView.setVisible(false);
        panelAdd.setVisible(true);
        
        int row = tbkelas.getSelectedRow();
        jLabel4.setText("Perbarui Data Kelas");
        
        txtId.setText(tbkelas.getValueAt(row, 0).toString());
        txtkelas.setText(tbkelas.getValueAt(row, 1).toString());
        txtNoruangan.setText(tbkelas.getValueAt(row, 2).toString());             
        txtWalas.setText(tbkelas.getValueAt(row, 3).toString());
      
    }

    private void updateData() {
       String IDKelas = txtId.getText();
        String Kelas = txtkelas.getText();
        String NoRuangan = txtNoruangan.getText();
        String Walas = txtWalas.getText();
       
        
        if(IDKelas.isEmpty() || Kelas.isEmpty() || NoRuangan.isEmpty() || Walas.isEmpty()){
        JOptionPane.showMessageDialog(this, "semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
        }
        try{
            String sql = "UPDATE kelas SET  kelas=?,noruangan=?,walikelas=? WHERE no=?";
            try(PreparedStatement st = conn.prepareStatement(sql)){
                
                st.setString(1,Kelas);
                st.setString(2,NoRuangan);
                st.setString(3,Walas);
                st.setString(4,IDKelas);
              

                
               
                int rowUpdated = st.executeUpdate();
                if(rowUpdated > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil di Perbarui");
                    resetForm();
                    loadData();
                    showPanel();
                   
                }
            }
        }catch(SQLException e){
           Logger.getLogger(MenuKelas.class.getName()).log(Level.SEVERE,null,e); 
        } 
    }

    private void deletData() {
     int selecteRow = tbkelas.getSelectedRow();
     int confirm = JOptionPane.showConfirmDialog(this, "Apakah yakin ingin menghapus data ini ?",
             "Konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
     if (confirm == JOptionPane.YES_OPTION){
         String id = tbkelas.getValueAt(selecteRow, 0).toString();
         try{
             String sql = "DELETE FROM kelas WHERE no=?";
             try(PreparedStatement st = conn.prepareStatement(sql)){
                 st.setString(1, id);
                 
                 int rowDeleted = st.executeUpdate();
                 if(rowDeleted > 0){
                     JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                    
                 }else{
                     JOptionPane.showMessageDialog(this, "Data Gagal dihapus");
                 }
             }
         } catch (SQLException e){    
         }
     }
     resetForm();
     loadData();
     showPanel();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbjeniskelamin = new javax.swing.ButtonGroup();
        panelMain = new javax.swing.JPanel();
        panelView = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbkelas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bthapus = new fosalgo.FButtonHapus();
        btTambah = new fosalgo.FButtonTambah();
        bBatal = new fosalgo.BATAL();
        panelAdd = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btSimpan = new fosalgo.FButtonTambah();
        btBatal = new fosalgo.BATAL();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNoruangan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtWalas = new javax.swing.JTextField();
        txtkelas = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));

        tbkelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tbkelas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbkelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbkelas);
        tbkelas.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Data Kelas");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Google Classroom.png"))); // NOI18N

        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cancel_1.png"))); // NOI18N
        bthapus.setText("HAPUS");
        bthapus.setFillOver(new java.awt.Color(255, 51, 0));
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btTambah.setText("TAMBAH");
        btTambah.setFillClick(new java.awt.Color(0, 153, 153));
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        bBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Go Back.png"))); // NOI18N
        bBatal.setText("UBAH");
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMain.add(panelView, "card2");

        panelAdd.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Tambah Data Kelas");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Google Classroom.png"))); // NOI18N

        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save.png"))); // NOI18N
        btSimpan.setText("SIMPAN");
        btSimpan.setFillClick(new java.awt.Color(0, 204, 51));
        btSimpan.setFillOver(new java.awt.Color(0, 153, 51));
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });

        btBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Go Back.png"))); // NOI18N
        btBatal.setText("BATAL");
        btBatal.setFillOver(new java.awt.Color(204, 51, 0));
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });

        txtId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel7.setText("NO");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel8.setText("NO Ruangan");

        txtNoruangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel12.setText("Wali Kelas");

        txtWalas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        txtkelas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkelasActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel14.setText("Kelas");

        javax.swing.GroupLayout panelAddLayout = new javax.swing.GroupLayout(panelAdd);
        panelAdd.setLayout(panelAddLayout);
        panelAddLayout.setHorizontalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 1121, Short.MAX_VALUE))
                            .addComponent(txtWalas))
                        .addContainerGap())
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoruangan)
                            .addComponent(txtId)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(panelAddLayout.createSequentialGroup()
                                        .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8)
                                    .addGroup(panelAddLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4))
                                    .addComponent(jLabel14))
                                .addGap(0, 930, Short.MAX_VALUE))
                            .addComponent(txtkelas))
                        .addGap(24, 24, 24))))
        );
        panelAddLayout.setVerticalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNoruangan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtWalas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelMain.add(panelAdd, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
    panelMain.removeAll();
    panelMain.add(panelAdd);
    panelMain.repaint();
    panelMain.revalidate();

    if(btTambah.getText().equals("UBAH")){
        dataTabel();
        btSimpan.setText("PERBARUI");
    }
    }//GEN-LAST:event_btTambahActionPerformed

    private void tbkelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkelasMouseClicked
    if(btTambah.getText().equals("TAMBAH"))
        btTambah.setText("TAMBAH");
        bthapus.setVisible(true);
        bBatal.setVisible(true);
    }//GEN-LAST:event_tbkelasMouseClicked

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
    showPanel();
    loadData();
    }//GEN-LAST:event_bBatalActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
     deletData();
    }//GEN-LAST:event_bthapusActionPerformed

    private void txtkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkelasActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        panelMain.removeAll();
        panelMain.add(panelView);
        panelMain.repaint();
        panelMain.revalidate();
        loadData();
    }//GEN-LAST:event_btBatalActionPerformed

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        if(btSimpan.getText().equals("TAMBAH"))
        {
            btSimpan.setText("SIMPAN");
        }
        else if(btSimpan.getText().equals("SIMPAN"))
        {
            insertData();
        }
        else if(btSimpan.getText().equals("PERBARUI"))
        {
            updateData();
        }
    }//GEN-LAST:event_btSimpanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.BATAL bBatal;
    private fosalgo.BATAL btBatal;
    private fosalgo.FButtonTambah btSimpan;
    private fosalgo.FButtonTambah btTambah;
    private fosalgo.FButtonHapus bthapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAdd;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTable tbkelas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNoruangan;
    private javax.swing.JTextField txtWalas;
    private javax.swing.JTextField txtkelas;
    // End of variables declaration//GEN-END:variables

}

        
