package models;

import models.db.Db;
import views.core.combobox.IComboBox;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductSunat implements IComboBox {
    private String product_id_sunat;
    private String name;

    public ProductSunat(){}
    public ProductSunat(String product_id_sunat, String name){
        this.product_id_sunat = product_id_sunat;
        this.name = name;
    }

    public List<ProductSunat> getProductSunat(){
        try{
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_PRODUCT_SUNAT);
            List<ProductSunat> productsSunat = new ArrayList<>();
            while (result.next()){
                productsSunat.add(new ProductSunat(
                        result.getString("product_id_sunat"),
                        result.getString("name")
                ));
            }
            result.getStatement().close();
            result.close();
            return productsSunat;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public Object getId() {
        return this.product_id_sunat;
    }

    @Override
    public Object getValue() {
        return this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
