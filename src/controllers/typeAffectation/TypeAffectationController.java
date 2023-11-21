package controllers.typeAffectation;

import controllers.BaseController;
import controllers.strategy.table.classes.TypeAffectationTableLoadStrategy;
import models.combobox.TaxComboBox;
import models.typeAffectation.TypeAffectation;
import views.JPBaseView;
import views.core.combobox.CustomComboBoxModel;
import views.core.table.ManageCellsActionButtons;
import views.typeAffectation.partials.JPTypeAffectationAction;
import views.typeAffectation.partials.JPTypeAffectationEditor;
import views.typeAffectation.partials.JPTypeAffectationList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TypeAffectationController extends BaseController<TypeAffectation> {

    JPTypeAffectationList typeAffectationList;
    JPTypeAffectationEditor typeAffectationEditor;
    public TypeAffectationController(JPBaseView baseView){
        this.baseView=baseView;
        COLUMN_NAMES= new String[]{"Id", "Nombre", "Aciones"};
        messages = new HashMap<>();
        messages.put("AddConfirm","¿Estas seguro de agregar el tipo de afectacion?");
        messages.put("EditConfirm","¿Estas seguro de editar el tipo de afectacion?");
        messages.put("DeleteConfirm","¿Estas seguro de eliminar el tipo de afectacion?");
        messages.put("SaveError","Se encontro un error");
        messages.put("SaveSuccess","Se guardo el tipo de afectacion correctamente");
        messages.put("DeleteSuccess","Se elimino el tipo de afectacion correctamente");
    }
    public void init(){
        typeAffectationEditor = (JPTypeAffectationEditor) baseView.panelEditor;
        typeAffectationList = (JPTypeAffectationList) baseView.panelList;
        typeAffectationList.makeTableHeader(COLUMN_NAMES,23);
        loadDataTableTypeAffectationAsync("");
    }
    public void loadDataTableTypeAffectationAsync(String query){
        tableLoadStrategy = new TypeAffectationTableLoadStrategy();
        loadDataTableAsync(
            CompletableFuture.supplyAsync(() -> new TypeAffectation().getTypesAffectationList(query)),
            (table) -> {
                table.setGridColor(new java.awt.Color(216,216,216));
                table.getColumnModel().getColumn(0).setMaxWidth(50);
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(1).setResizable(false);
                new ManageCellsActionButtons(table, 2, new JPTypeAffectationAction(this), new JPTypeAffectationAction(this));

                table.getColumnModel().getColumn(2).setMaxWidth(100);
                table.getColumnModel().getColumn(2).setMinWidth(100);

            }
        );
    }
    public void resetControls(){
        id = 0;
        typeAffectationEditor.txtId.setText("");
        typeAffectationEditor.txtId.setEditable(true);
        typeAffectationEditor.txtName.setText("");
        //typeAffectationEditor.cmbTax.setSelectedIndex(0);
        typeAffectationEditor.chkFree.setSelected(false);
        typeAffectationEditor.chkOnerous.setSelected(false);
    }
    public boolean validate(){
        if(typeAffectationEditor.txtName.getText().trim().isEmpty()){
            return false;
        }
        return typeAffectationEditor.cmbTax.getSelectedItem() != null;
    }
    public void reloadDataTableAsync(){loadDataTableTypeAffectationAsync("");}
    public int save(){
        TaxComboBox tax = (TaxComboBox) typeAffectationEditor.cmbTax.getSelectedItem();
        TypeAffectation typeAffectation = new TypeAffectation();
        typeAffectation.setType_affectation_id(typeAffectationEditor.txtId.getText());
        typeAffectation.setName(typeAffectationEditor.txtName.getText());
        typeAffectation.setTax_id((int) tax.getId());
        typeAffectation.setOnerous(typeAffectationEditor.chkOnerous.isSelected()?1:0);
        typeAffectation.setFree(typeAffectationEditor.chkFree.isSelected()?1:0);
        return typeAffectation.save(0);
    }
    public int delete(){
        return new TypeAffectation().delete("");
    }
    public void onClickEdit(ActionEvent e, int id){
        CompletableFuture<List<TaxComboBox>> futureTax = CompletableFuture.supplyAsync(new TaxComboBox()::getTaxesCmb);
        futureTax.thenAcceptAsync(taxes -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<TaxComboBox> comboBoxModel = new CustomComboBoxModel<>(taxes);
            typeAffectationEditor.cmbTax.setModel(comboBoxModel);
            typeAffectationEditor.cmbTax.setSelectedIndex(0);
        }));
        CompletableFuture<TypeAffectation> futureTypeAffectation = CompletableFuture.supplyAsync(() -> new TypeAffectation().getTypeAffectation(""));
        futureTypeAffectation.thenAcceptAsync(typeAffectation -> {
            SwingUtilities.invokeLater(() -> {
                //this.id = typeAffectation.getType_affectation_id();
                typeAffectationEditor.txtId.setText(typeAffectation.getType_affectation_id());
                typeAffectationEditor.txtId.setEditable(false);
                typeAffectationEditor.txtName.setText(typeAffectation.getName());
                typeAffectationEditor.cmbTax.setSelectedItemById(typeAffectation.getType_affectation_id());
                typeAffectationEditor.chkFree.setSelected(typeAffectation.getFree() == 1);
                typeAffectationEditor.chkOnerous.setSelected(typeAffectation.getOnerous() == 1);
            });
        });
        switchTab((JButton) e.getSource());
    }
    public void onClickBtnSearch(ActionEvent e){
        String query = typeAffectationList.txtQuery.getText();
        loadDataTableTypeAffectationAsync(query);
    }
}
