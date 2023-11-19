package controllers.strategy.table.classes;

import controllers.strategy.table.interfaces.TableLoadStrategy;
import models.customer.Customer;

import java.util.List;

public class CustomerTableStrategy implements TableLoadStrategy<Customer> {
    public Object[][] loadTableData(List<Customer> dataList) {
            return dataList.stream()
            .map(customer -> new Object[]{
                    String.valueOf(customer.getCustomer_id()),
                    customer.getDocument_type_name(),
                    customer.getDocument(),
                    customer.getFull_name(),
                    customer.getEmail()})
            .toArray(Object[][]::new);
    }
}
