package controllers;


import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.tax.Tax;
import views.JPBaseView;
import views.core.table.ManageTableHeader;
import views.core.table.TableCallback;
import views.core.table.TableModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class BaseController<T>{
    protected TableLoadStrategy<T> tableLoadStrategy;
    protected JPBaseView baseView;
    protected String[] COLUMN_NAMES;

    public void loadDataTableAsync(CompletableFuture<List<T>> futureData, TableCallback callback) {
        futureData.thenAcceptAsync(dataList -> {
            Object[][] information = tableLoadStrategy.loadTableData(dataList);
            SwingUtilities.invokeLater(() -> baseView.panelList.makeTable(information, COLUMN_NAMES,callback));
        });
    }

    public void onClickNew(ActionEvent e) {this.switchTab((JButton) e.getSource());};
    public abstract void onClickSave(ActionEvent e);
    public abstract void onClickDelete(ActionEvent e);
    public abstract void onClickEdit(ActionEvent e);
    public abstract void onClickSearch(ActionEvent e);
    public abstract void resetControls();
    public  void onClickCancel(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de cancelar la operación?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }

    public void switchTab(JButton button){
        String command = button.getName();
        baseView.cardLayout.show(baseView.tabContent,command);
    }
}
