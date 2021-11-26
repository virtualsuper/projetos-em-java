/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.TableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ANDRE
 */
public class AgruparColunas {

    private ColumnGroup[] columnGroup;
    private final Object[][] colunasTabela;
    private final String[] dsGrupoCol;
    private final JTable tabela;

    /**
     * <b> Utilizado para definir grupos de colunas em uma JTable</b>
     *
     * @param tabela
     * @param colunasTabela
     * @param dsGrupoCol
     * <li>1- tabela em que será aplicado o titulo com grupo de colunas</li>
     * <li>2- objeto bidirecional onde cada grupo de colunas corresponde a mesma
     * posição do array da descrição dos grupos do parâmetro 3</li>
     * <li>3- array com a string que define o nome dos grupos</li>
     * <p>
     * <li><b>Utilização:</li></b>
     * NewGroupableHeader headerGroupTabela = new NewGroupableHeader();<br>
     * headerGroupTabela.setGrupos(tbResumos, <br>new Object[][]{{0, 1, 2}, {3,
     * 4, 5}, {6, 7}}, "ALIQUOTA", "ICMS", "PERÍODO");<br>
     * </p>
     * |-------------------------------| <br>
     * |-ALIQUOTA--|---ICMS---|PERIODO-| <br>
     * |-----------|-----------|-------| <br>
     * | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | <br>
     * |-------------------------------| <br>
     */
    public AgruparColunas(JTable tabela, Object[][] colunasTabela, String... dsGrupoCol) {
        this.tabela = tabela;
        this.colunasTabela = colunasTabela;
        this.dsGrupoCol = dsGrupoCol;
        setHeaderGroup();
    }

    private static GroupableTableHeader header;

    private void setHeaderGroup() {
        tabela.setTableHeader(header = new GroupableTableHeader(tabela.getColumnModel()));
        if (colunasTabela.length != dsGrupoCol.length) {
            return;
        }
        columnGroup = new ColumnGroup[dsGrupoCol.length];
        for (int i = 0; i < dsGrupoCol.length; i++) {
            columnGroup[i] = new ColumnGroup((String) dsGrupoCol[i]);
        }
        TableColumnModel cm = tabela.getColumnModel();
        for (int c = 0; c < columnGroup.length; c++) {
            for (Object colunaX : colunasTabela[c]) {
                columnGroup[c].add(cm.getColumn((int) colunaX));
            }
        }
        for (ColumnGroup grupos : columnGroup) {
            header.addColumnGroup(grupos);
        }
    }

    /**
     * Ao posicionar o mouse sobre o titulo da coluna na Jtable aparece
     * informação
     *
     * @param i trazendo indece da coluna e informação <br>
     * modo de usar <br>
     * .setTooTip(new Object[][]{{0,"coluna 0"},{1,"coluna 1"}});
     */
    public static void setTooTip(Object[][] i) {
        for (Object[] o : i) {
            header.addToolTipToColumn((Integer) o[0], (String) o[1]);
        }
    }

    public static void setColorH(Object[][] i) {
        for (Object[] o : i) {
            header.getColumnModel().getColumn((int) o[0]).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public void setValue(Object value) {
                    setBackground(new Color(240, 239, 239));
                    setForeground(Color.BLACK);
                    super.setValue(value);
                }
            });

        }
    }
}

/**
 * ****************************************
 */
class GroupableTableHeader extends JTableHeader {

    protected Vector<ColumnGroup> columnGroups = new Vector<>();

    public GroupableTableHeader(TableColumnModel model) {
        super(model);
        setUI(new GroupableTableHeaderUI());
        setReorderingAllowed(false);
    }

    @Override
    public void updateUI() {
        setUI(new GroupableTableHeaderUI());
    }

    @Override
    public void setReorderingAllowed(boolean b) {
        reorderingAllowed = false;
    }

    public void addColumnGroup(ColumnGroup g) {
        columnGroups.addElement(g);
    }

    public Enumeration getColumnGroups(TableColumn col) {
        Enumeration e = columnGroups.elements();
        while (e.hasMoreElements()) {
            ColumnGroup cGroup = (ColumnGroup) e.nextElement();
            Vector v_ret = (Vector) cGroup.getColumnGroups(col, new Vector());
            if (v_ret != null) {
                return v_ret.elements();
            }
        }
        return null;
    }

    public void setColumnMargin() {
        int columnMargin = getColumnModel().getColumnMargin();
        Enumeration e = columnGroups.elements();
        while (e.hasMoreElements()) {
            ColumnGroup cGroup = (ColumnGroup) e.nextElement();
            cGroup.setColumnMargin(columnMargin);
        }
    }

    private HashMap tooltips = new HashMap();

    public String getToolTipText(MouseEvent evt) {
        int column = columnAtPoint(evt.getPoint());
        String tooltip = (String) tooltips.get(new Integer(column));
        return tooltip;
    }

    public void addToolTipToColumn(int column, String tooltip) {
        tooltips.put(new Integer(column), tooltip);
    }
}

/**
 * ****************************************
 */
class GroupableTableHeaderUI extends BasicTableHeaderUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        Rectangle clipBounds = g.getClipBounds();
        if (header.getColumnModel() == null) {
            return;
        }
        ((GroupableTableHeader) header).setColumnMargin();
        int column = 0;
        Dimension size = header.getSize();
        Rectangle cellRect = new Rectangle(0, 0, size.width, size.height);
        Hashtable h = new Hashtable();
        int columnMargin = header.getColumnModel().getColumnMargin();

        Enumeration enumeration = header.getColumnModel().getColumns();
        while (enumeration.hasMoreElements()) {
            cellRect.height = size.height;
            cellRect.y = 0;
            TableColumn aColumn = (TableColumn) enumeration.nextElement();
            Enumeration cGroups = ((GroupableTableHeader) header).getColumnGroups(aColumn);
            if (cGroups != null) {
                int groupHeight = 0;
                while (cGroups.hasMoreElements()) {
                    ColumnGroup cGroup = (ColumnGroup) cGroups.nextElement();
                    Rectangle groupRect = (Rectangle) h.get(cGroup);
                    if (groupRect == null) {
                        groupRect = new Rectangle(cellRect);
                        Dimension d = cGroup.getSize(header.getTable());
                        groupRect.width = d.width;
                        groupRect.height = d.height;
                        h.put(cGroup, groupRect);
                    }
                    paintCell(g, groupRect, cGroup);
                    groupHeight += groupRect.height;
                    cellRect.height = size.height - groupHeight;
                    cellRect.y = groupHeight;
                }
            }
            cellRect.width = aColumn.getWidth() + columnMargin;
            if (cellRect.intersects(clipBounds)) {
                paintCell(g, cellRect, column);
            }
            cellRect.x += cellRect.width;
            column++;
        }
    }

    private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
        TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
        TableCellRenderer renderer = aColumn.getHeaderRenderer();
        renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel header = new JLabel();
                header.setForeground(table.getTableHeader().getForeground());
                header.setBackground(table.getTableHeader().getBackground());
                header.setFont(table.getTableHeader().getFont());
                header.setHorizontalAlignment(JLabel.CENTER);
                header.setText(value.toString());
                header.setBorder(BorderFactory.createLineBorder(new Color(139, 139, 131)));
                return header;
            }

        };
        Component c = renderer.getTableCellRendererComponent(
                header.getTable(), aColumn.getHeaderValue(), false, false, -1, columnIndex);
        c.setBackground(UIManager.getColor("control"));
        rendererPane.add(c);
        rendererPane.paintComponent(g, c, header, cellRect.x, cellRect.y,
                cellRect.width, cellRect.height, true);
    }

    private void paintCell(Graphics g, Rectangle cellRect, ColumnGroup cGroup) {
        TableCellRenderer renderer = cGroup.getHeaderRenderer();
        Component component = renderer.getTableCellRendererComponent(
                header.getTable(), cGroup.getHeaderValue(), false, false, -1, -1);
        rendererPane.add(component);
        rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
                cellRect.width, cellRect.height, true);
    }

    private int getHeaderHeight() {
        int height = 0;
        TableColumnModel columnModel = header.getColumnModel();
        for (int column = 0; column < columnModel.getColumnCount(); column++) {
            TableColumn aColumn = columnModel.getColumn(column);
            TableCellRenderer renderer = aColumn.getHeaderRenderer();
            if (renderer == null) {
                return 60;
            }
            Component comp = renderer.getTableCellRendererComponent(
                    header.getTable(), aColumn.getHeaderValue(), false, false, -1, column);
            int cHeight = comp.getPreferredSize().height;
            Enumeration e = ((GroupableTableHeader) header).getColumnGroups(aColumn);
            if (e != null) {
                while (e.hasMoreElements()) {
                    ColumnGroup cGroup = (ColumnGroup) e.nextElement();
                    cHeight += cGroup.getSize(header.getTable()).height;
                }
            }
            height = Math.max(height, cHeight);
        }
        return height;
    }

    private Dimension createHeaderSize(long width) {
        TableColumnModel columnModel = header.getColumnModel();
        width += columnModel.getColumnMargin() * columnModel.getColumnCount();
        if (width > Integer.MAX_VALUE) {
            width = Integer.MAX_VALUE;
        }
        return new Dimension((int) width, getHeaderHeight());
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        long width = 0;
        Enumeration enumeration = header.getColumnModel().getColumns();
        while (enumeration.hasMoreElements()) {
            TableColumn aColumn = (TableColumn) enumeration.nextElement();
            width = width + aColumn.getPreferredWidth();
        }
        return createHeaderSize(width);
    }
}

/**
 * *******************************************
 */
class ColumnGroup {

    protected TableCellRenderer renderer;
    protected Vector v;
    protected String text;
    protected int margin = 0;

    public ColumnGroup(String text) {
        this(null, text);
    }

    public ColumnGroup(TableCellRenderer renderer, String text) {
        this.renderer = renderer == null ? new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    setForeground(header.getForeground());
                    setBackground(new JTable().getBackground());
                    setFont(header.getFont());
                }
                setHorizontalAlignment(JLabel.CENTER);
                setText((value == null) ? "" : value.toString());
                setBorder(BorderFactory.createLineBorder(new Color(139, 139, 131)));
                return this;
            }
        } : renderer;
        this.text = text;
        v = new Vector();
    }

    /**
     * @param obj TableColumn or ColumnGroup
     */
    public void add(Object obj) {
        if (obj == null) {
            return;
        }
        v.addElement(obj);
    }

    /**
     * @param c TableColumn
     * @param v ColumnGroups
     */
    public Vector getColumnGroups(TableColumn c, Vector g) {
        g.addElement(this);
        if (v.contains(c)) {
            return g;
        }
        Enumeration e = v.elements();
        while (e.hasMoreElements()) {
            Object obj = e.nextElement();
            if (obj instanceof ColumnGroup) {
                Vector groups
                        = (Vector) ((ColumnGroup) obj).getColumnGroups(c, (Vector) g.clone());
                if (groups != null) {
                    return groups;
                }
            }
        }
        return null;
    }

    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    public void setHeaderRenderer(TableCellRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }

    public Object getHeaderValue() {
        return text;
    }

    public Dimension getSize(JTable table) {
        Component comp = renderer.getTableCellRendererComponent(
                table, getHeaderValue(), false, false, -1, -1);
        int height = comp.getPreferredSize().height;
        int width = 0;
        Enumeration e = v.elements();
        while (e.hasMoreElements()) {
            Object obj = e.nextElement();
            if (obj instanceof TableColumn) {
                TableColumn aColumn = (TableColumn) obj;
                width += aColumn.getWidth();
                width += margin;
            } else {
                width += ((ColumnGroup) obj).getSize(table).width;
            }
        }
        return new Dimension(width, height);
    }

    public void setColumnMargin(int margin) {
        this.margin = margin;
        Enumeration e = v.elements();
        while (e.hasMoreElements()) {
            Object obj = e.nextElement();
            if (obj instanceof ColumnGroup) {
                ((ColumnGroup) obj).setColumnMargin(margin);
            }
        }
    }
}
