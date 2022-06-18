package view.tm;

public class EmployeeAttendTM {
    private String employeeID;
    private String employeeName;
    private String attendDate;
    private String attend;

    public EmployeeAttendTM() {

    }

    public EmployeeAttendTM(String employeeID, String employeeName, String attendDate, String attend) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.attendDate = attendDate;
        this.attend = attend;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(String attendDate) {
        this.attendDate = attendDate;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    @Override
    public String toString() {
        return "EmployeeAttendTM{" +
                "employeeID='" + employeeID + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", attendDate='" + attendDate + '\'' +
                ", attend='" + attend + '\'' +
                '}';
    }
}
