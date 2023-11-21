package models.combobox;

import models.person.Person;
import views.core.combobox.IComboBox;

import java.util.List;

public class EmployeeComboBox extends Person implements IComboBox {


    @Override
    public Object getId() {
        return getEmployeeId();
    }

    @Override
    public Object getValue() {
        return getName();
    }

    public String toString(){
        return  getName();
    }

    public List<EmployeeComboBox> getEmployeeCmb(String query){
        var list =getListPersons(query);
        return list.stream()
                .map(x -> {
                    EmployeeComboBox p = new EmployeeComboBox();
                    p.setEmployeeId(x.getEmployeeId());
                    p.setTypeDniId(x.getTypeDniId());
                    p.setDocument(x.getDocument());
                    p.setTelephone(x.getTelephone());
                    p.setName(x.getName());
                    p.setAddress(x.getAddress());
                    p.setReference(x.getReference());
                    p.setEmail(x.getEmail());
                    return p;
                }).toList();
    }
}
