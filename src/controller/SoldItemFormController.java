package controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Customer;
import model.Item;
import model.Sold;
import model.SoldItem;
import service.CustomerController;
import service.ItemController;
import service.OrderController;
import service.SoldItemController;
import view.tm.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class SoldItemFormController {
    public JFXButton btnViewSoldItem;
    public JFXButton btnViewAddItem;

    public Pane pnSoldItem;
    public Pane pnSoldItemList;
    public Pane pnAddSoldItem;
    
    public JFXComboBox<String> cmbCustomerID;
    public JFXComboBox<String> cmbItemCode;
    
    public JFXTextField txtCustomerName;
    public JFXTextField txtItemName;
    public JFXTextField txtSoldDate;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtSoldItemQty;
    
    public TableView<AddSoldItemTM> tblAddSoldItem;
    public TableColumn colCustomerID;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colSoldDate;
    public TableColumn colItemQty;
    public TableColumn colTotalPrice;
    public Label lblTotalCost;
    
    public TableView<SoldItemListTM> tblSoldItemList;
    public TableColumn colSoldItemCode;
    public TableColumn colSoldItemName;
    public TableColumn colListSoldDate;
    public TableColumn colSoldItemQty;
    
    public TableView<SoldTM> tblSoldItem;
    public TableColumn colSoldCustomerID;
    public TableColumn colSoldItemDate;
    public TableColumn colSoldTotalQty;
    public TableColumn colSoldTotalPrice;

    int storeAddItemRemove = -1;

    Pattern SQty = Pattern.compile("^[0-9]{1,}$");


    public void initialize(){

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colSoldDate.setCellValueFactory(new PropertyValueFactory<>("soldDate"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        colSoldCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colSoldItemDate.setCellValueFactory(new PropertyValueFactory<>("soldDate"));
        colSoldTotalQty.setCellValueFactory(new PropertyValueFactory<>("totalQty"));
        colSoldTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        colSoldItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colSoldItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colListSoldDate.setCellValueFactory(new PropertyValueFactory<>("soldDate"));
        colSoldItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQry"));

        try {

            loadDate();

            loadItemCodeCombo();

            loadCustomerIDCombo();

            setSoldItemToTable();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {

                setItemDataTxt(newValue);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {

                setCustomerDataTxt(newValue);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        tblAddSoldItem.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            storeAddItemRemove = (int) newValue;
        });

        tblSoldItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                setSoldItemListToTable(newValue.getCustomerID());

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        pnAddSoldItem.setVisible(true);
        btnViewSoldItem.setVisible(true);
        btnViewAddItem.setVisible(false);
        pnSoldItemList.setVisible(false);
        pnSoldItem.setVisible(false);
    }

    private void setSoldItemToTable() throws SQLException, ClassNotFoundException {

        ObservableList<SoldTM> sold = new SoldItemController().getAllSold();
        tblSoldItem.setItems(sold);

    }

    private void setSoldItemListToTable(String customerID) throws SQLException, ClassNotFoundException {

        ObservableList<SoldItemListTM> soldItemList = new SoldItemController().getAllSoldItem(customerID);
        tblSoldItemList.setItems(soldItemList);
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtSoldDate.setText(format.format(date));
    }

    private void loadItemCodeCombo() throws SQLException, ClassNotFoundException {

        List<String> itemCode = new ItemController().getItemID();
        cmbItemCode.getItems().setAll(itemCode);
    }

    private void loadCustomerIDCombo() throws SQLException, ClassNotFoundException {

        List<String> customerId = new CustomerController().getCustomerID();
        cmbCustomerID.getItems().setAll(customerId);
    }

    private void setItemDataTxt(String itemCode) throws SQLException, ClassNotFoundException {

        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            txtItemName.setText(i1.getItemName());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));

        }
    }

    private void setCustomerDataTxt(String customerID) throws SQLException, ClassNotFoundException {

        Customer c1 = new CustomerController().getCustomer(customerID);
        if (c1 == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            txtCustomerName.setText(c1.getCustomerName());
        }
    }

    public void btnViewSoldItemOnAction(ActionEvent actionEvent) {

        pnSoldItemList.setVisible(true);
        new ZoomIn(pnSoldItemList).play();
        pnSoldItem.setVisible(true);
        new ZoomIn(pnSoldItem).play();
        btnViewAddItem.setVisible(true);

        pnAddSoldItem.setVisible(false);
        btnViewSoldItem.setVisible(false);
    }

    public void btnViewAddItemOnAction(ActionEvent actionEvent) {

        pnAddSoldItem.setVisible(true);
        new ZoomIn(pnAddSoldItem).play();
        btnViewSoldItem.setVisible(true);

        pnSoldItemList.setVisible(false);
        pnSoldItem.setVisible(false);
        btnViewAddItem.setVisible(false);
    }

    ObservableList<AddSoldItemTM> obList = FXCollections.observableArrayList();
    public void btnAddOnAction(ActionEvent actionEvent) {

        if (new Validation().patternValidation(SQty,txtSoldItemQty) &
                cmbCustomerID.getSelectionModel().getSelectedIndex()>=0 &
                cmbItemCode.getSelectionModel().getSelectedIndex()>=0
        ) {

            String itemName = txtItemName.getText();
            String soldDate = txtSoldDate.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int itemQty = Integer.parseInt(txtSoldItemQty.getText());
            double total = itemQty * unitPrice;

            AddSoldItemTM addSoldItem = new AddSoldItemTM(
                    cmbCustomerID.getValue(),
                    cmbItemCode.getValue(),
                    itemName,
                    soldDate,
                    itemQty,
                    total
            );

            int rowNumber = isAddItem(addSoldItem);

            if (rowNumber == -1) {
                obList.add(addSoldItem);
            } else {
                AddSoldItemTM temp = obList.get(rowNumber);
                AddSoldItemTM newAddSoldItem = new AddSoldItemTM(
                        temp.getCustomerID(),
                        temp.getItemCode(),
                        temp.getItemName(),
                        temp.getSoldDate(),
                        temp.getItemQty() + itemQty,
                        temp.getTotalPrice() + total
                );
                obList.remove(rowNumber);
                obList.add(newAddSoldItem);
            }
            tblAddSoldItem.setItems(obList);
            calculateTotalCost();

        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Entered").show();
        }
    }


    private int isAddItem(AddSoldItemTM addSoldItem){

        for (int i = 0; i < obList.size(); i++) {
            if (addSoldItem.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    public void btnRemoveAddItemOnAction(ActionEvent actionEvent) {

        if (storeAddItemRemove == -1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else {
            obList.remove(storeAddItemRemove);
            calculateTotalCost();
            tblAddSoldItem.refresh();
        }
    }

    void calculateTotalCost(){
        double total=0;
        for (AddSoldItemTM addSoldItem : obList){
            total+=addSoldItem.getTotalPrice();
        }
        lblTotalCost.setText("Rs."+total);
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<SoldItem> soldItems = new ArrayList<>();
        double total = 0;
        int soldItemQty =0;
        for (AddSoldItemTM addSoldItem : obList){
            total+=addSoldItem.getTotalPrice();
            soldItemQty+=addSoldItem.getItemQty();
            soldItems.add(new SoldItem(
                    addSoldItem.getCustomerID(),
                    addSoldItem.getSoldDate(),
                    addSoldItem.getItemCode(),
                    addSoldItem.getItemQty()
            ));
        }

        Sold sold = new Sold(
                cmbCustomerID.getValue(),
                txtSoldDate.getText(),
                soldItemQty,
                total,
                soldItems
        );

        if (new SoldItemController().saveSold(sold)){

            new Alert(Alert.AlertType.CONFIRMATION,"Save Success..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
}
