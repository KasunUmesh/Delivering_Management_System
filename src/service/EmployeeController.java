package service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeAttend;
import view.tm.EmployeeAttendTM;
import view.tm.EmployeeTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeService {

    @Override
    public boolean saveEmployee(Employee e) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Employee VALUES(?,?,?,?,?)");

        stm.setObject(1,e.getEmployeeID());
        stm.setObject(2,e.getName());
        stm.setObject(3,e.getAddress());
        stm.setObject(4,e.getContactNumber());
        stm.setObject(5,e.getPosition());

        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee");
        ResultSet rst = stm.executeQuery();
        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()){
            employees.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(String employeeID) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Employee WHERE employeeID=?");
        stm.setString(1,employeeID);

        return stm.executeUpdate()>0;
    }

    @Override
    public Employee getEmployee(String employeeID) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee WHERE employeeID=?");
        stm.setObject(1,employeeID);
        ResultSet rst = stm.executeQuery();

        if (rst.next()){
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean updateEmployee(Employee e) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE Employee SET name=?, address=?, contactNumber=?, position=? WHERE employeeID=?");
        stm.setObject(1,e.getName());
        stm.setObject(2,e.getAddress());
        stm.setObject(3,e.getContactNumber());
        stm.setObject(4,e.getPosition());
        stm.setObject(5,e.getEmployeeID());

        return stm.executeUpdate()>0;
    }

    @Override
    public ObservableList<EmployeeTM> searchEmployee(String value) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Employee WHERE CONCAT(employeeID, name) LIKE '%" + value + "%'");
        ResultSet rst = stm.executeQuery();
        ObservableList<EmployeeTM> employee = FXCollections.observableArrayList();

        while (rst.next()){
            employee.add(new EmployeeTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return employee;
    }

    @Override
    public List<String> getEmployeeID() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(
                    rst.getString(1)
            );
        }
        return id;
    }

    @Override
    public boolean saveEmployeeAttend(EmployeeAttend a) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO EmpAttendance VALUES(?,?,?,?)");

        stm.setObject(1,a.getEmployeeID());
        stm.setObject(2,a.getEmployeeName());
        stm.setObject(3,a.getAttendDate());
        stm.setObject(4,a.getAttend());

        return stm.executeUpdate()>0;
    }

    @Override
    public ArrayList<EmployeeAttend> getAllEmployeeAttend() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM EmpAttendance");
        ResultSet rst = stm.executeQuery();
        ArrayList<EmployeeAttend> employeeAttend = new ArrayList<>();
        while (rst.next()){
            employeeAttend.add(new EmployeeAttend(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return employeeAttend;
    }

    @Override
    public boolean deleteEmployeeAttend(String employeeID) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM EmpAttendance WHERE employeeID=?");
        stm.setString(1,employeeID);

        return stm.executeUpdate()>0;
    }

    @Override
    public ObservableList<EmployeeAttendTM> searchEmployeeAttend(String value) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM EmpAttendance WHERE CONCAT(employeeID, employeeName, attendDate) LIKE '%" + value + "%'");
        ResultSet rst = stm.executeQuery();
        ObservableList<EmployeeAttendTM> employeeAttend = FXCollections.observableArrayList();

        while (rst.next()){
            employeeAttend.add(new EmployeeAttendTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return employeeAttend;
    }

}
