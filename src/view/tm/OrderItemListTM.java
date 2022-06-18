package view.tm;

public class OrderItemListTM {
    private String orderItemCode;
    private String orderItemName;
    private String orderItemDescription;
    private int orderDetailItemQty;

    public OrderItemListTM() {
    }

    public OrderItemListTM(String orderItemCode, String orderItemName, String orderItemDescription, int orderDetailItemQty) {
        this.orderItemCode = orderItemCode;
        this.orderItemName = orderItemName;
        this.orderItemDescription = orderItemDescription;
        this.orderDetailItemQty = orderDetailItemQty;
    }

    public String getOrderItemCode() {
        return orderItemCode;
    }

    public void setOrderItemCode(String orderItemCode) {
        this.orderItemCode = orderItemCode;
    }

    public String getOrderItemName() {
        return orderItemName;
    }

    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }

    public String getOrderItemDescription() {
        return orderItemDescription;
    }

    public void setOrderItemDescription(String orderItemDescription) {
        this.orderItemDescription = orderItemDescription;
    }

    public int getOrderDetailItemQty() {
        return orderDetailItemQty;
    }

    public void setOrderDetailItemQty(int orderDetailItemQty) {
        this.orderDetailItemQty = orderDetailItemQty;
    }

    @Override
    public String toString() {
        return "OrderItemListTM{" +
                "orderItemCode='" + orderItemCode + '\'' +
                ", orderItemName='" + orderItemName + '\'' +
                ", orderItemDescription='" + orderItemDescription + '\'' +
                ", orderDetailItemQty=" + orderDetailItemQty +
                '}';
    }
}

