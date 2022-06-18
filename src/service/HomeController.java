package service;

import controller.DashBoardFormController;
import db.DbConnection;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeController {
    public double getIncome() throws SQLException, ClassNotFoundException {
        double allIncome = 0;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SoldItem").executeQuery();
        while (rst.next()){
            allIncome+=rst.getDouble(4);
        }
        return allIncome;
    }

    public int[] getAttend() throws SQLException, ClassNotFoundException {
        int workingAttend=0;
        int notWorkingAttend=0;
        int [] attend = new int[2];

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM EmpAttendance WHERE attendDate =? ");
        stm.setObject(1,format.format(date));
        ResultSet rst = stm.executeQuery();

        while (rst.next()){
            if (rst.getString(4).equals("Yes")){
                workingAttend++;
            }else{
                notWorkingAttend++;
            }

        }
        attend[0] = workingAttend;
        attend[1] = notWorkingAttend;
        return attend;
    }

    public int getAllVehicle() throws SQLException, ClassNotFoundException {

        int vehicle=0;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Vehicle").executeQuery();
        while (rst.next()){
            vehicle++;
            rst.getString(1);
        }
        return vehicle;
    }

    public int getAllEmployee() throws SQLException, ClassNotFoundException {
        int employee = 0;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee").executeQuery();
        while (rst.next()){
            employee++;
            rst.getString(1);
        }
        return employee;
    }
}
