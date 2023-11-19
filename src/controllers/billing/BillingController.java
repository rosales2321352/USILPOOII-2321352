package controllers.billing;

import controllers.BaseController;
import models.billing.Bill;
import views.JPBaseView;
import views.billing.JPBillingView;
import views.billing.partials.JPBillingEditor;
import views.billing.partials.JPBillingList;

import java.awt.event.ActionEvent;
import java.util.HashMap;

public class BillingController extends BaseController<Bill> {

    JPBillingEditor billingEditor;
    JPBillingList jpBillingList;

    public BillingController(JPBaseView baseView){
        this.baseView = baseView;
        COLUMN_NAMES = new String[]{ "Id", "Nombre", "%","Acciones"};
        messages = new HashMap<>();
        messages.put("AddConfirm","");
        messages.put("EditConfirm","");
        messages.put("DeleteConfirm","");
        messages.put("SaveError","");
        messages.put("SaveSuccess","");
        messages.put("DeleteSuccess","");
    }



    @Override
    public void reloadDataTableAsync() {

    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int save() {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }

    @Override
    public void resetControls() {

    }

    public void onClickSearch(ActionEvent e){

    }
}
