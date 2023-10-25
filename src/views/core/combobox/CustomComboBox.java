package views.core.combobox;

import javax.swing.*;
import java.util.List;

public class CustomComboBox<T extends IComboBox> extends JComboBox<T>{

    public <P> void setSelectedItemById(P id) {
        for (int i = 0; i < getModel().getSize(); i++) {
            T element = getModel().getElementAt(i);
            if (element.getId() == id) {
                setSelectedItem(element);
                return;
            }
        }
    }

}
