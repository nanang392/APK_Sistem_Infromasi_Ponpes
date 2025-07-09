package login;

import View.MenuUtamaDS;
import static java.awt.SystemColor.menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import koneksi.koneksi;

public class MenuLogin extends javax.swing.JFrame {

    private Connection conn;
    public MenuLogin() {
        initComponents();
        conn = koneksi.getConnection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Username = new javax.swing.JLabel();
        Password = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        txtpw = new javax.swing.JPasswordField();
        Background = new javax.swing.JLabel();
        bLogin = new fosalgo.FButtonTambah();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 255, 102));

        Username.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Username.setText("Username");

        Password.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Password.setText("Password");

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Admin Settings Male_1.png"))); // NOI18N

        txtusername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtusername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 0)));
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });

        txtpw.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 0)));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login_1.png"))); // NOI18N

        bLogin.setText("L O G I N");
        bLogin.setFillClick(new java.awt.Color(0, 204, 51));
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtusername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(Password, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Username, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpw, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(114, Short.MAX_VALUE)
                        .addComponent(icon)
                        .addGap(89, 89, 89)))
                .addComponent(Background)
                .addGap(87, 87, 87))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(icon)
                .addGap(18, 18, 18)
                .addComponent(Username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpw, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Background))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 340));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameActionPerformed

    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
       prosesLogin();
    }//GEN-LAST:event_bLoginActionPerformed
private boolean validasiInput(){
        boolean valid = false;
        if(txtusername.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Username tidak boleh Kosong");
        }else if (txtpw.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(this,"Password tidak boleh Kosong");
        }else {
            valid = true;
        }
        return valid;
}
    private boolean checkLogin(String username, String password){
        if(conn !=null){
            try {
                String sql = "SELECT * FROM user WHERE username=? AND password=?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, username);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    
    private void prosesLogin(){
        if (validasiInput()){
            String username = txtusername.getText();
            String password = new String (txtpw.getPassword());
            if(checkLogin(username,password)){
                MenuUtamaDS mn = new MenuUtamaDS();
                mn.setVisible(true);
                mn.revalidate();
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Username dan Password Salah","Pesan",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
        public void setvisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Password;
    private javax.swing.JLabel Username;
    private fosalgo.FButtonTambah bLogin;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtpw;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}

