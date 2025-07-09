package View;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class MenuSantri extends javax.swing.JPanel {
    private int totalPages;
    
    private final Connection conn;
    String namaasrama;
    String Kelas;
    String kelas;
    String no;
    String noruangan;
    String walikelas;
    private Map<String, Object> parameter;
    
    public MenuSantri() {
        initComponents();
        
        conn = koneksi.getConnection();
        setTabelModel();
        loadData();
    }

    private void loadData() {
        getData((DefaultTableModel) tblsiswa.getModel());
        bthapus.setVisible(false);
        bBatal.setVisible(false);
    }
    
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new MenuSantri());
        panelMain.repaint();
        panelMain.revalidate();
    }
    private void setTabelModel() {
        DefaultTableModel model = (DefaultTableModel)tblsiswa.getModel();
        model.addColumn("ID Santri");
        model.addColumn("NISN");
        model.addColumn("Nama Santri");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Tempat, Tanggal Lahir");
        model.addColumn("Kelas");
        model.addColumn("Alamat");
        model.addColumn("Orang Tua/ Wali");
        model.addColumn("Tahun Masuk");
    }

    private void getData(DefaultTableModel model) {
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM santri";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
            String IDSantri = rs.getString("idsantri");
            String NisnSiswa = rs.getString("nisn");
            String NamaSiswa = rs.getString("nama");
            String JenisKelamin = rs.getString("jenisk");
            String TempatTL = rs.getString("ttl");
            String Kelas = rs.getString("kelas");
            String AlamatSiswa = rs.getString("alamat");
            String OrangtuaWali = rs.getString("ortu");
            String Thnmasuk = rs.getString("thnmasuk");
            
            Object[] Baris ={IDSantri,NisnSiswa,NamaSiswa,JenisKelamin,TempatTL,Kelas,AlamatSiswa,OrangtuaWali,Thnmasuk};
            model.addRow(Baris);
        }
       }
     } catch (SQLException e){
            Logger.getLogger(MenuSantri.class.getName()).log(Level.SEVERE,null,e);
        }
    }    

    private void insertData() {
        String IDSantri = txtId.getText();
        String NisnSiswa = txtNisn.getText();
        String NamaSiswa = txtNamasiswa.getText();
        String JenisKelamin;
            if (rbLaki.isSelected()){
                JenisKelamin = rbLaki.getText();
            }else if(rbPerempuan.isSelected()) {
                JenisKelamin = rbPerempuan.getText();   
            }else {
                JenisKelamin ="";
            }
        String TempatTL = txtTgl.getText();
        String Kelas = txtKelas.getText();
        String AlamatSiswa = txtAlamat.getText();
        String OrangtuaWali = txtortu.getText();
        String Thnmasuk = txtthnmasuk.getText();
        
        if(IDSantri.isEmpty() || NisnSiswa.isEmpty() || NamaSiswa.isEmpty() || JenisKelamin.isEmpty() || TempatTL.isEmpty() || 
                Kelas.isEmpty() || AlamatSiswa.isEmpty() || OrangtuaWali.isEmpty() || Thnmasuk.isEmpty()){
                JOptionPane.showMessageDialog(this, "semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
        }
        try{
            String sql = "INSERT INTO santri (idsantri,nisn,nama,jenisk,ttl,kelas,alamat,ortu,asrama) VALUES(?,?,?,?,?,?,?,?,?)";
            try(PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1,IDSantri);
                st.setString(2,NisnSiswa);
                st.setString(3,NamaSiswa);
                st.setString(4,JenisKelamin);
                st.setString(5,TempatTL);
                st.setString(6,Kelas);
                st.setString(7,AlamatSiswa);
                st.setString(8,OrangtuaWali);
                st.setString(9,Thnmasuk);
               
                int rowInserted = st.executeUpdate();
                if(rowInserted > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil di Dimpan");
                    resetForm();
                    loadData();
                    showPanel();
                   
                }
            }
        }catch(SQLException e){
           Logger.getLogger(MenuSantri.class.getName()).log(Level.SEVERE,null,e); 
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtNisn.setText("");
        txtNamasiswa.setText("");
        rbjeniskelamin.clearSelection();
        txtTgl.setText("");
        txtKelas.setText("");
        txtAlamat.setText("");
        txtortu.setText("");
    }
    private void dataTabel() {
        panelView.setVisible(false);
        panelAdd.setVisible(true);
        
        int row = tblsiswa.getSelectedRow();
        jLabel4.setText("Perbarui Data Santri");
        
        txtId.setText(tblsiswa.getValueAt(row, 0).toString());
        txtNisn.setText(tblsiswa.getValueAt(row, 1).toString());
        txtNamasiswa.setText(tblsiswa.getValueAt(row, 2).toString());
        
        String JenisKelamin = tblsiswa.getValueAt(row, 3).toString();
            if(JenisKelamin.equals("Laki - laki")){
                rbLaki.setSelected(true);
            }else if(JenisKelamin.equals("Perempuan")){
                rbPerempuan.setSelected(true);
            }
                     
        txtTgl.setText(tblsiswa.getValueAt(row, 4).toString());
        txtKelas.setText(tblsiswa.getValueAt(row, 5).toString());
        txtAlamat.setText(tblsiswa.getValueAt(row, 6).toString());
        txtortu.setText(tblsiswa.getValueAt(row, 7).toString());
        txtthnmasuk.setText(tblsiswa.getValueAt(row, 8).toString());
    }

    private void updateData() {
       String IDSantri = txtId.getText();
       String NisnSiswa = txtNisn.getText();
        String NamaSiswa = txtNamasiswa.getText();
        String JenisKelamin;
            if (rbLaki.isSelected()){
                JenisKelamin = rbLaki.getText();
            }else if(rbPerempuan.isSelected()) {
                JenisKelamin = rbPerempuan.getText();   
            }else {
                JenisKelamin ="";
            }
        String TempatTL = txtTgl.getText();
        String Kelas = txtKelas.getText();
        String AlamatSiswa = txtAlamat.getText();
        String OrangtuaWali = txtortu.getText();
        String Thnmasuk = txtthnmasuk.getText();
        
        if(IDSantri.isEmpty() || NisnSiswa.isEmpty() || NamaSiswa.isEmpty() || JenisKelamin.isEmpty() || TempatTL.isEmpty() 
                || Kelas.isEmpty()|| AlamatSiswa.isEmpty() || OrangtuaWali.isEmpty() || Thnmasuk.isEmpty()){
        JOptionPane.showMessageDialog(this, "semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
        }
        try{
            String sql = "UPDATE santri SET  nisn=?,nama=?,jenisk=?,ttl=?,kelas=?,alamat=?,ortu=?,thnmasuk=? WHERE idsantri=?";
            try(PreparedStatement st = conn.prepareStatement(sql)){
                
                st.setString(1,NisnSiswa);
                st.setString(2,NamaSiswa);
                st.setString(3,JenisKelamin);
                st.setString(4,TempatTL);
                st.setString(5,Kelas);
                st.setString(6,AlamatSiswa);
                st.setString(7,OrangtuaWali);
                st.setString(8,Thnmasuk);   
                st.setString(9,IDSantri);

                
               
                int rowUpdated = st.executeUpdate();
                if(rowUpdated > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil di Perbarui");
                    resetForm();
                    loadData();
                    showPanel();
                   
                }
            }
        }catch(SQLException e){
           Logger.getLogger(MenuSantri.class.getName()).log(Level.SEVERE,null,e); 
        } 
    }

    private void deletData() {
     int selecteRow = tblsiswa.getSelectedRow();
     int confirm = JOptionPane.showConfirmDialog(this, "Apakah yakin ingin menghapus data ini ?",
             "Konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
     if (confirm == JOptionPane.YES_OPTION){
         String id = tblsiswa.getValueAt(selecteRow, 0).toString();
         try{
             String sql = "DELETE FROM santri WHERE idsantri=?";
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

    private void searchData() {
        String kataKunci = txtSearch.getText();
        
        DefaultTableModel model = (DefaultTableModel) tblsiswa.getModel();
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM santri WHERE nama LIKE ? OR kelas LIKE ? OR idsantri LIKE ?";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        st.setString(1,"%" + kataKunci + "%");
        st.setString(2,"%" + kataKunci + "%");
        st.setString(3,"%" + kataKunci + "%");
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
            String IDSantri = rs.getString("idsantri");
            String NisnSiswa = rs.getString("nisn");
            String NamaSiswa = rs.getString("nama");
            String JenisKelamin = rs.getString("jenisk");
            String TempatTL = rs.getString("ttl");
            String Kelas = rs.getString("kelas");
            String AlamatSiswa = rs.getString("alamat");
            String OrangtuaWali = rs.getString("ortu");
            
            Object[] Baris ={NisnSiswa,NamaSiswa,JenisKelamin,TempatTL,Kelas,AlamatSiswa,OrangtuaWali};
            model.addRow(Baris);
        }
       }
     } catch (SQLException e){
            Logger.getLogger(MenuSantri.class.getName()).log(Level.SEVERE,null,e);
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

        rbjeniskelamin = new javax.swing.ButtonGroup();
        panelMain = new javax.swing.JPanel();
        panelView = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsiswa = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bthapus = new fosalgo.FButtonHapus();
        btTambah = new fosalgo.FButtonTambah();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bBatal = new fosalgo.BATAL();
        panelAdd = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btSimpan = new fosalgo.FButtonTambah();
        btBatal = new fosalgo.BATAL();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNamasiswa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTgl = new javax.swing.JTextField();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtortu = new javax.swing.JTextField();
        txtNisn = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtKelas = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtthnmasuk = new javax.swing.JTextField();
        bcari1 = new fosalgo.CARI();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));

        tblsiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tblsiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblsiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsiswa);
        tblsiswa.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Data Santri");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/User Groups_1.png"))); // NOI18N

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

        txtSearch.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Google Web Search_2.png"))); // NOI18N

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
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewLayout.createSequentialGroup()
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );

        panelMain.add(panelView, "card2");

        panelAdd.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Tambah Data Santri");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/User Groups_1.png"))); // NOI18N

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
        jLabel7.setText("ID Santri");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel8.setText("Nama Santri");

        txtNamasiswa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel9.setText("Jenis Kelamin");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel10.setText("Kelas");

        txtAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel11.setText("Alamat");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel12.setText("Tempat, Tanggal Lahir");

        txtTgl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglActionPerformed(evt);
            }
        });

        rbLaki.setBackground(new java.awt.Color(255, 255, 255));
        rbjeniskelamin.add(rbLaki);
        rbLaki.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        rbLaki.setText("Laki - laki");
        rbLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiActionPerformed(evt);
            }
        });

        rbPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        rbjeniskelamin.add(rbPerempuan);
        rbPerempuan.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        rbPerempuan.setText("Perempuan");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel13.setText("Orang Tua/ Wali");

        txtortu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtortu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtortuActionPerformed(evt);
            }
        });

        txtNisn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtNisn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNisnActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel14.setText("NISN");

        txtKelas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKelasActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel15.setText("Tahun Masuk");

        txtthnmasuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtthnmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtthnmasukActionPerformed(evt);
            }
        });

        bcari1.setText("cari");
        bcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAddLayout = new javax.swing.GroupLayout(panelAdd);
        panelAdd.setLayout(panelAddLayout);
        panelAddLayout.setHorizontalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNamasiswa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                    .addComponent(txtNisn, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAddLayout.createSequentialGroup()
                        .addComponent(rbLaki)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbPerempuan))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAddLayout.createSequentialGroup()
                        .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAddLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTgl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtortu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAddLayout.createSequentialGroup()
                            .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtthnmasuk, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(172, 172, 172))
        );
        panelAddLayout.setVerticalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtortu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNisn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNamasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbLaki)
                            .addComponent(rbPerempuan))))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtthnmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(281, Short.MAX_VALUE))
        );

        panelMain.add(panelAdd, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void rbLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbLakiActionPerformed

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

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
    panelMain.removeAll();
    panelMain.add(panelView);
    panelMain.repaint();
    panelMain.revalidate();
    loadData();
    }//GEN-LAST:event_btBatalActionPerformed

    private void tblsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsiswaMouseClicked
    if(btTambah.getText().equals("TAMBAH"))
        btTambah.setText("TAMBAH");
        bthapus.setVisible(true);
        bBatal.setVisible(true);
    }//GEN-LAST:event_tblsiswaMouseClicked

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
    showPanel();
    loadData();
    }//GEN-LAST:event_bBatalActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
     deletData();
    }//GEN-LAST:event_bthapusActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
     searchData();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtNisnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNisnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNisnActionPerformed

    private void txtortuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtortuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtortuActionPerformed

    private void txtTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglActionPerformed

    private void txtthnmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtthnmasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtthnmasukActionPerformed
    public void itemTerpilih(){
            popupasrama Pp = new popupasrama();
            Pp.asrama = this;
            txtthnmasuk.setText(namaasrama);

        }
    private void txtKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKelasActionPerformed

    private void bcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari1ActionPerformed
        popupkelas Pp = new popupkelas();
        Pp.kelas = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_bcari1ActionPerformed
    public void itemTerpilih1(){
        popupkelas Pp = new popupkelas();
        Pp.kelas = this;
        txtKelas.setText(kelas);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.BATAL bBatal;
    private fosalgo.CARI bcari1;
    private fosalgo.BATAL btBatal;
    private fosalgo.FButtonTambah btSimpan;
    private fosalgo.FButtonTambah btTambah;
    private fosalgo.FButtonHapus bthapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAdd;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTable tblsiswa;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtKelas;
    private javax.swing.JTextField txtNamasiswa;
    private javax.swing.JTextField txtNisn;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTgl;
    private javax.swing.JTextField txtortu;
    private javax.swing.JTextField txtthnmasuk;
    // End of variables declaration//GEN-END:variables

     
}

        
