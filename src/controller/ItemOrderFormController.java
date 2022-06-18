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
import model.Item;
import model.Order;
import model.OrderItem;
import service.ItemController;
import service.OrderController;
import view.tm.ItemListTM;
import view.tm.OrderItemListTM;
import view.tm.OrderTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ItemOrderFormController {
    public JFXTextField txtItemDescription;
    public JFXTextField txtOrderNumber;
    public JFXTextField txtItemName;
    public JFXTextField txtItemQty;
    public JFXComboBox<String> cmbItemCode;
    
    public TableView<ItemListTM> tblItemList;
    public TableColumn colOrderNumber;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colItemQty;
    
    public JFXButton btnViewOrderDetail;
    public JFXButton btnViewAddItem;
    
    public Pane pnOrderDetails;
    public Pane pnOrderItemList;
    public Pane pnAddItemList;

    public Label lblDate;
    
    public TableView<OrderTM> tblOrder;
    public TableColumn colOrderNumberOrdertb;
    public TableColumn colOrderDate;
    public TableColumn colOrderItemQty;

    public TableView<OrderItemListTM> tblOrderItemList;
    public TableColumn colOrderItemCode;
    public TableColumn colOrderItemName;
    public TableColumn colOrderItemDescription;
    public TableColumn colOrderDetailItemQty;

    Pattern OrderQty = Pattern.compile("^[0-9]{1,}$");


    int orderAddItemRemove = -1;

    public void initialize(){

        colOrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

        colOrderNumberOrdertb.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOrderItemQty.setCellValueFactory(new PropertyValueFactory<>("orderItemQty"));

        colOrderItemCode.setCellValueFactory(new PropertyValueFactory<>("orderItemCode"));
        colOrderItemName.setCellValueFactory(new PropertyValueFactory<>("orderItemName"));
        colOrderItemDescription.setCellValueFactory(new PropertyValueFactory<>("orderItemDescription"));
        colOrderDetailItemQty.setCellValueFactory(new PropertyValueFactory<>("orderDetailItemQty"));

        loadDate();

        try {

            loadItemCodeCombo();

            setOrderToTable();

            setOrderNumber();




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

        tblItemList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            orderAddItemRemove = (int) newValue;
        });

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                setOrderItemToTable(newValue.getOrderNumber());

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        pnAddItemList.setVisible(true);
        btnViewOrderDetail.setVisible(true);
        btnViewAddItem.setVisible(false);
        pnOrderDetails.setVisible(false);
        pnOrderItemList.setVisible(false);

    }

    private void setOrderToTable() throws SQLException, ClassNotFoundException {

        ObservableList<OrderTM> order = new OrderController().getAllOrder();
        tblOrder.setItems(order);

    }

    private void setOrderItemToTable(String orderNumber) throws SQLException, ClassNotFoundException {

        ObservableList<OrderItemListTM> orderItemList = new OrderController().getAllOrderItem(orderNumber);
        tblOrderItemList.setItems(orderItemList);
    }

    private void setOrderNumber() throws SQLException, ClassNotFoundException {
        txtOrderNumber.setText(new OrderController().getOrderNumber());
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));
    }

    private void loadItemCodeCombo() throws SQLException, ClassNotFoundException {

        List<String> itemCode = new ItemController().getItemID();
        cmbItemCode.getItems().setAll(itemCode);
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

    ObservableList<ItemListTM> obList = FXCollections.observableArrayList();
    public void btnAddOnAction(ActionEvent actionEvent) {

        if (
                new Validation().patternValidation(OrderQty,txtItemQty) & cmbItemCode.getSelectionModel().getSelectedIndex()>=0
        ) {

            String orderNumber = txtOrderNumber.getText();
            String itemName = txtItemName.getText();
            String description = txtItemDescription.getText();
            int itemQty = Integer.parseInt(txtItemQty.getText());

            ItemListTM itemList = new ItemListTM(
                    orderNumber,
                    cmbItemCode.getValue(),
                    itemName,
                    description,
                    itemQty
            );

            int rowNumber = isAddItem(itemList);

            if (rowNumber == -1) {
                obList.add(itemList);
            } else {
                ItemListTM temp = obList.get(rowNumber);
                ItemListTM newItemList = new ItemListTM(
                        temp.getOrderNumber(),
                        temp.getItemCode(),
                        temp.getItemName(),
                        temp.getItemDescription(),
                        temp.getItemQty() + itemQty
                );
                obList.remove(rowNumber);
                obList.add(newItemList);
            }

            tblItemList.setItems(obList);

        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Entered").show();
        }
    }

    private int isAddItem(ItemListTM itemList){

        for (int i = 0; i < obList.size(); i++) {
            if (itemList.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    public void btnViewOrderDetailOnAction(ActionEvent actionEvent) {

        pnOrderDetails.setVisible(true);
        new ZoomIn(pnOrderDetails).play();
        pnOrderItemList.setVisible(true);
        new ZoomIn(pnOrderItemList).play();
        btnViewAddItem.setVisible(true);

        pnAddItemList.setVisible(false);
        btnViewOrderDetail.setVisible(false);


    }

    public void btnViewAddItemOnAction(ActionEvent actionEvent) {

        pnAddItemList.setVisible(true);
        new ZoomIn(pnAddItemList).play();
        btnViewOrderDetail.setVisible(true);

        pnOrderDetails.setVisible(false);
        pnOrderItemList.setVisible(false);
        btnViewAddItem.setVisible(false);
    }

    public void btnRemoveAddItemOnAction(ActionEvent actionEvent) {

        if (orderAddItemRemove == -1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Row").show();
        }else {
            obList.remove(orderAddItemRemove);
            tblItemList.refresh();
        }
    }

    public void btnConfirmOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<OrderItem> items = new ArrayList<>();
        int orderItemQty =0;
        for (ItemListTM itemList : obList){
            orderItemQty+=1;
            items.add(new OrderItem(

                    itemList.getOrderNumber(),
                    itemList.getItemCode(),
                    itemList.getItemQty()

            ));
        }

        Order order = new Order(
                txtOrderNumber.getText(),
                lblDate.getText(),
                orderItemQty,
                items
        );

        if (new OrderController().saveOrders(order)){

            new Alert(Alert.AlertType.CONFIRMATION,"Save Success..").show();

            setOrderToTable();
            setOrderNumber();

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }
}
