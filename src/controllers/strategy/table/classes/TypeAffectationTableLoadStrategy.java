package controllers.strategy.table.classes;

import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.typeAffectation.TypeAffectation;

import java.util.List;

public class TypeAffectationTableLoadStrategy implements TableLoadStrategy<TypeAffectation> {
    @Override
    public Object[][] loadTableData(List<TypeAffectation> dataList) {
        return dataList.stream()
        .map(typeAffectation -> new Object[]{
            String.valueOf(typeAffectation.getType_affectation_id()),
            typeAffectation.getName()})
        .toArray(Object[][]::new);
    }
}
