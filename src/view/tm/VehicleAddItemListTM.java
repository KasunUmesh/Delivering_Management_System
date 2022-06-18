package view.tm;

public class VehicleAddItemListTM {
    private String vehicleID;
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private int itemQty;

    public VehicleAddItemListTM() {
    }

    public VehicleAddItemListTM(String vehicleID, String itemCode, String itemName, String itemDescription, int itemQty) {
        this.vehicleID = vehicleID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
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
        return "VehicleAddItemListTM{" +
                "vehicleID='" + vehicleID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemQty=" + itemQty +
                '}';
    }
}
