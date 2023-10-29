package view.core.table;

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

    public ManageCellsActionButtons(JTable table, int column, JPanel panel1, JPanel panel2)
    {
        super();
        this.table = table;
        actionsRenderer =  panel1;
        actionsEdit =  panel2;

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        actionsRenderer.setBackground(Color.WHITE);
        /*if (hasFocus)
        {
            renderButton.setForeground(table.getForeground());
        }
        else if (isSelected)
        {
            renderButton.setForeground(table.getSelectionForeground());
        }
        else
        {
            renderButton.setForeground(table.getForeground());
        }
        this.loadValue(value);
        return renderButton;*/

        return this.actionsRenderer;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        this.editorValue = value;
        return this.actionsEdit;
    }

    public Object getCellEditorValue()
    {
        return editorValue;
    }

}
