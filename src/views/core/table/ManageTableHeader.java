package views.core.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ManageTableHeader implements TableCellRenderer {


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel jcomponent = null;

        if(value instanceof String) {
            jcomponent = new JLabel((String) value);
            jcomponent.setHorizontalAlignment(SwingConstants.CENTER);
            jcomponent.setSize(30, jcomponent.getWidth());
            jcomponent.setPreferredSize(new Dimension(6, jcomponent.getWidth()));
        }

        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0,0,1,1,new java.awt.Color(255,255,255)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(new Color(65,65,65));
        jcomponent.setToolTipText("Tabla");
        jcomponent.setForeground(Color.WHITE);
        return jcomponent;
    }


}
