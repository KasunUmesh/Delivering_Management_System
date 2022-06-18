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
import model.Vehicle;
import service.EmployeeController;
import service.VehicleController;
import view.tm.EmployeeTM;
import view.tm.VehicleTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class VehicleFormController {
    public JFXTextField txtVehicleID;
    public JFXTextField txtVehicleNumber;
    public JFXTextField txtVehicleType;
    public JFXTextField txtDescription;

    public TableView<VehicleTM> tblVehicle;
    public TableColumn colVehicleID;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDescription;
    public JFXTextField txtSearchVehicle;

    VehicleTM selectedVehicle = null;

    Pattern VID = Pattern.compile("^[V][0-9]{3,}$");
    Pattern VNumber=Pattern.compile("^[A-Z]{2}[0-9]{4}$");
    Pattern VType=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern VDescription=Pattern.compile("^[A-z0-9/,. ]{4,150}$");

    public void initialize(){

        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {

            setVehicleToTable(new VehicleController().getAllVehicle());

            tblVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null){
                    selectedVehicle = newValue;
                }
            });

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setVehicleToTable(ArrayList<Vehicle> vehicles){
        ObservableList<VehicleTM> obList = FXCollections.observableArrayList();
        vehicles.forEach(v -> {
            obList.add(new VehicleTM(
                    v.getVehicleID(),
                    v.getVehicleNumber(),
                    v.getVehicleType(),
                    v.getDescription()
            ));
        });
        tblVehicle.setItems(obList);

    }


    public void btnAddVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new Validation().patternValidation(VID,txtVehicleID) &
                new Validation().patternValidation(VNumber,txtVehicleNumber) &
                new Validation().patternValidation(VType,txtVehicleType) &
                new Validation().patternValidation(VDescription,txtDescription)
        ) {

            Vehicle v1 = new Vehicle(
                    txtVehicleID.getText(),
                    txtVehicleNumber.getText(),
                    txtVehicleType.getText(),
                    txtDescription.getText()
            );
            if (new VehicleController().saveVehicle(v1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();

                setVehicleToTable(new VehicleController().getAllVehicle());

                txtVehicleID.clear();
                txtVehicleNumber.clear();
                txtVehicleType.clear();
                txtDescription.clear();
                txtVehicleID.requestFocus();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();

                txtVehicleID.clear();
                txtVehicleNumber.clear();
                txtVehicleType.clear();
                txtDescription.clear();
                txtVehicleID.requestFocus();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Entered").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle v1 = new Vehicle(
                txtVehicleID.getText(),
                txtVehicleNumber.getText(),
                txtVehicleType.getText(),
                txtDescription.getText()
        );
        if (new VehicleController().updateVehicle(v1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..").show();

            setVehicleToTable(new VehicleController().getAllVehicle());

            txtVehicleID.clear();
            txtVehicleNumber.clear();
            txtVehicleType.clear();
            txtDescription.clear();

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle v1 = new VehicleController().getVehicle(selectedVehicle.getVehicleID());
        if (v1 == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            setData(v1);
        }
    }

    void setData(Vehicle v){
        txtVehicleID.setText(v.getVehicleID());
        txtVehicleNumber.setText(v.getVehicleNumber());
        txtVehicleType.setText(v.getVehicleType());
        txtDescription.setText(v.getDescription());
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (selectedVehicle != null){
            if (new VehicleController().deleteVehicle(selectedVehicle.getVehicleID())){

                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();
                setVehicleToTable(new VehicleController().getAllVehicle());

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }
    }

    public void txtSearchVehicleOnKey(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        ObservableList<VehicleTM> vehicle = new VehicleController().searchVehicle(txtSearchVehicle.getText());
        tblVehicle.setItems(vehicle);
    }
}
