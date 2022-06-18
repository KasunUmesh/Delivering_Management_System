package controller;

import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class LoginFormController {

    public AnchorPane root;

    public void btnMinimizeOnMouseClick(MouseEvent mouseEvent) {
        Stage stage= (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }

    public void btnCancelOnMouseClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
        Scene scene = new Scene(parent);

        Stage stage = new Stage();
        Stage stage1 = (Stage) this.root.getScene().getWindow();
        stage1.close();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(parent.isManaged());
        stage.show();

    }
}
