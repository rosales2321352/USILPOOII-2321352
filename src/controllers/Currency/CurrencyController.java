package controllers.Currency;


import models.Currency.Currency;
import views.Currency.CurrencyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CurrencyController {

    final String[] COLUMN_NAMES = { "Id","Name", "Symbol" , "Acciones" };
    private final CurrencyView panel;
    private String name="";



    private int currency_id = 0;

    public CurrencyController(CurrencyView panel){
        this.panel = panel;
    }

    public void renderObjects(){
        this.panel.CurrencyList.makeTableHeader(COLUMN_NAMES);
    }

    public void resetControls(){
        this.currency_id=0;
        this.panel.CurrencyEditor.txtName.setText("");
        this.panel.CurrencyEditor.txtsymbol.setText("");
        this.panel.CurrencyEditor.lblTitle.setText("Agregar moneda");
        this.panel.CurrencyEditor.txtsymbol.setEditable(true);
        this.panel.CurrencyEditor.txtlocation.setText("");
        this.panel.CurrencyEditor.txtpredetermined.setSelected(false);
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
        if(panel.CurrencyEditor.txtsymbol.getText().trim().isEmpty()){
            return false;}

        if(panel.CurrencyEditor.txtiso_code.getText().trim().isEmpty()){
                return false;
        }

        if(panel.CurrencyEditor.txtlocation.getText().trim().isEmpty()){
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
        String query = panel.CurrencyList.txtQuery.getText();
        loadDataTableAsync(query);
    }

    public void onClickBtnDelete(ActionEvent e, int currency_id){
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
    }
    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
    }

    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent, command);
    }
}
