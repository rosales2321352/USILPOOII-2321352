package views.core.table;

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
        return column == getColumnCount() - 1;
    }

}
