package views;

import views.core.table.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public abstract class JPBaseList extends JPanel{
    protected JTable table;
    protected TableModel model;
    public JPanel titlePanel;
    public JPBaseList(){
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
    }

    public abstract void drawControls();
    public void makeTitle(String title){
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD,24));
        titlePanel.add(lblTitle);
    }
    public void makeTableHeader(String[] titles, int len){
        Object[][] objects = new Object[len][];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new Object[]{""};
        }
        model = new TableModel(objects,titles);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);
    }
    public void makeTable(Object[][] information, String[] titles, TableCallback callback){
        model = new TableModel(information,titles);
        table.setModel(model);
        for(int i=0;i < table.getColumnCount() - 1;i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new ManageGeneralCells("text"));
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        table.setGridColor(new java.awt.Color(216,216,216));

        if(callback != null){
            callback.apply(table);
        }

        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new ManageTableHeader());
        table.setTableHeader(jTableHeader);

    }
}
