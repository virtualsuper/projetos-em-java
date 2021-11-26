/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.InternalFrame;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 *
 * @author ANDRE
 */
public class GFrame extends JInternalFrame {

    /**
     * Filtra os dados da(s) coluna(s) informada(s)
     *
     * @param tabela
     * @param filtroColuna Object[][]{{valor,coluna},{valor,coluna}}
     */
    public static void filtrarJTable(JTable tabela, Object[][] filtroColuna) {
        tabela.setRowSorter(null);
        TableModel tbModel = tabela.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbModel);
        List<RowFilter<TableModel, Object>> listFiltros = new ArrayList<>();
        RowFilter<TableModel, Object> compoundRowFilter = null;
        sorter.setRowFilter(compoundRowFilter);
        RowFilter<TableModel, Object>[] filtros = new RowFilter[filtroColuna.length];
        for (int i = 0; i < filtroColuna.length; i++) {
            Object[] registro = filtroColuna[i];
            filtros[i] = RowFilter.regexFilter("(?i)" + registro[0], (int) registro[1]);
            listFiltros.add(filtros[i]);
        }
        tabela.setRowSorter(sorter);
        compoundRowFilter = RowFilter.andFilter(listFiltros);
        sorter.setRowFilter(compoundRowFilter);
    }

    public static void filtrarJTableSemLike(JTable tabela, Object[][] filtroColuna) {
        tabela.setRowSorter(null);
        TableModel tbModel = tabela.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbModel);
        List<RowFilter<TableModel, Object>> listFiltros = new ArrayList<>();
        RowFilter<TableModel, Object> compoundRowFilter = null;
        sorter.setRowFilter(compoundRowFilter);
        RowFilter<TableModel, Object>[] filtros = new RowFilter[filtroColuna.length];
        for (int i = 0; i < filtroColuna.length; i++) {
            Object[] registro = filtroColuna[i];
            filtros[i] = RowFilter.regexFilter(String.valueOf(registro[0]), (int) registro[1]);
            listFiltros.add(filtros[i]);
        }
        tabela.setRowSorter(sorter);
        compoundRowFilter = RowFilter.andFilter(listFiltros);
        sorter.setRowFilter(compoundRowFilter);
    }

    /**
     * Método para que seja aceito apenas numeros, virgula e sinal de menos em
     * campos de texto colocar no evento keyTyped do campo de texto
     *
     * @param evt <br>
     * passado cada caracter pressionado
     * @param pontoVirgula <br>
     * escolher se aceitará '.' ou ',' ou nao queire aceitar nem '.' nem ','
     * entao deve colocar '0'
     * @param isNegativo <br>
     * define se o valor poderá ou não ser negativo
     */
    public static void KeyDouble(java.awt.event.KeyEvent evt, char pontoVirgula, boolean isNegativo) {
        char negatigo = '0';
        if (isNegativo == true) {
            negatigo = KeyEvent.VK_MINUS;
        }

        char c = evt.getKeyChar();
        if (!((c == KeyEvent.VK_BACK_SPACE)
                || (c == pontoVirgula)
                || (c == negatigo)
                || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER)
                || (c == KeyEvent.VK_TAB) || (Character.isDigit(c)))) {
            evt.consume();
        }

    }
    int k1, k2;

    /**
     * Fixar quantidade de Caracteres suportada pelo JTextField, informar se
     * usara somente numeros ou Strings.
     *
     * @param evt
     * @param field
     * @param limite
     * @param numbers
     */
    public void fixarLength(java.awt.event.KeyEvent evt, JTextField field, int limite, Boolean numbers) {

        if (numbers) {
            try {
                Long.parseLong(field.getText());
            } catch (Exception e) {
                field.setText(null);
            }
        }

        if (field.getText().length() > limite) {
            field.setText(field.getText().substring(0, limite));
        }
        k2 = k1;
        k1 = evt.getKeyCode();
        if ((k2 == 17 && k1 == 86) || (k2 == 86 && k1 == 86)) {
            if (field.getText().length() > limite) {
                field.setText(field.getText().substring(0, limite));
            }

        }
    }

    public Maiusculo_Minusculo UPPER() {
        return new Maiusculo_Minusculo(true);
    }

    public Maiusculo_Minusculo LOWER() {
        return new Maiusculo_Minusculo(false);
    }

    public class Maiusculo_Minusculo extends PlainDocument {

        Boolean maiusculo;

        public Maiusculo_Minusculo(Boolean opcao) {
            super();
            maiusculo = opcao;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            if (maiusculo) {
                super.insertString(offs, str.toUpperCase(), a);
            } else {
                super.insertString(offs, str.toLowerCase(), a);
            }
        }
    }

    /**
     * passa objetos por parametros
     *
     * @param maximizado
     * @param classe
     * @param parametros
     */
    public static void addInternalFrame(boolean maximizado, Class classe, Object... parametros) {
        Class[] clazz = new Class[parametros.length];
        for (int i = 0; i < parametros.length; i++) {
            clazz[i] = parametros[i].getClass();
        }
        AddInternalFrame add = new AddInternalFrame(maximizado, classe, clazz, parametros);
        add.execute();
    }

    /**
     * Cria tipo de classe e passa parametro do tipo dessa classe;
     *
     * @param maximizado
     * @param classe form que sera aberto
     * @param tipo new Class[]{String.class,Short.class}
     * @param valor valores das repectivas classes
     */
    public static void addInternalFrame2(boolean maximizado, final Class classe, Class[] tipo, Object... valor) {
        AddInternalFrame add = new AddInternalFrame(maximizado, classe, tipo, valor);
        add.execute();
    }

    /**
     * Limpa todos os Fields pertencente ao Container
     *
     * @param container
     */
    public static void cleanFields(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextComponent) {
                ((JTextComponent) c).setText(null);
            } else if (c instanceof Container) {
                cleanFields((Container) c);
            }
        }
    }

    /**
     * Adiciona na Jtable um Text Area na Célula
     */
    public class TextAreaCellRenderer extends JTextArea implements TableCellRenderer {

        public TextAreaCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable jTable, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
            // set color & border here  
            setText((obj == null) ? "" : obj.toString());
            setSize(jTable.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (jTable.getRowHeight(row) != getPreferredSize().height) {
                jTable.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }

    /**
     *
     * @return Retorna Ano Correnete "2014"
     */
    public static String GetAno() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date());
    }

    /**
     *
     * @return Retorna o Mês Corrente "10"
     */
    public static String GetMes() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(new Date());
    }

    /**
     * coloca Mascara nas Celulas da Jtable somente para valores
     */
    public TableCellRenderNumerico formataCelulasVLR = new TableCellRenderNumerico("#,##0.00; -#,##0.00");
    public formataCelulaDataTime formataCelulasDateTime = new formataCelulaDataTime("dd/MM/yyyy HH:mm:ss");
    public formataCelulaDataTime formataCelulasDate = new formataCelulaDataTime("dd/MM/yyyy");

    public class TableCellRenderNumerico extends DefaultTableCellRenderer {

        private final DecimalFormat formatador = new DecimalFormat();
        private final int tamanhoTotal;

        public TableCellRenderNumerico(String pattern) {
            formatador.applyPattern(pattern);
            tamanhoTotal = pattern.length();
            setHorizontalAlignment(4);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                try {
                    Double.parseDouble(value.toString());
                    String sValor = formatador.format(value);
                    for (int i = tamanhoTotal - sValor.length(); i > 0; i--) {
                        value = " " + sValor;
                    }

                } catch (NumberFormatException e) {
                    System.err.println(" ------ NÃO É VALOR --------------");
                    System.err.println(e);
                }
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

        public void setCellRenderer(JTable table, int... column) {
            for (int i : column) {
                table.getColumnModel().getColumn(i).setCellRenderer(this);
            }
        }

    }

    public class formataCelulaDataTime extends DefaultTableCellRenderer {

        private final SimpleDateFormat formatador;

        public formataCelulaDataTime(String pattern) {
            formatador = new SimpleDateFormat(pattern);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                String sValor = formatador.format(value);
                value = sValor;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

        public void setCellRenderer(JTable table, int... column) {
            for (int i : column) {
                table.getColumnModel().getColumn(i).setCellRenderer(this);
            }
        }

    }

    /**
     * serve para adicionar ToolTip nas celulas Ex: CellRendererToolTip ttNatRec
     * = new CellRendererToolTip();
     * jtable.getColumnModel().getColumn(12).setCellRenderer(ttNatRec);
     *
     */
    public class CellRendererToolTip extends DefaultTableCellRenderer {

        // Mantém todos os tooltips com suas linhas.   
        private Map<Long, String> tooltip = new HashMap<Long, String>();

        // Mantém a linha atual que este objeto está renderizando.   
        private int row;

        // Busca qual é a linha atual.   
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            this.row = row;

            return c;
        }

        // Retorna o tooltip baseado no map.   
        public String getToolTipText() {
            return tooltip.get(new Long(row));
        }

        // Adiciona um tooltip pela linha.   
        public void addToolTip(int row, String text) {
            tooltip.put(new Long(row), text);
        }

    }

    /**
     * Alinha coluna(s) jTable
     *
     * @param alinhamento => 0: Centro - 2: Esquerda - 4: Direita
     * @param tabela
     * @param col exemplo: alinharColuna(2, ProdutoTable, 2,3,4,5);
     *
     */
    public static void alinharCelulas(Integer alinhamento, JTable tabela, Integer... col) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(alinhamento);

        for (int i = 0; i < col.length; i++) {
            tabela.getColumnModel().getColumn(col[i]).setCellRenderer(r);
        }
    }

    /**
     * retorna a data formatada com mascara
     *
     * @param dtData
     * @return
     */
    public static String dateToStr(java.util.Date dtData) {
        if (dtData == null) {
            return "";
        }
        String data = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dtData);
        data = format.format(gc.getTime());
        return data;
    }

    /**
     * retorna caminho da imagem
     *
     * @param bt
     * @param imagem imagem desejada
     */
    public void efeitoImgem(final JButton bt, ImageIcon imagem) {
        bt.setIcon(imagem);
        bt.setSize(80, 80);
        bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
                bt.setSize(100, 100);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
                bt.setSize(80, 80);
            }
        });
    }

    /**
     * serve para converter html para texto normal e joga na Jtable ex:
     * getColumn(2).setCellRenderer(new HtmlRenderBase());
     */
    public class HtmlRenderBase extends JEditorPane implements TableCellRenderer {

        public HtmlRenderBase() {
            setEditable(!true);
            setContentType("text/html");
            setUI(new BasicEditorPaneUI());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object obj, boolean sel, boolean foc, int row, int col) {
            setBackground(UIManager.getColor(sel ? "nimbusSelection" : "Table.background"));
            setText("<font color=\"" + (sel ? "white" : "black") + "\"> " + obj.toString() + "</font>");
            return this;
        }
    }

    public static String removeAcento(String texto) {
        String ret;
        CharSequence cs = new StringBuilder(texto);
        ret = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return ret;
    }

    /**
     * Ativa/desativa vários componentes dentro de um container
     *
     * @param container - Normalmente um Panel onde estão os demais componentes
     * @param enable boolean
     */
    public static void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    private final static MouseAdapter mouseAdapter = new MouseAdapter() {
    };

    /**
     * Dispara o cursor de espera que permanece ativo até que a ordem especifica
     * seja disparada
     *
     * @param component
     */
    public static void startCursor(JComponent component) {
        RootPaneContainer root = ((RootPaneContainer) component.getTopLevelAncestor());
        root.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        root.getGlassPane().addMouseListener(mouseAdapter);
        root.getGlassPane().setVisible(true);
    }

    /**
     * Para o cursor de espera
     *
     * @param component
     */
    public static void stopCursor(JComponent component) {
        RootPaneContainer root = ((RootPaneContainer) component.getTopLevelAncestor());
        root.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        root.getGlassPane().removeMouseListener(mouseAdapter);
        root.getGlassPane().setVisible(false);
    }

    public static String formatarPocentagem(BigDecimal valor) {
        if (valor != null) {
            //        DecimalFormat df = new DecimalFormat("0.00");

            return String.format("%.2f", valor);
//            Locale brasil = new Locale("pt", "BR");
//            DecimalFormat numberFormat = new DecimalFormat("#######0.00", new DecimalFormatSymbols(brasil));
//            numberFormat.setParseBigDecimal(true);
//            numberFormat.setDecimalSeparatorAlwaysShown(true);
//            numberFormat.setMinimumFractionDigits(2);
//            return String.valueOf(numberFormat);
        } else {
            return "";
        }

    }

    public static String formatarValores(BigDecimal valor) {
        System.out.println("val"+valor);
        if (valor != null) {
//              DecimalFormat df = new DecimalFormat("0.00");
            Locale brasil = new Locale("pt", "BR");
            DecimalFormat numberFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(brasil));
            numberFormat.setParseBigDecimal(true);
            numberFormat.setDecimalSeparatorAlwaysShown(true);
            numberFormat.setMinimumFractionDigits(2);
            System.out.println("aa"+String.valueOf(numberFormat.format(valor)));
            return String.valueOf(numberFormat.format(valor));
        } else {
            return "";
        }

    }

    /**
     * Método para marcar ou desmarcar todas as linhas de uma determinada tabela
     *
     * @param table
     * @param coluna
     */
    public void listenerHeaderTable(final javax.swing.JTable table, final int coluna) {
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
                        table.setValueAt(status, i, coluna);
                    }
                    table.repaint();
                }
            }
        });

    }

    public static class barraProgresso extends Thread {

        JProgressBar pb;
        String texto;

        public barraProgresso(JProgressBar pb, String texto) {
            this.pb = pb;
            this.pb.setVisible(false);
            this.texto = texto;
        }

        @Override
        public synchronized void start() {
            pb.setString(texto);
            pb.setStringPainted(true);
            pb.setIndeterminate(true);
            pb.setVisible(true);
        }

        @Override
        public void finalize() throws Throwable {
            pb.setString("");
            pb.setStringPainted(false);
            pb.setIndeterminate(false);
            pb.setVisible(false);

        }

    }

    public List<Object> getUf() {
        ArrayList uf = new ArrayList();
        uf.add("BR");
        uf.add("AC");
        uf.add("AL");
        uf.add("AP");
        uf.add("AM");
        uf.add("BA");
        uf.add("CE");
        uf.add("DF");
        uf.add("ES");
        uf.add("GO");
        uf.add("MA");
        uf.add("MT");
        uf.add("MS");
        uf.add("MG");
        uf.add("PA");
        uf.add("PB");
        uf.add("PR");
        uf.add("PE");
        uf.add("PI");
        uf.add("RR");
        uf.add("RO");
        uf.add("RJ");
        uf.add("RN");
        uf.add("RS");
        uf.add("SC");
        uf.add("SP");
        uf.add("SE");
        uf.add("TO");

        return uf;
    }

    public void formataCpfCnpj(JTextField field) {
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER)
                        || (c == KeyEvent.VK_TAB) || (Character.isDigit(c)))) {
                    e.consume();
                }
                if (field.getText().length() > 18) {
                    field.setText(field.getText().substring(0, 18));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    return;
                }

                String numeros = field.getText().replaceAll("[^0-9]", "").trim();
                int length = field.getText().replaceAll("[^0-9]", "").trim().length();

                k2 = k1;
                k1 = e.getKeyCode();
                if ((k2 == 17 && k1 == 86) || (k2 == 86 && k1 == 86)) {
                    if (length > 14) {
                        System.out.println("aaa");
                        numeros = numeros.substring(0, 15);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    if (length == 11) {
                        field.setText(numeros.substring(0, 3) + "." + numeros.substring(3, 6) + "." + numeros.substring(6, 9) + "-" + numeros.substring(9, 11));
                    }

                } else {
                    if (length == 3) {
                        field.setText(numeros.substring(0, 3) + ".");
                    }
                    if (length == 6) {
                        field.setText(numeros.substring(0, 3) + "." + numeros.substring(3, 6) + ".");
                    }
                    if (length == 9) {
                        field.setText(numeros.substring(0, 3) + "." + numeros.substring(3, 6) + "." + numeros.substring(6, 9) + "-");
                    }
                    if (length == 12) {
                        field.setText(numeros.substring(0, 2) + "." + numeros.substring(2, 5) + "." + numeros.substring(5, 8) + "/" + numeros.substring(8, 12) + "-");
                    }
                    if (length >= 14) {
                        field.setText(numeros.substring(0, 2) + "." + numeros.substring(2, 5) + "." + numeros.substring(5, 8) + "/" + numeros.substring(8, 12) + "-" + numeros.substring(12, 14));
                    }
                }
            }
        });

    }
}
