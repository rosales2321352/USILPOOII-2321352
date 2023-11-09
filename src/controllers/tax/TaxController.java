package controllers.tax;

import models.tax.Tax;
import views.tax.TaxView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;
public class TaxController {
    private int tax_id;
    private TaxView panel;
    final String[] COLUMN_NAMES = { "Id", "Nombre", "%", "Aciones"};
    public TaxController (TaxView panel){
        this.panel = panel;
    }
    public void renderObjects(){
        this.panel.taxList.makeTableHeader(COLUMN_NAMES);
        this.loadDataTableAsync("");
    }
    public void loadDataTableAsync(String query){
        CompletableFuture<List<Tax>> futureTax = CompletableFuture.supplyAsync(() -> {
            return new Tax().getTaxes(query);
        });
        futureTax.thenAcceptAsync(taxes -> {
            Object[][] information = taxes.stream()
                .map(tax -> new Object[]{
                    String.valueOf(tax.getTax_id()),
                    tax.getName() ,
                    String.valueOf(tax.getPercentage())})
                .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.taxList.makeTable(information,COLUMN_NAMES));
        });
    }

    public void resetControls(){
        this.panel.taxEditor.txtName.setText("");
        this.panel.taxEditor.numPercentaje.setValue(0);
        this.panel.taxEditor.lblTitle.setText("Agregar Impuesto");
    }
    public boolean validate(){
        if(this.panel.taxEditor.txtName.getText().trim().isEmpty()){
            return false;
        }
        if((Double)this.panel.taxEditor.numPercentaje.getValue() < 0){
            return false;
        }
        return true;
    }
    public int save(){
        Tax tax = new Tax();
        tax.setTax_id(tax_id);
        tax.setName(this.panel.taxEditor.txtName.getText());
        tax.setPercentage((Double) this.panel.taxEditor.numPercentaje.getValue());
        return tax.save();
    }
    public void onClickBtnNew(ActionEvent e){ this.switchTab((JButton) e.getSource()); }
    public void onClickBtnSave(ActionEvent e){
        if(validate()){
            String message = this.tax_id == 0 ? "¿Está seguro de crear la unidad?" : "¿Está seguro de actualizar la unidad?";
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = save();
                if(rowsAffected < 1){
                    message = "Ha ocurrido un error en el proceso.";
                }else{
                    message = "Impuesto guardado correctamente.";
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
    public void onClickBtnEdit(ActionEvent e, int tax_id){
        this.panel.taxEditor.lblTitle.setText("Editar Impuesto");
        CompletableFuture<Tax> futureTax = CompletableFuture.supplyAsync(() -> new Tax().getTax(tax_id));
        futureTax.thenAcceptAsync(tax -> SwingUtilities.invokeLater(() -> {
            this.tax_id = tax.getTax_id();
            panel.taxEditor.txtName.setText(tax.getName());
            panel.taxEditor.numPercentaje.setValue(tax.getPercentage());
        }));
        this.switchTab((JButton) e.getSource());
    }
    public void onClickBtnCancel(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de cancelar la operación?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION) {
            this.resetControls();
            this.switchTab((JButton) e.getSource());
        }
    }
    public void onClickBtnDelete(ActionEvent e,int tax_id){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar el impuesto?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            String message ="";
            if(new Tax().delete(tax_id) < 1){
                message = "Ha ocurrido un error en el proceso";
            }else{
                message = "Impuesto eliminado correctamente.";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Atención", JOptionPane.INFORMATION_MESSAGE);
            this.loadDataTableAsync("");
        }
        this.tax_id = 0;
    }
    public void onClickBtnSearch(ActionEvent e){
        String query = panel.taxList.txtQuery.getText();
        loadDataTableAsync(query);
    }
    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }
}
