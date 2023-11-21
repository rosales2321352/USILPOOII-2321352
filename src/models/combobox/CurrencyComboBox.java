package models.combobox;

import models.Currency.Currency;
import views.core.combobox.CustomComboBox;
import views.core.combobox.IComboBox;

import java.util.List;

public class CurrencyComboBox extends Currency implements IComboBox {
    @Override
    public Object getId() {
        return getCurrency_id();
    }

    @Override
    public Object getValue() {
        return getName();
    }

    public String toString(){

        return getName();


    }

    public List<CurrencyComboBox> getCurrenciesCmb(){
        var list =this.getCurrencies("");
        return list.stream()
                .map(x -> {
                    CurrencyComboBox dtc = new CurrencyComboBox();
                    dtc.setCurrency_id(x.getCurrency_id());
                    dtc.setName(x.getName());
                    return dtc;
                }).toList();





    }


}
