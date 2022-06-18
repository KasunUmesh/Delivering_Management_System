package model;

public class SoldItem {
    private String customerID;
    private String soldDate;
    private String itemCode;
    private int soldItemQty;

    public SoldItem() {
    }

    public SoldItem(String customerID, String soldDate, String itemCode, int soldItemQty) {
        this.customerID = customerID;
        this.soldDate = soldDate;
        this.itemCode = itemCode;
        this.soldItemQty = soldItemQty;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getSoldItemQty() {
        return soldItemQty;
    }

    public void setSoldItemQty(int soldItemQty) {
        this.soldItemQty = soldItemQty;
    }

    @Override
    public String toString() {
        return "SoldItem{" +
                "customerID='" + customerID + '\'' +
                ", soldDate='" + soldDate + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", soldItemQty=" + soldItemQty +
                '}';
    }
}
