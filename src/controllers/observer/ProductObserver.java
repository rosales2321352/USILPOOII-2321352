package controllers.observer;


import controllers.billing.BillingController;
import controllers.product.ProductController;

public class ProductObserver implements IObserver {

    private final ISubject subject;
    private int value;
    private String productInfo;
    BillingController controller;
    public ProductObserver(ISubject subject, BillingController controller) {
        if (subject == null) {
            throw new IllegalArgumentException("No Publisher found");
        }
        this.controller=controller;
        this.subject = subject;
        this.productInfo = "";
    }

    private void display() {
        //System.out.println(productInfo + ": " + value);
        controller.changeProducts();
    }

    @Override
    public void update(int value) {
        this.value = value;
        display();
    }

    @Override
    public void subscribe() {
        this.subject.subscribeObserver(this);
    }

    @Override
    public void unSubscribe() {
        this.subject.unsubscribeObserver(this);
    }
}