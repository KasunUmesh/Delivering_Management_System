package controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Item;
import model.VehicleStock;
import model.VehicleStockItem;
import service.ItemController;
import service.VehicleController;
import service.VehicleStockController;
import view.tm.VehicleAddItemListTM;
import view.tm.VehicleStockItemTM;
import view.tm.VehicleStockTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class VehicleStockFormController {
    public Pane pnVehicleStock;
    public Pane pnVehicleItemList;
    public Pane pnAddItemList;

    public JFXButton btnViewVehicleItem;
    public JFXButton btnViewAddItem;

    public JFXTextField txtItemName;
    public JFXTextField txtItemDescription;
    public JFXTextField txtStoreDate;
    public JFXTextField txtStoreItemQty;
    public JFXTextField txtStockItemQty;

    public JFXComboBox<String> cmbItemCode;
    public JFXComboBox<String> cmbVehicleID;

    public TableView<VehicleAddItemListTM> tblAddItemList;
    public TableColumn colVehicleID;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colItemQty;

    public TableView<VehicleStockTM> tblVehicleItemStock;
    public TableColumn colStockVehicleID;
    public TableColumn colVStoreDate;
    public TableColumn colQtyOnVehicle;

    public TableView<VehicleStockItemTM> tblVehicleItemList;
    public TableColumn colVehicleItemCode;
    public TableColumn colVehicleItemName;
    public TableColumn colVehicleItemDescription;
    public TableColumn colVehicleItemQty;

    int orderAddItemRemove = -1;

    Pattern SQty = Pattern.compile("^[0-9]{1,}$");


    public void initialize(){

        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

        colStockVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        colVStoreDate.setCellValueFactory(new PropertyValueFactory<>("vehicleStoreDate"));
        colQtyOnVehicle.setCellValueFactory(new PropertyValueFactory<>("qtyOnVehicle"));

        colVehicleItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colVehicleItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colVehicleItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colVehicleItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

        loadDate();

        try {

            loadItemCodeCombo();

            setVehicleStockToTable();

            loadVehicleIDCombo();

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

        tblAddItemList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            orderAddItemRemove = (int) newValue;
        });

        tblVehicleItemStock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {

                setVehicleStockItemToTable(newValue.getVehicleID());

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        pnAddItemList.setVisible(true);
        btnViewVehicleItem.setVisible(true);
        btnViewAddItem.setVisible(false);
        pnVehicleItemList.setVisible(false);
        pnVehicleStock.setVisible(false);
    }

    private void setVehicleStockToTable() throws SQLException, ClassNotFoundException {

        ObservableList<VehicleStockTM> vehicleStock = new VehicleStockController().getAllVehicleStock();
        tblVehicleItemStock.setItems(vehicleStock);
    }

    private void setVehicleStockItemToTable(String vehicleID) throws SQLException, ClassNotFoundException {

        ObservableList<VehicleStockItemTM> vehicleStockItem = new VehicleStockController().getAllVehicleStockItem(vehicleID);
        tblVehicleItemList.setItems(vehicleStockItem);
    }

    private void setItemDataTxt(String itemCode) throws SQLException, ClassNotFoundException {

        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            txtItemName.setText(i1.getItemName());
            txtItemDescription.setText(i1.getItemDescription());
            txtStockItemQty.setText(String.valueOf(i1.getQtyOnStock()));

        }
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtStoreDate.setText(format.format(date));
    }

    private void loadItemCodeCombo() throws SQLException, ClassNotFoundException {

        List<String> itemCode = new ItemController().getItemID();
        cmbItemCode.getItems().setAll(itemCode);
    }

    private void loadVehicleIDCombo() throws SQLException, ClassNotFoundException {

        List<String> vehicleID = new VehicleController().getVehicleID();
        cmbVehicleID.getItems().setAll(vehicleID);
    }

    public void btnViewVehicleItemOnAction(ActionEvent actionEvent) {

        pnVehicleStock.setVisible(true);
        new ZoomIn(pnVehicleStock).play();
        pnVehicleItemList.setVisible(true);
        new ZoomIn(pnVehicleItemList).play();
        btnViewAddItem.setVisible(true);

        pnAddItemList.setVisible(false);
        btnViewVehicleItem.setVisible(false);
    }

    public void btnViewAddItemOnAction(ActionEvent actionEvent) {

        pnAddItemList.setVisible(true);
        new ZoomIn(pnAddItemList).play();
        btnViewVehicleItem.setVisible(true);

        pnVehicleStock.setVisible(false);
        pnVehicleItemList.setVisible(false);
        btnViewAddItem.setVisible(false);
    }

    ObservableList<VehicleAddItemListTM> obList = FXCollections.observableArrayList();
    public void btnAddItemOnAction(ActionEvent actionEvent) {

        if (new Validation().patternValidation(SQty,txtStoreItemQty) &
                cmbVehicleID.getSelectionModel().getSelectedIndex()>=0 &
                cmbItemCode.getSelectionModel().getSelectedIndex()>=0

        ) {

            String itemName = txtItemName.getText();
            String description = txtItemDescription.getText();
            int itemQty = Integer.parseInt(txtStoreItemQty.getText());
            int stockItemQty = Integer.parseInt(txtStockItemQty.getText());

            if (stockItemQty < itemQty) {
                new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
                return;
            }


            VehicleAddItemListTM vehicleAddItemList = new VehicleAddItemListTM(
                    cmbVehicleID.getValue(),
                    cmbItemCode.getValue(),
                    itemName,
                    description,
                    itemQty
            );

            int rowNumber = isAddItem(vehicleAddItemList);

            if (rowNumber == -1) {
                obList.add(vehicleAddItemList);

            } else {

                VehicleAddItemListTM temp = obList.get(rowNumber);
                VehicleAddItemListTM newVehicleAddItemList = new VehicleAddItemListTM(
                        temp.getVehicleID(),
                        temp.getItemCode(),
                        temp.getItemName(),
                        temp.getItemDescription(),
                        temp.getItemQty() + itemQty
                );
                obList.remove(rowNumber);
                obList.add(newVehicleAddItemList);
            }
            tblAddItemList.setItems(obList);

        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Entered").show();
        }
    }

    private int isAddItem(VehicleAddItemListTM vehicleAddItemList){

        for (int i = 0; i < obList.size(); i++) {
            if (vehicleAddItemList.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    public void btnRemoveItemOnAction(ActionEvent actionEvent) {
        if (orderAddItemRemove == -1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else {
            obList.remove(orderAddItemRemove);
            tblAddItemList.refresh();
        }
    }

    public void btnConfirmOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<VehicleStockItem> vehicleStockItems = new ArrayList<>();
        int vehicleItemQty = 0;
        for (VehicleAddItemListTM vehicleAddItemList : obList){

            vehicleItemQty+=1;
            vehicleStockItems.add(new VehicleStockItem(
                    vehicleAddItemList.getVehicleID(),
                    vehicleAddItemList.getItemCode(),
                    txtStoreDate.getText(),
                    vehicleAddItemList.getItemQty()
            ));
        }

        VehicleStock vehicleStock = new VehicleStock(
                cmbVehicleID.getValue(),
                txtStoreDate.getText(),
                vehicleItemQty,
                vehicleStockItems
        );

        if (new VehicleStockController().saveVehicleStore(vehicleStock)){

            new Alert(Alert.AlertType.CONFIRMATION,"Save Success..").show();

            setVehicleStockToTable();

        }else {

            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
}
