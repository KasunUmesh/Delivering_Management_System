package view.tm;

public class AddSoldItemTM {
    private String customerID;
    private String itemCode;
    private String itemName;
    private String soldDate;
    private int itemQty;
    private Double totalPrice;

    public AddSoldItemTM() {
    }

    public AddSoldItemTM(String customerID, String itemCode, String itemName, String soldDate, int itemQty, Double totalPrice) {
        this.customerID = customerID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.soldDate = soldDate;
        this.itemQty = itemQty;
        this.totalPrice = totalPrice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "AddSoldItemTM{" +
                "customerID='" + customerID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", soldDate='" + soldDate + '\'' +
                ", itemQty=" + itemQty +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
