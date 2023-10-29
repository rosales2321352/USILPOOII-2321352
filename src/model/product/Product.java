package model.product;

import classes.db.Db;
import classes.db.classes.MysqlConnection;
import classes.db.classes.Parameter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Product {
    private Integer product_id;
    private String product_id_sunat;
    private String reference;
    private String type_affectation_id;
    private String name;

    public Product(){
    }

    public Product(Integer product_id, String product_id_sunat, String reference, String type_affectation_id, String name) {
        this.product_id = product_id;
        this.product_id_sunat = product_id_sunat;
        this.reference = reference;
        this.type_affectation_id = type_affectation_id;
        this.name = name;
    }

    public Integer getProductId() { return  this.product_id; }
    public String getProductIdSunat(){ return this.product_id_sunat; }
    public String getReference(){ return this.reference; }
    public String getTypeAffectationId(){ return this.type_affectation_id; }
    public String getName(){
        return this.name;
    }



    public List<Product> getListProducts(){

        try {
            String sql = "select product_id ,product_id_sunat ,reference, type_affectation_id , name  from product p";
            ResultSet result = Db.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (result.next()){
                products.add(new Product(
                        result.getInt("product_id"),
                        result.getString("product_id_sunat"),
                        result.getString("reference"),
                        result.getString("type_affectation_id"),
                        result.getString("name")
                ));
            }
            result.getStatement().close();
            result.close();
            return products;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }


}
