package models.category;

import models.db.Db;
import models.db.classes.Parameter;
import models.ModelSQL;
import views.core.combobox.IComboBox;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void setCategoryId(Integer category_id){
        this.category_id= category_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDistintiveColor(String distintive_color){
        this.distintive_color = distintive_color;
    }

    public String toString(){
        return this.name;
    }

    public List<Category> getCategoriesList(){
        return this.getCategoriesList("");
    }
    public List<Category> getCategoriesList(String query){
        try {
            String stmt = query.trim().isEmpty()?ModelSQL.SQL_STMT_GET_CATEGORIES:ModelSQL.SQL_STMT_GET_CATEGORIES_BY_LIKE;
            ResultSet result;
            if(query.trim().isEmpty()) {
                result = Db.executeQuery(stmt);
            }else{
                query = '%'+query+'%';
                result = Db.executeQuery(stmt, Arrays.asList(
                        new Parameter<>(1,query),
                        new Parameter<>(2,query)
                ));
            }
            List<Category> categories = new ArrayList<>();
            while (result.next()){
                Category category = new Category();
                category.setCategoryId(result.getInt("category_id"));
                category.setName(result.getString("name"));
                category.setDistintiveColor(result.getString("distintive_color"));
                categories.add(category);
            }
            result.getStatement().close();
            result.close();
            return categories;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Category getCategory(int id){
        try{
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1,id));
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_CATEGORY,parameters);
            Category category  = new Category();
            while(result.next()){
                category.setCategoryId(result.getInt("category_id"));
                category.setName(result.getString("name"));
                category.setDistintiveColor(result.getString("distintive_color"));
            }
            result.getStatement().close();
            result.close();
            return category;
        }catch (Exception e){
            return new Category();
        }
    }
    public int save(){
        try{
            String stmt = category_id==0?ModelSQL.SQL_STMT_INSERT_CATEGORY:ModelSQL.SQL_STMT_UPDATE_CATEGORY;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1,this.name));
            parameters.add(new Parameter<>(2,this.distintive_color));
            if(category_id!=0) {
                parameters.add(new Parameter<>(3,this.category_id));
            }
            return Db.executeUpdate(stmt,parameters);
        }catch (Exception e){
            return 0;
        }
    }
    public int delete(int category_id){
        try{
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1,category_id));
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_CATEGORY,parameters);
        }catch (Exception e){
            return 0;
        }
    }

}
