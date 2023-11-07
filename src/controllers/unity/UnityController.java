package controllers.unity;

import models.product.Product;
import models.typeAffectation.TypeAffectation;
import models.unity.Unity;
import views.unity.UnityView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UnityController {

    final String[] COLUMN_NAMES = { "Id", "Nombre", "Símbolo", "Aciones"};
    private UnityView panel;
    private int unity_id = 0;
    public UnityController(UnityView panel){
        this.panel = panel;
    }
    public void renderObjects(){
        this.panel.unityList.makeTableHeader(COLUMN_NAMES);
    }
    public void resetControls(){
        this.unity_id = 0;
        this.panel.unityEditor.txtName.setText("");
        this.panel.unityEditor.txtSymbol.setText("");
    }
    public void loadDataTableAsync(String query){
        CompletableFuture<List<Unity>> futureUnity = CompletableFuture.supplyAsync(() -> {
            return new Unity().getUnits(query);
        });
        futureUnity.thenAcceptAsync(unities -> {
            Object[][] information = unities.stream()
                    .map(unity -> new Object[]{
                            String.valueOf(unity.getUnityId()),
                            unity.getName() ,
                            unity.getSymbol()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.unityList.makeTable(information,COLUMN_NAMES));
        });
    }
    public boolean validate(){
        if(panel.unityEditor.txtName.getText().trim().isEmpty()){
            return false;
        }
        if(panel.unityEditor.txtSymbol.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }
    public int save(){
        Unity unity  = new Unity();
        unity.setUnityId(unity_id);
        unity.setName(panel.unityEditor.txtName.getText());
        unity.setSymbol(panel.unityEditor.txtSymbol.getText());

        return unity.save();
    }

    public void onClickBtnCancel(ActionEvent e){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de cancelar la operación?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }
    public void onClickBtnEdit(ActionEvent e, int unity_id){
        this.panel.unityEditor.lblTitle.setText("Editar Unidad de Medida");
        CompletableFuture<Unity> futureUnity = CompletableFuture.supplyAsync(() -> new Unity().getUnit(unity_id));
        futureUnity.thenAcceptAsync(unity -> SwingUtilities.invokeLater(() -> {
            this.unity_id = unity.getUnityId();
            panel.unityEditor.txtName.setText(unity.getName());
            panel.unityEditor.txtSymbol.setText(unity.getSymbol());
        }));
        this.switchTab((JButton) e.getSource());
    }
    public void onClickBtnSave(ActionEvent e){
        if(validate()){
            String message = this.unity_id == 0 ? "¿Está seguro de crear la unidad?" : "¿Está seguro de actualizar la unidad?";
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = save();
                if(rowsAffected < 1){
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar la unidad.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Unidad guardado correctamente.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }
                this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }
    public void onClickBtnSearch(ActionEvent e){
        String query = panel.unityList.txtQuery.getText();
        loadDataTableAsync(query);
    }
    public void onClickBtnDelete(ActionEvent e, int unity_id){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar la unidad de medida?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            String message ="";
            if(new Unity().delete(unity_id) < 1){
                message = "Ha ocurrido un error en el proceso";
            }else{
                message = "Producto eliminado correctamente.";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Atención", JOptionPane.INFORMATION_MESSAGE);
            this.loadDataTableAsync("");
        }
        this.unity_id = 0;
    }
    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }
    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }
}
