package views.core.table;

import views.core.panel.IActionPanel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ManageCellsActionButtons extends AbstractCellEditor implements TableCellRenderer, TableCellEditor
{
    JTable table;
    Object editorValue;
    JPanel actionsRenderer;
    JPanel actionsEdit;
    IActionPanel actionPanel;

    public ManageCellsActionButtons(JTable table, int column, IActionPanel panel1, IActionPanel panel2)
    {
        super();
        this.table = table;
        actionsRenderer =  panel1.getPanel();
        actionsEdit =  panel2.getPanel();
        actionPanel = panel2;

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        actionsRenderer.setBackground(Color.WHITE);
        return actionsRenderer;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        editorValue = value;
        Object id_value = table.getValueAt(row,0);
        if(id_value instanceof String){
            actionPanel.setId(id_value);
        }
        return actionsEdit;
    }

    public Object getCellEditorValue()
    {
        return editorValue;
    }

}
