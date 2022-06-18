package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import model.OrderItem;
import view.tm.OrderItemListTM;
import view.tm.OrderTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController implements OrderService{
    @Override
    public boolean saveOrders(Order order) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        PreparedStatement stm = con.prepareStatement("INSERT INTO CompanyOrder VALUES(?,?,?)");
        stm.setObject(1,order.getOrderNumber());
        stm.setObject(2,order.getOrderDate());
        stm.setObject(3,order.getOrderItemQty());

        try {
            if (stm.executeUpdate()>0){

                if (saveOrderItem(order.getOrderNumber(),order.getItems())){

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
        } finally{
            con.setAutoCommit(true);
        }
        return false;
    }

    private boolean saveOrderItem(String orderNumber, ArrayList<OrderItem> items) throws SQLException, ClassNotFoundException {

        for (OrderItem temp : items){
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO PlaceOrder VALUES(?,?,?)");
            stm.setObject(1,orderNumber);
            stm.setObject(2,temp.getItemCode());
            stm.setObject(3,temp.getItemQty());

            if (stm.executeUpdate()>0){

            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public ObservableList<OrderTM> getAllOrder() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM CompanyOrder").executeQuery();
        ObservableList<OrderTM> order = FXCollections.observableArrayList();

        while (rst.next()){
            order.add(new OrderTM(
                    rst.getString(1),
                    rst.getString(2),
                    Integer.parseInt(rst.getString(3))
            ));
        }
        return order;
    }

    @Override
    public ObservableList<OrderItemListTM> getAllOrderItem(String orderNumber) throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement(
                        "SELECT i.itemCode, i.itemName, i.itemDescription, p.itemQTY FROM StockItem i JOIN PlaceOrder p ON p.itemCode=i.itemCode WHERE p.orderNumber = '"+orderNumber+"'").executeQuery();

        ObservableList<OrderItemListTM> orderItemList = FXCollections.observableArrayList();
        while (rst.next()){
            orderItemList.add(new OrderItemListTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return orderItemList;
    }

    @Override
    public String getOrderNumber() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT orderNumber FROM CompanyOrder ORDER BY orderNumber DESC LIMIT 1").executeQuery();

        if (rst.next()){

            int tempID = Integer.parseInt(rst.getString(1).split("-")[1]);

            tempID = tempID+1;
            if (tempID<9){

                return "O-00"+tempID;

            }else if (tempID<99){
                return "O-0"+tempID;

            }else {
                return "O-"+tempID;
            }


        }else {
            return "O-001";
        }

    }

}
