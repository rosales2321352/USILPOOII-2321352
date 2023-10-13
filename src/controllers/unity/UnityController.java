package controllers.unity;

import controllers.BaseController;
import controllers.strategy.table.classes.UnityTableLoadStrategy;
import models.unity.Unity;
import views.JPBaseView;
import views.core.table.ManageCellsActionButtons;
import views.unity.partials.JPUnityAction;
import views.unity.partials.JPUnityEditor;
import views.unity.partials.JPUnityList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class UnityController extends BaseController<Unity> {
    JPUnityEditor unityEditor;
    JPUnityList unityList;
    public UnityController(JPBaseView baseView){
        this.baseView=baseView;
        COLUMN_NAMES= new String[]{"Id", "Nombre", "Símbolo", "Aciones"};
        messages = new HashMap<>();
        messages.put("AddConfirm","");
        messages.put("EditConfirm","");
        messages.put("DeleteConfirm","");
        messages.put("SaveError","");
        messages.put("SaveSuccess","");
        messages.put("DeleteSuccess","");
    }
    public void init(){
        unityEditor = (JPUnityEditor) baseView.panelEditor;
        unityList = (JPUnityList) baseView.panelList;
        unityList.makeTableHeader(COLUMN_NAMES,23);
        loadDataTableUnityAsync("");
    }
    public void loadDataTableUnityAsync(String query){
        tableLoadStrategy = new UnityTableLoadStrategy();
        loadDataTableAsync(
            CompletableFuture.supplyAsync(() -> new Unity().getUnits(query)),
            (table) -> {
                table.getColumnModel().getColumn(0).setMaxWidth(50);
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(1).setResizable(false);
                table.getColumnModel().getColumn(2).setMaxWidth(80);
                table.getColumnModel().getColumn(2).setResizable(false);
                new ManageCellsActionButtons(table, 3, new JPUnityAction(this), new JPUnityAction(this));
                table.getColumnModel().getColumn(3).setMaxWidth(100);
                table.getColumnModel().getColumn(3).setMinWidth(100);
            }
        );
    }
    public void reloadDataTableAsync(){loadDataTableUnityAsync("");}
    public int save(){
        Unity unity  = new Unity();
        unity.setUnityId(id);
        unity.setName(unityEditor.txtName.getText());
        unity.setSymbol(unityEditor.txtSymbol.getText());
        return unity.save();
    }
    public int delete(){ return new Unity().delete(id);}
    public boolean validate(){
        if(unityEditor.txtName.getText().trim().isEmpty()){
            return false;
        }
        return !unityEditor.txtSymbol.getText().trim().isEmpty();
    }

    @Override
    public void resetControls() {
        id = 0;
        unityEditor.txtName.setText("");
        unityEditor.txtSymbol.setText("");
    }
    public void onClickEdit(ActionEvent e, int unity_id){
        unityEditor.lblTitle.setText("Editar Unidad de Medida");
        CompletableFuture<Unity> futureUnity = CompletableFuture.supplyAsync(() -> new Unity().getUnit(unity_id));
        futureUnity.thenAcceptAsync(unity -> SwingUtilities.invokeLater(() -> {
            id = unity.getUnityId();
            unityEditor.txtName.setText(unity.getName());
            unityEditor.txtSymbol.setText(unity.getSymbol());
        }));
        this.switchTab((JButton) e.getSource());
    }
    public void onClickSearch(ActionEvent e){
        String query = unityList.txtQuery.getText();
        loadDataTableUnityAsync(query);
    }
}
