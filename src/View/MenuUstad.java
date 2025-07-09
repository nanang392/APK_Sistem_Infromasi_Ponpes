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

public class MenuUstad extends javax.swing.JPanel {
    private int totalPages;
    
    private final Connection conn;
    public MenuUstad() {
        initComponents();
        
        conn = koneksi.getConnection();
        setTabelModel();
        loadData();
    }

    
    private void loadData() {
        getData((DefaultTableModel) tbUstad.getModel());
        bthapus.setVisible(false);
        bBatal.setVisible(false);
    }
    
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new MenuUstad());
        panelMain.repaint();
        panelMain.revalidate();
    }
    private void setTabelModel() {
        DefaultTableModel model = (DefaultTableModel)tbUstad.getModel();
        model.addColumn("ID");
        model.addColumn("Nama Ustad");
        model.addColumn("Jabatan");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Tempat, Tanggal Lahir");
        model.addColumn("Alamat");
        model.addColumn("NO Hp");
        
    }

    private void getData(DefaultTableModel model) {
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM ustad";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
            String IDUstad = rs.getString("idustad");
            String NamaUstad = rs.getString("namaustad");
            String Jabatan = rs.getString("jabatan");
            String JenisKelamin = rs.getString("jenis");
            String TempatTL = rs.getString("ttl");
            String Alamat = rs.getString("alamat");
            String NoHp = rs.getString("nohp");
            
            Object[] Baris ={IDUstad,NamaUstad,Jabatan,JenisKelamin,TempatTL,Alamat,NoHp};
            model.addRow(Baris);
        }
       }
     } catch (SQLException e){
            Logger.getLogger(MenuUstad.class.getName()).log(Level.SEVERE,null,e);
        }
    }    

    private void insertData() {
        String IDUstad = txtID.getText();
        String NamaUstad = txtNamaust.getText();
        String Jabatan = txtJabatan.getText();
        String JenisKelamin;
            if (rbLakiust.isSelected()){
                JenisKelamin = rbLakiust.getText();
            }else if(rbPerempuanust.isSelected()) {
                JenisKelamin = rbPerempuanust.getText();   
            }else {
                JenisKelamin ="";
            }
        String TempatTL = txtTglust.getText();
        String Alamat = txtAlamatust.getText();
        String NoHp = txtNohp.getText();
        
        if(IDUstad.isEmpty() || NamaUstad.isEmpty() || JenisKelamin.isEmpty() || TempatTL.isEmpty() || 
                Alamat.isEmpty() || NoHp.isEmpty()){
                JOptionPane.showMessageDialog(this, "semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
        }
        try{
            String sql = "INSERT INTO ustad (idustad,namaustad,jabatan,jenis,ttl,alamat,nohp) VALUES(?,?,?,?,?,?,?)";
            try(PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1,IDUstad);
                st.setString(2,NamaUstad);
                st.setString(3,Jabatan);
                st.setString(4,JenisKelamin);
                st.setString(5,TempatTL);
                st.setString(6,Alamat);
                st.setString(7,NoHp);

               
                int rowInserted = st.executeUpdate();
                if(rowInserted > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil di Dimpan");
                    resetForm();
                    loadData();
                    showPanel();
                   
                }
            }
        }catch(SQLException e){
           Logger.getLogger(MenuUstad.class.getName()).log(Level.SEVERE,null,e); 
        }
    }

    private void resetForm() {
        txtID.setText("");
        txtNamaust.setText("");
        txtJabatan.setText("");
        rbjeniskelamin.clearSelection();
        txtTglust.setText("");
        txtAlamatust.setText("");
        txtNohp.setText("");

    }
    private void dataTabel() {
        panelView.setVisible(false);
        panelAdd.setVisible(true);
        
        int row = tbUstad.getSelectedRow();
        jLabel4.setText("Perbarui Data Ustad/z");
        
        txtID.setText(tbUstad.getValueAt(row, 0).toString());
        txtNamaust.setText(tbUstad.getValueAt(row, 1).toString());
        txtJabatan.setText(tbUstad.getValueAt(row, 2).toString());
        
        String JenisKelamin = tbUstad.getValueAt(row, 3).toString();
            if(JenisKelamin.equals("Laki - laki")){
                rbLakiust.setSelected(true);
            }else if(JenisKelamin.equals("Perempuan")){
                rbPerempuanust.setSelected(true);
            }
                     
        txtTglust.setText(tbUstad.getValueAt(row, 4).toString());
        txtAlamatust.setText(tbUstad.getValueAt(row, 5).toString());
        txtNohp.setText(tbUstad.getValueAt(row, 6).toString());

    }

    private void updateData() {
       String IDUstad = txtID.getText();
        String NamaUstad = txtNamaust.getText();
        String Jabatan = txtJabatan.getText();
        String JenisKelamin;
            if (rbLakiust.isSelected()){
                JenisKelamin = rbLakiust.getText();
            }else if(rbPerempuanust.isSelected()) {
                JenisKelamin = rbPerempuanust.getText();   
            }else {
                JenisKelamin ="";
            }
        String TempatTL = txtTglust.getText();
        String Alamat = txtAlamatust.getText();
        String NoHp = txtNohp.getText();

        
        if(IDUstad.isEmpty() || NamaUstad.isEmpty() || Jabatan.isEmpty() || JenisKelamin.isEmpty() || TempatTL.isEmpty() 
                || Alamat.isEmpty()|| NoHp.isEmpty()){
        JOptionPane.showMessageDialog(this, "semua kolom harus diisi", "Validasi", JOptionPane.ERROR_MESSAGE);
                return;
        }
        try{
            String sql = "UPDATE ustad SET namaustad=?,jabatan=?,jenis=?,ttl=?,alamat=?,nohp=? WHERE idustad=?";
            try(PreparedStatement st = conn.prepareStatement(sql)){
                
                st.setString(1,NamaUstad);
                st.setString(2,Jabatan);
                st.setString(3,JenisKelamin);
                st.setString(4,TempatTL);
                st.setString(5,Alamat);
                st.setString(6,NoHp);                     
                st.setString(7,IDUstad);
                
               
                int rowUpdated = st.executeUpdate();
                if(rowUpdated > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil di Perbarui");
                    resetForm();
                    loadData();
                    showPanel();
                   
                }
            }
        }catch(SQLException e){
           Logger.getLogger(MenuUstad.class.getName()).log(Level.SEVERE,null,e); 
        } 
    }

    private void deletData() {
     int selecteRow = tbUstad.getSelectedRow();
     int confirm = JOptionPane.showConfirmDialog(this, "Apakah yakin ingin menghapus data ini ?",
             "Konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
     if (confirm == JOptionPane.YES_OPTION){
         String id = tbUstad.getValueAt(selecteRow, 0).toString();
         try{
             String sql = "DELETE FROM ustad WHERE idustad=?";
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
        
        DefaultTableModel model = (DefaultTableModel) tbUstad.getModel();
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM ustad WHERE namaustad LIKE ? OR idustad LIKE ?";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        st.setString(1,"%" + kataKunci + "%");
        st.setString(2,"%" + kataKunci + "%");
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
            String IDUstad = rs.getString("idustad");
            String NamaUstad = rs.getString("namaustad");
            String JenisKelamin = rs.getString("jenis");
            String TempatTL = rs.getString("ttl");
            String Alamat = rs.getString("alamat");
            String NoHp = rs.getString("nohp");
            
            Object[] Baris ={IDUstad,NamaUstad,JenisKelamin,TempatTL,Alamat,NoHp};
            model.addRow(Baris);
        }
       }
     } catch (SQLException e){
            Logger.getLogger(MenuUstad.class.getName()).log(Level.SEVERE,null,e);
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
        tbUstad = new javax.swing.JTable();
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
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNamaust = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAlamatust = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNohp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTglust = new javax.swing.JTextField();
        rbLakiust = new javax.swing.JRadioButton();
        rbPerempuanust = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtJabatan = new javax.swing.JTextField();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));

        tbUstad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tbUstad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbUstad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUstadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUstad);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Data Ustad/z");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tuition_1.png"))); // NOI18N

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
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(563, 563, 563)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );

        panelMain.add(panelView, "card2");

        panelAdd.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Tambah Data Ustad/z");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tuition_1.png"))); // NOI18N

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

        txtID.setText("  ");
        txtID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel7.setText("ID Ustad");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel8.setText("Nama Ustad");

        txtNamaust.setText("  ");
        txtNamaust.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel9.setText("Jenis Kelamin");

        txtAlamatust.setText("  ");
        txtAlamatust.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel10.setText("Alamat");

        txtNohp.setText("  ");
        txtNohp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel11.setText("NO. HP");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel12.setText("Tempat, Tanggal Lahir");

        txtTglust.setText("  ");
        txtTglust.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        rbLakiust.setBackground(new java.awt.Color(255, 255, 255));
        rbjeniskelamin.add(rbLakiust);
        rbLakiust.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        rbLakiust.setText("Laki - laki");
        rbLakiust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiustActionPerformed(evt);
            }
        });

        rbPerempuanust.setBackground(new java.awt.Color(255, 255, 255));
        rbjeniskelamin.add(rbPerempuanust);
        rbPerempuanust.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        rbPerempuanust.setText("Perempuan");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel13.setText("Jabatan");

        txtJabatan.setText("  ");
        txtJabatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        javax.swing.GroupLayout panelAddLayout = new javax.swing.GroupLayout(panelAdd);
        panelAdd.setLayout(panelAddLayout);
        panelAddLayout.setHorizontalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamaust)
                    .addComponent(txtID)
                    .addComponent(txtAlamatust)
                    .addComponent(txtNohp)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(rbLakiust)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbPerempuanust))
                            .addComponent(jLabel7)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(0, 1018, Short.MAX_VALUE))
                    .addComponent(txtTglust)
                    .addComponent(txtJabatan))
                .addGap(24, 24, 24))
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
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNamaust, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLakiust)
                    .addComponent(rbPerempuanust))
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addGap(20, 20, 20)
                .addComponent(txtTglust, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAlamatust, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNohp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
        );

        panelMain.add(panelAdd, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void rbLakiustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiustActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbLakiustActionPerformed

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

    private void tbUstadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUstadMouseClicked
    if(btTambah.getText().equals("TAMBAH"))
        btTambah.setText("TAMBAH");
        bthapus.setVisible(true);
        bBatal.setVisible(true);
    }//GEN-LAST:event_tbUstadMouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.BATAL bBatal;
    private fosalgo.BATAL btBatal;
    private fosalgo.FButtonTambah btSimpan;
    private fosalgo.FButtonTambah btTambah;
    private fosalgo.FButtonHapus bthapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JRadioButton rbLakiust;
    private javax.swing.JRadioButton rbPerempuanust;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTable tbUstad;
    private javax.swing.JTextField txtAlamatust;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtJabatan;
    private javax.swing.JTextField txtNamaust;
    private javax.swing.JTextField txtNohp;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTglust;
    // End of variables declaration//GEN-END:variables

     
}

        
