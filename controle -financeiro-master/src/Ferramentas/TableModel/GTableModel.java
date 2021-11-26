package Ferramentas.TableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;


/**
 * Um model generico para JTable. A Classe(<b>T</b>) do tipo da lista devem 
 * implementar {@link GenericObject} para criar os get e set.
 * @author christian
 * @param <T> Deve ser uma classe de entidade que implementa {@link GenericObject}
 * @see GenericObject
 */
public class GTableModel<T extends TbModel.GetSet> extends javax.swing.table.AbstractTableModel{
    private List<T> list;
    private JTable  table;
    private String  names[];
    private Class   types[];
    private boolean edits[];
    private int     order[];
    // <editor-fold defaultstate="collapsed" desc="Constructor">                          
    /**
     * Constroi um novo GTableModel, com uma lista vazia.
     * Apos Instanciar é Obrigado usar o metodo {@link #setName(java.lang.String[]) }.<br><br>
     * <b>metodos opcionais:</b>
     * <ul>
     * <li>{@link #setClass(java.lang.Class[]) }
     * <li>{@link #setEditable(boolean[]) }
     * <li>{@link #setOrder(int[]) }
     * </ul>
     */
    public GTableModel() {
        list = new ArrayList();
    }
    
    /**
     * Constroi um novo GTableModel, com uma lista.
     * @param list
     */
    public GTableModel(List<T> list) {
        this.list = list;
       
    }
    
    /**
     * coloca a lista em {@link class}
     * @param list
     */
    public void setList(List<T> list){
        this.list = new ArrayList(list);
        fireTableDataChanged();
    }
    
    /**
     * seta o nome das colunas da JTable<br>
     * <b>Exemplo:</b> GTableModel.setName("nome da coluna1", "coluna2", "3" );
     * @param name
     */
    public void setName(String... name){
        names = name;
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].toUpperCase();
        }
        Arrays.fill(types = new Class[names.length], String.class);
        Arrays.fill(edits = new boolean[names.length], false);
        order = new int[names.length];
        for (int i = 0; i < order.length; i++) {
            order[i]=i;
        }

    }
    
    /**
     * seta o tipo de classe para todas as colunas da JTable
     * @param type
     */
    public void setAllClass(Class type){
        Arrays.fill(types, type);
    }
    
    /**
     * seta o tipo de classe das colunas da JTable<br> para cada coluna em sequencia
     * <b>Exemplo:</b> GTableModel.setClass(String.class , Integer.class, Boolean.class);
     * @param type
     * @see  #setAllClass(java.lang.Class) 
     */
    public void setClass(Class... type){
        System.arraycopy(type, 0, types, 0, type.length);
    }
//    public void setClass(int i, Class type){
//        types[i] = type;
//    }
    
    /**
     * seta todas as colunas da JTable editavel ou nao
     * @param b
     */
    public void setAllEditable(boolean b){
        Arrays.fill(edits, b);
    }
    
    /**
     * seta se a coluna da JTable é editavel ou nao para cada coluna em sequencia.
     * <br><b>Exemplo:</b> GTableModel.setClass(true, false, true);
     * @param edit
     * @see  #setAllEditable(boolean) 
     * @see  #setEditable(int) 
     */
    public void setEditable(boolean... edit){
        System.arraycopy(edit, 0, edits, 0, edit.length);
    }
    
    /**
     * troca a coluna para editavel ou nao
     * @param i o indice da coluna
     */
    public void setEditable(int i){
        edits[i] = !edits[i];
    }
    
    /**
     * reordena a coluna da jtable.
     * <br><b>Exemplo:</b> Uma tabela com dados exibindo em sequencia: Id, Nome, Idade,
     * por padrão a ordem dela é 0, 1, 2. Usando o metodo GTableModel.setOrder(1, 0, 2); 
     * estara reordenando a ordem padrao de 0, 1, 2 para 1, 0, 2. Assim os dados serao exibidos: Nome, Id, Idade.
     * @param order a ordem da sequencia a ser exibida
     */
    public void setOrder(int... order){
        System.arraycopy(order, 0, this.order, 0, order.length);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="AbstractTableModel">
    public Object getValueAt(int row, int col) {
        return list.get(row).get(order[col]);
    }
    public void setValueAt(Object val, int row, int col) {
        list.get(row).set(order[col], val);
    }
    
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
        return list.size();
    }
    public int getColumnCount() {
        return names.length;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Table">
    public void setTable(JTable table) {
        this.table = table;
        this.table.setModel(this);
        this.table.setRowHeight(23);
    }

    public T get() {
        return list.get(table.convertRowIndexToModel(table.getSelectedRow()));
    }
//    public List<T> getSelectedObjects() {
//        List<T> selecteds = new ArrayList();
//        for (int i : table.getSelectedRows()) {
//            selecteds.add(list.get(table.convertRowIndexToModel(i)));
//        }
//        return selecteds;
//    }
    
    public void setMaxWidth(Integer... width){
        for (int i = 0; i < width.length; i++)
            if(width[i]!= null) table.getColumnModel().getColumn(i).setMaxWidth(width[i]);
    }

    public void setPreferredWidth(int... width){
        for (int i = 0; i < width.length; i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
    }
    public void setMinWidth(int... width){
        for (int i = 0; i < width.length; i++)
            table.getColumnModel().getColumn(i).setMinWidth(width[i]);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="List">
    
    /**
     * Returns a list.
     */
    public List<T> getList() {
        return list;
    }
    
    /**
     * Returns the element at the specified position in this list.
     *
     * @param row rowIndex of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public T get(int row) {
        return list.get(table.convertRowIndexToModel(row));
    }
    
    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended to this list
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this list
     */
    public void add(T e) {
        list.add(e);
        fireTableDataChanged();
    }
    
    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param e element to be removed from this list, if present
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this list
     */
    public void remove(T e) {
        list.remove(e);
        fireTableDataChanged();
    }
    //</editor-fold>
    
}