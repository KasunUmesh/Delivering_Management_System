package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ReturnItem;
import view.tm.ReturnItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnItemController implements ReturnItemService{
    @Override
    public boolean saveReturnItem(ReturnItem r) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Returns VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,r.getCustomerID());
        stm.setObject(2,r.getItemCode());
        stm.setObject(3,r.getReturnDate());
        stm.setObject(4,r.getQtyOnReturn());

        return stm.executeUpdate()>0;
    }

    @Override
    public ObservableList<ReturnItemTM> getAllReturnItem() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement(
                        "SELECT c.customerID,c.customerName,i.itemCode,i.itemName,i.itemDescription,r.returnDate,r.qtyOnReturn FROM CustomerShop c JOIN Returns r ON r.customerID=c.customerID JOIN StockItem i ON r.itemCode=i.itemCode").
                executeQuery();

        ObservableList<ReturnItemTM> list = FXCollections.observableArrayList();

        while (rst.next()){
            list.add(new ReturnItemTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getInt(7)
            ));
        }
        return list;
    }
}
