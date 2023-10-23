package models;

public class ModelSQL {

    public static final String SQL_STMT_GET_PRODUCTS = "select product_id ,product_id_sunat ,reference, type_affectation_id , name  from product p";
    public static final String SQL_STMT_INSERT_PRODUCT = "insert into (product_sunat,category_id,unity_id,reference,name,type_affectation,outstanding) values (?,?,?,?,?,?,?)";

}
