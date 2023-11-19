package views.billing.partials;

import controllers.billing.BillingController;
import views.JPBaseEditor;

public class JPBillingEditor extends JPBaseEditor {

    private final BillingController controller;

    public JPBillingEditor(BillingController controller){
        super();
        this.controller=controller;
    }

    @Override
    public void drawControls() {

    }
}
