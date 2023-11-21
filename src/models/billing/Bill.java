package models.billing;

import models.ModelSQL;
import models.db.Db;
import models.db.classes.Parameter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Bill {

    private int document_id;
    private int customer_id;
    private String customer_name;
    private int employee_id;
    private String series;
    private String correlative;
    private String n_document;
    private Date date_issue;
    private Date due_date;
    private int currency_conversion_id;
    private int n_items;
    private double gross_amount;
    private double discount;
    private int discount_type;
    private double discount_percentage;
    private double  net_total;
    private double total_pay;

    public Bill(){}

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getN_document() {
        return n_document;
    }

    public void setN_document(String n_document) {
        this.n_document = n_document;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCorrelative() {
        return correlative;
    }

    public void setCorrelative(String correlative) {
        this.correlative = correlative;
    }

    public Date getDate_issue() {
        return date_issue;
    }

    public void setDate_issue(Date date_issue) {
        this.date_issue = date_issue;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getCurrency_conversion_id() {
        return currency_conversion_id;
    }

    public void setCurrency_conversion_id(int currency_conversion_id) {
        this.currency_conversion_id = currency_conversion_id;
    }

    public int getN_items() {
        return n_items;
    }

    public void setN_items(int n_items) {
        this.n_items = n_items;
    }

    public double getGross_amount() {
        return gross_amount;
    }

    public void setGross_amount(double gross_amount) {
        this.gross_amount = gross_amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(int discount_type) {
        this.discount_type = discount_type;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public double getNet_total() {
        return net_total;
    }

    public void setNet_total(double net_total) {
        this.net_total = net_total;
    }

    public double getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(double total_pay) {
        this.total_pay = total_pay;
    }

    public List<Bill> getBillList(String query){
        try{
            ResultSet resultSet;
            if(query.trim().isEmpty()){
                resultSet = Db.executeQuery(ModelSQL.SQL_STMT_GET_BILLINGS);
            }else{
                query = '%'+query+'%';
                resultSet = Db.executeQuery(ModelSQL.SQL_STMT_GET_BILLINGS_BY_LIKE, Arrays.asList(
                    new Parameter<>(1,query),
                    new Parameter<>(2,query)
                ));
            }
            List<Bill> billList = new ArrayList<>();
            while(resultSet.next()){
                Bill bi = new Bill();
                bi.setDocument_id(resultSet.getInt("document_id"));
                bi.setDate_issue(resultSet.getDate("date_issue"));
                bi.setN_document(resultSet.getString("n_document"));
                bi.setCustomer_name(resultSet.getString("full_name"));
                bi.setTotal_pay(resultSet.getDouble("total_pay"));
                billList.add(bi);
            }
            resultSet.getStatement().close();
            resultSet.close();
            return billList;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public String getNewCorrelative(){
        try{
            ResultSet resultSet = Db.executeQuery(ModelSQL.SQL_STMT_GET_CORRELATIVE);
            if(resultSet.next()){
                return resultSet.getString("new_correlative");
            }
            return "";
        }catch (Exception e){
            return "";
        }
    }
}
