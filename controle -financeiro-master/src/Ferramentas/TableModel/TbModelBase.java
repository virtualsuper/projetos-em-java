/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

public class TbModelBase<E> extends AbstractTableModel implements Iterable<E> {

    protected List<E> list = new ArrayList<>();
    private JTable table;
    private String names[];
    private Class types[];
    private boolean edits[];
    int order[];

    //<editor-fold defaultstate="collapsed" desc="List">
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = new ArrayList<>(list);
        fireTableDataChanged();
    }

    public void setListLinked(List<E> list) {
        this.list = list;
        fireTableDataChanged();
    }

    /**
     * Returns the element at the specified position in table.
     *
     * @param row index of the element to return
     * @return the element at the specified position in table
     * @throws IndexOutOfBoundsException if the index is out of range (<tt>index
     * &lt; 0 || index &gt;= size()</tt>)
     */
    public E get(int row) {
        return list.get(convertRow(row));
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="setProperty">
    /**
     * setName passando propriedades como Editaval, TipoClass, Tamanho. as
     * propriedades sao separados por |.<br>
     * <ul>
     * <li>use '!' para editavel. <br><p>
     * ex: setName(tabela, "!|NomeCampo")</p>
     * <li>use b, i, d, D para classe.<br> ex: setName(tabela,
     * "b|NomeCampo")</li>
     * <li>use + ou - ou = (maxSize, minSize, prefSize) e numero para largura da
     * coluna.
     * <br>ex: setName(tabela, "+30|NomeCampo"(ou -30, =30)) </li>
     * </ul>
     * e podem ser usados todos juntos ex: setName(table, "!b+30| NomeCampo")
     * (Editavel, Boolean.class, max30) é obrigatorio seguir a sequencia
     * [editavel][classe][tamanho] e vim separado por |
     *
     * @param table
     * @param name
     */
    public void setName(JTable table, String... name) {
        setName(name);
        String[] property = new String[name.length];
        for (int i = 0; i < name.length; i++) {
            String s = name[i].toUpperCase();
            int indexOf = s.indexOf("|");
            property[i] = "";

            if (indexOf < 0) {
                continue;
            }
            name[i] = s.substring(indexOf + 1);
            property[i] = s.substring(0, indexOf);
        }

        setTable(table);
        for (int i = 0; i < property.length; i++) {
            String s = property[i];
            if (s.isEmpty()) {
                continue;
            }
            if (s.startsWith("!")) {
                s = s.replace("!", "");
                setEditableAt(i);
                if (s.isEmpty()) {
                    continue;
                }
            }
//            if (s.startsWith("#")){
//                s = s.replace("#", "");
//                getColumn(i).getCellRenderer()
//                setCenter(order);
//                if (s.isEmpty()) continue;
//            }

            if (Character.isAlphabetic(s.charAt(0))) {
                switch (s.charAt(0)) {
                    case 'b':
                        setClassAt(Boolean.class, i);
                        break;
                    case 'D':
                        setClassAt(Date.class, i);
                        break;
                    case 'i':
                        setClassAt(Integer.class, i);
                        break;
                    case 'd':
                        setClassAt(Double.class, i);
                        break;
                }
                if (s.length() == 1) {
                    continue;
                }
            }

            Matcher m = Pattern.compile("([+-=])(\\d+)").matcher(s);
            if (m.find()) {
                int width = Integer.parseInt(m.group(2));
                switch (m.group(1)) {
                    case "-":
                        getColumn(i).setMinWidth(width);
                        break;
                    case "+":
                        getColumn(i).setMaxWidth(width);
                        break;
                    case "=":
                        getColumn(i).setPreferredWidth(width);
                        break;
                }
            }
        }
    }

    /**
     * Seta o nome Titulo da coluna em sequencia. ex: setName("titulo 1 ", "2",
     * ...);
     *
     * @param name
     */
    public void setName(String... name) {
        for (int i = 0; i < name.length; i++) {
            name[i] = name[i].toUpperCase();
        }
        names = name;
        Arrays.fill(types = new Class[names.length], String.class);
        Arrays.fill(edits = new boolean[names.length], false);
        order = new int[names.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
    }

    /**
     * Seta o type da coluna em sequencia. setClass(Boolean.class, ...)
     *
     * @param type
     */
    public void setClass(Class... type) {
        System.arraycopy(type, 0, types, 0, type.length);
    }

    /**
     * Seta o type da coluna em coluna especifica. ex: setClassAt(classTipo,
     * colunas1, colunas5, ...)
     *
     * @param type
     * @param col
     */
    public void setClassAt(Class type, int... col) {
        for (int i : col) {
            if (i < types.length) {
                types[i] = type;
            }
        }
    }

    /**
     *
     * @param edit
     */
    public void setEditable(boolean... edit) {
        System.arraycopy(edit, 0, edits, 0, edit.length);
    }

    public void setEditableAt(int... col) {
        for (int j : col) {
            edits[j] = !edits[j];
        }
    }

    public void setEditableAll(boolean edit) {
        Arrays.fill(edits, edit);
    }

    public void setOrder(int... col) {
        System.out.println(Arrays.toString(order));
        System.arraycopy(col, 0, this.order, 0, col.length);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="TableModel">
    public Object getValueAt(int row, int col) {
        return null;
    }

    ;
    public void setValueAt(Object val, int row, int col) {
    }

    ;

    public String getColumnName(int i) {
        return names[i];
    }

    public Class<?> getColumnClass(int i) {
        return types[i];
    }

    public boolean isCellEditable(int row, int i) {
        return edits[i];
    }

    public int getRowCount() {
        return size();
    }

    public int getColumnCount() {
        return names.length;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Table">

    public void setTable(JTable table) {
        (this.table = table).setModel(this);
        (this.table = table).setRowHeight(23);

    }

    public E getSelected() {
        return table.getSelectedRow() < 0 ? null : get(table.getSelectedRow());
    }

    public List<E> getSelecteds() {
        ArrayList<E> l = new ArrayList<>();
        for (int i : table.getSelectedRows()) {
            l.add(get(i));
        }
        return l;
    }

    public void removeSelecteds() {
        removeAll(getSelecteds());
    }

    public void updateSelecteds() {
        for (int i : table.getSelectedRows()) {
            fireTableRowsUpdated(i, i);
        }
    }

    public void setMaxWidth(Integer... width) {
        for (int i = 0; i < width.length; i++) {
            if (width[i] != null) {
                getColumn(i).setMaxWidth(width[i]);
            }
        }
    }

    public void setMaxWidthAt(Integer col, Integer width) {
        getColumn(col).setMaxWidth(width);
    }

    public void setMinWidthAt(Integer col, Integer width) {
        getColumn(col).setMinWidth(width);
    }

    public void setMinWidth(Integer... width) {
        for (int i = 0; i < width.length; i++) {
            if (width[i] != null) {
                getColumn(i).setMinWidth(width[i]);
            }
        }
    }

    public void setMinMaxWidth(Integer... width) {
        setMinWidth(width);
        setMaxWidth(width);
        setMinWidth(width);
    }

    public void setPreferredWidth(int... width) {
        for (int i = 0; i < width.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
        }
    }

    public void setCenter(int... colum) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(0);
        for (int i : colum) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="utils">

    /**
     * Adiciona Acao no titulo da coluna, na indice especificado pelo parametro,
     * que ao clicar, seta o valor para true ou false.
     *
     * @param col
     */
    public void addBoolAlternable(final int col) {
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (table.getTableHeader().columnAtPoint(e.getPoint()) == col
                        && table.getRowCount() > 0) {
                    if (table.getRowCount() < 1) {
                        return;
                    }
                    boolean b = !(boolean) getValueAt(convertRow(0), col);
//                    boolean b = !(boolean) get(0).get(col);
                    for (int i = 0; i < table.getRowCount(); i++) {
                        setValueAt(b, convertRow(i), col);
                    }
//                        get(i).set(col, b);
                    table.repaint();
                }
            }
        });
    }

    /**
     * esse methodo equivale a table.getColumnModel().getColumn(col);
     *
     * @param col
     * @return
     */
    public TableColumn getColumn(int col) {
        return table.getColumnModel().getColumn(col);
    }

    /**
     * seta o filtro , e adiciona acoes no componente, para que quando mudar o
     * valor dispare o filtro.
     *
     * @param filter
     * @param component
     */
    public void setFilterOn(RowFilter filter, JComponent... component) {
        table.setAutoCreateRowSorter(true);
        ((TableRowSorter) table.getRowSorter()).setRowFilter(filter);
        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.getRowSorter().allRowsChanged();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        table.scrollRectToVisible(table.getCellRect(selectedRow(), 0, false));
                    }
                });
            }
        };
        KeyAdapter actk = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                table.getRowSorter().allRowsChanged();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        table.scrollRectToVisible(table.getCellRect(selectedRow(), 0, false));
                    }
                });
            }
        };
        for (JComponent c : component) {
            if (c instanceof JTextComponent) {
                c.addKeyListener(actk);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).addActionListener(act);
            } else if (c instanceof JButton) {
                ((JButton) c).addActionListener(act);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).addActionListener(act);
            }
        }

    }

    /**
     * retorna a linha selecionada Convertida
     *
     * @return
     */
    public int selectedRow() {
        return convertRow(table.getSelectedRow());
    }

    /**
     * convert a linha passado pelo parametro
     *
     * @param i
     * @return
     */
    public int convertRow(int i) {
        if (0 > i || i >= size()) {
            return i;
        }
        if (convertRow == convertTemp) {
            i = convertRow ? table.convertRowIndexToModel(i) : i;
        } //        else if (convertTemp == true){
        //            i = table.convertRowIndexToModel(i);
        //            convertTemp = !convertTemp;
        else {
            convertTemp = convertRow;
        }
        return i;
    }

    public void add(E e) {
        add(size(), e);
//        int lastIndex = getRowCount() - 1;
//        fireTableRowsInserted(lastIndex, lastIndex);
    }

    public void add(int index, E element) {
        index = convertRow(index);
        list.add(index, element);
        fireTableRowsInserted(index, index);
    }

    public void addAll(Collection<? extends E> c) {
        int size = list.size();
        list.addAll(c);
        fireTableRowsInserted(size, list.size() - 1);
//        for (E e : c) add(e);
    }

    public E remove(int index) {
        index = convertRow(index);
        E e = list.remove(index);
        fireTableRowsDeleted(index, index);
        return e;
    }

    public void remove(E o) {
        int i = indexOf(o);
        list.remove(o);
        fireTableRowsDeleted(i, i);
    }

    public void removeAll(Collection<E> c) {
        for (E e : c) {
            remove(e);
        }
    }

    public void addFromSelected(int index, E element) {
        index = selectedRow() + index;
        list.add(index, element);
        fireTableRowsInserted(index, index);
    }

    public E getFromSelected(int index) {
        return list.get(selectedRow() + index);
    }

    public void moveSelectedUp() {
        moveSelected(selectedRow() - 1);
    }

    public void moveSelectedDown() {
        moveSelected(selectedRow() + 2);
    }

    public void moveSelected(int to) {
        int from = selectedRow();
        if (from < to) {
            --to;
        }
        if (from < 0 || 0 > to || to >= size()) {
            return;
        }
        E e = remove(from);
        add(to, e);
        table.setRowSelectionInterval(to, to);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int indexOf(E o) {
        return list.indexOf(o);
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public boolean contains(E o) {
        return list.contains(o);
    }

    private boolean convertRow = true, convertTemp = true;

    /**
     * o tbModel convert as linhas automaticamente em relacao aos dados da model
     * e da tabela. para desabilitar use esse metodo.
     *
     * @param b
     */
    public void setAutoConvertRow(boolean b) {
        convertTemp = convertRow = b;
    }

    /**
     * use esse metodo para desabilitar para um metodo. ex:
     * convert(false).remove(objeto);
     *
     * @param b
     * @return
     */
    public TbModelBase convert(boolean b) {
        convertTemp = b;
        return this;
    }

    public TbModelBase convert() {
        return convert(!convertTemp);
    }

}
