package controllers.strategy.table.classes;

import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.tax.Tax;
import models.unity.Unity;

import java.util.List;

public class UnityTableLoadStrategy implements TableLoadStrategy<Unity> {
    public Object[][] loadTableData(List<Unity> dataList) {
        return dataList.stream()
                .map(unity -> new Object[]{
                        String.valueOf(unity.getUnityId()),
                        unity.getName() ,
                        unity.getSymbol()})
                .toArray(Object[][]::new);
    }
}

