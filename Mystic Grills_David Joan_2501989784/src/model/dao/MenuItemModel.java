package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MenuItem;

public class MenuItemModel {
    DatabaseConnector dbConnector = DatabaseConnector.getConnection();

    public Boolean createMenuItem(String menuItemName, String menuItemDescription, Double menuItemPrice) {
        Boolean isSuccess = false;
    	try {
            PreparedStatement preparedStatement = dbConnector.prepare("INSERT INTO menuitems (menuItemName, menuItemDescription, menuItemPrice) VALUES (?, ?, ?)");
            preparedStatement.setString(1, menuItemName);
            preparedStatement.setString(2, menuItemDescription);
            preparedStatement.setDouble(3, menuItemPrice);

            dbConnector.executePreparedQueryIUD(preparedStatement);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return isSuccess;
    }

    public Boolean updateMenuItem(int menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
        Boolean isSuccess = false;
        try {
            PreparedStatement preparedStatement = dbConnector.prepare("UPDATE menuitems SET menuItemName = ?, menuItemDescription = ?, menuItemPrice = ? WHERE menuItemId = ?");
            preparedStatement.setString(1, menuItemName);
            preparedStatement.setString(2, menuItemDescription);
            preparedStatement.setDouble(3, menuItemPrice);
            preparedStatement.setInt(4, menuItemId);

            dbConnector.executePreparedQueryIUD(preparedStatement);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public Boolean deleteMenuItem(int menuItemId) {
        Boolean isSuccess = false;
        try {
            PreparedStatement preparedStatement = dbConnector.prepare("DELETE FROM menuitems WHERE menuItemId = ?");
            preparedStatement.setInt(1, menuItemId);

            dbConnector.executePreparedQueryIUD(preparedStatement);
            isSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public ArrayList<MenuItem> getAllMenuItems() {
    	ArrayList<MenuItem> menuItems = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM menuitems");
            ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

            while (resultSet.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setMenuItemId(resultSet.getInt("menuItemId"));
                menuItem.setMenuItemName(resultSet.getString("menuItemName"));
                menuItem.setMenuItemDescription(resultSet.getString("menuItemDescription"));
                menuItem.setMenuItemPrice(resultSet.getDouble("menuItemPrice"));

                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public MenuItem getMenuItemById(int menuItemId) {
        MenuItem menuItem = new MenuItem();
        try {
            PreparedStatement preparedStatement = dbConnector.prepare("SELECT * FROM menuitems WHERE menuItemId = ?");
            preparedStatement.setInt(1, menuItemId);

            ResultSet resultSet = dbConnector.executePreparedQueryS(preparedStatement);

            if (resultSet.next()) {
                menuItem.setMenuItemId(resultSet.getInt("menuItemId"));
                menuItem.setMenuItemName(resultSet.getString("menuItemName"));
                menuItem.setMenuItemDescription(resultSet.getString("menuItemDescription"));
                menuItem.setMenuItemPrice(resultSet.getDouble("menuItemPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItem;
    }
}
