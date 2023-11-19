package controllers.CurrencyConversion;
import models.CurrencyConversion.CurrencyConversion;
import views.CurrencyConversion.partials.CurrencyConversionEditor;
import models.ModelSQL;
import views.CurrencyConversion.CurrencyConversionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CurrencyConversionController {

    final String[] COLUMN_NAMES = { "Id","Currency","Conversion","Sale","Buy", "Acciones" };
    private final CurrencyConversionView panel;
    private String conversion="";

    private int currency_id = 0;

    public CurrencyConversionController(CurrencyConversionView panel){
        this.panel = panel;
    }

    public void renderObjects(){
        this.panel.CurrencyConversionList.makeTableHeader(COLUMN_NAMES);
    }

    public void resetControls(){
        this.currency_id=0;
        this.panel.CurrencyConversionEditor.txtcurrency.setText("");
        this.panel.CurrencyConversionEditor.lblTitle.setText("Agregar tipo de cambio");
        this.panel.CurrencyConversionEditor.txtconversion.setText("");
        this.panel.CurrencyConversionEditor.txtsale.setText("");
        this.panel.CurrencyConversionEditor.txtbuy.setText("");
    }

    public void loadDataTableAsync(String query){
        CompletableFuture<List<CurrencyConversion>> futureCurrencyConversion = CompletableFuture.supplyAsync(() -> {
            return new CurrencyConversion().getCurrencyConversions(query);
        });
        futureCurrencyConversion.thenAcceptAsync(unities -> {
            Object[][] information = unities.stream()
                    .map(currencyConversion -> new Object[]{
                            String.valueOf(currencyConversion.getCurrency_id()),
                            currencyConversion.getConversion_id(),currencyConversion.getCurrency_id()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.CurrencyConversionList.makeTable(information, COLUMN_NAMES));
        });
    }

    public boolean validate(){
        if(panel.CurrencyConversionEditor.txtcurrency.getText().trim().isEmpty()){
            return false;
        }
        if(panel.CurrencyConversionEditor.txtconversion.getText().trim().isEmpty()){
            return false;}

        if(panel.CurrencyConversionEditor.txtsale.getText().trim().isEmpty()){
            return false;
        }

        if(panel.CurrencyConversionEditor.txtbuy.getText().trim().isEmpty()){
            return false;}
        return true;
    }

    public int save(){
        Currency currency  = new Currency();
        currency.setCurrency_id(this.currency_id);
        currency.setName(panel.CurrencyEditor.txtName.getText());
        currency.setSymbol(panel.CurrencyEditor.txtsymbol.getText());
        currency.setIso_code(panel.CurrencyEditor.txtiso_code.getText());
        currency.setLocation(panel.CurrencyEditor.txtlocation.getText());
        currency.setPredeterminanted(panel.CurrencyEditor.txtpredetermined.isSelected()?1:0);



        return currency.save();
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

    public void onClickBtnEdit(ActionEvent e, int currencyId){
        this.panel.CurrencyEditor.lblTitle.setText("Editar Moneda");
        CompletableFuture<Currency> futureCurrency = CompletableFuture.supplyAsync(() -> new Currency().getCurrency(currencyId));
        futureCurrency.thenAcceptAsync(currency -> SwingUtilities.invokeLater(() -> {
            this.currency_id=currency.getCurrency_id();
            this.panel.CurrencyEditor.txtName.setText(currency.getName());
            this.panel.CurrencyEditor.txtsymbol.setText(currency.getSymbol());
            this.panel.CurrencyEditor.txtlocation.setText(currency.getLocation());
            this.panel.CurrencyEditor.txtpredetermined.setSelected(currency.getPredeterminanted()==1);
            this.panel.CurrencyEditor.txtiso_code.setText(currency.getIso_code());

        }));
        this.switchTab((JButton) e.getSource());
    }

    public void onClickBtnSave(ActionEvent e){
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
    }

    public void onClickBtnSearch(ActionEvent e){
        String query = panel.CurrencyConversionList.txtQuery.getText();
        loadDataTableAsync(query);
    }

    /*public void onClickBtnDelete(ActionEvent e, int currency_id){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar la moneda?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            String message ="";
            if(new models.Currency.Currency().delete(currency_id) < 1){
                message = "Ha ocurrido un error en el proceso";
            }else{
                message = "Moneda eliminada correctamente.";
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
