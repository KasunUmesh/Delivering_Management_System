package model;

public class Item {
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private double unitPrice;
    private int qtyOnStock;

    public Item() {

    }

    public Item(String itemCode, String itemName, String itemDescription, double unitPrice, int qtyOnStock) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.unitPrice = unitPrice;
        this.qtyOnStock = qtyOnStock;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnStock() {
        return qtyOnStock;
    }

    public void setQtyOnStock(int qtyOnStock) {
        this.qtyOnStock = qtyOnStock;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnStock=" + qtyOnStock +
                '}';
    }
}
