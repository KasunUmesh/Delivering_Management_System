package service;

import javafx.collections.ObservableList;
import model.Vehicle;
import view.tm.VehicleTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleService {
    public boolean saveVehicle(Vehicle v) throws SQLException, ClassNotFoundException;
    public ArrayList<Vehicle> getAllVehicle() throws SQLException, ClassNotFoundException;
    public boolean deleteVehicle(String vehicleID) throws SQLException, ClassNotFoundException;
    public Vehicle getVehicle(String vehicleID) throws SQLException, ClassNotFoundException;
    public boolean updateVehicle(Vehicle v) throws SQLException, ClassNotFoundException;
    public ObservableList<VehicleTM> searchVehicle(String value) throws SQLException, ClassNotFoundException;
    public List<String> getVehicleID() throws SQLException, ClassNotFoundException;
}