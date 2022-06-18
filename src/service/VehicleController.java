package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Vehicle;
import view.tm.EmployeeTM;
import view.tm.VehicleTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleController implements VehicleService{
    @Override
    public boolean saveVehicle(Vehicle v) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Vehicle VALUES(?,?,?,?)");

        stm.setObject(1,v.getVehicleID());
        stm.setObject(2,v.getVehicleNumber());
        stm.setObject(3,v.getVehicleType());
        stm.setObject(4,v.getDescription());

        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<Vehicle> getAllVehicle() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Vehicle");
        ResultSet rst = stm.executeQuery();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        while (rst.next()){
            vehicles.add(new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            ));
        }
        return vehicles;
    }

    @Override
    public boolean deleteVehicle(String vehicleID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Vehicle WHERE vehicleID=?");
        stm.setString(1,vehicleID);

        return stm.executeUpdate()>0;
    }

    @Override
    public Vehicle getVehicle(String vehicleID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Vehicle WHERE vehicleID=?");
        stm.setObject(1,vehicleID);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean updateVehicle(Vehicle v) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE Vehicle SET vehicleNumber=?, vehicleType=?, description=? WHERE vehicleID=?");
        stm.setObject(1,v.getVehicleNumber());
        stm.setObject(2,v.getVehicleType());
        stm.setObject(3,v.getDescription());
        stm.setObject(4,v.getVehicleID());

        return stm.executeUpdate()>0;
    }

    @Override
    public ObservableList<VehicleTM> searchVehicle(String value) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Vehicle WHERE CONCAT(vehicleID, vehicleNumber, description) LIKE '%" + value + "%'");
        ResultSet rst = stm.executeQuery();
        ObservableList<VehicleTM> vehicle = FXCollections.observableArrayList();

        while (rst.next()){
            vehicle.add(new VehicleTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return vehicle;
    }

    @Override
    public List<String> getVehicleID() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Vehicle").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(
                    rst.getString(1)
            );
        }
        return id;
    }
}
