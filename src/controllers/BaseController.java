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
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class BaseController<T>{
    protected TableLoadStrategy<T> tableLoadStrategy;
    protected JPBaseView baseView;
    protected Map<String,String> messages;
    protected String[] COLUMN_NAMES;
    protected int id;

    public void loadDataTableAsync(CompletableFuture<List<T>> futureData, TableCallback callback) {
        futureData.thenAcceptAsync(dataList -> {
            Object[][] information = tableLoadStrategy.loadTableData(dataList);
            SwingUtilities.invokeLater(() -> baseView.panelList.makeTable(information, COLUMN_NAMES,callback));
        });
    }
    public abstract boolean validate();
    public abstract int save();
    public abstract int delete();
    public void onClickNew(ActionEvent e) {this.switchTab((JButton) e.getSource());};
    public void onClickSave(ActionEvent e){
        if(validate()){
            String message = this.id == 0 ? messages.get("AddConfirm") : messages.get("EditConfirm");
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = save();
                if(rowsAffected < 1){
                    message = messages.get("SaveError");
                }else{
                    message = messages.get("SaveSuccess");
                }
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        "Atención", JOptionPane.INFORMATION_MESSAGE);
                //this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }
    public void onClickDelete(ActionEvent e){
        int response = JOptionPane.showConfirmDialog(null,
                messages.get("DeleteConfirm"),
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            String message ="";
            if(delete() < 1){
                message = messages.get("SaveError");
            }else{
                message =  messages.get("DeleteSuccess");
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Atención", JOptionPane.INFORMATION_MESSAGE);
        }
        this.id = 0;
    }

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
