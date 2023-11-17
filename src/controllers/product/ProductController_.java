package controllers.product;

import controllers.BaseController;
import controllers.strategy.table.classes.ProductTableLoadStrategy;
import models.ProductSunat;
import models.category.Category;
import models.combobox.UnityComboBox;
import models.product.Product;
import models.typeAffectation.TypeAffectation;
import models.unity.Unity;
import views.JPBaseView;
import views.core.combobox.CustomComboBoxModel;
import views.core.table.ManageCellsActionButtons;
import views.product.partials.JPProductAction;
import views.product.partials.JPProductEditor;
import views.product.partials.JPProductList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProductController_ extends BaseController<Product> {

    JPProductList productList;
    JPProductEditor productEditor;

    public ProductController_(JPBaseView baseView){
        this.baseView=baseView;
        COLUMN_NAMES=new String[]{ "Id", "SUNAT", "Referencia", "TA", "Descripción", "Aciones"};
        messages = new HashMap<>();
        messages.put("AddConfirm","");
        messages.put("EditConfirm","");
        messages.put("DeleteConfirm","");
        messages.put("SaveError","");
        messages.put("SaveSuccess","");
        messages.put("DeleteSuccess","");
    }
    public void init(){
        productEditor = (JPProductEditor) baseView.panelEditor;
        productList = (JPProductList) baseView.panelList;
        productList.makeTableHeader(COLUMN_NAMES,50);
        loadDataTableProductAsync("");
        loadDataComboBoxAsync();
    }
    public void loadDataTableProductAsync(String query){
        tableLoadStrategy = new ProductTableLoadStrategy();
        loadDataTableAsync(
            CompletableFuture.supplyAsync(() -> new Product().getListProducts(query)),
            (table) -> {
                table.getColumnModel().getColumn(0).setMaxWidth(50);
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(1).setMaxWidth(60);
                table.getColumnModel().getColumn(1).setResizable(false);
                table.getColumnModel().getColumn(2).setMaxWidth(80);
                table.getColumnModel().getColumn(2).setResizable(false);
                table.getColumnModel().getColumn(3).setMaxWidth(40);
                table.getColumnModel().getColumn(3).setResizable(false);
                new ManageCellsActionButtons(table, 5, new JPProductAction(this), new JPProductAction(this));
                table.getColumnModel().getColumn(5).setMaxWidth(100);
                table.getColumnModel().getColumn(5).setMinWidth(100);
            }
        );
    }
    public void reloadDataTableAsync(){ loadDataTableProductAsync(""); }
    public int save(){
        String name = productEditor.txtName.getText();
        Category category = (Category) productEditor.cmbCategory.getSelectedItem();
        TypeAffectation typeAffectation = (TypeAffectation) productEditor.cmbTypeAffectation.getSelectedItem();
        ProductSunat productSunat = (ProductSunat) productEditor.cmbProductSunat.getSelectedItem();
        Unity unity = (Unity) productEditor.cmbUnity.getSelectedItem();
        String reference = productEditor.txtReference.getText();

        Product product = new Product();
        product.setProductId(id);
        product.setName(name);
        product.setCategoryId((int) category.getId());
        product.setProductIdSunat((String) productSunat.getId());
        product.setType_affectation_id((String) typeAffectation.getId());
        product.setUnityId((int) unity.getUnityId());
        product.setReference(reference);
        product.setOutstanding(0);
        return product.save();
    }
    public int delete(){return new Product().delete(id);}
    public boolean validate(){
        String name = productEditor.txtName.getText();
        Category category = (Category) productEditor.cmbCategory.getSelectedItem();
        TypeAffectation typeAffectation = (TypeAffectation) productEditor.cmbTypeAffectation.getSelectedItem();
        ProductSunat productSunat = (ProductSunat) productEditor.cmbProductSunat.getSelectedItem();
        Unity unity = (Unity) productEditor.cmbUnity.getSelectedItem();

        if(name.trim().isEmpty()){
            return false;
        }
        if(category == null){
            return false;
        }
        if(typeAffectation == null){
            return false;
        }
        if(productSunat == null){
            return false;
        }
        return unity != null;
    }
    public void resetControls(){
        id=0;
        productEditor.txtName.setText("");
        productEditor.cmbCategory.setSelectedIndex(0);
        productEditor.cmbTypeAffectation.setSelectedIndex(0);
        productEditor.cmbProductSunat.setSelectedIndex(0);
        productEditor.cmbUnity.setSelectedIndex(0);
        productEditor.txtReference.setText("");
    }
    public void loadDataComboBoxAsync(){
        CompletableFuture<List<Category>> futureCategory = CompletableFuture.supplyAsync(new Category()::getCategoriesList);
        CompletableFuture<List<TypeAffectation>> futureTypeAffectation = CompletableFuture.supplyAsync(new TypeAffectation()::getTypesAffectationList);
        CompletableFuture<List<ProductSunat>> futureProductSunat = CompletableFuture.supplyAsync(new ProductSunat()::getProductSunat);
        CompletableFuture<List<UnityComboBox>> futureUnity = CompletableFuture.supplyAsync(new UnityComboBox()::getUnits_);

        futureCategory.thenAcceptAsync(categories -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<Category> comboBoxModel = new CustomComboBoxModel<>(categories);
            productEditor.cmbCategory.setModel(comboBoxModel);
            //panel.productEditor.cmbCategory.setSelectedIndex(0);
        }));
        futureProductSunat.thenAcceptAsync(productSunats -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<ProductSunat> comboBoxModel = new CustomComboBoxModel<>(productSunats);
            productEditor.cmbProductSunat.setModel(comboBoxModel);
            productEditor.cmbProductSunat.setSelectedIndex(0);
        }));
        futureTypeAffectation.thenAcceptAsync(typeAffectations -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<TypeAffectation> comboBoxModel = new CustomComboBoxModel<>(typeAffectations);
            productEditor.cmbTypeAffectation.setModel(comboBoxModel);
            productEditor.cmbTypeAffectation.setSelectedIndex(0);
        }));
        futureUnity.thenAcceptAsync(unities -> SwingUtilities.invokeLater(() -> {
            CustomComboBoxModel<UnityComboBox> comboBoxModel = new CustomComboBoxModel<>(unities);
            productEditor.cmbUnity.setModel(comboBoxModel);
            productEditor.cmbUnity.setSelectedIndex(0);
        }));
    }
    public void onClickEdit(ActionEvent e, Object id){
        CompletableFuture<Product> futureProduct = CompletableFuture.supplyAsync(() -> new Product().getProduct((int) id));
        futureProduct.thenAcceptAsync(product -> SwingUtilities.invokeLater(() -> {
            this.id = product.getProductId();
            productEditor.cmbCategory.setSelectedItemById(product.getCategoryId());
            productEditor.cmbProductSunat.setSelectedItemById(product.getProductIdSunat());
            productEditor.cmbTypeAffectation.setSelectedItemById(product.getTypeAffectationId());
            productEditor.cmbUnity.setSelectedItemById(product.getCategoryId());
            productEditor.txtName.setText(product.getName());
            productEditor.txtReference.setText(product.getReference());
        }));
        this.switchTab((JButton) e.getSource());
    }
    public void obClickSearch(ActionEvent e){
        String query = productList.txtQuery.getText();
        if(query != null){
            loadDataTableProductAsync(query);
        }
    }
}
