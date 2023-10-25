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

    public Product(){}

    public Integer getProductId() { return  this.product_id; }
    public String getProductIdSunat(){ return this.product_id_sunat; }
    public String getReference(){ return this.reference; }
    public String getTypeAffectationId(){ return this.type_affectation_id; }
    public String getName(){
        return this.name;
    }
    public Integer getCategoryId(){ return this.category_id; }
    public Integer getUnityId(){ return this.unity_id; }
    public Integer getOutstanding(){ return this.outstanding; }

    public void setProductId(Integer product_id) { this.product_id = product_id; }
    public void setProductIdSunat(String product_id_sunat) { this.product_id_sunat = product_id_sunat; }
    public void setCategoryId(Integer category_id) { this.category_id = category_id; }
    public void setUnityId(Integer unity_id) { this.unity_id = unity_id; }
    public void setReference(String reference) { this.reference = reference; }
    public void setName(String name) { this.name = name; }

    public void setType_affectation_id(String type_affectation_id) {
        this.type_affectation_id = type_affectation_id;
    }

    public void setOutstanding(Integer outstanding) {
        this.outstanding = outstanding;
    }

    public List<Product> getListProducts(){
        try {
            var result = Db.executeQuery(ModelSQL.SQL_STMT_GET_PRODUCTS);
            List<Product> products = new ArrayList<>();
            while (result.next()){
                var product = new Product();
                product.setProductId(result.getInt("product_id"));
                product.setProductIdSunat(result.getString("product_id_sunat"));
                product.setReference(result.getString("reference"));
                product.setType_affectation_id(result.getString("type_affectation_id"));
                product.setName(result.getString("name"));
                products.add(product);
            }
            result.getStatement().close();
            result.close();
            return products;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Product getProduct(int product_id){
        try{
            var result = Db.executeQuery(ModelSQL.SQL_STMT_GET_PRODUCT,new Parameter[]{
                    new Parameter<>(1,product_id)
            });
            Product product = new Product();
            while(result.next()){
                product.setProductId(result.getInt("product_id"));
                product.setProductIdSunat(result.getString("product_id_sunat"));
                product.setCategoryId(result.getInt("category_id"));
                product.setUnityId(result.getInt("unity_id"));
                product.setReference(result.getString("reference"));
                product.setName(result.getString("name"));
                product.setType_affectation_id(result.getString("type_affectation_id"));
                product.setOutstanding(result.getInt("outstanding"));
            }
            return product;
        }catch (Exception e){
            return new Product();
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
