package controllers.strategy.table.classes;

import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.category.Category;
import models.product.Product;

import java.util.List;

public class ProductTableLoadStrategy implements TableLoadStrategy<Product> {
    public Object[][] loadTableData(List<Product> dataList) {
        return dataList.stream()
                .map(product -> new Object[]{ String.valueOf(product.getProductId()), product.getProductIdSunat(), product.getReference(), product.getTypeAffectationId(), product.getName()})
                .toArray(Object[][]::new);
    }
}
