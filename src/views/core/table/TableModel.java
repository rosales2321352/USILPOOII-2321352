package views.core.table;

import javax.swing.table.DefaultTableModel;

public class TableModel  extends DefaultTableModel {
    String[] titles;
    Object[][] data;
    Boolean editable = false;
    public TableModel(Object[][] data, String[] titles, Boolean editable){
        super();
        this.titles = titles;
        this.data = data;
        this.editable = true;
        setDataVector(data,titles);
    }
    public TableModel(Object[][] data, String[] titles){
        super();
        this.titles = titles;
        this.data = data;
        setDataVector(data,titles);
    }


    @Override
    public boolean isCellEditable(int row,int column){
        if(!editable) {
            return column == getColumnCount() - 1;
        }
        return true;
    }

}
