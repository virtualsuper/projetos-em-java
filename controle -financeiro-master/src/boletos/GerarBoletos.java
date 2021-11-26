
package boletos;

import Ferramentas.InternalFrame.GFrame;
import boletos.controllers.BoletosController;
import static Ferramentas.InternalFrame.GFrame.startCursor;
import static Ferramentas.InternalFrame.GFrame.stopCursor;
import clientes.CadastrarClientesBoleto;
import Ferramentas.Managers.Managers;
import Ferramentas.Mensagem.Mensagem;
import Ferramentas.TableModel.TbModelReflection;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;
import org.apache.commons.lang3.StringUtils;
import principal.models.TbBoletosGerados;
import principal.models.TbClienteContratos;

/**
 *
 * @author ANDRE PORTO
 */
public final class GerarBoletos extends GFrame {

    EntityManager em = Managers.GetManager();
    TbModelReflection mdContratos;
    List<TbClienteContratos> lContratos;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatNum = new SimpleDateFormat("yyMM");

    /**
     * Creates new form CadastrarBoletoContrato
     */
    public GerarBoletos() {
        initComponents();
        setTitle("Gerar Boletos");
        try {
            MaskFormatter mask = new MaskFormatter("##/####");
            mask.setPlaceholderCharacter('_');
            mask.install(tfCompetencia);
        } catch (ParseException ex) {
            Logger.getLogger(CadastrarClientesBoleto.class.getName()).log(Level.SEVERE, null, ex);
        }

        mdContratos = new TbModelReflection<TbClienteContratos>() {
            {
                setName(jTable1, "dia vencimento", "Cliente", "cpf/cnpj", "valor", "tipo boleto", "gerar boletos", "Boleto gerado");
                setModel("diaVencimento", "clienteId.dsCliente", "clienteId.nrCpfCnpj14", "vlBoleto", "tipoBoletoId.dsTipoBoleto", "inGerar", "inBoletoGerado");
                setEditableAt(5);
            }
        };
        HeaderTable(jTable1, 5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btGerar = new javax.swing.JButton();
        tfCompetencia = new javax.swing.JFormattedTextField();
        btBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btGerar.setText("Gerar");
        btGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerarActionPerformed(evt);
            }
        });

        tfCompetencia.setText("09/2021");
        tfCompetencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCompetenciaKeyReleased(evt);
            }
        });

        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(tfCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btBuscar)
                .addGap(192, 192, 192)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar)
                    .addComponent(jButton1))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btGerar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerarActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (file.showDialog(file, "Gerar") == 0) {
            new Thread() {

                @Override
                public void run() {
                    startCursor(rootPane);
                    File files = file.getSelectedFile();

                    // BoletosController c = new BoletosController();
                    TbBoletosGerados boleto;
                    for (TbClienteContratos contrato : lContratos) {
                        if (contrato.getInGerar() && !contrato.getInBoletoGerado()) {
                            contrato.setInBoletoGerado(false);
                            contrato.setInGerar(true);
                            boleto = new TbBoletosGerados();
                            boleto.setContratoId(contrato);
                            boleto.setInEnviadoRemessa(false); 
                            boleto.setDtProcessado(new Date());
                            boleto.setCompetencia(tfCompetencia.getText());
                            boleto.setIn2Via(false);
                            try {
                                boleto.setDtVencimento(format.parse(contrato.getDiaVencimento() + "/" + tfCompetencia.getText()));
                            } catch (ParseException ex) {
                                Logger.getLogger(GerarBoletos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (boleto.getDtVencimento().before(new Date())) {
                                Calendar cal = Calendar.getInstance();
                                cal.add(Calendar.DAY_OF_MONTH, +5);
                                boleto.setDtVencimento(cal.getTime());
                            }

                            em.persist(boleto);
                            em.getTransaction().begin();
                            em.getTransaction().commit();

                            boleto.setVlBoleto(contrato.getVlBoleto());
                            boleto.setNrNossoNumero(StringUtils.leftPad(boleto.getId().toString(), 8, "0"));
                            // boleto.setCdDac(BoletosController.GerarDac("023683420109" + boleto.getNrNossoNumero()));
                            em.merge(boleto);
                            BoletosController.gerar(boleto, files);
                           // BoletosController.EnviarEmail(boleto);

                        }
                    }
                    em.getTransaction().begin();
                    em.getTransaction().commit();

                    stopCursor(rootPane);
                    Mensagem.show(Mensagem.GERAR_BOLETOS);
                }

            }.start();

        }

    }//GEN-LAST:event_btGerarActionPerformed


    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        try {
            // TODO add your handling code here:
            lContratos = em.createQuery("SELECT t FROM TbClienteContratos t where ?1 BETWEEN t.dtInicial AND t.dtFinal").setParameter(1, format.parse("01/" + tfCompetencia.getText())).getResultList();
            for (TbClienteContratos c : lContratos) {
                List<TbBoletosGerados> lBoleto;
                lBoleto = em.createQuery("SELECT t FROM TbBoletosGerados t where t.contratoId=?1 and t.competencia=?2").setParameter(1, c).setParameter(2, tfCompetencia.getText()).getResultList();
                if (!lBoleto.isEmpty()) {
                    c.setInBoletoGerado(true);
                    c.setInGerar(false);
                } else {
                    c.setInGerar(true);
                    c.setInBoletoGerado(false);
                }

            }
            mdContratos.setList(lContratos);
        } catch (ParseException ex) {
            Logger.getLogger(GerarBoletos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btBuscarActionPerformed

    private void tfCompetenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCompetenciaKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btBuscar.doClick();
        }
    }//GEN-LAST:event_tfCompetenciaKeyReleased

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        if (jTable1.getSelectedColumn() == 5) {
            if (jTable1.getValueAt(jTable1.getSelectedRow(), 6).equals(true)) {
                jTable1.setValueAt(false, jTable1.getSelectedRow(), 5);
            }
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       TbBoletosGerados boleto = new TbBoletosGerados();
        BoletosController.EnviarEmail(boleto);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btGerar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField tfCompetencia;
    // End of variables declaration//GEN-END:variables

    public void HeaderTable(final javax.swing.JTable table, final int coluna) {
        final JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Boolean status;
                int col = header.columnAtPoint(e.getPoint());
                if ((header.getCursor().getType() == Cursor.DEFAULT_CURSOR) && col == coluna) {
                    if (table.getValueAt(0, coluna).equals(true)) {
                        status = false;
                    } else {
                        status = true;
                    }
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if (table.getValueAt(i, coluna + 1).equals(true)) {
                            table.setValueAt(false, i, coluna);
                        } else {
                            table.setValueAt(status, i, coluna);
                        }
                    }
                    table.repaint();
                }
            }
        });

    }

}