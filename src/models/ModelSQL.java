package models;

public class ModelSQL {

    public static final String SQL_STMT_GET_PRODUCTS = "select product_id ,product_id_sunat ,reference, type_affectation_id , name  from product p order by product_id desc limit 50";
    public static final String SQL_STMT_GET_PRODUCTS_BY_LIKE = "select product_id ,product_id_sunat ,reference, type_affectation_id , name  from product p WHERE p.product_id  like ? or p.reference like ? or p.name  like ? order by product_id desc limit 50";
    public static final String SQL_STMT_GET_PRODUCT = "select *  from product p where p.product_id = ? limit 50";
    public static final String SQL_STMT_INSERT_PRODUCT = "insert into product (product_id_sunat,category_id,unity_id,reference,name,type_affectation_id,outstanding) values (?,?,?,?,?,?,?)";
    public static final String SQL_STMT_UPDATE_PRODUCT = "update product set product_id_sunat = ? ,category_id = ? ,unity_id = ? ,reference = ? ,name = ? ,type_affectation_id = ? ,outstanding = ? where product_id = ?";
    public static final String SQL_STMT_DELETE_PRODUCT = "delete from product where product_id = ?";
    //CATEGORIES
    public static final String SQL_STMT_GET_CATEGORIES = "select * from category c limit 50";
    public static final String SQL_STMT_GET_CATEGORIES_BY_LIKE = "select * from category  where category_id like ? OR name like ? limit 50";
    public static final String SQL_STMT_GET_CATEGORY = "select * from category c where category_id = ? limit 50";
    public static final String SQL_STMT_INSERT_CATEGORY = "insert into category (name, distintive_color) values (?,?)";
    public static final String SQL_STMT_UPDATE_CATEGORY = "update category set name=?,distintive_color=? where category_id=? ";
    public static final String SQL_STMT_DELETE_CATEGORY = "delete from category  where category_id = ?";
    //TYPE_AFFECTATION
    public static final String SQL_STMT_GET_TYPES_AFFECTATIONS = "SELECT * from type_affectation ta limit 50";
    public static final String SQL_STMT_GET_TYPE_AFFECTATION = "select * from type_affectation where type_affectation_id = ?";
    public static final String SQL_STMT_GET_TYPES_AFFECTATIONS_BY_LIKE = "select * from type_affectation where type_affectation_id like ? or name like ? limit 50";
    public static final String SQL_STMT_INSERT_TYPE_AFFECTATION = "insert into type_affectation (type_affectation_id,name,`free`,tax_id,onerous) value  (?,?,?,?,?)";
    public static final String SQL_STMT_UPDATE_TYPE_AFFECTATION = "update type_affectation set name=?,`free`=?,tax_id=?,onerous=? where type_affectation_id=?";
    public static final String SQL_STMT_DELETE_TYPE_AFFECTATION = "delete from type_affectation where type_affectation.type_affectation_id=?";
    //PRODUCTS SUNAT
    public static final String SQL_STMT_GET_PRODUCT_SUNAT = "select * from product_sunat ps limit 50";
    //UNITIES
    public static final String SQL_STMT_GET_UNITIES = "select * from unity u limit 50";
    public static final String SQL_STMT_GET_UNITIES_BY_LIKE = "select * from unity where name like ? or symbol like ? limit 50";
    public static final String SQL_STMT_GET_UNITY = "select * from unity u where u.unity_id = ? limit 50";
    public static final String SQL_STMT_INSERT_UNITY = "insert into unity (name,symbol) values (?,?)";
    public static final String SQL_STMT_UPDATE_UNITY = "update unity set name = ? , symbol = ? WHERE unity_id = ?";
    public static final String SQL_STMT_DELETE_UNITY = "delete from unity where unity_id = ?";
    //TAX
    public static final String SQL_STMT_GET_TAXES = "select tax_id , name ,percentage  from tax t limit 50";
    public static final String SQL_STMT_GET_TAXES_BY_LIKE = "select tax_id , name ,percentage  from tax t where t.tax_id like ? or t.name like ? limit 50";
    public static final String SQL_STMT_GET_TAX = "select tax_id , name ,percentage  from tax t where t.tax_id = ? limit 50";
    public static final String SQL_STMT_INSERT_TAX = "insert into tax (name,percentage,amount,is_percentage) values (?,?,0,1)";
    public static final String SQL_STMT_UPDATE_TAX = "update tax set name=?,percentage=? where tax_id=?";
    public static final String SQL_STMT_DELETE_TAX = "delete from tax where tax_id=?";
    //LOGIN
    public static final String SQL_STMT_AUTHENTICATED = "SELECT * FROM user WHERE username = ? AND password = ? limit 50";

    //DocumentType
    public static final String SQL_STMT_GET_DOCUMENTSTYPES = "SELECT document_type_id , name  FROM document_type dt  limit 50";

    public static final String SQL_STMT_GET_DOCUMENTSTYPES_BY_LIKE = "SELECT document_type_id , name  FROM document_type dt WHERE document_type_id LIKE ? or name LIKE ? limit 50";
    public static final String SQL_STMT_GET_DOCUMENTTYPE= "SELECT document_type_id , name  FROM document_type dt WHERE document_type_id = ? limit 50";

    public static final String SQL_STMT_INSERT_DOCUMENTSTYPES = "INSERT  into document_type (name,document_type_id) values (?,?)";
    public static final String SQL_STMT_UPDATE_DOCUMENTSTYPES = "UPDATE document_type SET name=? WHERE document_type_id = ?";
    public static final String SQL_STMT_DELETE_DOCUMENTSTYPES = "delete from document_type where document_type_id =?";



}
