package service;

import javafx.collections.ObservableList;
import model.Customer;
import view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String customerID) throws SQLException, ClassNotFoundException;
    public Customer getCustomer(String customerID) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public ObservableList<CustomerTM> searchCustomer(String value) throws SQLException, ClassNotFoundException;
    public List<String> getCustomerID() throws SQLException, ClassNotFoundException;
}
