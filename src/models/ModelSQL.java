package models;

public class ModelSQL {

    public static final String SQL_STMT_GET_PRODUCTS = "select product_id ,product_id_sunat ,reference, type_affectation_id , name  from product p order by product_id desc limit 50";
    public static final String SQL_STMT_GET_PRODUCTS_BY_LIKE = "select product_id ,product_id_sunat ,reference, type_affectation_id , name  from product p WHERE p.product_id  like ? or p.reference like ? or p.name  like ? order by product_id desc limit 50";
    public static final String SQL_STMT_GET_PRODUCT = "select *  from product p where p.product_id = ?";
    public static final String SQL_STMT_INSERT_PRODUCT = "insert into product (product_id_sunat,category_id,unity_id,reference,name,type_affectation_id,outstanding) values (?,?,?,?,?,?,?)";
    public static final String SQL_STMT_UPDATE_PRODUCT = "update product set product_id_sunat = ? ,category_id = ? ,unity_id = ? ,reference = ? ,name = ? ,type_affectation_id = ? ,outstanding = ? where product_id = ?";
    public static final String SQL_STMT_DELETE_PRODUCT = "delete from product where product_id = ?";
    //CATEGORIES
    public static final String SQL_STMT_GET_CATEGORIES = "select * from category c ";
    public static final String SQL_STMT_GET_CATEGORIES_BY_LIKE = "select * from category  where category_id like ? OR name like ?";
    public static final String SQL_STMT_GET_CATEGORY = "select * from category c where category_id = ?";
    public static final String SQL_STMT_INSERT_CATEGORY = "insert into category (name, distintive_color) values (?,?)";
    public static final String SQL_STMT_UPDATE_CATEGORY = "update category set name=?,distintive_color=? where category_id=? ";
    public static final String SQL_STMT_DELETE_CATEGORY = "delete from category  where category_id = ?";
    //TYPE_AFFECTATION
    public static final String SQL_STMT_GET_TYPE_AFFECTATION = "SELECT * from type_affectation ta ";
    //PRODUCTS SUNAT
    public static final String SQL_STMT_GET_PRODUCT_SUNAT = "select * from product_sunat ps ";
    //UNITIES
    public static final String SQL_STMT_GET_UNITIES = "select * from unity u ";
    public static final String SQL_STMT_GET_UNITIES_BY_LIKE = "select * from unity where name like ? or symbol like ?";
    public static final String SQL_STMT_GET_UNITY = "select * from unity u where u.unity_id = ?";
    public static final String SQL_STMT_INSERT_UNITY = "insert into unity (name,symbol) values (?,?)";
    public static final String SQL_STMT_UPDATE_UNITY = "update unity set name = ? , symbol = ? WHERE unity_id = ?";
    public static final String SQL_STMT_DELETE_UNITY = "delete from unity where unity_id = ?";
    //LOGIN
    public static final String SQL_STMT_AUTHENTICATED = "SELECT * FROM user WHERE username = ? AND password = ?";

}
