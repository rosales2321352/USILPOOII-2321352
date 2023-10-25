package models;

public class ModelSQL {

    public static final String SQL_STMT_GET_PRODUCTS = "select product_id ,product_id_sunat ,reference, type_affectation_id , name  from product p";
    public static final String SQL_STMT_GET_PRODUCT = "select *  from product p where p.product_id = ?";
    public static final String SQL_STMT_INSERT_PRODUCT = "insert into (product_sunat,category_id,unity_id,reference,name,type_affectation,outstanding) values (?,?,?,?,?,?,?)";
    public static final String SQL_STMT_GET_CATEGORIES = "select * from category c ";
    public static final String SQL_STMT_GET_TYPE_AFFECTATION = "SELECT * from type_affectation ta ";
    public static final String SQL_STMT_GET_PRODUCT_SUNAT = "select * from product_sunat ps ";
    public static final String SQL_STMT_GET_UNITIES = "select * from unity u ";

}
