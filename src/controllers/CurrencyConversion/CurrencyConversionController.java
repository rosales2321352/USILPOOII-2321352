package controllers.CurrencyConversion;
import models.CurrencyConversion.CurrencyConversion;
import models.combobox.CurrencyComboBox;
import views.CurrencyConversion.CurrencyConversionView;
import views.core.combobox.CustomComboBox;
import views.core.combobox.CustomComboBoxModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CurrencyConversionController {

    final String[] COLUMN_NAMES = { "Id","Currency","Sale","Buy","Date", "Acciones" };
    private final CurrencyConversionView panel;
    private final String conversion="";

    private int conversion_id = 0;
    private int currency_id;

    //private  int currency_id=0;
    public CurrencyConversionController(CurrencyConversionView panel){
        this.panel = panel;
    }

    public void renderObjects(){
        this.panel.CurrencyConversionList.makeTableHeader(COLUMN_NAMES);
    }

    public void resetControls(){
        //this.currency_id=0;
        this.conversion_id=0;
        this.panel.CurrencyConversionEditor.txtcurrency.setSelectedIndex(0);
        this.panel.CurrencyConversionEditor.lblTitle.setText("Agregar tipo de cambio");
        this.panel.CurrencyConversionEditor.txtsale.setText("");
        this.panel.CurrencyConversionEditor.txtbuy.setText("");
    }
    public void loadDataTableAsync(String query){
        CompletableFuture<List<CurrencyConversion>> futureCurrencyConversion = CompletableFuture.supplyAsync(() -> {
            return new CurrencyConversion().getCurrencyConversions(query);
        });
        futureCurrencyConversion.thenAcceptAsync(unities -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Object[][] information = unities.stream()
                    .map(currencyConversion -> new Object[]{
                            String.valueOf(currencyConversion.getConversion_id()),
                            currencyConversion.getName(),
                            String.valueOf(currencyConversion.getSale()),
                            String.valueOf(currencyConversion.getBuy()),
                            dateFormat.format(currencyConversion.getDate())})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.CurrencyConversionList.makeTable(information, COLUMN_NAMES));
        });
    }


    /*public void loadDataTableAsync(String query){
        CompletableFuture<List<CurrencyConversion>> futureCurrencyConversion = CompletableFuture.supplyAsync(() -> {
            return new CurrencyConversion().getCurrencyConversions(query);
        });
        futureCurrencyConversion.thenAcceptAsync(unities -> {
            Object[][] information = unities.stream()
                    .map(currencyConversion -> new Object[]{
                            String.valueOf(currencyConversion.getConversion_id()),
                            String.valueOf(currencyConversion.getConversion_id()),
                            String.valueOf(currencyConversion.getSale()),
                            String.valueOf(currencyConversion.getBuy()),
                            String.valueOf(currencyConversion.getDate())})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.CurrencyConversionList.makeTable(information, COLUMN_NAMES));
        });
    }*/

    /*public boolean validate(){
        if(panel.CurrencyConversionEditor.txtcurrency.getText().trim().isEmpty()){
            return false;
        }
        if(panel.CurrencyConversionEditor.txtdate.getText().trim().isEmpty()){
            return false;}

        if(panel.CurrencyConversionEditor.txtsale.getText().trim().isEmpty()){
            return false;
        }

        if(panel.CurrencyConversionEditor.txtbuy.getText().trim().isEmpty()){
            return false;}
        return true;
    }*/
    public boolean validate() {
        //if (panel.CurrencyConversionEditor.txtcurrency.getText().trim().isEmpty()) {
          //  return false;
        //}


        if (panel.CurrencyConversionEditor.txtdate.getModel().getValue() == null) {
            return false;
        }

        if (panel.CurrencyConversionEditor.txtsale.getText().trim().isEmpty()) {
            return false;
        }

        if (panel.CurrencyConversionEditor.txtbuy.getText().trim().isEmpty()) {
            return false;
        }

        return true;
    }


    /*public int save(){
        CurrencyConversion currencyConversion  = new CurrencyConversion();
        currencyConversion.setCurrency_id(this.currency_id);
        //currencyConversion.setConversion_id(this.conversion_id);
        currencyConversion.setDate((Date)panel.CurrencyConversionEditor.txtdate.getModel().getValue());
        currencyConversion.setBuy(Double.parseDouble(panel.CurrencyConversionEditor.txtbuy.getText()));
        currencyConversion.setSale(Double.parseDouble(panel.CurrencyConversionEditor.txtsale.getText()));



        return currencyConversion.save();
    }*/
    public int save(){
        CurrencyComboBox currency = (CurrencyComboBox) panel.CurrencyConversionEditor.txtcurrency.getSelectedItem();
        CurrencyConversion currencyConversion  = new CurrencyConversion();
        currencyConversion.setCurrency_id((int) currency.getId());
        currencyConversion.setConversion_id(this.conversion_id);
        java.util.Date utilDate = (java.util.Date) panel.CurrencyConversionEditor.txtdate.getModel().getValue();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        currencyConversion.setDate(sqlDate);
        currencyConversion.setBuy(Double.parseDouble(panel.CurrencyConversionEditor.txtbuy.getText()));
        currencyConversion.setSale(Double.parseDouble(panel.CurrencyConversionEditor.txtsale.getText()));

        return currencyConversion.save();
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

    /*public void onClickBtnEdit(ActionEvent e, int currencyId){
        this.panel.CurrencyConversionEditor.lblTitle.setText("Editar Tipo de Cambio");
        CompletableFuture<CurrencyConversion> futureCurrencyConversion = CompletableFuture.supplyAsync(() -> new CurrencyConversion().getCurrencyConversion(currencyId));
        futureCurrencyConversion.thenAcceptAsync(currencyConversion -> SwingUtilities.invokeLater(() -> {
            this.currency_id=currencyConversion.getCurrency_id();
            this.panel.CurrencyConversionEditor.txtcurrency.setText(String.valueOf(currencyConversion.getCurrency_id()));
            this.panel.CurrencyConversionEditor.txtconversion.setText(String.valueOf(currencyConversion.getConversion_id()));
            this.panel.CurrencyConversionEditor.txtdate.getModel().setDate(currencyConversion.getDate().getYear(),currencyConversion.getDate().getMonth(),currencyConversion.getDate().getDay());
            this.panel.CurrencyConversionEditor.txtsale.setText(String.valueOf(currencyConversion.getBuy()));
            this.panel.CurrencyConversionEditor.txtbuy.setText(String.valueOf(currencyConversion.getSale()));

        }));
        this.switchTab((JButton) e.getSource());
    }*/
    public void onClickBtnEdit(ActionEvent e, int currencyId){
        this.panel.CurrencyConversionEditor.lblTitle.setText("Editar Tipo de Cambio");
        CompletableFuture<CurrencyConversion> futureCurrencyConversion = CompletableFuture.supplyAsync(() -> new CurrencyConversion().getCurrencyConversion(currencyId));
        futureCurrencyConversion.thenAcceptAsync(currencyConversion -> SwingUtilities.invokeLater(() -> {
            this.currency_id = currencyConversion.getCurrency_id();
            this.panel.CurrencyConversionEditor.txtcurrency.setSelectedItemById(currencyConversion.getCurrency_id());
            this.panel.CurrencyConversionEditor.txtconversion.setText(String.valueOf(currencyConversion.getConversion_id()));

            java.util.Date date = currencyConversion.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.panel.CurrencyConversionEditor.txtdate.getModel().setDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            this.panel.CurrencyConversionEditor.txtsale.setText(String.valueOf(currencyConversion.getBuy()));
            this.panel.CurrencyConversionEditor.txtbuy.setText(String.valueOf(currencyConversion.getSale()));

        }));
        this.switchTab((JButton) e.getSource());
    }


    public void onClickBtnSave(ActionEvent e){
        if(validate()){
            String message="";
            if (this.conversion_id !=0) {

                message ="¿Está seguro de agregar el tipo de cambio?" ;
            }
            else {
                message = "¿Está seguro de actualizar el tipo de cambio?";
            }
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = save();
                if(rowsAffected < 1){
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar el tipo de cambio.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Tipo de cambio guardado correctamente.",
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

    public void loadDataComboBox(){
        CompletableFuture<List<CurrencyComboBox>> futureCurency=CompletableFuture.supplyAsync(()->new CurrencyComboBox().getCurrenciesCmb());
        futureCurency.thenAcceptAsync(currency->SwingUtilities.invokeLater(()->{
            CustomComboBoxModel<CurrencyComboBox> model=new CustomComboBoxModel<>(currency);
            panel.CurrencyConversionEditor.txtcurrency.setModel(model);
            panel.CurrencyConversionEditor.txtcurrency.setSelectedIndex(0);
        }));
    }

}

