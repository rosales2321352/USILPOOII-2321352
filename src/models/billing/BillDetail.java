package models.billing;

public class BillDetail {

    private int document_detail_id;
    private int document_id;
    private int product_id;
    private String product_name;
    private double quantity;
    private double unity_price;
    private double tax;
    private int tax_id;
    private int type_affectation_id;
    private double total_price;

    public BillDetail(){}

    public int getDocument_detail_id() {
        return document_detail_id;
    }

    public void setDocument_detail_id(int document_detail_id) {
        this.document_detail_id = document_detail_id;
    }

    public int getDocument_id() {
        return document_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnity_price() {
        return unity_price;
    }

    public void setUnity_price(double unity_price) {
        this.unity_price = unity_price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getTax_id() {
        return tax_id;
    }

    public void setTax_id(int tax_id) {
        this.tax_id = tax_id;
    }

    public int getType_affectation_id() {
        return type_affectation_id;
    }

    public void setType_affectation_id(int type_affectation_id) {
        this.type_affectation_id = type_affectation_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
