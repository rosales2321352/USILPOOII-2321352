package controllers.category;

import controllers.BaseController;
import controllers.strategy.table.classes.CategoryTableStrategy;
import models.category.Category;
import views.JPBaseView;
import views.category.partials.*;
import views.core.table.ManageCellsActionButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class CategoryController extends BaseController<Category> {
    JPCategoryList categoryList;
    JPCategoryEditor categoryEditor;
    public CategoryController(JPBaseView baseView){
        this.baseView=baseView;
        COLUMN_NAMES= new String[]{ "Id", "Nombre", "Color", "Aciones"};
        messages = new HashMap<>();
        messages.put("AddConfirm","");
        messages.put("EditConfirm","");
        messages.put("DeleteConfirm","");
        messages.put("SaveError","");
        messages.put("SaveSuccess","");
        messages.put("DeleteSuccess","");
    }
    public void init(){
        categoryEditor = (JPCategoryEditor) baseView.panelEditor;
        categoryList = (JPCategoryList) baseView.panelList;
        categoryList.makeTableHeader(COLUMN_NAMES,23);
        loadDataTableCategoryAsync("");
    }
    public void loadDataTableCategoryAsync(String query){
        tableLoadStrategy = new CategoryTableStrategy();
        loadDataTableAsync(
            CompletableFuture.supplyAsync(() -> new Category().getCategoriesList(query)),
            (table) -> {
                table.setGridColor(new java.awt.Color(216,216,216));
                table.getColumnModel().getColumn(0).setMaxWidth(50);
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(1).setResizable(false);
                table.getColumnModel().getColumn(2).setMaxWidth(80);
                table.getColumnModel().getColumn(2).setResizable(false);
                new ManageCellsActionButtons(table, 3, new JPCategoryAction(this), new JPCategoryAction(this));
                table.getColumnModel().getColumn(3).setMaxWidth(100);
                table.getColumnModel().getColumn(3).setMinWidth(100);
            }
        );
    }
    public void reloadDataTableAsync(){loadDataTableCategoryAsync("");}
    public int save(){
        Category category = new Category();
        category.setCategoryId(id);
        category.setName(categoryEditor.txtName.getText());
        category.setDistintiveColor(categoryEditor.txtColor.getText());
        return category.save();
    }
    public int delete(){return new Category().delete(id);}
    public boolean validate(){
        if(categoryEditor.txtName.getText().isEmpty()){
            return false;
        }
        return !categoryEditor.txtColor.getText().isEmpty();
    }
    public void resetControls(){
        id =0;
        categoryEditor.txtName.setText("");
        categoryEditor.txtColor.setText("");
    }
    public void onClickEdit(ActionEvent e, int id){
        categoryEditor.lblTitle.setText("Editar Categoría");
        CompletableFuture<Category> futureCategory = CompletableFuture.supplyAsync(() -> new Category().getCategory(id));
        futureCategory.thenAcceptAsync(category -> SwingUtilities.invokeLater(() -> {
            this.id = category.getCategoryId();
            categoryEditor.txtName.setText(category.getName());
            categoryEditor.txtColor.setText(category.getDistintiveColor());
        }));
        this.switchTab((JButton) e.getSource());
    }
    public void onClickSearch(ActionEvent e){
        String query = categoryList.txtQuery.getText();
        if(query != null){
            this.loadDataTableCategoryAsync(query);
        }
    }
}
