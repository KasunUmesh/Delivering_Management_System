package service;

import javafx.collections.ObservableList;
import model.Sold;
import view.tm.SoldItemListTM;
import view.tm.SoldTM;

import java.sql.SQLException;

public interface SoldItemService {
    public boolean saveSold(Sold sold) throws SQLException, ClassNotFoundException;
    public ObservableList<SoldTM> getAllSold() throws SQLException, ClassNotFoundException;
    public ObservableList<SoldItemListTM> getAllSoldItem(String customerID) throws SQLException, ClassNotFoundException;
}
