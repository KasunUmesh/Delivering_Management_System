package model;

import java.util.ArrayList;

public class VehicleStock {
    private String vehicleID;
    private String vStoreDate;
    private int qtyOnVehicle;
    private ArrayList<VehicleStockItem> items;

    public VehicleStock() {
    }

    public VehicleStock(String vehicleID, String vStoreDate, int qtyOnVehicle, ArrayList<VehicleStockItem> items) {
        this.vehicleID = vehicleID;
        this.vStoreDate = vStoreDate;
        this.qtyOnVehicle = qtyOnVehicle;
        this.items = items;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getvStoreDate() {
        return vStoreDate;
    }

    public void setvStoreDate(String vStoreDate) {
        this.vStoreDate = vStoreDate;
    }

    public int getQtyOnVehicle() {
        return qtyOnVehicle;
    }

    public void setQtyOnVehicle(int qtyOnVehicle) {
        this.qtyOnVehicle = qtyOnVehicle;
    }

    public ArrayList<VehicleStockItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<VehicleStockItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "VehicleStock{" +
                "vehicleID='" + vehicleID + '\'' +
                ", vStoreDate='" + vStoreDate + '\'' +
                ", qtyOnVehicle=" + qtyOnVehicle +
                ", items=" + items +
                '}';
    }
}

