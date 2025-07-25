/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author Nanang M32
 */
public class MenuDashbord extends javax.swing.JPanel {

    static void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private final Connection conn;
    public MenuDashbord() {
        initComponents();
        conn = koneksi.getConnection();
        setTabelModel();
        loadData();
        
        
    }
    
    public void getData(DefaultTableModel model){
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM santri";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
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

    private void setTabelModel() {
        DefaultTableModel model = (DefaultTableModel)tbsiswa.getModel();
        model.addColumn("NISN");
        model.addColumn("Nama Santri");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Tempat, Tanggal Lahir");
        model.addColumn("Kelas");
        model.addColumn("Alamat");
        model.addColumn("Orang Tua/ Wali");
    }

    private int jumlahsantri(){
        int totalSantri =0;
        try{
            String sql = "SELECT COUNT(*) AS total FROM santri";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                totalSantri = rs.getInt("total");
            }
        }catch(Exception e) {
            e.printStackTrace();
    }return totalSantri;
    }
    
     private int jumlahustad(){
        int totalUstad =0;
        try{
            String sql = "SELECT COUNT(*) AS total FROM ustad";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                totalUstad = rs.getInt("total");
            }
        }catch(Exception e) {
            e.printStackTrace();
    }return totalUstad;
    }
     
     private int jumlahKelas(){
        int totalKelas =0;
        try{
            String sql = "SELECT COUNT(*) AS total FROM kelas";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                totalKelas = rs.getInt("total");
            }
        }catch(Exception e) {
            e.printStackTrace();
    }return totalKelas;
    }
     private int jumlahPembayaran(){
        int totalPembayaran =0;
        try{
            String sql = "SELECT COUNT(*) AS total FROM pembayaran";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                totalPembayaran = rs.getInt("total");
            }
        }catch(Exception e) {
            e.printStackTrace();
    }return totalPembayaran;
    }

    private void loadData() {
        lbJmlsantri.setText(String.valueOf(jumlahsantri()));
        lbJmlustd.setText(String.valueOf(jumlahustad()));
        lbjmlkelas.setText(String.valueOf(jumlahKelas()));
        lbpembayaran.setText(String.valueOf(jumlahPembayaran()));
    getData((DefaultTableModel) tbsiswa.getModel());
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
        carddatasantri = new javax.swing.JPanel();
        nama = new javax.swing.JLabel();
        lbJmlsantri = new javax.swing.JLabel();
        icone = new javax.swing.JLabel();
        carddataustad = new javax.swing.JPanel();
        namaustad = new javax.swing.JLabel();
        lbJmlustd = new javax.swing.JLabel();
        icone1 = new javax.swing.JLabel();
        carddatakelas = new javax.swing.JPanel();
        namakelas = new javax.swing.JLabel();
        lbjmlkelas = new javax.swing.JLabel();
        icone2 = new javax.swing.JLabel();
        carddataasrama = new javax.swing.JPanel();
        pembayaran = new javax.swing.JLabel();
        lbpembayaran = new javax.swing.JLabel();
        icone5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsiswa = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        carddatasantri.setBackground(new java.awt.Color(0, 255, 51));

        nama.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        nama.setForeground(new java.awt.Color(102, 102, 102));
        nama.setText("DATA SANTRI");

        lbJmlsantri.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJmlsantri.setForeground(new java.awt.Color(102, 102, 102));
        lbJmlsantri.setText("25");

        icone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Conference_1.png"))); // NOI18N

        javax.swing.GroupLayout carddatasantriLayout = new javax.swing.GroupLayout(carddatasantri);
        carddatasantri.setLayout(carddatasantriLayout);
        carddatasantriLayout.setHorizontalGroup(
            carddatasantriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddatasantriLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(carddatasantriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carddatasantriLayout.createSequentialGroup()
                        .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(icone))
                    .addGroup(carddatasantriLayout.createSequentialGroup()
                        .addComponent(lbJmlsantri)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        carddatasantriLayout.setVerticalGroup(
            carddatasantriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddatasantriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(carddatasantriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(icone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lbJmlsantri)
                .addContainerGap())
        );

        carddataustad.setBackground(new java.awt.Color(0, 255, 204));

        namaustad.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        namaustad.setForeground(new java.awt.Color(102, 102, 102));
        namaustad.setText("DATA USTAD/Z");

        lbJmlustd.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJmlustd.setForeground(new java.awt.Color(102, 102, 102));
        lbJmlustd.setText("15");

        icone1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tuition.png"))); // NOI18N

        javax.swing.GroupLayout carddataustadLayout = new javax.swing.GroupLayout(carddataustad);
        carddataustad.setLayout(carddataustadLayout);
        carddataustadLayout.setHorizontalGroup(
            carddataustadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddataustadLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(carddataustadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaustad, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(carddataustadLayout.createSequentialGroup()
                        .addComponent(lbJmlustd)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icone1)
                .addContainerGap())
        );
        carddataustadLayout.setVerticalGroup(
            carddataustadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddataustadLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(carddataustadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(icone1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namaustad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(lbJmlustd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        carddatakelas.setBackground(new java.awt.Color(0, 255, 51));

        namakelas.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        namakelas.setForeground(new java.awt.Color(102, 102, 102));
        namakelas.setText("DATA KELAS");

        lbjmlkelas.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbjmlkelas.setForeground(new java.awt.Color(102, 102, 102));
        lbjmlkelas.setText("06");

        icone2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Online Group Studying.png"))); // NOI18N

        javax.swing.GroupLayout carddatakelasLayout = new javax.swing.GroupLayout(carddatakelas);
        carddatakelas.setLayout(carddatakelasLayout);
        carddatakelasLayout.setHorizontalGroup(
            carddatakelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddatakelasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(carddatakelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbjmlkelas)
                    .addGroup(carddatakelasLayout.createSequentialGroup()
                        .addComponent(namakelas)
                        .addGap(58, 58, 58)
                        .addComponent(icone2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        carddatakelasLayout.setVerticalGroup(
            carddatakelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddatakelasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(carddatakelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(icone2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namakelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lbjmlkelas)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        carddataasrama.setBackground(new java.awt.Color(0, 255, 255));

        pembayaran.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        pembayaran.setForeground(new java.awt.Color(102, 102, 102));
        pembayaran.setText("PEMBAYARAN");

        lbpembayaran.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbpembayaran.setForeground(new java.awt.Color(102, 102, 102));
        lbpembayaran.setText("10");

        icone5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MacBook Money_2.png"))); // NOI18N

        javax.swing.GroupLayout carddataasramaLayout = new javax.swing.GroupLayout(carddataasrama);
        carddataasrama.setLayout(carddataasramaLayout);
        carddataasramaLayout.setHorizontalGroup(
            carddataasramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddataasramaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(carddataasramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carddataasramaLayout.createSequentialGroup()
                        .addComponent(lbpembayaran)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(carddataasramaLayout.createSequentialGroup()
                        .addComponent(pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(icone5)
                        .addGap(20, 20, 20))))
        );
        carddataasramaLayout.setVerticalGroup(
            carddataasramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carddataasramaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(carddataasramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(icone5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lbpembayaran)
                .addContainerGap())
        );

        tbsiswa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 0)));
        tbsiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbsiswa.setGridColor(new java.awt.Color(0, 204, 0));
        jScrollPane1.setViewportView(tbsiswa);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Data Santri");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(carddatasantri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(carddataustad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(49, 49, 49)
                            .addComponent(carddatakelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(54, 54, 54)
                            .addComponent(carddataasrama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(carddatasantri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carddataasrama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carddatakelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carddataustad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel carddataasrama;
    private javax.swing.JPanel carddatakelas;
    private javax.swing.JPanel carddatasantri;
    private javax.swing.JPanel carddataustad;
    private javax.swing.JLabel icone;
    private javax.swing.JLabel icone1;
    private javax.swing.JLabel icone2;
    private javax.swing.JLabel icone5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbJmlsantri;
    private javax.swing.JLabel lbJmlustd;
    private javax.swing.JLabel lbjmlkelas;
    private javax.swing.JLabel lbpembayaran;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel namakelas;
    private javax.swing.JLabel namaustad;
    private javax.swing.JLabel pembayaran;
    private javax.swing.JTable tbsiswa;
    // End of variables declaration//GEN-END:variables

}
