package controllers.tax;

import controllers.BaseController;
import controllers.strategy.table.classes.TaxTableLoadStrategy;
import models.tax.Tax;
import views.JPBaseView;
import views.core.table.ManageCellsActionButtons;
import views.tax.JPTaxAction;
import views.tax.JPTaxEditor;
import views.tax.JPTaxList;
import views.tax.partials.TaxAction;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class TaxController_  extends BaseController<Tax>{
    JPTaxEditor taxEditor;
    JPTaxList taxList;
    public TaxController_(JPBaseView baseView){
        this.baseView = baseView;
        this.taxEditor = (JPTaxEditor) this.baseView.panelEditor;
        this.taxList = (JPTaxList) this.baseView.panelList;
        this.COLUMN_NAMES = new String[]{ "Id", "Nombre", "%","Acciones"};
        this.messages = new HashMap<>();
        this.messages.put("AddConfirm","");
        this.messages.put("EditConfirm","");
        this.messages.put("DeleteConfirm","");
        this.messages.put("SaveError","");
        this.messages.put("SaveSuccess","");
        this.messages.put("DeleteSuccess","");
    }
    public void loadDataTableTaxAsync(String query){
        this.tableLoadStrategy = new TaxTableLoadStrategy();
        this.loadDataTableAsync(
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
    public int save(){
        Tax tax = new Tax();
        tax.setTax_id(this.id);
        tax.setName(this.taxEditor.txtName.getText());
        tax.setPercentage((Double) this.taxEditor.spnPercentage.getValue());
        return tax.save();
    }

    public int delete(){
        return new Tax().delete(this.id);
    }

    public boolean validate(){
        JPTaxEditor taxEditor = (JPTaxEditor) this.baseView.panelEditor;
        if(taxEditor.txtName.getText().trim().isEmpty()){
            return false;
        }

        return true;
    }

    @Override
    public void resetControls() {
        this.taxEditor.txtName.setText("");
        this.taxEditor.spnPercentage.setValue(0);
        this.baseView.panelEditor.lblTitle.setText("Agregar Impuesto");
    }
}
