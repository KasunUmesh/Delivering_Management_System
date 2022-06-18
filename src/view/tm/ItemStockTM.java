package view.tm;

public class ItemStockTM {
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private double unitPrice;
    private int qtyOnStock;

    public ItemStockTM() {

    }

    public ItemStockTM(String itemCode, String itemName, String itemDescription, double unitPrice, int qtyOnStock) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setItemDescription(itemDescription);
        this.setUnitPrice(unitPrice);
        this.setQtyOnStock(qtyOnStock);
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
        return "ItemStockTM{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnStock=" + qtyOnStock +
                '}';
    }
}
