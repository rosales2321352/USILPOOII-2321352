package view.core.table;

import view.core.panel.ActionPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellManagement extends DefaultTableCellRenderer {

    private String type = "text";
    private Font normal = new Font("Arial",Font.PLAIN,12);
    private Font bold = new Font("Arial",Font.BOLD,12);
    public CellManagement(){

    }
    public CellManagement(String type){
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
        if(type.equals("actions")){
            ActionPanel actions = new ActionPanel();
            return  actions;
        }
        /*if(type.equals("number")){
            if(hasFocus){
                backgroundColor = backgroundColorSelected;
            }else{
                backgroundColor = backgroundColorDefactut;
            }
            this.setHorizontalAlignment(JLabel.LEFT);
            this.setText((String) value);
            this.setBackground((isSelected)?backgroundColor:Color.WHITE);
            return this;
        }*/

        return  this;
    }

}
