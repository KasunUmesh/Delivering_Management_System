package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Item;
import view.tm.ItemStockTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService {

    @Override
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO StockItem VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,i.getItemCode());
        stm.setObject(2,i.getItemName());
        stm.setObject(3,i.getItemDescription());
        stm.setObject(4,i.getUnitPrice());
        stm.setObject(5,i.getQtyOnStock());

        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM StockItem");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()){
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    Double.parseDouble(rst.getString(4)),
                    Integer.parseInt(rst.getString(5))
            ));
        }
        return items;
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "DELETE FROM StockItem WHERE itemCode=?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,itemCode);

        return stm.executeUpdate()>0;

    }

    @Override
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE StockItem SET itemName=?, itemDescription=?, unitPrice=?, qtyOnStock=? WHERE itemCode=?");
        stm.setObject(1,i.getItemName());
        stm.setObject(2,i.getItemDescription());
        stm.setObject(3,i.getUnitPrice());
        stm.setObject(4,i.getQtyOnStock());
        stm.setObject(5,i.getItemCode());

        return stm.executeUpdate()>0;
    }

    @Override
    public Item getItem(String itemCode) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM StockItem WHERE itemCode=?");
        stm.setObject(1,itemCode);

        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    Double.parseDouble(rst.getString(4)),
                    Integer.parseInt(rst.getString(5))
            );
        }else {
            return null;
        }
    }

    @Override
    public ObservableList<ItemStockTM> searchItem(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement stm = con.prepareStatement("SELECT * FROM StockItem WHERE CONCAT(itemCode, itemName, itemDescription) LIKE '%" + value + "%'");
        ResultSet rst = stm.executeQuery();
        ObservableList<ItemStockTM> items = FXCollections.observableArrayList();

        while (rst.next()){
            items.add(new ItemStockTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    Double.parseDouble(rst.getString(4)),
                    Integer.parseInt(rst.getString(5))
            ));
        }
        return items;
    }

    @Override
    public List<String> getItemID() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM StockItem").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(
                    rst.getString(1)
            );
        }
        return id;
    }

}
