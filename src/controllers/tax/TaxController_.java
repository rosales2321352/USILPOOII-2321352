package controllers.tax;

import controllers.BaseController;
import controllers.strategy.table.classes.TaxTableLoadStrategy;
import models.tax.Tax;
import views.JPBaseView;
import views.core.table.ManageCellsActionButtons;
import views.tax.JPTaxAction;
import views.tax.partials.TaxAction;

import java.awt.event.ActionEvent;
import java.util.concurrent.CompletableFuture;

public class TaxController_  extends BaseController<Tax>{

    public TaxController_(JPBaseView baseView){
        this.baseView = baseView;
        this.COLUMN_NAMES = new String[]{ "Id", "Nombre", "%","Acciones"};
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

    @Override
    public void onClickSave(ActionEvent e) {
        System.out.println("Hola");
    }

    @Override
    public void onClickDelete(ActionEvent e) {

    }

    @Override
    public void onClickEdit(ActionEvent e) {

    }

    @Override
    public void onClickSearch(ActionEvent e) {

    }

    @Override
    public void resetControls() {
        /*this.panel.taxEditor.txtName.setText("");
        this.panel.taxEditor.numPercentaje.setValue(0);
        this.panel.taxEditor.lblTitle.setText("Agregar Impuesto");*/
    }
}
