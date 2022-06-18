package service;


import javafx.collections.ObservableList;
import model.VehicleStock;
import view.tm.VehicleStockItemTM;
import view.tm.VehicleStockTM;

import java.sql.SQLException;

public interface VehicleStockService {

    public boolean saveVehicleStore(VehicleStock vehicleStock) throws SQLException, ClassNotFoundException;
    public ObservableList<VehicleStockTM> getAllVehicleStock() throws SQLException, ClassNotFoundException;
    public ObservableList<VehicleStockItemTM> getAllVehicleStockItem(String vehicleID) throws SQLException, ClassNotFoundException;
}
