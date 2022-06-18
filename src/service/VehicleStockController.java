package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.VehicleStock;
import model.VehicleStockItem;
import view.tm.VehicleStockItemTM;
import view.tm.VehicleStockTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleStockController implements VehicleStockService{
    @Override
    public boolean saveVehicleStore(VehicleStock vehicleStock) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        PreparedStatement stm = con.prepareStatement("INSERT INTO VehicleStock VALUES(?,?,?)");
        stm.setObject(1,vehicleStock.getVehicleID());
        stm.setObject(2,vehicleStock.getvStoreDate());
        stm.setObject(3,vehicleStock.getQtyOnVehicle());

        try {
            if (stm.executeUpdate()>0){

                if (saveVehicleStoreItem(vehicleStock.getVehicleID(),vehicleStock.getItems())){

                    con.commit();
                    return true;

                }else {

                    con.rollback();
                    return false;

                }

            }else {

                con.rollback();
                return false;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }finally {
            con.setAutoCommit(true);
        }
        return false;
    }

    private boolean saveVehicleStoreItem(String vehicleID, ArrayList<VehicleStockItem> vehicleStockItems) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        for (VehicleStockItem temp : vehicleStockItems){

            PreparedStatement stm = con.prepareStatement("INSERT INTO Store VALUES(?,?,?,?)");
            stm.setObject(1,vehicleID);
            stm.setObject(2,temp.getItemCode());
            stm.setObject(3,temp.getvStoreDate());
            stm.setObject(4,temp.getStoreItemQty());

            if (stm.executeUpdate()>0){

                if (updateItemQtyStock(temp.getItemCode(), temp.getStoreItemQty())){

                }else {
                    return false;
                }

            }else {
                return false;
            }
        }
        return true;
    }

    private boolean updateItemQtyStock(String itemCode, int storeItemQty) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE StockItem SET qtyOnStock=(qtyOnStock-" + storeItemQty + ") WHERE itemCode ='" + itemCode + "'");

        return stm.executeUpdate()>0;
    }

    @Override
    public ObservableList<VehicleStockTM> getAllVehicleStock() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM VehicleStock").executeQuery();
        ObservableList<VehicleStockTM> vehicleStock = FXCollections.observableArrayList();

        while (rst.next()){
            vehicleStock.add(new VehicleStockTM(
                    rst.getString(1),
                    rst.getString(2),
                    Integer.parseInt(rst.getString(3))
            ));
        }
        return vehicleStock;
    }

    @Override
    public ObservableList<VehicleStockItemTM> getAllVehicleStockItem(String vehicleID) throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement(
                        "SELECT i.itemCode, i.itemName, i.itemDescription, s.storeItemQTY FROM StockItem i JOIN Store s ON s.itemCode=i.itemCode WHERE s.vehicleID = '" + vehicleID + "'").
                executeQuery();

        ObservableList<VehicleStockItemTM> vehicleStockItem = FXCollections.observableArrayList();
        while (rst.next()){
            vehicleStockItem.add(new VehicleStockItemTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return vehicleStockItem;
    }
}
