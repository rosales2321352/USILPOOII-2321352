package controllers.typeAffectation;

import models.combobox.TaxComboBox;
import models.product.Product;
import models.tax.Tax;
import models.typeAffectation.TypeAffectation;
import views.core.combobox.CustomComboBoxModel;
import views.typeAffectation.TypeAffectationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TypeAffectationController {
    private String typeAffectation_id = "";
    private int editor = 0;
    private TypeAffectationView panel;
    final String[] COLUMN_NAMES = { "Id", "Nombre", "Aciones"};
    public TypeAffectationController(TypeAffectationView panel){
        this.panel = panel;
    }
    public void renderObjects(){
        this.panel.typeAffectationList.makeTableHeader(COLUMN_NAMES);
        this.loadDataTableAsync("");
        this.loadDataComboBoxAsync();
    }
    public void loadDataTableAsync(String query){
        CompletableFuture<List<TypeAffectation>> futureTypeAffectation = CompletableFuture.supplyAsync(() -> {
            return new TypeAffectation().getTypesAffectationList(query);
        });
        futureTypeAffectation.thenAcceptAsync(unities -> {
            Object[][] information = unities.stream()
                    .map(typeAffectation -> new Object[]{
                            String.valueOf(typeAffectation.getType_affectation_id()),
                            typeAffectation.getName()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.typeAffectationList.makeTable(information,COLUMN_NAMES));
        });
    }
    public void loadDataComboBoxAsync(){
        CompletableFuture<List<TaxComboBox>> futureTax = CompletableFuture.supplyAsync(new TaxComboBox()::getTaxesCmb);
        futureTax.thenAcceptAsync(taxes -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<TaxComboBox> comboBoxModel = new CustomComboBoxModel<>(taxes);
            panel.typeAffectationEditor.cmbTax.setModel(comboBoxModel);
            panel.typeAffectationEditor.cmbTax.setSelectedIndex(0);
        }));
    }

    public void resetControls(){
        this.typeAffectation_id = "";
        this.editor = 0;
        panel.typeAffectationEditor.txtId.setText("");
        panel.typeAffectationEditor.txtId.setEditable(true);
        this.panel.typeAffectationEditor.txtName.setText("");
        this.panel.typeAffectationEditor.cmbTax.setSelectedIndex(0);
        this.panel.typeAffectationEditor.chkFree.setSelected(false);
        this.panel.typeAffectationEditor.chkOnerous.setSelected(false);
    }
    public int save(){
        TaxComboBox tax = (TaxComboBox) this.panel.typeAffectationEditor.cmbTax.getSelectedItem();
        TypeAffectation typeAffectation = new TypeAffectation();
        typeAffectation.setType_affectation_id(this.panel.typeAffectationEditor.txtId.getText());
        typeAffectation.setName(this.panel.typeAffectationEditor.txtName.getText());
        typeAffectation.setTax_id((int) tax.getId());
        typeAffectation.setOnerous(this.panel.typeAffectationEditor.chkOnerous.isSelected()?1:0);
        typeAffectation.setFree(this.panel.typeAffectationEditor.chkFree.isSelected()?1:0);
        return typeAffectation.save(editor);
    }
    public boolean validate(){
        if(this.panel.typeAffectationEditor.txtName.getText().trim().isEmpty()){
            return false;
        }
        if(this.panel.typeAffectationEditor.cmbTax.getSelectedItem() == null){
            return  false;
        }

        return true;
    }
    public void onClickBtnNew(ActionEvent e){ this.switchTab((JButton) e.getSource()); }
    public void onClickBtnCancel(ActionEvent e){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de cancelar la operación?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }
    public void onClickBtnSave(ActionEvent e){
        if(validate()){
            String message = this.typeAffectation_id.trim().isEmpty() ? "¿Está seguro de crear el tipo de afectación?" : "¿Está seguro de actualizar el tipo de afectación?";
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = this.save();
                if(rowsAffected < 1){
                    message = "Ha ocurrido un error en el proceso.";
                }else{
                    message = "Tipo de Afectación Guardado Correctamente.";
                }
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        "Atención", JOptionPane.INFORMATION_MESSAGE);
                this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }
    public void onClickBtnDelete(ActionEvent e, String id){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar el tipo de afectación?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            String message ="";
            if(new TypeAffectation().delete(id) < 1){
                message = "Ha ocurrido un error en el proceso";
            }else{
                message = "Tipo de Afectación Eliminado Correctamente.";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Atención", JOptionPane.INFORMATION_MESSAGE);
            this.loadDataTableAsync("");
        }
        this.typeAffectation_id="";
    }
    public void onClickBtnEdit(ActionEvent e, String id){
        CompletableFuture<TypeAffectation> futureTypeAffectation = CompletableFuture.supplyAsync(() -> new TypeAffectation().getTypeAffectation(id));
        futureTypeAffectation.thenAcceptAsync(typeAffectation -> {
            SwingUtilities.invokeLater(() -> {
                this.editor = 1;
                this.typeAffectation_id = typeAffectation.getType_affectation_id();
                panel.typeAffectationEditor.txtId.setText(typeAffectation.getType_affectation_id());
                panel.typeAffectationEditor.txtId.setEditable(false);
                panel.typeAffectationEditor.txtName.setText(typeAffectation.getName());
                panel.typeAffectationEditor.cmbTax.setSelectedItemById(typeAffectation.getType_affectation_id());
                panel.typeAffectationEditor.chkFree.setSelected(typeAffectation.getFree() == 1);
                panel.typeAffectationEditor.chkOnerous.setSelected(typeAffectation.getOnerous() == 1);
            });
        });
        this.switchTab((JButton) e.getSource());
    }
    public void onClickBtnSearch(ActionEvent e){
        String query = panel.typeAffectationList.txtQuery.getText();
        loadDataTableAsync(query);
    }
    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }
}
