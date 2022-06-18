package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Item;
import service.HomeController;
import service.ItemController;
import view.tm.ItemStockTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class HomePageFormController {
    public Label lblDate;
    public Label lblTime;
    public Label lblIncome;
    public Label lblWorking;
    public Label lblNotWorking;
    public Label lblAllEmployee;
    public Label lblVehicle;

    public TableView<ItemStockTM> tblItemList;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnStock;

    public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnStock.setCellValueFactory(new PropertyValueFactory<>("qtyOnStock"));

        try {

            setItemToTable(new ItemController().getAllItem());

            lblIncome.setText(String.valueOf(new HomeController().getIncome()));

            lblVehicle.setText(String.valueOf(new HomeController().getAllVehicle()));

            lblAllEmployee.setText(String.valueOf(new HomeController().getAllEmployee()));

            int [] attend = new HomeController().getAttend();
            lblWorking.setText(String.valueOf(attend[0]));
            lblNotWorking.setText(String.valueOf(attend[1]));



        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        loadDateAndTime();
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
        tblItemList.setItems(obList);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() +" : "+ currentTime.getMinute() +" : "+ currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

    }
}
