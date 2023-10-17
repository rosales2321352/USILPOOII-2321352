package view.core.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomTable extends JTable {

    public CustomTable(DefaultTableModel model){
        super(model);
        setAutoCreateRowSorter(true);
        setFont(new Font("Arial", Font.PLAIN,12));
        setRowHeight(24);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < getColumnModel().getColumnCount(); i++ ){
            getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

}
