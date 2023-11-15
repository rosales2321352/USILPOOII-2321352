package controllers.tax;

import controllers.BaseController;
import controllers.strategy.table.classes.TaxTableLoadStrategy;
import models.tax.Tax;
import views.JPBaseView;
import views.core.table.ManageCellsActionButtons;
import views.tax.partials.JPTaxAction;
import views.tax.partials.JPTaxEditor;
import views.tax.partials.JPTaxList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class TaxController extends BaseController<Tax>{
    JPTaxEditor taxEditor;
    JPTaxList taxList;
    public TaxController(JPBaseView baseView){
        this.baseView = baseView;
        COLUMN_NAMES = new String[]{ "Id", "Nombre", "%","Acciones"};
        messages = new HashMap<>();
        messages.put("AddConfirm","");
        messages.put("EditConfirm","");
        messages.put("DeleteConfirm","");
        messages.put("SaveError","");
        messages.put("SaveSuccess","");
        messages.put("DeleteSuccess","");
    }
    public void init(){
        taxEditor = (JPTaxEditor) baseView.panelEditor;
        taxList = (JPTaxList) baseView.panelList;
        taxList.makeTableHeader(COLUMN_NAMES,23);
        loadDataTableTaxAsync("");
    }
    public void loadDataTableTaxAsync(String query){
        tableLoadStrategy = new TaxTableLoadStrategy();
        loadDataTableAsync(
            CompletableFuture.supplyAsync(() -> new Tax().getTaxes(query)),
            (table) -> {
                table.getColumnModel().getColumn(0).setMaxWidth(50);
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(1).setResizable(false);
                table.getColumnModel().getColumn(2).setMaxWidth(80);
                table.getColumnModel().getColumn(2).setResizable(false);
                new ManageCellsActionButtons(table, 3, new JPTaxAction(this), new JPTaxAction(this));
                table.getColumnModel().getColumn(3).setMaxWidth(100);
                table.getColumnModel().getColumn(3).setMinWidth(100);
        });
    }
    public void reloadDataTableAsync(){
        loadDataTableTaxAsync("");
    }
    public int save(){
        Tax tax = new Tax();
        tax.setTax_id(id);
        tax.setName(taxEditor.txtName.getText());
        tax.setPercentage((Double) taxEditor.spnPercentage.getValue());
        return tax.save();
    }

    public int delete(){
        return new Tax().delete(id);
    }

    public boolean validate(){
        return !taxEditor.txtName.getText().trim().isEmpty();
    }

    @Override
    public void resetControls() {
        taxEditor.txtName.setText("");
        taxEditor.spnPercentage.setValue(0);
        baseView.panelEditor.lblTitle.setText("Agregar Impuesto");
    }
    public void onClickEdit(ActionEvent e, int tax_id){
        taxEditor.lblTitle.setText("Editar Impuesto");
        CompletableFuture<Tax> futureTax = CompletableFuture.supplyAsync(() -> new Tax().getTax(tax_id));
        futureTax.thenAcceptAsync(tax -> SwingUtilities.invokeLater(() -> {
            id = tax.getTax_id();
            taxEditor.txtName.setText(tax.getName());
            taxEditor.spnPercentage.setValue(tax.getPercentage());
        }));
        switchTab((JButton) e.getSource());
    }
    public void onClickSearch(ActionEvent e){
        String query = taxList.txtQuery.getText();
        loadDataTableTaxAsync(query);
    }
}
