package models.category;

import classes.db.Db;
import models.ModelSQL;
import views.core.combobox.IComboBox;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Category implements IComboBox {

    private int category_id;
    private String name;
    private String distintive_color;
    public Category(){}
    public Category(int category_id,String name, String distintive_color){
        this.category_id = category_id;
        this.name = name;
        this.distintive_color = distintive_color;
    }

    public int getCategoryId(){
        return this.category_id;
    }
    @Override
    public Object getId(){
        return this.category_id;
    }

    @Override
    public Object getValue() {
        return this.name;
    }

    public String getName(){
        return this.name;
    }
    public String getDistintiveColor(){
        return this.distintive_color;
    }

    public String toString(){
        return this.name;
    }

    public List<Category> getCategoriesList(){
        try {
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_CATEGORIES);
            List<Category> categories = new ArrayList<>();
            while (result.next()){
                categories.add(new Category(
                        result.getInt("category_id"),
                        result.getString("name"),
                        result.getString("distintive_color")
                ));
            }
            result.getStatement().close();
            result.close();
            return categories;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

}
