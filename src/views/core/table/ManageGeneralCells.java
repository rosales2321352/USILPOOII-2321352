package views.core.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ManageGeneralCells extends DefaultTableCellRenderer {

    private String type = "text";
    private Font normal = new Font("Open Sans",Font.PLAIN,12);
    private Font bold = new Font("Open Sans",Font.BOLD,12);
    public ManageGeneralCells(){

    }
    public ManageGeneralCells(String type){
        this.type = type;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Color backgroundColor = null;
        Color backgroundColorDefault = new Color(192,192,192);
        Color backgroundColorSelected = new Color(140,140,140);

        if(isSelected){
            this.setBackground(backgroundColorSelected);
        }else{
            this.setBackground(Color.WHITE);
        }
        if(type.equals("text")){
            if(hasFocus){
                backgroundColor = backgroundColorSelected;
            }else{
                backgroundColor = backgroundColorDefault;
            }
            this.setHorizontalAlignment(JLabel.LEFT);
            if(value instanceof String) {
                this.setText((String) value);
            }
            this.setFont(normal);
            this.setForeground(Color.BLACK);
            this.setBackground((isSelected)?backgroundColor:Color.WHITE);
            return this;
        }
        return  this;
    }

    protected void setValue(Object value) {
        setText((value == null) ? "" : value.toString());
    }

}
