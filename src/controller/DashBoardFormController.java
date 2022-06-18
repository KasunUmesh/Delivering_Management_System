package controller;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public StackPane rootHome;
    public StackPane rootFullPage;

    public void initialize(){

        try {
            StackPane pane = FXMLLoader.load(getClass().getResource("../view/HomePageForm.fxml"));
            rootHome.getChildren().setAll(pane);
            pane.setMaxWidth(1634);
            pane.setMaxHeight(1010);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/EmployeeForm.fxml"));
        rootHome.getChildren().setAll(pane);
        //pane.setMaxWidth(1634);
        //pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/HomePageForm.fxml"));
        rootHome.getChildren().setAll(pane);
        pane.setMaxWidth(1634);
        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/VehicleForm.fxml"));
        rootHome.getChildren().setAll(pane);
        pane.setMaxWidth(1634);
        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
        
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"));
        rootHome.getChildren().setAll(pane);
        pane.setMaxWidth(1634);
        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }

    public void btnItemOrderOnAtion(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/ItemOrderForm.fxml"));
        rootHome.getChildren().setAll(pane);
        pane.setMaxWidth(1634);
        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }

    public void btnItemStockOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/ItemStockForm.fxml"));
        rootHome.getChildren().setAll(pane);
        //pane.setMaxWidth(1634);
        //pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }

    public void btnVehicleStockOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/VehicleStockForm.fxml"));
        rootHome.getChildren().setAll(pane);
        pane.setMaxWidth(1634);
        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }

    public void btnSoldItemOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/SoldItemForm.fxml"));
        rootHome.getChildren().setAll(pane);
        pane.setMaxWidth(1634);
        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }

    public void btnReturnItemOnAction(ActionEvent actionEvent) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("../view/ReturnItemForm.fxml"));
        rootHome.getChildren().setAll(pane);
        pane.setMaxWidth(1634);
        pane.setMaxHeight(1010);

        new FadeIn(rootHome).play();
    }
}
