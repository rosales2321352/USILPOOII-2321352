package controllers.customer;

import controllers.BaseController;
import controllers.strategy.table.classes.CustomerTableStrategy;
import models.combobox.DocumentTypeComboBox;
import models.customer.Customer;
import models.documents.DocumentType;
import views.JPBaseView;
import views.category.partials.JPCategoryAction;
import views.core.combobox.CustomComboBoxModel;
import views.core.table.ManageCellsActionButtons;
import views.customer.partials.JPCustomerAction;
import views.customer.partials.JPCustomerEditor;
import views.customer.partials.JPCustomerList;
import views.tax.partials.JPTaxAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomerController extends BaseController<Customer> {

    JPCustomerList customerList;
    JPCustomerEditor customerEditor;
    public CustomerController(JPBaseView baseView){
        this.baseView = baseView;
        COLUMN_NAMES = new String[]{ "Id","Tipo Documento","Documento", "Nombres y Apellidos","Correo","Acciones"};
        messages = new HashMap<>();
        messages.put("AddConfirm","¿Estás seguro de crear al cliente?");
        messages.put("EditConfirm","¿Estás seguro de editar al cliente?");
        messages.put("DeleteConfirm","¿Estás seguro de eliminar al cliente?");
        messages.put("SaveError","Algo salió mal, no se pudo completar la operación");
        messages.put("SaveSuccess","Cliente grabado correctamente");
        messages.put("DeleteSuccess","Cliente eliminado correctamente");
    }

    public void init(){
        customerList = (JPCustomerList) baseView.panelList;
        customerEditor = (JPCustomerEditor) baseView.panelEditor;
        customerList.makeTableHeader(COLUMN_NAMES,50);
        loadDataTableCustomerAsync("");
        loadDataComboBoxAsync();
    }

    public void loadDataTableCustomerAsync(String query){
        tableLoadStrategy = new CustomerTableStrategy();
        loadDataTableAsync(
                CompletableFuture.supplyAsync(() -> new Customer().getCustomerList(query)),
                (table) -> {
                    table.getColumnModel().getColumn(0).setMaxWidth(50);
                    table.getColumnModel().getColumn(0).setResizable(false);
                    table.getColumnModel().getColumn(1).setMaxWidth(200);
                    table.getColumnModel().getColumn(1).setResizable(false);
                    table.getColumnModel().getColumn(2).setMaxWidth(80);
                    table.getColumnModel().getColumn(2).setResizable(false);
                    new ManageCellsActionButtons(table, 5, new JPCustomerAction(this), new JPCustomerAction(this));
                    table.getColumnModel().getColumn(5).setMaxWidth(100);
                    table.getColumnModel().getColumn(5).setMinWidth(100);
                }
        );
    }
    public void loadDataComboBoxAsync(){
        CompletableFuture<List<DocumentTypeComboBox>> documentTypeFuture = CompletableFuture.supplyAsync(() -> new DocumentTypeComboBox().getDocumentTypeCmb(""));
        documentTypeFuture.thenAcceptAsync(documentType -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<DocumentTypeComboBox> model = new CustomComboBoxModel<>(documentType);
            customerEditor.cmbTypeDocument.setModel(model);
            customerEditor.cmbTypeDocument.setSelectedIndex(0);
        }));
    }
    @Override
    public void reloadDataTableAsync() {
        loadDataTableCustomerAsync("");
    }

    @Override
    public boolean validate() {
        if(customerEditor.txtDocument.getText().trim().isEmpty()) return false;
        if(customerEditor.txtFullName.getText().trim().isEmpty()) return false;
        if(customerEditor.txtAddress.getText().trim().isEmpty()) return false;
        if(customerEditor.txtTelephoneNumber.getText().trim().isEmpty()) return false;
        return !customerEditor.txtEmail.getText().trim().isEmpty();
    }

    @Override
    public int save() {
        DocumentTypeComboBox documentType = (DocumentTypeComboBox) customerEditor.cmbTypeDocument.getSelectedItem();
        Customer customer = new Customer();
        customer.setCustomer_id(id);
        customer.setDocument_type_id((int) documentType.getId());
        customer.setDocument(customerEditor.txtDocument.getText());
        customer.setFull_name(customerEditor.txtFullName.getText());
        customer.setAddress(customerEditor.txtAddress.getText());
        customer.setEmail(customerEditor.txtEmail.getText());
        customer.setTelephone_number(customerEditor.txtTelephoneNumber.getText());
        customer.setReference(customerEditor.txtReference.getText());
        return customer.save();
    }

    @Override
    public int delete() {
        return new Customer().delete(id);
    }

    @Override
    public void resetControls() {
        customerEditor.lblTitle.setText("Agregar Nuevo Cliente");
        customerEditor.cmbTypeDocument.setSelectedIndex(0);
        customerEditor.txtDocument.setText("");
        customerEditor.txtFullName.setText("");
        customerEditor.txtAddress.setText("");
        customerEditor.txtEmail.setText("");
        customerEditor.txtTelephoneNumber.setText("");
        customerEditor.txtReference.setText("");
    }

    public void onClickEdit(ActionEvent e, int id){
        customerEditor.lblTitle.setText("Edición de Cliente");
        CompletableFuture<Customer> customerFuture = CompletableFuture.supplyAsync(() -> {
           return new Customer().getCustomer(id);
        });
        customerFuture.thenAcceptAsync(customer -> SwingUtilities.invokeLater(() -> {
            this.id = id;
            customerEditor.cmbTypeDocument.setSelectedItemById(customer.getCustomer_id());
            customerEditor.txtDocument.setText(customer.getDocument());
            customerEditor.txtFullName.setText(customer.getFull_name());
            customerEditor.txtAddress.setText(customer.getAddress());
            customerEditor.txtEmail.setText(customer.getEmail());
            customerEditor.txtTelephoneNumber.setText(customer.getTelephone_number());
            customerEditor.txtReference.setText(customer.getReference());
        }));
        this.switchTab((JButton) e.getSource());
    }

    public void onClickSearch(ActionEvent e){
        String query = customerList.txtQuery.getText();
        if(query != null){
            loadDataTableCustomerAsync(query);
        }
    }
}
