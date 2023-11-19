package models.customer;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    private int customer_id;
    private int document_type_id;
    private String document_type_name;
    private String document;
    private String full_name;
    private String address;
    private String email;
    private String telephone_number;
    private String reference;

    public Customer(){}

    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public int getDocument_type_id() {
        return document_type_id;
    }
    public void setDocument_type_id(int document_type_id) {
        this.document_type_id = document_type_id;
    }
    public String getDocument_type_name() {
        return document_type_name;
    }
    public void setDocument_type_name(String document_type_name) {
        this.document_type_name = document_type_name;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone_number() {
        return telephone_number;
    }
    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Customer> getCustomerList(String query){
        try{
            ResultSet resultSet;
            if(query.trim().isEmpty()){
                resultSet = Db.executeQuery(ModelSQL.SQL_STMT_GET_CUSTOMERS);
            }else{
                query = '%'+query+'%';
                resultSet = Db.executeQuery(ModelSQL.SQL_STMT_GET_CUSTOMERS_BY_LIKE, Arrays.asList(
                        new Parameter<>(1,query),
                        new Parameter<>(2,query),
                        new Parameter<>(3,query),
                        new Parameter<>(4,query)
                ));
            }
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setDocument_type_id(resultSet.getInt("document_type_id"));
                customer.setDocument_type_name(resultSet.getString("document_type_name"));
                customer.setDocument(resultSet.getString("document"));
                customer.setFull_name(resultSet.getString("full_name"));
                customer.setEmail(resultSet.getString("email"));
                customerList.add(customer);
            }
            resultSet.getStatement().close();
            resultSet.close();
            return customerList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    public Customer getCustomer(int customer_id){
        try{
            ResultSet resultSet = Db.executeQuery(ModelSQL.SQL_STMT_GET_CUSTOMER,List.of(
                new Parameter<>(1,customer_id))
            );
            Customer customer = new Customer();
            while (resultSet.next()){
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                customer.setDocument_type_id(resultSet.getInt("document_type_id"));
                customer.setDocument(resultSet.getString("document"));
                customer.setFull_name(resultSet.getString("full_name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setEmail(resultSet.getString("email"));
                customer.setTelephone_number(resultSet.getString("telephone_number"));
                customer.setReference(resultSet.getString("reference"));
            }
            resultSet.getStatement().close();
            resultSet.close();
            return customer;
        }catch (Exception e){
            return new Customer();
        }
    }

    public int save(){
        try{
            String stmt = ModelSQL.SQL_STMT_INSERT_CUSTOMER;
            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter<>(1,document_type_id));
            parameters.add(new Parameter<>(2,document));
            parameters.add(new Parameter<>(3,full_name));
            parameters.add(new Parameter<>(4,address));
            parameters.add(new Parameter<>(5,email));
            parameters.add(new Parameter<>(6,telephone_number));
            parameters.add(new Parameter<>(7,reference));
            if(customer_id!=0){
                stmt = ModelSQL.SQL_STMT_UPDATE_CUSTOMER;
                parameters.add(new Parameter<>(8,customer_id));
            }
            return Db.executeUpdate(stmt,parameters);
        }catch (Exception e){
            return 0;
        }
    }
    public int delete(int customer_id){
        try{
            return Db.executeUpdate(ModelSQL.SQL_STMT_DELETE_CUSTOMER, List.of(
               new Parameter<>(1,customer_id)
            ));
        }catch (Exception e){
            return 0;
        }
    }
}
