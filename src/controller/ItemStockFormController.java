package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Item;
import service.ItemController;
import view.tm.ItemStockTM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ItemStockFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQTYOnStock;
    
    public TableView<ItemStockTM> tblItemStock;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnStock;

    public JFXTextField txtSearch;

    ItemStockTM selectedItem = null;

    Pattern SID = Pattern.compile("^[I][0-9]{3,}$");
    Pattern SName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern SDescription=Pattern.compile("^[A-z0-9/,. ]{4,150}$");
    Pattern SUnitPrice = Pattern.compile("^[0-9]{1,}$");
    Pattern SQty = Pattern.compile("^[0-9]{1,}$");

    public void initialize(){

        try {

            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQtyOnStock.setCellValueFactory(new PropertyValueFactory<>("qtyOnStock"));

            setItemToTable(new ItemController().getAllItem());

            tblItemStock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null){
                    selectedItem = newValue;
                }
            });


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setItemToTable(ArrayList<Item> items){
        ObservableList<ItemStockTM> obList = FXCollections.observableArrayList();
        items.forEach(e->{
            obList.add(new ItemStockTM(
                    e.getItemCode(),
                    e.getItemName(),
                    e.getItemDescription(),
                    e.getUnitPrice(),
                    e.getQtyOnStock()
            ));
        });
        tblItemStock.setItems(obList);
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new Validation().patternValidation(SID,txtItemCode) &
                new Validation().patternValidation(SName,txtItemName) &
                new Validation().patternValidation(SDescription,txtItemDescription) &
                new Validation().patternValidation(SUnitPrice,txtUnitPrice) &
                new Validation().patternValidation(SQty,txtQTYOnStock)


        ) {
            Item i1 = new Item(
                    txtItemCode.getText(),
                    txtItemName.getText(),
                    txtItemDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQTYOnStock.getText())
            );
            if (new ItemController().saveItem(i1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();

                setItemToTable(new ItemController().getAllItem());

                txtItemCode.clear();
                txtItemName.clear();
                txtItemDescription.clear();
                txtUnitPrice.clear();
                txtQTYOnStock.clear();
                txtItemCode.requestFocus();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();

                txtItemCode.clear();
                txtItemName.clear();
                txtItemDescription.clear();
                txtUnitPrice.clear();
                txtQTYOnStock.clear();
                txtItemCode.requestFocus();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Entered").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1 = new Item(
                txtItemCode.getText(),
                txtItemName.getText(),
                txtItemDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQTYOnStock.getText())
        );
        if (new ItemController().updateItem(i1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..").show();
            setItemToTable(new ItemController().getAllItem());

            txtItemCode.clear();
            txtItemName.clear();
            txtItemDescription.clear();
            txtUnitPrice.clear();
            txtQTYOnStock.clear();

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }

    }

    public void btnRemoveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (selectedItem != null){
            if (new ItemController().deleteItem(selectedItem.getItemCode())){

                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();

                setItemToTable(new ItemController().getAllItem());

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item i1 = new ItemController().getItem(selectedItem.getItemCode());
        if (i1 == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            setData(i1);
        }
    }

    public void txtSearchOnKeyRelease(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        ObservableList<ItemStockTM> items = new ItemController().searchItem(txtSearch.getText());
        tblItemStock.setItems(items);

    }

    void setData(Item i){
        txtItemCode.setText(i.getItemCode());
        txtItemName.setText(i.getItemName());
        txtItemDescription.setText(i.getItemDescription());
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
        txtQTYOnStock.setText(String.valueOf(i.getQtyOnStock()));

    }


}
