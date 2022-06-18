package service;

import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeAttend;
import view.tm.EmployeeAttendTM;
import view.tm.EmployeeTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    public boolean saveEmployee(Employee e) throws SQLException, ClassNotFoundException;
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;
    public boolean deleteEmployee(String employeeID) throws SQLException, ClassNotFoundException;
    public Employee getEmployee(String employeeID) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(Employee e) throws SQLException, ClassNotFoundException;
    public ObservableList<EmployeeTM> searchEmployee(String value) throws SQLException, ClassNotFoundException;
    public List<String> getEmployeeID() throws SQLException, ClassNotFoundException;
    public boolean saveEmployeeAttend(EmployeeAttend a) throws SQLException, ClassNotFoundException;
    public ArrayList<EmployeeAttend> getAllEmployeeAttend() throws SQLException, ClassNotFoundException;
    public boolean deleteEmployeeAttend(String employeeID) throws SQLException, ClassNotFoundException;
    public ObservableList<EmployeeAttendTM> searchEmployeeAttend(String value) throws SQLException, ClassNotFoundException;
}
