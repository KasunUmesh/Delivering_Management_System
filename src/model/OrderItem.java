package model;

public class OrderItem {
    private String orderNumber;
    private String itemCode;
    private int itemQty;

    public OrderItem() {
    }

    public OrderItem(String orderNumber, String itemCode, int itemQty) {
        this.orderNumber = orderNumber;
        this.itemCode = itemCode;
        this.itemQty = itemQty;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderNumber='" + orderNumber + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemQty=" + itemQty +
                '}';
    }
}
