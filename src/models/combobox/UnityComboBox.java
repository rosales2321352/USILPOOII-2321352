package models.combobox;

import models.unity.Unity;
import views.core.combobox.IComboBox;

import java.util.List;

public class UnityComboBox extends Unity implements IComboBox {

    @Override
    public Object getId() {
        return this.getUnityId();
    }

    @Override
    public Object getValue() {
        return this.getName();
    }

    @Override
    public String toString(){
        return this.getSymbol();
    }


    public List<UnityComboBox> getUnits_(){
        var list =this.getUnits();
        List<UnityComboBox> cmb = list.stream()
                .map(x -> {
                    UnityComboBox uc = new UnityComboBox();
                    uc.setUnityId(x.getUnityId());
                    uc.setName(x.getName());
                    uc.setSymbol(x.getSymbol());
                    return uc;
                }).toList();
        return  cmb;
    }
}
