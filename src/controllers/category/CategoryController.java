package controllers.category;

import models.category.Category;
import models.product.Product;
import views.category.CategoryView;
import views.product.ProductView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CategoryController {
    final private CategoryView panel;
    final String[] COLUMN_NAMES = { "Id", "Nombre", "Color", "Aciones"};
    private int category_id = 0;
    public CategoryController(CategoryView panel){
        this.panel = panel;
    }
    public void renderObjects(){
        this.panel.categoryList.makeTableHeader(COLUMN_NAMES);
    }

    public void loadDataTableAsync(String query){
        CompletableFuture<List<Category>> futureTableCategory = CompletableFuture.supplyAsync(() -> {
            return new Category().getCategoriesList(query);
        });
        futureTableCategory.thenAcceptAsync(categories -> {
            Object[][] information = categories.stream()
                    .map(category -> new Object[]{
                            String.valueOf(category.getCategoryId()),
                            category.getName() ,
                            category.getDistintiveColor()})
                    .toArray(Object[][]::new);
            SwingUtilities.invokeLater(() -> panel.categoryList.makeTable(information,COLUMN_NAMES));
        });
    }
    public Integer deleteCategory(){
        Category category = new Category();
        category.setCategoryId(this.category_id);
        return category.delete();
    }
    private int save(){
        Category category = new Category();
        if(this.category_id!=0) category.setCategoryId(this.category_id);
        category.setName(this.panel.categoryEditor.txtName.getText());
        category.setDistintiveColor(this.panel.categoryEditor.txtColor.getText());
        return category.save(category_id);
    }
    private void resetControls(){
        this.category_id =0;
        this.panel.categoryEditor.txtName.setText("");
        this.panel.categoryEditor.txtColor.setText("");
    }
    public void onClickBtnNew(ActionEvent e){
        this.switchTab((JButton) e.getSource());
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

    public void onClickBtnSave(ActionEvent e){
        if(this.validate()) {
            String message = this.category_id == 0 ? "¿Está seguro de crear la categoría?" : "¿Está seguro de actualizar la categoría?";
            int response = JOptionPane.showConfirmDialog(null,
                    message,
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                var rowsAffected = this.save();
                if(rowsAffected < 1){
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo guardar la categoría.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Categoría guardada correctamente.",
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                }
                this.loadDataTableAsync("");
                this.resetControls();
                this.switchTab((JButton) e.getSource());
            }
        }
    }
    public void onClickBtnEdit(ActionEvent e, int id){
        this.panel.categoryEditor.lblTitle.setText("Editar Categoría");
        CompletableFuture<Category> futureCategory = CompletableFuture.supplyAsync(() -> new Category().getCategory(id));
        futureCategory.thenAcceptAsync(category -> SwingUtilities.invokeLater(() -> {
            this.category_id = category.getCategoryId();
            this.panel.categoryEditor.txtName.setText(category.getName());
            this.panel.categoryEditor.txtColor.setText(category.getDistintiveColor());
        }));
        this.switchTab((JButton) e.getSource());
    }
    public void onClickBtnDelete(ActionEvent e, int category_id){
        int response = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de eliminar la categoría?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if(response == JOptionPane.YES_OPTION){
            this.category_id = category_id;
            var rowsAffected = this.deleteCategory();
            String message = "";
            if(rowsAffected > 1){
                message = "Categoría eliminada correctamente.";
            }else{
                message = "No se pudo eliminar la categoría.";
            }
            JOptionPane.showMessageDialog(null, message, "Atención", JOptionPane.INFORMATION_MESSAGE);
            this.loadDataTableAsync("");
        }
        this.category_id = 0;
    }
    public void onClickSearchCategory(ActionEvent e){
        String query = panel.categoryList.txtQuery.getText();
        if(query != null){
            this.loadDataTableAsync(query);
        }
    }
    private boolean validate(){
        if(this.panel.categoryEditor.txtName.getText().isEmpty()){
            return false;
        }
        if(this.panel.categoryEditor.txtColor.getText().isEmpty()){
            return false;
        }
        return true;
    }
    public void switchTab(JButton button){
        String command = button.getName();
        panel.cardLayout.show(panel.tabContent,command);
    }
}
