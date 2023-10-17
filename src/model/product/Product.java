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
    private String name;

    public Product(){
    }
    public Product(Integer product_id, String name){
        this.product_id = product_id;
        this.name = name;
    }

    public Integer getProductId() { return  this.product_id; }
    public String getName(){
        return this.name;
    }

    public List<Product> getListProducts(){

        try {
            String sql = "SELECT * FROM product LIMIT 50 OFFSET 0";
            ResultSet result = Db.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (result.next()){
                products.add(new Product(
                        result.getInt("product_id"),
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
