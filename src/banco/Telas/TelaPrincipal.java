/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.Telas;

import banco.dao.EstabelecimentosDao;
import banco.dao.UsuarioLogado;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        EmpresasDoUsuario();
    }

    UsuarioLogado user = new UsuarioLogado();
    int id;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCadastrarEmpresa = new javax.swing.JPanel();
        btnEmails = new javax.swing.JButton();
        btnTelefones = new javax.swing.JButton();
        btnEnderecos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabEstabelecimentos = new javax.swing.JTable();
        btnEmpresas = new javax.swing.JButton();
        olaUser = new javax.swing.JLabel();
        seusEstabelecimentos = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnEmails.setText("Emails");
        btnEmails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailsActionPerformed(evt);
            }
        });

        btnTelefones.setText("Telefones");
        btnTelefones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTelefonesActionPerformed(evt);
            }
        });

        btnEnderecos.setText("Endereços");
        btnEnderecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnderecosActionPerformed(evt);
            }
        });

        tabEstabelecimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabEstabelecimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabEstabelecimentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabEstabelecimentos);

        btnEmpresas.setText("Ver Dados Empresa");
        btnEmpresas.setEnabled(false);
        btnEmpresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpresasActionPerformed(evt);
            }
        });

        olaUser.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        olaUser.setText("Olá");

        seusEstabelecimentos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        seusEstabelecimentos.setText("Seus Estabelecimentos:");

        jButton1.setText("Cadastrar Empresa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Alterar Dados");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnCadastrarEmpresaLayout = new javax.swing.GroupLayout(btnCadastrarEmpresa);
        btnCadastrarEmpresa.setLayout(btnCadastrarEmpresaLayout);
        btnCadastrarEmpresaLayout.setHorizontalGroup(
            btnCadastrarEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCadastrarEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnCadastrarEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(seusEstabelecimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(olaUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btnCadastrarEmpresaLayout.createSequentialGroup()
                        .addGroup(btnCadastrarEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btnCadastrarEmpresaLayout.createSequentialGroup()
                                .addComponent(btnEmpresas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(btnCadastrarEmpresaLayout.createSequentialGroup()
                                .addComponent(btnEmails)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTelefones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnderecos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        btnCadastrarEmpresaLayout.setVerticalGroup(
            btnCadastrarEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCadastrarEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(olaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btnCadastrarEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmails)
                    .addComponent(btnTelefones)
                    .addComponent(btnEnderecos)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seusEstabelecimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(btnCadastrarEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmpresas)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCadastrarEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCadastrarEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:UsuarioLogado user = new UsuarioLogado();
        olaUser.setText("Olá " + user.getNome() + "!");
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TelaCadastroEmpresa abrir = new TelaCadastroEmpresa(0);
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEmpresasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpresasActionPerformed
        // TODO add your handling code here:
        TelaDadosEmpresa abrir = new TelaDadosEmpresa(id);
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEmpresasActionPerformed

    private void tabEstabelecimentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabEstabelecimentosMouseClicked
        // TODO add your handling code here:
        int linha = tabEstabelecimentos.getSelectedRow();

        if (linha > -1) {
            btnEmpresas.setEnabled(true);
            id = (int) tabEstabelecimentos.getValueAt(linha, 0);
        }
    }//GEN-LAST:event_tabEstabelecimentosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        TelaCadastro abrir = new TelaCadastro(user.getIdusuario());
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTelefonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTelefonesActionPerformed
        // TODO add your handling code here:
        TelaTelefones abrir = new TelaTelefones(user.getIdusuario(), "USUARIO");
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnTelefonesActionPerformed

    private void btnEmailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailsActionPerformed
        // TODO add your handling code here:
        TelaEmails abrir = new TelaEmails(user.getIdusuario(), "USUARIO");
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEmailsActionPerformed

    private void btnEnderecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnderecosActionPerformed
        // TODO add your handling code here:
        TelaEnderecos abrir = new TelaEnderecos(user.getIdusuario(), "USUARIO");
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEnderecosActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    public void EmpresasDoUsuario() {
        EstabelecimentosDao estDao = new EstabelecimentosDao();
        try {
            tabEstabelecimentos.setModel(estDao.getTabelaEstabelecimentos());
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCadastrarEmpresa;
    private javax.swing.JButton btnEmails;
    private javax.swing.JButton btnEmpresas;
    private javax.swing.JButton btnEnderecos;
    private javax.swing.JButton btnTelefones;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel olaUser;
    private javax.swing.JLabel seusEstabelecimentos;
    private javax.swing.JTable tabEstabelecimentos;
    // End of variables declaration//GEN-END:variables
}
