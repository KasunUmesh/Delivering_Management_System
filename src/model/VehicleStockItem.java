package model;

public class VehicleStockItem {
    private String vehicleID;
    private String itemCode;
    private String vStoreDate;
    private int storeItemQty;

    public VehicleStockItem() {
    }

    public VehicleStockItem(String vehicleID, String itemCode, String vStoreDate, int storeItemQty) {
        this.vehicleID = vehicleID;
        this.itemCode = itemCode;
        this.vStoreDate = vStoreDate;
        this.storeItemQty = storeItemQty;
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

    public String getvStoreDate() {
        return vStoreDate;
    }

    public void setvStoreDate(String vStoreDate) {
        this.vStoreDate = vStoreDate;
    }

    public int getStoreItemQty() {
        return storeItemQty;
    }

    public void setStoreItemQty(int storeItemQty) {
        this.storeItemQty = storeItemQty;
    }

    @Override
    public String toString() {
        return "VehicleStockItem{" +
                "vehicleID='" + vehicleID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", vStoreDate='" + vStoreDate + '\'' +
                ", storeItemQty=" + storeItemQty +
                '}';
    }
}

