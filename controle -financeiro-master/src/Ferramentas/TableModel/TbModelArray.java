/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ferramentas.TableModel;

/**
 * para usar, jeva o explemplo em util.generic.ExemploTbModel linha 74 (nao esqueca de comentar a linha 77)
 * @author CHRISTIAN
 */
public class TbModelArray extends TbModelBase<Object[]>{
    public Object getValueAt(int row, int col) {
        return list.get(row)[order[col]];
    }
    
    public void setValueAt(Object val, int row, int col) {
        list.get(row)[order[col]]= val;
        fireTableCellUpdated(row, col);
    }
}
