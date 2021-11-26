/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.Mensagem;

import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ANDRE PORTO
 */
public enum Mensagem {

    
    SALVAR("Salvo com sucesso!", "PARABENS", 4),
    GERAR_BOLETOS("Os Boletos Foram Gerados com Sucesso!", "PARABENS", 4),
    CNPJ_INVALIDO("CNPJ Invalido!", "ERRO", 0),
    CNPJ_CADASTRADO("CNPJ Já Cadastrado!", "ATENÇÃO", 1),
    DATA_CORRETAMENTE("Preencha a data corretamente", "ATENÇÃO", 1),
    REMESSA("Arquivo Remessa Gerado com Sucesso", "SUCESSO", 4),
    GERAR_EMAIL("Os Emails Foram Enviados com Sucesso!", "PARABÉNS", 4),;


    //
    private Mensagem(String texto, String titulo, int tipo) {
        this.texto = texto;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    private static final Icon icone[] = {
        new ImageIcon(Mensagem.class.getResource("/imagens/mensagens/erro.png")),
        new ImageIcon(Mensagem.class.getResource("/imagens/mensagens/info.png")),
        new ImageIcon(Mensagem.class.getResource("/imagens/mensagens/atencao.png")),
        new ImageIcon(Mensagem.class.getResource("/imagens/mensagens/pergunta.png")),
        new ImageIcon(Mensagem.class.getResource("/imagens/mensagens/sucesso.png"))};
    private final String texto;
    private final String titulo;
    private final int tipo;

    public static void show(Mensagem mensagem) {
        JOptionPane.showMessageDialog(null, mensagem.texto, mensagem.titulo, 0, icone[mensagem.tipo]);
    }

    public static boolean showConfirma(Mensagem mensagem) {
        return JOptionPane.showConfirmDialog(null, mensagem.texto, mensagem.titulo, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public static int showConfirmaTodos(Mensagem mensagem) {
        JCheckBox remember = new JCheckBox("Aplicar a todos");
        JPanel panel = new JPanel(new BorderLayout()); // or whatever layout you find best
        panel.add(new JLabel(mensagem.texto), BorderLayout.NORTH);
        panel.add(remember, BorderLayout.SOUTH);
        int ret = JOptionPane.showConfirmDialog(null, panel, mensagem.titulo, JOptionPane.YES_NO_OPTION, 0, icone[mensagem.tipo]);
        System.out.println("rest" + ret);
        if (remember.isSelected()) {
            return ret + 7;
        }else
        return ret;
    }

}
