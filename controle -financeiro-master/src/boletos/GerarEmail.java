
package boletos;

import Ferramentas.InternalFrame.GFrame;
import static Ferramentas.InternalFrame.GFrame.startCursor;
import static Ferramentas.InternalFrame.GFrame.stopCursor;
import clientes.CadastrarClientesBoleto;
import Ferramentas.Managers.Managers;
import Ferramentas.Mensagem.Mensagem;
import Ferramentas.TableModel.TbModelReflection;
import boletos.controllers.BoletosController;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import principal.models.TbBoletosGerados;
import principal.models.TbClienteContratos;

/**
 *
 * @author ANDRE PORTO
 */
public final class GerarEmail extends GFrame {

    EntityManager em = Managers.GetManager();
    TbModelReflection mdContratos;
    List<TbClienteContratos> lContratos;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatNum = new SimpleDateFormat("yyMM");
    TbModelReflection mdPendentes;
    List<TbBoletosGerados> lPendentes;
    Calendar dtVencidos = Calendar.getInstance();
    Calendar dtFinal = Calendar.getInstance();
    Calendar dtInicial = Calendar.getInstance();


    public GerarEmail() {
        initComponents();
        setTitle("Enviar Boletos para Email");
        mdPendentes = new TbModelReflection<TbBoletosGerados>() {
            {
                setName(jTable1, "competencia", "Processado", "Cliente", "Email", "Vencimento", "Valor", "Enviar","Enviado");
                setModel("competencia", "dtProcessado", "contratoId.clienteId.dsCliente", "contratoId.clienteId.dsEmail", "dtVencimento", "vlBoleto", "inGerar","inBoletoGerado");
                setPreferredWidth(50, 50, 200, 100, 150, 10, 50, 50);
                setEditableAt(6);
            }
        };

        dtInicial.add(Calendar.MONTH, -2);
        dtFinal.add(Calendar.MONTH, +2);
        tfDtFim.setDate(dtFinal.getTime());
        tfDtIni.setDate(dtInicial.getTime());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfDtIni = new Ferramentas.Datas.DateField();
        tfDtFim = new Ferramentas.Datas.DateField();
        btBuscar = new javax.swing.JButton();
        cbStatus = new javax.swing.JComboBox();

        setClosable(true);

        btGerar.setText("Gerar");
        btGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGerarActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Data Inicial:");

        jLabel2.setText("Data Final:");

        jLabel3.setText("Status:");

        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "ATIVOS", "VENCIDOS" }));
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(753, Short.MAX_VALUE)
                        .addComponent(btGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfDtIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(btBuscar)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tfDtIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btBuscar)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(btGerar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGerarActionPerformed
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (file.showDialog(file, "Gerar") == 0) {
            new Thread() {

                @Override
                public void run() {
                    startCursor(rootPane);
                    // BoletosController c = new BoletosController();
                    TbBoletosGerados boleto;
                    for (TbBoletosGerados contrato : lPendentes) {
                        if (contrato.getInGerar()&& !contrato.getInBoletoGerado()) {
                            //   contrato.setInBoletoGerado(true);
                            //    contrato.setInGerar(false);
                            boleto = new TbBoletosGerados();
                            boleto.setDsEmail(contrato.getDsEamil());
                            boleto.setDsUrl(contrato.getDsUrl());
                            //    boleto.setDtProcessado(new Date());
                            BoletosController.EnviarEmail(boleto);

                        }
                    }
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    stopCursor(rootPane);
                    Mensagem.show(Mensagem.GERAR_EMAIL);
                }

            }.start();

        }
                

    }//GEN-LAST:event_btGerarActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
               if (jTable1.getSelectedColumn() == 5) {
            if (jTable1.getValueAt(jTable1.getSelectedRow(), 6).equals(true)) {
                jTable1.setValueAt(false, jTable1.getSelectedRow(), 5);
            }
        }

    }//GEN-LAST:event_jTable1MouseReleased

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        // TODO add your handling code here:
        lPendentes = em.createQuery("SELECT t FROM TbBoletosGerados t where t.in2Via=?1 and t.dtVencimento BETWEEN ?2 and ?3 and t.dtPagamento is null").setParameter(1, false).setParameter(2, tfDtIni.getDate()).setParameter(3, tfDtFim.getDate()).getResultList();
        mdPendentes.setList(lPendentes);
    }//GEN-LAST:event_btBuscarActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
        if (cbStatus.getSelectedIndex() == 0) {
            filtrarJTable(null, 0);
        } else if (cbStatus.getSelectedIndex() == 1) {
            filtrarJTable(dtVencidos.getTime(), 1);
        } else if (cbStatus.getSelectedIndex() == 2) {
            filtrarJTable(dtVencidos.getTime(), 2);

        }
    }//GEN-LAST:event_cbStatusActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btGerar;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private Ferramentas.Datas.DateField tfDtFim;
    private Ferramentas.Datas.DateField tfDtIni;
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
    
        void filtrarJTable(Date data, int vencidos) {
        TableModel tbModel = jTable1.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbModel);
        List<RowFilter<TableModel, Object>> listFiltros = new ArrayList<>();
        RowFilter<TableModel, Object> compoundRowFilter = null;
        sorter.setRowFilter(compoundRowFilter);
        if (vencidos == 1) {
            listFiltros.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, data, 6));
        } else if (vencidos == 2) {
            listFiltros.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, data, 6));
        } else {
            listFiltros.add(RowFilter.regexFilter("(?i)", 6));
        }

        jTable1.setRowSorter(sorter);
        compoundRowFilter = RowFilter.andFilter(listFiltros);
        sorter.setRowFilter(compoundRowFilter);
    }

}