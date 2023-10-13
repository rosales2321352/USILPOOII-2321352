package views.core.table;

import javax.swing.*;

@FunctionalInterface
public interface TableCallback {
    void apply(JTable table);
}
