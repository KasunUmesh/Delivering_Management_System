package service;

import javafx.collections.ObservableList;
import model.Item;
import view.tm.ItemStockTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemService {
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException;
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException;
    public Item getItem(String itemCode) throws SQLException, ClassNotFoundException;
    public ObservableList<ItemStockTM> searchItem(String value) throws SQLException, ClassNotFoundException;
    public List<String> getItemID() throws SQLException, ClassNotFoundException;
}
