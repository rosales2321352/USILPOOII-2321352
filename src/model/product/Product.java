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
    
    private String name;

    public Product(){
    }
    public Product(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<Product> getListProducts(){
        MysqlConnection conn = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            String sql = "SELECT name FROM product LIMIT 50 OFFSET 0";
            conn = new MysqlConnection();
            statement = conn.connection().createStatement();
            result = statement.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (result.next()){
                products.add(new Product(result.getString("name")));
            }
            return products;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }finally {
            try {
                result.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
