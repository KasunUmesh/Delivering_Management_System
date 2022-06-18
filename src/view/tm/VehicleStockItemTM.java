package view.tm;

public class VehicleStockItemTM {
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private int itemQty;

    public VehicleStockItemTM() {
    }

    public VehicleStockItemTM(String itemCode, String itemName, String itemDescription, int itemQty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
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
        return "VehicleStockItemTM{" +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemQty=" + itemQty +
                '}';
    }
}
