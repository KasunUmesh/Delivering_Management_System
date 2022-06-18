package view.tm;

public class ItemListTM {
    private String orderNumber;
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private int itemQty;

    public ItemListTM(String text, Object value, String itemName, String itemDescription, String s) {
    }

    public ItemListTM(String orderNumber, String itemCode, String itemName, String itemDescription, int itemQty) {
        this.orderNumber = orderNumber;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "orderNumber='" + orderNumber + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemQty=" + itemQty +
                '}';
    }
}
