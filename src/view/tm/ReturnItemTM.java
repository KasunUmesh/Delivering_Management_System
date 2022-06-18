package view.tm;

public class ReturnItemTM {
    private String customerID;
    private String customerName;
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private String returnDate;
    private int qtyOnReturn;

    public ReturnItemTM() {
    }

    public ReturnItemTM(String customerID, String customerName, String itemCode, String itemName, String itemDescription, String returnDate, int qtyOnReturn) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.returnDate = returnDate;
        this.qtyOnReturn = qtyOnReturn;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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
        return "ReturnItemTM{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", qtyOnReturn=" + qtyOnReturn +
                '}';
    }
}
