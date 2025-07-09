package View;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

/**
 *
 * @author Nanang M32
 */
public class LaporanNilai extends javax.swing.JPanel {
    private int totalPages;
    private DefaultTableModel tabmode;
    private final Connection conn;
    private Component rootPane;

    private Map<String, Object> parameter;
    String semester;
    String thnmasuk;
    String kelas;
    public LaporanNilai() {
        initComponents();
        
        
        conn = koneksi.getConnection();
        setTabelModel();
        loadData();

    }
 private void setTabelModel() {
        
        Object[] Baris ={"NO ID","NISN","Nama","Kelas",
            "PPKN","B.indo","MTK","IPA","B.Inggris","IPS","TIK"
            ,"B.Arab","Al-Quran","Hadist","Fiqh","Akhlaq","Akidah",
            "Tarikh","Shorof","Aswaja"};
        tabmode = new DefaultTableModel(null, Baris);
 
        try {
        String sql = "SELECT * FROM nilai ";
        java.sql.Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        while (hasil.next()){
        tabmode.addRow(new Object[]{
        hasil.getString(1),
        hasil.getString(2),
        hasil.getString(3),
        hasil.getString(4),
        hasil.getString(5),
        hasil.getString(6),
        hasil.getString(7),
        hasil.getString(8),
        hasil.getString(9),
        hasil.getString(10),
        hasil.getString(11),
        hasil.getString(12),
        hasil.getString(13),
        hasil.getString(14),
        hasil.getString(15),
        hasil.getString(16),
        hasil.getString(17),
        hasil.getString(18),
        hasil.getString(19),
        hasil.getString(20),
        hasil.getString(21),
        hasil.getString(22),
        hasil.getString(23),
        hasil.getString(24),
        hasil.getString(25),
        hasil.getString(26),
        
        
        
        });
        }
        tblnilai.setModel(tabmode);
        } catch (SQLException e) {
            
        }
    }
  private void loadData() {
        getData((DefaultTableModel) tblnilai.getModel());

    }
    private void getData(DefaultTableModel model) {
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM nilai";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
            String NO = rs.getString("no");
            String NisnSiswa = rs.getString("nisn");
            String NamaSiswa = rs.getString("nama");
            String Kelas = rs.getString("kelas");
            String PPKN = rs.getString("ppkn");
            String BIndo = rs.getString("bindo");
            String MTK = rs.getString("mtk");
            String IPA = rs.getString("ipa");
            String BInggris = rs.getString("binggris");
            String IPS = rs.getString("ips");
            String TIK = rs.getString("tik");
            String BArab = rs.getString("barab");
            String AlQuran = rs.getString("alquran");
            String Hadist = rs.getString("hadist");
            String Fiqh = rs.getString("fiqh");
            String Akhlak = rs.getString("akhlak");
            String Akidah = rs.getString("akidah");
            String Tarikh = rs.getString("tarikh");
            String Shorof = rs.getString("shorof");
            String Aswaja = rs.getString("aswaja");
            
            Object[] Baris ={NO, NisnSiswa, NamaSiswa, Kelas, 
            PPKN, BIndo, MTK, IPA, BInggris, IPS, TIK, 
            BArab, AlQuran, Hadist, Fiqh, Akhlak, Akidah, 
            Tarikh, Shorof, Aswaja};
            model.addRow(Baris);
        }
       }
     } catch (SQLException e){
            Logger.getLogger(Nilai.class.getName()).log(Level.SEVERE,null,e);
        }
    }    
    private void searchData() {
        String kataKunci = txtkelas.getText();
        
        DefaultTableModel model = (DefaultTableModel) tblnilai.getModel();
        model.setRowCount(0);
        
    try {
    String sql ="SELECT * FROM santri WHERE kelas LIKE ?";
    try (PreparedStatement st = conn.prepareStatement(sql)){
        st.setString(1,"%" + kataKunci + "%");
        ResultSet rs = st.executeQuery();
        
        while (rs.next()){
          String NisnSiswa = rs.getString("nisn");
            String NamaSiswa = rs.getString("nama");
            String JenisKelamin = rs.getString("jenisk");
            String TempatTL = rs.getString("ttl");
            String Kelas = rs.getString("kelas");
            String AlamatSiswa = rs.getString("alamat");
            String OrangtuaWali = rs.getString("ortu");
            String Thnmasuk = rs.getString("thnmasuk");
             String Semester = rs.getString("semester");
            
            Object[] Baris ={NisnSiswa,NamaSiswa,JenisKelamin,TempatTL,Kelas,AlamatSiswa,OrangtuaWali,Thnmasuk,Semester};
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
        jLabel1 = new javax.swing.JLabel();
        Bcetak = new fosalgo.CARI();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnilai = new javax.swing.JTable();
        txtkelas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txttahun = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsemester = new javax.swing.JTextField();
        bcari = new fosalgo.CARI();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Laporan Nilai Santri TP. 2024/2025");

        Bcetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Print.png"))); // NOI18N
        Bcetak.setText("Cetak Nilai");
        Bcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcetakActionPerformed(evt);
            }
        });

        tblnilai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tblnilai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblnilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnilai);

        txtkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkelasActionPerformed(evt);
            }
        });

        jLabel2.setText("Kelas");

        jLabel3.setText("Tahun");

        jLabel4.setText("Semester");

        bcari.setText("cari");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(447, 447, 447)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtkelas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                    .addComponent(txttahun, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsemester, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(Bcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(Bcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        panelMain.add(panelView, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void BcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BcetakActionPerformed
        try {
            String path="src/Report/lpnilai.jasper";      // letak penyimpanan report
            HashMap parameter = new HashMap();
            parameter.put("kelasnilai",txtkelas.getText());
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
        }
    }//GEN-LAST:event_BcetakActionPerformed

    private void tblnilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnilaiMouseClicked
       
    }//GEN-LAST:event_tblnilaiMouseClicked

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        popupnilaisiswa Pp = new popupnilaisiswa();
        Pp.nilaisantri = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_bcariActionPerformed

    private void txtkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkelasActionPerformed
        searchData();
    }//GEN-LAST:event_txtkelasActionPerformed
public void itemTerpilih(){
    popupnilaisiswa Pp = new popupnilaisiswa();
    Pp.nilaisantri = this;
    txtkelas.setText(kelas);
    txttahun.setText(thnmasuk);
    txtsemester.setText(semester);
}
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new LaporanNilai());
        panelMain.repaint();
        panelMain.revalidate();
    }
   
     
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.CARI Bcetak;
    private fosalgo.CARI bcari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTable tblnilai;
    private javax.swing.JTextField txtkelas;
    private javax.swing.JTextField txtsemester;
    private javax.swing.JTextField txttahun;
    // End of variables declaration//GEN-END:variables

}

        
