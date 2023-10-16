package view.core;

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
        getColumnModel().getColumn(0).setCellRenderer(renderer);
    }

}
