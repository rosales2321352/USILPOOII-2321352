package views.core.combobox;

import javax.swing.*;
import java.util.List;



public class CustomComboBoxModel<T extends IComboBox> extends DefaultComboBoxModel<T> {
    public CustomComboBoxModel(List<T> list){
        for(T item: list ){
            addElement(item);
        }
    }

    public int getSelectedItemId() {
        T selected = (T) getSelectedItem();
        if (selected != null) {
            return (int)selected.getId();
        }
        return -1; // Valor predeterminado si no se puede obtener el ID
    }

    public String getSelectedItemName() {
        T selected = (T) getSelectedItem();
        if (selected != null) {
            return (String)selected.getValue();
        }
        return null; // Valor predeterminado si no se puede obtener el nombre
    }



}
