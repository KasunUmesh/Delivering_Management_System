package service;

import javafx.collections.ObservableList;
import model.ReturnItem;
import view.tm.ReturnItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReturnItemService {
    public boolean saveReturnItem(ReturnItem r) throws SQLException, ClassNotFoundException;
    public ObservableList<ReturnItemTM> getAllReturnItem() throws SQLException, ClassNotFoundException;
}
