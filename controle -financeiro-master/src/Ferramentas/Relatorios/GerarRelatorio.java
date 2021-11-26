
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.Relatorios;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author ANDRE PORTO
 */
public class GerarRelatorio {

    @SuppressWarnings("UnusedAssignment")
    public void print(String titulo, String pathjasper, List listObjects, HashMap parametros) throws Exception {
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listObjects);

        URL arquivo = getClass().getResource(pathjasper);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, ds);
        JRViewer viewer = new JRViewer(print);
        if (listObjects.size() > 0) {
            // cria o JFrame
            JFrame frameRelatorio = new JFrame(titulo);
            // adiciona o JRViewer no JFrame
            frameRelatorio.add(viewer, BorderLayout.CENTER);
            // configura o tamanho padrão do JFrame
            frameRelatorio.setSize(500, 500);
            // maximiza o JFrame para ocupar a tela toda.
            frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // configura a operação padrão quando o JFrame for fechado.
            frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // exibe o JFrame
            frameRelatorio.setVisible(true);

        }
    }

    public void pdf(String arquivoPdf, String pathjasper, List listObjects, HashMap parametros) throws Exception {
        
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listObjects);
        URL arquivo = getClass().getResource(pathjasper);
        
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, ds);
        JasperExportManager.exportReportToPdfFile(print, arquivoPdf);

    }
}
