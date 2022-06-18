package view.tm;

public class EmployeeTM {
    private String employeeID;
    private String name;
    private String address;
    private String contactNumber;
    private String position;

    public EmployeeTM() {

    }

    public EmployeeTM(String employeeID, String name, String address, String contactNumber, String position) {
        this.employeeID = employeeID;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.position = position;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "employeeID='" + employeeID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
