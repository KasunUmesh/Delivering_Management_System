package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import view.tm.CustomerTM;
import view.tm.EmployeeTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService{
    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO CustomerShop VALUES (?,?,?,?,?)");

        stm.setObject(1,c.getCustomerID());
        stm.setObject(2,c.getCustomerName());
        stm.setObject(3,c.getShopName());
        stm.setObject(4,c.getAddress());
        stm.setObject(5,c.getContactNumber());

        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM CustomerShop");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()){
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return customers;

    }

    @Override
    public boolean deleteCustomer(String customerID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM CustomerShop WHERE customerID=?");
        stm.setString(1,customerID);

        return stm.executeUpdate()>0;

    }

    @Override
    public Customer getCustomer(String customerID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM CustomerShop WHERE customerID=?");
        stm.setObject(1,customerID);
        ResultSet rst = stm.executeQuery();

        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE CustomerShop SET customerName=?, shopName=?, address=?, contactNumber=? WHERE customerID=?");

        stm.setObject(1,c.getCustomerName());
        stm.setObject(2,c.getShopName());
        stm.setObject(3,c.getAddress());
        stm.setObject(4,c.getContactNumber());
        stm.setObject(5,c.getCustomerID());

        return stm.executeUpdate()>0;
    }

    @Override
    public ObservableList<CustomerTM> searchCustomer(String value) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM CustomerShop WHERE CONCAT(customerID, customerName, shopName) LIKE '%" + value + "%'");
        ResultSet rst = stm.executeQuery();
        ObservableList<CustomerTM> customer = FXCollections.observableArrayList();

        while (rst.next()){
            customer.add(new CustomerTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return customer;
    }

    @Override
    public List<String> getCustomerID() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM CustomerShop").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(
                    rst.getString(1)
            );
        }
        return id;
    }
}
