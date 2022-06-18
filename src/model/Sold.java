package model;

import java.util.ArrayList;

public class Sold {
    private String customerID;
    private String soldDate;
    private int totalQty;
    private double totalPrice;
    private ArrayList<SoldItem> items;

    public Sold() {
    }

    public Sold(String customerID, String soldDate, int totalQty, double totalPrice, ArrayList<SoldItem> items) {
        this.customerID = customerID;
        this.soldDate = soldDate;
        this.totalQty = totalQty;
        this.totalPrice = totalPrice;
        this.items = items;
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

    public ArrayList<SoldItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SoldItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Sold{" +
                "customerID='" + customerID + '\'' +
                ", soldDate='" + soldDate + '\'' +
                ", totalQty=" + totalQty +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }
}

