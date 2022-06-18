package controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Employee;
import model.EmployeeAttend;
import service.EmployeeController;
import view.tm.EmployeeAttendTM;
import view.tm.EmployeeTM;
import view.tm.ItemStockTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public Pane pnViewAttend;
    public Pane pnViewEmployee;

    public JFXTextField txtEmployeeID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNumber;
    public JFXTextField txtPosition;

    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEmployeeID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContactNumber;
    public TableColumn colPosition;
    
    public JFXTextField txtSearchEmployee;
    public JFXComboBox<String> cmbEmployeeID;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtAttendDate;
    public JFXComboBox<String> cmbAttend;

    public TableView<EmployeeAttendTM> tblEmployeeAttend;
    public TableColumn colEmployeeIDAttend;
    public TableColumn colNameAttend;
    public TableColumn colDateAttend;
    public TableColumn colAttendance;
    
    public JFXTextField txtSearchAttend;

    EmployeeTM selectedEmployee = null;
    EmployeeAttendTM selectedEmployeeAttend = null;

    Pattern EID = Pattern.compile("^[E][0-9]{3,}$");
    Pattern EName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern EAddress=Pattern.compile("^[A-z0-9/,. ]{4,150}$");
    Pattern EMobile=Pattern.compile("^[0-9]{9,10}$");


    public void initialize(){

        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

        colEmployeeIDAttend.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colNameAttend.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colDateAttend.setCellValueFactory(new PropertyValueFactory<>("attendDate"));
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("attend"));


        try {

            setEmployeeToTable(new EmployeeController().getAllEmployee());

            setEmployeeAttendToTable(new EmployeeController().getAllEmployeeAttend());

            loadEmployeeID();

            loadAttendCombo();

            tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null){
                    selectedEmployee = newValue;
                }
            });

            tblEmployeeAttend.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null){
                    selectedEmployeeAttend = newValue;
                }
            });


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        cmbEmployeeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                setEmployeeData(newValue);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });



        pnViewAttend.setVisible(true);
        pnViewEmployee.setVisible(false);

    }

    private void loadAttendCombo(){
        ObservableList<String> list = FXCollections.observableArrayList("Yes", "No");
        cmbAttend.setItems(list);
    }

    private void setEmployeeToTable(ArrayList<Employee> employees){
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
        employees.forEach(e -> {
            obList.add(new EmployeeTM(
                    e.getEmployeeID(),
                    e.getName(),
                    e.getAddress(),
                    e.getContactNumber(),
                    e.getPosition()
            ));
        });
        tblEmployee.setItems(obList);

    }

    private void setEmployeeAttendToTable(ArrayList<EmployeeAttend> employeeAttend){
        ObservableList<EmployeeAttendTM> obList = FXCollections.observableArrayList();
        employeeAttend.forEach(e -> {
            obList.add(new EmployeeAttendTM(
                    e.getEmployeeID(),
                    e.getEmployeeName(),
                    e.getAttendDate(),
                    e.getAttend()
            ));
        });
        tblEmployeeAttend.setItems(obList);
    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtAttendDate.setText(format.format(date));
    }

    private void loadEmployeeID() throws SQLException, ClassNotFoundException {
        List<String> employeeID = new EmployeeController().getEmployeeID();
        cmbEmployeeID.getItems().setAll(employeeID);
    }

    private void setEmployeeData(String employeeID) throws SQLException, ClassNotFoundException {

        Employee e1 = new EmployeeController().getEmployee(employeeID);
        if (e1 == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            txtEmployeeName.setText(e1.getName());
            loadDate();
        }
    }

    public void btnViewEmployeeOnAction(ActionEvent actionEvent) {
        pnViewEmployee.setVisible(true);
        new ZoomIn(pnViewEmployee).play();
        pnViewAttend.setVisible(false);

    }

    public void btnViewAttendOnAction(ActionEvent actionEvent) {
        pnViewAttend.setVisible(true);
        new ZoomIn(pnViewAttend).play();
        pnViewEmployee.setVisible(false);
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new Validation().patternValidation(EID,txtEmployeeID) &
                new Validation().patternValidation(EName,txtName) &
                new Validation().patternValidation(EAddress,txtAddress) &
                new Validation().patternValidation(EMobile,txtContactNumber) &
                new Validation().patternValidation(EName,txtPosition)

        ) {


            Employee e1 = new Employee(
                    txtEmployeeID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContactNumber.getText(),
                    txtPosition.getText()
            );
            if (new EmployeeController().saveEmployee(e1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();

                setEmployeeToTable(new EmployeeController().getAllEmployee());

                txtEmployeeID.clear();
                txtName.clear();
                txtAddress.clear();
                txtContactNumber.clear();
                txtPosition.clear();
                txtEmployeeID.requestFocus();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();

                txtEmployeeID.clear();
                txtName.clear();
                txtAddress.clear();
                txtContactNumber.clear();
                txtPosition.clear();
                txtEmployeeID.requestFocus();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Entered").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Employee e1 = new Employee(
                txtEmployeeID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContactNumber.getText(),
                txtPosition.getText()
        );
        if (new EmployeeController().updateEmployee(e1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..").show();

            setEmployeeToTable(new EmployeeController().getAllEmployee());

            txtEmployeeID.clear();
            txtName.clear();
            txtAddress.clear();
            txtContactNumber.clear();
            txtPosition.clear();

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Employee e1 = new EmployeeController().getEmployee(selectedEmployee.getEmployeeID());
        if (e1 == null){

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        }else {
            setData(e1);
        }
    }

    void setData(Employee e){
        txtEmployeeID.setText(e.getEmployeeID());
        txtName.setText(e.getName());
        txtAddress.setText(e.getAddress());
        txtContactNumber.setText(e.getContactNumber());
        txtPosition.setText(e.getPosition());
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (selectedEmployee != null){
            if (new EmployeeController().deleteEmployee(selectedEmployee.getEmployeeID())){

                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();

                setEmployeeToTable(new EmployeeController().getAllEmployee());

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }
    }

    public void txtSearchEmployeeOnKeyRelease(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        ObservableList<EmployeeTM> employee = new EmployeeController().searchEmployee(txtSearchEmployee.getText());
        tblEmployee.setItems(employee);
    }

    public void btnAddAttendOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (cmbEmployeeID.getSelectionModel().getSelectedIndex()>=0 & cmbAttend.getSelectionModel().getSelectedIndex()>=0) {

            EmployeeAttend a1 = new EmployeeAttend(
                    cmbEmployeeID.getValue(),
                    txtEmployeeName.getText(),
                    txtAttendDate.getText(),
                    cmbAttend.getValue()
            );
            if (new EmployeeController().saveEmployeeAttend(a1)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Save Success..").show();

                setEmployeeAttendToTable(new EmployeeController().getAllEmployeeAttend());


            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();

            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please Select ComboBox").show();
        }
    }

    public void btnRemoveAttendOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (selectedEmployeeAttend != null){
            if (new EmployeeController().deleteEmployeeAttend(selectedEmployeeAttend.getEmployeeID())){

                new Alert(Alert.AlertType.CONFIRMATION, "Delete Success..").show();

                setEmployeeAttendToTable(new EmployeeController().getAllEmployeeAttend());

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }
    }

    public void txtSearchAttendOnKey(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeAttendTM> employeeAttend = new EmployeeController().searchEmployeeAttend(txtSearchAttend.getText());
        tblEmployeeAttend.setItems(employeeAttend);
    }
}
