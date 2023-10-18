package view.core.table;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener
{
    JTable table;
    JButton renderButton;
    JButton editButton;
    String text;

    Object editorValue;

    public ButtonColumn(JTable table, int column)
    {
        super();
        this.table = table;
        Icon icon = new ImageIcon("/res/icons/delete.png");
        renderButton = new JButton("HOla Mundo",icon);
        renderButton.setText("Hola RenderButton");
        renderButton.setFont(new Font("Arial",Font.BOLD,12));

        editButton = new JButton();
        editButton.setText("Hola");
        editButton.setFont(new Font("Arial",Font.BOLD,12));
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (hasFocus)
        {
            renderButton.setText("Hola Mundi");
            renderButton.setForeground(table.getForeground());
            //enderButton.setBackground(UIManager.getColor("Button.background"));
            renderButton.setBackground(Color.YELLOW);

        }
        else if (isSelected)
        {
            renderButton.setForeground(table.getSelectionForeground());
            //renderButton.setBackground(table.getSelectionBackground());
            renderButton.setBackground(Color.RED);
        }
        else
        {
            renderButton.setForeground(table.getForeground());
            //renderButton.setBackground(UIManager.getColor("Button.background"));
            renderButton.setBackground(Color.BLUE);
        }

        renderButton.setText( (value == null) ? "" : value.toString() );

        if(value == null)
        {
            editButton.setText("");
            editButton.setIcon(null);

        }else if(value  instanceof Image){
            try {
                Image e = ImageIO.read(getClass().getResource("/res/icons/ss.png"));
                editButton.setIcon(new ImageIcon(e));
            }catch (Exception e){
                e.printStackTrace();
            }
            editButton.setText("");

        }else{
            editButton.setText(value.toString());
            editButton.setIcon(null);
        }





        return renderButton;
    }

    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
    {

        if(value == null)
        {
                editButton.setText("");
                editButton.setIcon(null);

        }else if(value  instanceof Icon){
            try {
                Image e = ImageIO.read(getClass().getResource("/res/icons/ss.png"));
                editButton.setIcon((Icon) e);
            }catch (Exception e){
                e.printStackTrace();
            }
            editButton.setText("");
        }else{
            editButton.setText(value.toString());
            editButton.setIcon(null);
        }

        this.editorValue = value;
        return editButton;
    }

    public Object getCellEditorValue()
    {
        System.out.println("Hola");

        return editorValue;
    }

    public void actionPerformed(ActionEvent e)
    {
        fireEditingStopped();
        System.out.println( e.getActionCommand() + " : " + table.getSelectedRow());
    }
}
