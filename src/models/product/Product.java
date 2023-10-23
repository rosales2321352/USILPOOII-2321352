package models.product;

import classes.db.Db;
import classes.db.classes.Parameter;
import models.ModelSQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product {
    private Integer product_id;
    private String product_id_sunat;
    private Integer category_id;
    private Integer unity_id;
    private String reference;
    private String name;
    private String type_affectation_id;
    private Integer outstanding;

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
            ResultSet result = Db.executeQuery(ModelSQL.SQL_STMT_GET_PRODUCTS);
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

    public int insert(){
        try{
            var parameters = new Parameter[]{
                    new Parameter<>(0, product_id_sunat),
                    new Parameter<>(1,category_id),
                    new Parameter<>(2,unity_id),
                    new Parameter<>(3,reference),
                    new Parameter<>(4,name),
                    new Parameter<>(5,type_affectation_id),
                    new Parameter<>(6,outstanding),
            };

            return Db.executeUpdate(ModelSQL.SQL_STMT_INSERT_PRODUCT,parameters);
        }catch (Exception e){
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE,null,e);
            return 0;
        }
    }


}
