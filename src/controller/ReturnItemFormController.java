package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Employee;
import model.Item;
import model.ReturnItem;
import service.CustomerController;
import service.EmployeeController;
import service.ItemController;
import service.ReturnItemController;
import view.tm.ReturnItemTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReturnItemFormController {
    public JFXComboBox<String> cmbCustomerID;
    public JFXTextField txtCustomerName;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemDescription;
    public JFXTextField txtReturnDate;
    public JFXTextField txtQtyOnReturn;

    public TableView<ReturnItemTM> tblReturnItem;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colReturnDate;
    public TableColumn colQtyOnReturn;

    public void initialize(){

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colQtyOnReturn.setCellValueFactory(new PropertyValueFactory<>("qtyOnReturn"));

        try {

            loadCustomerIdCombo();

            loadItemIdCombo();

            loadReturnItemToTable();


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                setCustomerDataTxt(newValue);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                setItemDataTxt(newValue);
                
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    private void loadReturnItemToTable() throws SQLException, ClassNotFoundException {

        ObservableList<ReturnItemTM> obList = new ReturnItemController().getAllReturnItem();
        tblReturnItem.setItems(obList);
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtReturnDate.setText(format.format(date));
    }

    private void setCustomerDataTxt(String customerID) throws SQLException, ClassNotFoundException {

        Customer c1 = new CustomerController().getCustomer(customerID);
        if (c1 == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            txtCustomerName.setText(c1.getCustomerName());
            loadDate();
        }
    }

    private void setItemDataTxt(String itemCode) throws SQLException, ClassNotFoundException {

        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null){

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        }else {
            txtItemName.setText(i1.getItemName());
            txtItemDescription.setText(i1.getItemDescription());
        }
    }

    private void loadCustomerIdCombo() throws SQLException, ClassNotFoundException {

        List<String> customerID = new CustomerController().getCustomerID();
        cmbCustomerID.getItems().setAll(customerID);
    }

    private void loadItemIdCombo() throws SQLException, ClassNotFoundException {

        List<String> itemID = new ItemController().getItemID();
        cmbItemCode.getItems().setAll(itemID);
    }

    public void btnAddReturnItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ReturnItem r1 = new ReturnItem(
                cmbCustomerID.getValue(),
                cmbItemCode.getValue(),
                txtReturnDate.getText(),
                Integer.parseInt(txtQtyOnReturn.getText())
        );
        if (new ReturnItemController().saveReturnItem(r1)){

            new Alert(Alert.AlertType.CONFIRMATION,"Save Success..").show();

            loadReturnItemToTable();

        }else {

            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }
    }
}
