package View;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
public class Nilai extends javax.swing.JPanel {
    private int totalPages;
    private DefaultTableModel tabmode;
    private final Connection conn;
    private Component rootPane;
    String idsantri;
    String nisn;
    String nama;
    String kelas;

    public Nilai() {
        initComponents();
        
        
        conn = koneksi.getConnection();
        setTabelModel();
        loadData();
    }
private void loadData() {
        getData((DefaultTableModel) tbnilai.getModel());
        bthapus.setVisible(false);
        bBatal.setVisible(true);
    }
    
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new Nilai());
        panelMain.repaint();
        panelMain.revalidate();
    }
    private void setTabelModel() {
        
        Object[] Baris ={"NO ID","NISN","Nama","Kelas",
            "PPKN","B.indo","MTK","IPA","B.Inggris","IPS","TIK"
            ,"B.Arab","Al-Quran","Hadist","Fiqh","Akhlaq","Akidah",
            "Tarikh","Shorof","Aswaja","Total","Nilai Rata2",
            "Status","Tanpa Keterangan","Sakit","Izin"};
 tabmode = new DefaultTableModel(null, Baris);
 
        try {
        String sql = "SELECT * FROM nilai ";
        java.sql.Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        while (hasil.next()){
        tabmode.addRow(new Object[]{
        hasil.getString(1),hasil.getString(2),hasil.getString(3),hasil.getString(4),hasil.getString(5),
        hasil.getString(6),hasil.getString(7),hasil.getString(8),hasil.getString(9),hasil.getString(10),
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
        tbnilai.setModel(tabmode);
        } catch (SQLException e) {
            
        }
        
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
            String Total = rs.getString("total");
            String N_Akhir = rs.getString("nakhir");
            String Status = rs.getString("status");
            String Keterangan = rs.getString("tketerangan");
            String Sakit = rs.getString("sakit");
            String Izin = rs.getString("izin");
            
            Object[] Baris ={NO, NisnSiswa, NamaSiswa, Kelas, 
            PPKN, BIndo, MTK, IPA, BInggris, IPS, TIK, 
            BArab, AlQuran, Hadist, Fiqh, Akhlak, Akidah, 
            Tarikh, Shorof, Aswaja, Total, N_Akhir, Status,Keterangan,Sakit,Izin};
            model.addRow(Baris);
        }
       }
     } catch (SQLException e){
            Logger.getLogger(Nilai.class.getName()).log(Level.SEVERE,null,e);
        }
    }    

    private void insertData() {
        String sql = "insert into nilai  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
        try{

        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, txtno.getText());
        stat.setString(2, txtnisn.getText());
        stat.setString(3, txtnama.getText());
        stat.setString(4, txtkelas.getText());
        stat.setString(5, tppkn.getText());
        stat.setString(6, tindo.getText());
        stat.setString(7, tmtk.getText());
        stat.setString(8, tipa.getText());
        stat.setString(9, tbinggris.getText());
        stat.setString(10, tips.getText());
        stat.setString(11, tik.getText());
        stat.setString(12, tarab.getText());
        stat.setString(13, talquran.getText());
        stat.setString(14, thadist.getText());
        stat.setString(15, tfiqih.getText());
        stat.setString(16, takhlaq.getText());
        stat.setString(17, takidah.getText());
        stat.setString(18, ttarikh.getText());
        stat.setString(19, tshorof.getText());
        stat.setString(20, taswaja.getText());
        stat.setString(21, ttotal.getText());
        stat.setString(22, trata.getText());
        stat.setString(23, txtstatus.getText());
        stat.setString(24, txtket.getText());
        stat.setString(25, txtsakit.getText());
        stat.setString(26, txtizin.getText());
        stat.executeUpdate();

        JOptionPane.showMessageDialog(null, "data berhasil disimpan");
        }
       catch (SQLException e){
       JOptionPane.showMessageDialog(null, "data gagal disimpan"+e);
       }
      loadData();
     showPanel();
    }
public void Hitung(){
    double Nppkn = Integer.parseInt(tppkn.getText());
    double Nindo = Integer.parseInt(tindo.getText());
    double Nmtk = Integer.parseInt(tmtk.getText());
    double Nipa = Integer.parseInt(tipa.getText());
    double Ninggris = Integer.parseInt(tbinggris.getText());
    double Nips = Integer.parseInt(tips.getText());
    double Ntik = Integer.parseInt(tik.getText());
    double Narab = Integer.parseInt(tarab.getText());
    double Nalquran = Integer.parseInt(talquran.getText());
    double Nhadist = Integer.parseInt(thadist.getText());
    double Nfiqih = Integer.parseInt(tfiqih.getText());
    double Nakhlaq = Integer.parseInt(takhlaq.getText());
    double Nakidah = Integer.parseInt(takidah.getText());
    double Ntarikh = Integer.parseInt(ttarikh.getText());
    double Nshorof = Integer.parseInt(tshorof.getText());
    double Naswaja = Integer.parseInt(taswaja.getText());
        
    
    
    double Nilai= Nppkn+Nindo+Nmtk+Nipa+Ninggris+Nips+Ntik+Narab+Nalquran+Nhadist+Nfiqih+Nakhlaq+Nakidah+Ntarikh+Nshorof+Naswaja;
    int jumlahMataPelajaran = 16; // Total jumlah mata pelajaran
    double rataRata = Nilai / jumlahMataPelajaran;

    // Format angka dengan koma sebagai pemisah ribuan
    ttotal.setText(String.format("%.2f", Nilai));       // Menampilkan total nilai
    trata.setText(String.format("%.2f", rataRata));     // Menampilkan rata-rata nilai

    // Tentukan status berdasarkan rata-rata
    if (rataRata >= 85) {
        txtstatus.setText("Terlampaui");
    } else if (rataRata > 75) {
        txtstatus.setText("Terlampaui");
    } else if (rataRata > 65) {
        txtstatus.setText("Belum Tuntas");
    } else if (rataRata > 50) {
        txtstatus.setText("Cukup");
    } else {
        txtstatus.setText("Cukup");
    }
}
    private void deletData() {
     int selecteRow = tbnilai.getSelectedRow();
     int confirm = JOptionPane.showConfirmDialog(this, "Apakah yakin ingin menghapus data ini ?",
             "Konfirmasi Hapus Data",JOptionPane.YES_NO_OPTION);
     if (confirm == JOptionPane.YES_OPTION){
         String id = tbnilai.getValueAt(selecteRow, 0).toString();
         try{
             String sql = "DELETE FROM nilai WHERE no=?";
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
        tbnilai = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bthapus = new fosalgo.FButtonHapus();
        btTambah = new fosalgo.FButtonTambah();
        bBatal = new fosalgo.BATAL();
        txtno = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtnisn = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtkelas = new javax.swing.JTextField();
        tppkn = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tindo = new javax.swing.JTextField();
        tipa = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        bcari = new fosalgo.CARI();
        cETAK2 = new fosalgo.CETAK();
        jLabel24 = new javax.swing.JLabel();
        ttotal = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtstatus = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        tmtk = new javax.swing.JTextField();
        hitung = new fosalgo.CARI();
        jLabel26 = new javax.swing.JLabel();
        tbinggris = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tips = new javax.swing.JTextField();
        tik = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        tarab = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        talquran = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        thadist = new javax.swing.JTextField();
        tfiqih = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        takhlaq = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        takidah = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        ttarikh = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        tshorof = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        taswaja = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        trata = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtket = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtsakit = new javax.swing.JTextField();
        txtizin = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 153));
        setPreferredSize(new java.awt.Dimension(1026, 716));
        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));

        tbnilai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tbnilai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbnilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbnilai);
        tbnilai.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Penilaian");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Health Graph.png"))); // NOI18N

        bthapus.setBackground(new java.awt.Color(153, 153, 0));
        bthapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cancel_1.png"))); // NOI18N
        bthapus.setText("HAPUS");
        bthapus.setFillOver(new java.awt.Color(255, 51, 0));
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btTambah.setText("SIMPAN");
        btTambah.setFillClick(new java.awt.Color(0, 153, 153));
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });

        bBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Go Back.png"))); // NOI18N
        bBatal.setText("BATAL");
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        txtno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel15.setText("NO ID");

        txtnisn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel16.setText("NISN");

        txtnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel17.setText("Nama");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel18.setText("Kelas");

        txtkelas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));

        tppkn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tppkn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tppknActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel19.setText("PPKN");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel20.setText("B.indo");

        tindo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tindo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tindoActionPerformed(evt);
            }
        });

        tipa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tipa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel21.setText("IPA");

        bcari.setText("cari");
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
            }
        });

        cETAK2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Print.png"))); // NOI18N
        cETAK2.setText("CETAK RAPORT");
        cETAK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cETAK2ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel24.setText("Total");

        ttotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        ttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel25.setText("Status");

        txtstatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        txtstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstatusActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel23.setText("MTK");

        tmtk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tmtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmtkActionPerformed(evt);
            }
        });

        hitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Equals.png"))); // NOI18N
        hitung.setText("HITUNG");
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel26.setText("B.Inggris");

        tbinggris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tbinggris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbinggrisActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel27.setText("IPS");

        tips.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tips.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipsActionPerformed(evt);
            }
        });

        tik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tikActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel28.setText("TIK");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel29.setText("NILAI PELAJARAN MUATAN NASIONAL ");

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel30.setText("B.Arab");

        tarab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tarab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarabActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel31.setText("Al-Qur'an");

        talquran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        talquran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                talquranActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel32.setText("Hadist");

        thadist.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        thadist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thadistActionPerformed(evt);
            }
        });

        tfiqih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tfiqih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfiqihActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel33.setText("Fiqh");

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel34.setText("NILAI PELAJARAN MUATAN LOKAL");

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel35.setText("Akhlaq");

        takhlaq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        takhlaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takhlaqActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel36.setText("Akidah");

        takidah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        takidah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takidahActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel37.setText("Tarikh");

        ttarikh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        ttarikh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttarikhActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel38.setText("Shorof");

        tshorof.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        tshorof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tshorofActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel39.setText("Aswaja");

        taswaja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        taswaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taswajaActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel40.setText("Nilai Rata-rata");

        trata.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        trata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trataActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel41.setText("Tanpa Keterangan");

        txtket.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        txtket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtketActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel42.setText("Sakit");

        txtsakit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        txtsakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsakitActionPerformed(evt);
            }
        });

        txtizin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        txtizin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtizinActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel43.setText("Izin");

        jLabel44.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel44.setText("KETIDAKHADIRAN");

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelViewLayout.createSequentialGroup()
                                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel17)
                                            .addGroup(panelViewLayout.createSequentialGroup()
                                                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel44)
                                            .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtno)
                                                .addComponent(txtkelas)
                                                .addComponent(txtnisn)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewLayout.createSequentialGroup()
                                                    .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelViewLayout.createSequentialGroup()
                                                            .addComponent(jLabel41)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(txtket, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jLabel42)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                                            .addComponent(jLabel43))
                                                        .addGroup(panelViewLayout.createSequentialGroup()
                                                            .addComponent(cETAK2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtizin, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(42, 42, 42))
                                    .addGroup(panelViewLayout.createSequentialGroup()
                                        .addComponent(txtsakit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(202, 202, 202)))
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addGroup(panelViewLayout.createSequentialGroup()
                                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelViewLayout.createSequentialGroup()
                                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel26)
                                                    .addComponent(jLabel21)
                                                    .addComponent(jLabel27)
                                                    .addComponent(jLabel28))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(tbinggris, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                                    .addComponent(tipa, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tips, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tik, javax.swing.GroupLayout.Alignment.LEADING)))
                                            .addGroup(panelViewLayout.createSequentialGroup()
                                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(35, 35, 35)
                                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(tppkn, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                                    .addComponent(tmtk)
                                                    .addComponent(tindo))))
                                        .addGap(140, 140, 140)
                                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel34)
                                            .addComponent(jLabel38)
                                            .addGroup(panelViewLayout.createSequentialGroup()
                                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel31)
                                                        .addComponent(jLabel32)
                                                        .addComponent(jLabel33)
                                                        .addComponent(jLabel30)
                                                        .addComponent(jLabel35)
                                                        .addComponent(jLabel36))
                                                    .addGroup(panelViewLayout.createSequentialGroup()
                                                        .addComponent(jLabel37)
                                                        .addGap(21, 21, 21))
                                                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(takhlaq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(panelViewLayout.createSequentialGroup()
                                                        .addGap(32, 32, 32)
                                                        .addComponent(tarab, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(talquran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(thadist, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tfiqih, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(takidah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(taswaja, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ttarikh, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tshorof, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(panelViewLayout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel40)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(trata, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 72, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnisn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel44)
                        .addGap(11, 11, 11)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtket, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(txtsakit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)
                            .addComponent(txtizin, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel34))
                        .addGap(18, 18, 18)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tppkn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel30)
                            .addComponent(tarab, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(tindo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(tmtk, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(tipa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(tbinggris, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(tips, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(tik, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(talquran, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(thadist, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(tfiqih, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(takhlaq, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel36)
                                    .addComponent(takidah, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelViewLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel38))
                                    .addGroup(panelViewLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel37)
                                            .addComponent(ttarikh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tshorof, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39)
                                    .addComponent(taswaja, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(trata, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hitung, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cETAK2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMain.add(panelView, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
   insertData();
    }//GEN-LAST:event_btTambahActionPerformed

    private void tbnilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnilaiMouseClicked
        int bar = tbnilai.getSelectedRow();
        String a = tabmode.getValueAt(bar, 1).toString();
        txtnisn.setText(a);
        if(btTambah.getText().equals("TAMBAH"))
        btTambah.setText("UBAH");
         bBatal.setVisible(true);
         bthapus.setVisible(true);
        
    }//GEN-LAST:event_tbnilaiMouseClicked
       
    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
    showPanel();
    loadData();
    }//GEN-LAST:event_bBatalActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
     deletData();
    }//GEN-LAST:event_bthapusActionPerformed

    private void tppknActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tppknActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tppknActionPerformed

    private void tipaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipaActionPerformed
     
    }//GEN-LAST:event_tipaActionPerformed

    private void tindoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tindoActionPerformed
     
    }//GEN-LAST:event_tindoActionPerformed

    private void cETAK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cETAK2ActionPerformed
       try {
            String path="src/Report/report6.jasper";      // letak penyimpanan report
            HashMap parameter = new HashMap();
            parameter.put("kode2",txtnisn.getText());   
            JasperPrint print = JasperFillManager.fillReport(path,parameter,conn);
            JasperViewer.viewReport(print,false);
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
            }
    }//GEN-LAST:event_cETAK2ActionPerformed

    private void ttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalActionPerformed

    private void txtstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstatusActionPerformed

    private void tmtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmtkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tmtkActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
       Hitung();
    }//GEN-LAST:event_hitungActionPerformed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        popupsantri1 Pp = new popupsantri1();
        Pp.santri = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_bcariActionPerformed

    private void tbinggrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbinggrisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbinggrisActionPerformed

    private void tipsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipsActionPerformed

    private void tikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tikActionPerformed

    private void tarabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarabActionPerformed

    private void talquranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talquranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_talquranActionPerformed

    private void thadistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thadistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thadistActionPerformed

    private void tfiqihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfiqihActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfiqihActionPerformed

    private void takhlaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takhlaqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_takhlaqActionPerformed

    private void takidahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takidahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_takidahActionPerformed

    private void ttarikhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttarikhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttarikhActionPerformed

    private void tshorofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tshorofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tshorofActionPerformed

    private void taswajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taswajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taswajaActionPerformed

    private void trataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trataActionPerformed

    private void txtketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtketActionPerformed

    private void txtsakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsakitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsakitActionPerformed

    private void txtizinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtizinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtizinActionPerformed
public void itemTerpilih(){
    popupsantri1 Pp = new popupsantri1();
    Pp.santri = this;
    txtno.setText(idsantri);
    txtnisn.setText(nisn);
    txtnama.setText(nama);
    txtkelas.setText(kelas);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fosalgo.BATAL bBatal;
    private fosalgo.CARI bcari;
    private fosalgo.FButtonTambah btTambah;
    private fosalgo.FButtonHapus bthapus;
    private fosalgo.CETAK cETAK2;
    private fosalgo.CARI hitung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.ButtonGroup rbjeniskelamin;
    private javax.swing.JTextField takhlaq;
    private javax.swing.JTextField takidah;
    private javax.swing.JTextField talquran;
    private javax.swing.JTextField tarab;
    private javax.swing.JTextField taswaja;
    private javax.swing.JTextField tbinggris;
    private javax.swing.JTable tbnilai;
    private javax.swing.JTextField tfiqih;
    private javax.swing.JTextField thadist;
    private javax.swing.JTextField tik;
    private javax.swing.JTextField tindo;
    private javax.swing.JTextField tipa;
    private javax.swing.JTextField tips;
    private javax.swing.JTextField tmtk;
    private javax.swing.JTextField tppkn;
    private javax.swing.JTextField trata;
    private javax.swing.JTextField tshorof;
    private javax.swing.JTextField ttarikh;
    private javax.swing.JTextField ttotal;
    private javax.swing.JTextField txtizin;
    private javax.swing.JTextField txtkelas;
    private javax.swing.JTextField txtket;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnisn;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txtsakit;
    private javax.swing.JTextField txtstatus;
    // End of variables declaration//GEN-END:variables

   
}

        
