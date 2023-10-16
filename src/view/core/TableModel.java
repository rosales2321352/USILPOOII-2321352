package view.core;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TableModel  extends DefaultTableModel {
    String[] titles;
    Object[][] data;

    public TableModel(Object[][] data, String[] titles){
        super();
        this.titles = titles;
        this.data = data;
        setDataVector(data,titles);
    }


    @Override
    public boolean isCellEditable(int row,int column){
        return true;
    }

}
