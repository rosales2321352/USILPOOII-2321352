package models.combobox;

import models.tax.Tax;
import views.core.combobox.IComboBox;

import java.util.List;

public class TaxComboBox extends Tax implements IComboBox {

    @Override
    public Object getId() {
        return this.getTax_id();
    }

    @Override
    public Object getValue() {
        return this.getName();
    }

    @Override
    public String toString(){
        return this.getName();
    }
    public List<TaxComboBox> getTaxesCmb(){
        return this.getTaxesCmb("");
    }
    public List<TaxComboBox> getTaxesCmb(String query){
        var list =this.getTaxes(query);
        return list.stream()
                .map(x -> {
                    TaxComboBox uc = new TaxComboBox();
                    uc.setTax_id(x.getTax_id());
                    uc.setName(x.getName());
                    uc.setPercentage(x.getPercentage());
                    return uc;
                }).toList();
    }
}
