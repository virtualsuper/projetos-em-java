/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ferramentas.TableModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * para usar, jeva o explemplo em util.generic.ExemploTbModel linha 25
 * @author CHRISTIAN
 * @param <E>
 */

public class TbModelReflection<E> extends TbModelBase<E> {
    
    private Field[][] fields;

    @Override
    public Object getValueAt(int row, int col) {
        try {
            Object o = list.get(row);
            for (Field f : fields[order[col]])
                o = f.get(o);
            return o;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(TbModelReflection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void setValueAt(Object val, final int row, final int col) {
        try {
            Field[] f = fields[order[col]];
            Object o = list.get(row);
            for (int i = 0; i < f.length-1; i++)
                o = f[i].get(o);
            f[f.length-1].set(o, val);
            fireTableCellUpdated(row, col);
        } catch (IllegalAccessException | IllegalArgumentException ex) {
       //     Logger.getLogger(TbModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Para usar esse metodo a classse de entidade de ter a annotacao @Atributes com seus respectivos value.
     * veja exemplo em linha 40 legi.Model.LegiBase
     * @see legi.Model.LegiBase
     */
    public void setModelByAttributes() {
        setModel("Attributes");
    }
    
    /**
     * seta os atribus a ser exibido na tbModel. 
     * Usando esse model nao precisa setar o tipo da coluna(setClass()), 
     * quando passa os attributos nesse model ele autoDetecta o tipo da Classe. 
     * <b>detalhe:</b> para auto detectar o tipo da classe, esse metodo deve ser usado depois do setName().
     * @param names
     */
    public void setModel(String... names) {
        ParameterizedType p = (ParameterizedType) getClass().getGenericSuperclass();
        Class<E> clazz = (Class<E>) p.getActualTypeArguments()[0];

        if (names[0].equals("Attributes")){
            names = clazz.getAnnotation(Attributes.class).value().split(", | ");
        }
//<editor-fold defaultstate="collapsed" desc="All">
//        if (names[0].equals("All")){
//            Field[] fs = clazz.getDeclaredFields();
//            fields = new Field[fs.length][];
//            for (int i = 0; i < fs.length-1; i++) {
//                Field f = fs[i];
//                f.setAccessible(true);
//                if (order!=null) setClassAt(f.getType(), i);
//                fields[i] = new Field[]{f};
//                System.out.println(Arrays.toString(fields[i]));
//            }
//            return ;
//        }
//</editor-fold>
        fields = new Field[names.length][];
        for (int i = 0; i < names.length; i++) {
            try {
                String[] split = names[i].split("\\.");
                Field[] f = new Field[split.length];
                Field last = f[0] = clazz.getDeclaredField(split[0]);
                last.setAccessible(true);
                for (int j = 1; j < split.length; j++){
                    f[j] = last = last.getType().getDeclaredField(split[j]);
                    last.setAccessible(true);
                }
                if (order!=null) setClassAt(last.getType(), i);
                fields[i] = f;

            } catch (NoSuchFieldException ex) {
                Logger.getLogger(TbModelReflection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Annotation que especifica os atributos a ser usado pela TbModelReflection
     */
    @Target(value = {ElementType.METHOD,ElementType.TYPE})
    @Retention(value = RetentionPolicy.RUNTIME)
    public static @interface Attributes{

        /**
         * Atributos sao separados por espaco ou virula e espaco
         * @return
         */
        public String value() default "";
    }

}
