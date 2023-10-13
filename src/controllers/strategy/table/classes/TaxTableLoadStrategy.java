package controllers.strategy.table.classes;

import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.tax.Tax;

import java.util.List;

public class TaxTableLoadStrategy implements TableLoadStrategy<Tax> {
    @Override
    public Object[][] loadTableData(List<Tax> dataList) {
        return dataList.stream()
        .map(tax -> new Object[]{
            String.valueOf(tax.getTax_id()),
            tax.getName(),
            String.valueOf(tax.getPercentage())})
        .toArray(Object[][]::new);
    }
}
