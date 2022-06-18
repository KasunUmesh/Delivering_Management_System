package view.tm;

public class VehicleStockTM {
    private String vehicleID;
    private String vehicleStoreDate;
    private int qtyOnVehicle;

    public VehicleStockTM() {
    }

    public VehicleStockTM(String vehicleID, String vehicleStoreDate, int qtyOnVehicle) {
        this.vehicleID = vehicleID;
        this.vehicleStoreDate = vehicleStoreDate;
        this.qtyOnVehicle = qtyOnVehicle;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleStoreDate() {
        return vehicleStoreDate;
    }

    public void setVehicleStoreDate(String vehicleStoreDate) {
        this.vehicleStoreDate = vehicleStoreDate;
    }

    public int getQtyOnVehicle() {
        return qtyOnVehicle;
    }

    public void setQtyOnVehicle(int qtyOnVehicle) {
        this.qtyOnVehicle = qtyOnVehicle;
    }

    @Override
    public String toString() {
        return "VehicleStockTM{" +
                "vehicleID='" + vehicleID + '\'' +
                ", vehicleStoreDate='" + vehicleStoreDate + '\'' +
                ", qtyOnVehicle=" + qtyOnVehicle +
                '}';
    }
}
