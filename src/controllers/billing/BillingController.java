package controllers.billing;

import controllers.BaseController;
import controllers.observer.*;
import controllers.observer.MutableObject;
import controllers.strategy.table.classes.BillingTableStrategy;
import models.billing.Bill;
import models.billing.BillDetail;
import models.combobox.EmployeeComboBox;
import models.product.Product;
import views.JPBaseView;
import views.billing.partials.JPBillingEditor;
import views.billing.partials.JPBillingList;
import views.core.combobox.CustomComboBoxModel;
import views.core.table.ManageGeneralCells;
import views.core.table.ManageTableHeader;
import views.core.table.TableModel;
import views.product.partials.JPProductSelector;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BillingController extends BaseController<Bill> {

    JPBillingEditor billingEditor;
    JPBillingList billingList;
    String[] COLUMN_EDITOR;
    Bill bill;
    List<BillDetail> billDetails;
    ISubject mutableObject;

    public BillingController(JPBaseView baseView){
        this.baseView = baseView;
        COLUMN_NAMES = new String[]{ "Id", "Nombre", "%","Acciones"};
        COLUMN_EDITOR = new String[]{ "Producto","Cantidad","Precio","Total","Acciones" };
        bill = new Bill();
        billDetails = new ArrayList<>();
        messages = new HashMap<>();
        messages.put("AddConfirm","");
        messages.put("EditConfirm","");
        messages.put("DeleteConfirm","");
        messages.put("SaveError","");
        messages.put("SaveSuccess","");
        messages.put("DeleteSuccess","");
        initObserver();
    }

    public void initObserver(){
        mutableObject = new MutableObject("Id del Producto");
        IObserver observer1 = new ProductObserver(mutableObject, this);
        mutableObject.subscribeObserver(observer1);
    }

    public void changeProducts(){
        int product_id = ((IMutableObject) mutableObject).getValue();
        CompletableFuture<Product> futureProduct = CompletableFuture.supplyAsync(() -> new Product().getProduct(product_id));
        futureProduct.thenAcceptAsync(product -> SwingUtilities.invokeLater(() -> {
            BillDetail bd = new BillDetail();
            bd.setProduct_id(product.getProductId());
            bd.setProduct_name(product.getName());
            bd.setQuantity(0);
            bd.setUnity_price(0);
            bd.setTotal_price(0);
            billDetails.add(bd);

            Object[][] information = billDetails.stream()
                    .map(x -> new Object[]{
                        x.getProduct_name(),
                        String.valueOf(x.getQuantity()),
                        String.valueOf(x.getUnity_price()),
                        String.valueOf(x.getTotal_price())
                    }).toArray(Object[][]::new);
            //System.out.println(billDetails.stream().count());

            billingEditor.model = new TableModel(information,COLUMN_EDITOR);
            billingEditor.table.setModel(billingEditor.model);
            for(int i=0;i < billingEditor.table.getColumnCount() - 1;i++){
                billingEditor.table.getColumnModel().getColumn(i).setCellRenderer(new ManageGeneralCells("text"));
            }
            billingEditor.table.getTableHeader().setReorderingAllowed(false);
            billingEditor.table.setRowHeight(25);
            billingEditor.table.setGridColor(new java.awt.Color(216,216,216));


            JTableHeader jTableHeader = billingEditor.table.getTableHeader();
            jTableHeader.setDefaultRenderer(new ManageTableHeader());
            billingEditor.table.setTableHeader(jTableHeader);

        }));
    }

    public void init(){
        billingList = (JPBillingList) baseView.panelList;
        billingEditor = (JPBillingEditor) baseView.panelEditor;
        billingList.makeTableHeader(COLUMN_NAMES,50);
        billingEditor.makeTableHeader(COLUMN_EDITOR,15,true,null);
        loadDataTableBillingAsync("");
        loadComboBoxAsync();
        initValues();
        billingEditor.table.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedColumn = billingEditor.table.getSelectedColumn();
                if (selectedColumn == 0) {
                    SwingUtilities.invokeLater(() -> {
                        JDialog dialog = new JDialog();
                        dialog.setModal(true);
                        dialog.setMinimumSize(new Dimension(700, 500));
                        dialog.setResizable(false);
                        dialog.setLocationRelativeTo(baseView);
                        dialog.add(new JPProductSelector(mutableObject));
                        dialog.setVisible(true);
                    });
                }
            }
        });
    }

    public void initValues(){
        billingEditor.txtSeries.setText("F001");
        CompletableFuture<String> futureCorrelative = CompletableFuture.supplyAsync(bill::getCorrelative);
        futureCorrelative.thenAcceptAsync(correlative -> SwingUtilities.invokeLater(() -> {
            billingEditor.txtCorrelative.setText(bill.getNewCorrelative());
        }));
    }

    public void loadComboBoxAsync(){
        CompletableFuture<List<EmployeeComboBox>> futureEmployee = CompletableFuture.supplyAsync(() -> new EmployeeComboBox().getEmployeeCmb(""));
        futureEmployee.thenAcceptAsync(employee -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<EmployeeComboBox> comboBoxModel = new CustomComboBoxModel<>(employee);
            billingEditor.cmbEmployee.setModel(comboBoxModel);
            billingEditor.cmbEmployee.setSelectedIndex(0);
        }));
    }

    public void loadDataTableBillingAsync(String query){
        tableLoadStrategy = new BillingTableStrategy();
        loadDataTableAsync(
                CompletableFuture.supplyAsync(() -> new Bill().getBillList(query)),
                (table) -> {

                }
        );
    }



    @Override
    public void reloadDataTableAsync() {
        loadDataTableBillingAsync("");
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int save() {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }

    @Override
    public void resetControls() {

    }

    public void onClickSearch(ActionEvent e){

    }
}
