package controllers.strategy.table.interfaces;

import java.util.List;

public interface TableLoadStrategy<T> {
    Object[][] loadTableData(List<T> dataList);
}
