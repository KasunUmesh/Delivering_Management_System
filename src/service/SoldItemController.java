package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Sold;
import model.SoldItem;
import view.tm.OrderItemListTM;
import view.tm.OrderTM;
import view.tm.SoldItemListTM;
import view.tm.SoldTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SoldItemController implements SoldItemService{
    @Override
    public boolean saveSold(Sold sold) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        PreparedStatement stm = con.prepareStatement("INSERT INTO SoldItem VALUES(?,?,?,?)");
        stm.setObject(1,sold.getCustomerID());
        stm.setObject(2,sold.getSoldDate());
        stm.setObject(3,sold.getTotalQty());
        stm.setObject(4,sold.getTotalPrice());

        try {
            if (stm.executeUpdate()>0){
                if (saveSoldItem(sold.getCustomerID(), sold.getItems())){
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

    private boolean saveSoldItem(String customerID, ArrayList<SoldItem> soldItems) throws SQLException, ClassNotFoundException {

        for (SoldItem temp : soldItems){
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Sold VALUES(?,?,?,?)");
            stm.setObject(1,customerID);
            stm.setObject(2,temp.getSoldDate());
            stm.setObject(3,temp.getItemCode());
            stm.setObject(4,temp.getSoldItemQty());

            if (stm.executeUpdate()>0){

            }else {
                return false;
            }
        }
        return true;

    }

    @Override
    public ObservableList<SoldTM> getAllSold() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SoldItem").executeQuery();
        ObservableList<SoldTM> sold = FXCollections.observableArrayList();

        while (rst.next()){
            sold.add(new SoldTM(
                    rst.getString(1),
                    rst.getString(2),
                    Integer.parseInt(rst.getString(3)),
                    Double.parseDouble(rst.getString(4))
            ));
        }
        return sold;
    }

    @Override
    public ObservableList<SoldItemListTM> getAllSoldItem(String customerID) throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement(
                        "SELECT i.itemCode, i.itemName, s.soldDate, s.soldItemQty FROM StockItem i JOIN Sold s ON s.itemCode=i.itemCode WHERE s.customerID = '"+customerID+"'").executeQuery();

        ObservableList<SoldItemListTM> soldItemList = FXCollections.observableArrayList();
        while (rst.next()){
            soldItemList.add(new SoldItemListTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return soldItemList;

    }
}

