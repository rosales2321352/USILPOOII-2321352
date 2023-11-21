package controllers.strategy.table.classes;

import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.billing.Bill;
import models.category.Category;

import java.util.List;

public class BillingTableStrategy implements TableLoadStrategy<Bill> {
    public Object[][] loadTableData(List<Bill> dataList) {
        return dataList.stream()
                .map(bill -> new Object[]{
                        String.valueOf(bill.getDocument_id()),
                        String.valueOf(bill.getDate_issue()),
                        bill.getN_document(),
                        bill.getCustomer_name(),
                        String.valueOf(bill.getTotal_pay())})
                .toArray(Object[][]::new);
    }
}
