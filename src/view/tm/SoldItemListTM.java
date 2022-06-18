package view.tm;

public class SoldItemListTM {
    private String itemCode;
    private String itemName;
    private String soldDate;
    private int itemQry;

    public SoldItemListTM() {
    }

    public SoldItemListTM(String itemCode, String itemName, String soldDate, int itemQry) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.soldDate = soldDate;
        this.itemQry = itemQry;
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

    public int getItemQry() {
        return itemQry;
    }

    public void setItemQry(int itemQry) {
        this.itemQry = itemQry;
    }

    @Override
    public String toString() {
        return "SoldItemListTM{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", soldDate='" + soldDate + '\'' +
                ", itemQry=" + itemQry +
                '}';
    }
}
