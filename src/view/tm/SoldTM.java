package view.tm;

public class SoldTM {
    private String customerID;
    private String soldDate;
    private int totalQty;
    private double totalPrice;

    public SoldTM() {
    }

    public SoldTM(String customerID, String soldDate, int totalQty, double totalPrice) {
        this.customerID = customerID;
        this.soldDate = soldDate;
        this.totalQty = totalQty;
        this.totalPrice = totalPrice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "SoldTM{" +
                "customerID='" + customerID + '\'' +
                ", soldDate='" + soldDate + '\'' +
                ", totalQty=" + totalQty +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
