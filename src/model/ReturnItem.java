package model;

public class ReturnItem {
    private String customerID;
    private String itemCode;
    private String returnDate;
    private int qtyOnReturn;

    public ReturnItem() {
    }

    public ReturnItem(String customerID, String itemCode, String returnDate, int qtyOnReturn) {
        this.customerID = customerID;
        this.itemCode = itemCode;
        this.returnDate = returnDate;
        this.qtyOnReturn = qtyOnReturn;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getQtyOnReturn() {
        return qtyOnReturn;
    }

    public void setQtyOnReturn(int qtyOnReturn) {
        this.qtyOnReturn = qtyOnReturn;
    }

    @Override
    public String toString() {
        return "ReturnItem{" +
                "customerID='" + customerID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", qtyOnReturn=" + qtyOnReturn +
                '}';
    }
}
