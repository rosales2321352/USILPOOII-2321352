package controllers.Currency;


import models.Currency.Currency;
import models.ModelSQL;
import views.Currency.CurrencyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CurrencyController {

    final String[] COLUMN_NAMES = { "Id", "Nombre" , "Acciones" };
    private final CurrencyView panel;
    private String name="";

    private boolean edit=false;

    public CurrencyController(CurrencyView panel){
        this.panel = panel;
    }

    public void renderObjects(){
        this.panel.CurrencyList.makeTableHeader(COLUMN_NAMES);
    }

    public void resetControls(){
        this.edit=false;
        this.panel.CurrencyEditor.txtName.setText("");
        this.panel.CurrencyEditor.txtId.setText("");
        this.panel.CurrencyEditor.lblTitle.setText("Agregar moneda");
        this.panel.CurrencyEditor.txtId.setEditable(true);



    }

    public void loadDataTableAsync(String query){
        CompletableFuture<List<Currency>> futureCurrency = CompletableFuture.supplyAsync(() -> {
            return new Currency().getCurrencies(query);
        });
        futureCurrency.thenAcceptAsync(unities -> {
            Object[][] information = unities.stream()
                    .map(currency -> new Object[]{
                            String.valueOf(currency.getCurrency_id()),
                            currency.getName(),currency.getSymbol()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.CurrencyList.makeTable(information, COLUMN_NAMES));
        });
    }

    public boolean validate(){
        if(panel.CurrencyEditor.txtName.getText().trim().isEmpty()){
            return false;
        }
        if(panel.CurrencyEditor.txtId.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

  /*  public int save(){
        Currency currency  = new Currency();
        currency.setCurrency_id(panel.CurrencyEditor.txtId.getText());
        currency.setName(panel.CurrencyEditor.txtName.getText());

        return currency.save(edit);
    }*/

    public void onClickBtnCancel(ActionEvent e){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de cancelar la operación?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }

    /*public void onClickBtnEdit(ActionEvent e, String name){
        this.panel.CurrencyEditor.lblTitle.setText("Editar Moneda");
        CompletableFuture<Currency> futureCurrency = CompletableFuture.supplyAsync(() -> new Currency().getname(name));
        futureCurrency.thenAcceptAsync(Currency -> SwingUtilities.invokeLater(() -> {
            this.edit = true;
            panel.CurrencyEditor.txtId.setText(Currency.getname());
            panel.CurrencyEditor.txtId.setEditable(false);
            panel.CurrencyEditor.txtName.setText(Currency.getName());
        }));
        this.switchTab((JButton) e.getSource());
    }*/

    /*public void onClickBtnSave(ActionEvent e){
        if(validate()){
            String message="";
            if (this.name.trim().isEmpty()) {

                message ="¿Está seguro de crear la moneda?" ;
            }
            else {
                message = "¿Está seguro de actualizar la moneda?";
            }
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = save();
                if(rowsAffected < 1){
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar la moneda.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Moneda guardada correctamente.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }
                this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }*/

    public void onClickBtnSearch(ActionEvent e){
        String query = panel.CurrencyList.txtQuery.getText();
        loadDataTableAsync(query);
    }

  /*  public void onClickBtnDelete(ActionEvent e, String documents_id){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar la moneda?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            String message ="";
            if(new models.Currency.Currency().delete(name) < 1){
                message = "Ha ocurrido un error en el proceso";
            }else{
                message = "Moneda eliminado correctamente.";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Atención", JOptionPane.INFORMATION_MESSAGE);
            this.loadDataTableAsync("");
        }
        this.name = "";
    }*/
    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent, command);
    }
}
