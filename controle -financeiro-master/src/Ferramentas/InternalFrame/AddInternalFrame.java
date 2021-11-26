/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.InternalFrame;

import java.awt.Container;
import java.awt.Point;
import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import principal.view.Principal;

/**
 *
 * @author ANDRE
 */
public class AddInternalFrame extends SwingWorker implements Runnable {

    Boolean maximizado;
    Class classe;
    Class[] tipo;
    Object[] valor;

    public AddInternalFrame(Boolean maximizado, final Class classe, Class[] tipo, Object... valor) {
        this.maximizado = maximizado;
        this.classe = classe;
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int testes = 0;

        final JProgressBar p = principal.view.Principal.rodape;
        p.setString("Carregando");
        p.setStringPainted(true);
        p.setIndeterminate(true);
        p.setVisible(true);

        try {
            JInternalFrame frame = (JInternalFrame) classe.getConstructor(tipo).newInstance(valor);
            for (JInternalFrame iframe : Principal.desktop.getAllFrames()) {
                if (iframe.getClass().equals(classe)) {
                    iframe.dispose();
                }
            }
            if (testes == 0) {
                // removeMenu(frame);
                frame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone.png")));
                Principal.desktop.add(frame);
                frame.setLocation(new Point((Principal.desktop.getWidth() - frame.getSize().width) / 2, (Principal.desktop.getHeight() - frame.getSize().height) / 2));
                frame.setSelected(true);
                frame.setVisible(true);
                if (maximizado == true) {
                    frame.setMaximum(true);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Mensagem do erro: " + ex.getCause().getMessage());
            System.err.println("Classe do erro: " + ex.getClass());
            System.err.println("Causa do erro: " + ex.getCause());
        } finally {
            p.setStringPainted(false);
            p.setIndeterminate(false);
            p.setVisible(false);
            return 0;
        }

    }

    @Override
    protected void done() {
    }

    public static void removeMenu(javax.swing.JInternalFrame c) {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) c.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
    }
}
