package controllers.strategy.table.classes;

import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.category.Category;
import models.tax.Tax;

import java.util.List;

public class CategoryTableStrategy implements TableLoadStrategy<Category> {
    public Object[][] loadTableData(List<Category> dataList) {
        return dataList.stream()
                .map(category -> new Object[]{
                        String.valueOf(category.getCategoryId()),
                        category.getName() ,
                        category.getDistintiveColor()})
                .toArray(Object[][]::new);
    }
}
