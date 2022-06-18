package service;

import javafx.collections.ObservableList;
import model.Order;
import model.OrderItem;
import view.tm.OrderItemListTM;
import view.tm.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderService {
    public boolean saveOrders(Order order) throws SQLException, ClassNotFoundException;
    public ObservableList<OrderTM> getAllOrder() throws SQLException, ClassNotFoundException;
    public ObservableList<OrderItemListTM> getAllOrderItem(String orderNumber) throws SQLException, ClassNotFoundException;
    public String getOrderNumber() throws SQLException, ClassNotFoundException;
}
