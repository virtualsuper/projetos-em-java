package Ferramentas.TableModel;


public class TbModel<E extends TbModel.GetSet> extends TbModelBase<E>{

    @Override
    public Object getValueAt(int row, int col) {
        return list.get(row).get(order[col]);//.get(order.length<row?order[col]:col);
    }

    @Override
    public void setValueAt(Object val, int row, int col) {
        list.get(row).set(order[col], val);
        fireTableCellUpdated(row, col);
    }

    /**
     * As classes de entidades que serao usadas com a {@link TbModel},
     * todas devem implementar a interface {@link GetSet} e seus metodos get e set.
     * @author christian
     * @see #get(int)
     * @see #set(int, java.lang.Object)
     */
    public static interface GetSet {

        /**
         * o metodo que retorna os valores dos atributos para cada coluna.
         * @param col a indice da coluna
         * @return o valor dos atributos da entidade
         */
        Object get(int col);

        /**
         * o metodo que seta o valor para o atributo.
         * @param col a indice da coluna
         * @param val valor a ser alterado.
         */
        void set(int col, Object val);
    }
    
}
