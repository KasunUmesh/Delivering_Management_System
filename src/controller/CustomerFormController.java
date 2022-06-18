package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Customer;
import model.Employee;
import service.CustomerController;
import service.EmployeeController;
import view.tm.CustomerTM;
import view.tm.EmployeeTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CustomerFormController {
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerName;
    public JFXTextField txtShopName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNumber;

    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colShopName;
    public TableColumn colAddress;
    public TableColumn colContactNumber;


    public JFXTextField txtSearchCustomer;

    CustomerTM selectedCustomer = null;

    Pattern CID = Pattern.compile("^[C][0-9]{3,}$");
    Pattern CName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern CShopName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern CMobile=Pattern.compile("^[0-9]{9,10}$");
    Pattern CAddress=Pattern.compile("^[A-z0-9/,. ]{4,150}$");


    public void initialize(){

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        try {

            setCustomerToTable(new CustomerController().getAllCustomer());

            tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null){
                    selectedCustomer = newValue;
                }
            });

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setCustomerToTable(ArrayList<Customer> customers){
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        customers.forEach(c -> {
            obList.add(new CustomerTM(
                    c.getCustomerID(),
                    c.getCustomerName(),
                    c.getShopName(),
                    c.getAddress(),
                    c.getContactNumber()
            ));
        });
        tblCustomer.setItems(obList);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new Validation().patternValidation(CID,txtCustomerID) &
                new Validation().patternValidation(CName,txtCustomerName) &
                new Validation().patternValidation(CShopName,txtShopName) &
                new Validation().patternValidation(CMobile,txtContactNumber) &
                new Validation().patternValidation(CAddress,txtAddress)
        ) {
            Customer c1 = new Customer(
                    txtCustomerID.getText(),
                    txtCustomerName.getText(),
                    txtShopName.getText(),
                    txtAddress.getText(),
                    txtContactNumber.getText()
            );
            if (new CustomerController().saveCustomer(c1)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();

                setCustomerToTable(new CustomerController().getAllCustomer());

                txtCustomerID.clear();
                txtCustomerName.clear();
                txtShopName.clear();
                txtAddress.clear();
                txtContactNumber.clear();
                txtCustomerID.requestFocus();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();

                txtCustomerID.clear();
                txtCustomerName.clear();
                txtShopName.clear();
                txtAddress.clear();
                txtContactNumber.clear();
                txtCustomerID.requestFocus();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Entered").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new Customer(
                txtCustomerID.getText(),
                txtCustomerName.getText(),
                txtShopName.getText(),
                txtAddress.getText(),
                txtContactNumber.getText()
        );
        if (new CustomerController().updateCustomer(c1)){

            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..").show();

            setCustomerToTable(new CustomerController().getAllCustomer());

            txtCustomerID.clear();
            txtCustomerName.clear();
            txtShopName.clear();
            txtAddress.clear();
            txtContactNumber.clear();

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new CustomerController().getCustomer(selectedCustomer.getCustomerID());
        if (c1 == null){

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        }else {
            setData(c1);
        }
    }

    void setData(Customer c){
        txtCustomerID.setText(c.getCustomerID());
        txtCustomerName.setText(c.getCustomerName());
        txtShopName.setText(c.getShopName());
        txtAddress.setText(c.getAddress());
        txtContactNumber.setText(c.getContactNumber());
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (selectedCustomer != null){
            if (new CustomerController().deleteCustomer(selectedCustomer.getCustomerID())){

                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();

                setCustomerToTable(new CustomerController().getAllCustomer());

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }
    }

    public void txtSearchCustomerOnKey(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        ObservableList<CustomerTM> customer = new CustomerController().searchCustomer(txtSearchCustomer.getText());
        tblCustomer.setItems(customer);
    }
}
