package model;

import java.util.ArrayList;

public class Order {
    private String orderNumber;
    private String orderDate;
    private int orderItemQty;
    private ArrayList<OrderItem> items;

    public Order() {
    }

    public Order(String orderNumber, String orderDate, int orderItemQty, ArrayList<OrderItem> items) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderItemQty = orderItemQty;
        this.items = items;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderItemQty() {
        return orderItemQty;
    }

    public void setOrderItemQty(int orderItemQty) {
        this.orderItemQty = orderItemQty;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderItemQty=" + orderItemQty +
                ", items=" + items +
                '}';
    }
}
