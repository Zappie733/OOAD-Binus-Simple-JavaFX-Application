package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MenuItem;
import model.dao.MenuItemModel;

public class MenuItemController {
    private MenuItemModel menuItemModel;

    public MenuItemController() {
        this.menuItemModel = new MenuItemModel();
    }

    public String createMenuItem(String menuItemName, String menuItemDescription, Double menuItemPrice) {
    	Boolean isMenuItemNameUnique = isMenuItemNameUnique(menuItemName);
    	
    	if(menuItemName.isEmpty()) {
			return "Name can not be empty";
		}
    	
    	if(!isMenuItemNameUnique) {
			return "Name has already been used";
		}
    	
    	if(menuItemDescription.isEmpty()) {
			return "Description can not be empty";
		}
    	
    	if(menuItemDescription.length() <= 10) {
			return "Descripton must be more than 10 characters long";
		}
    	
		if(menuItemPrice == null) {
			return "Prive is invalid, (ex valid: 0.0)";
		} 
		
    	if(menuItemPrice < 2.5) {
			return "Price must be at least 2.5";
		}
    	
		Boolean isSuccess = menuItemModel.createMenuItem(menuItemName, menuItemDescription, menuItemPrice);
    	if(isSuccess) {
    		return "Create Menu Item Success";
    	}
   
    	return "Create Menu Item Failed";
    }
    
    public boolean isMenuItemNameUnique(String menuItemName) { 
        ArrayList<MenuItem> allMenuItems = menuItemModel.getAllMenuItems();

        for (MenuItem menuItem : allMenuItems) {
        	
            if (menuItem.getMenuItemName().equalsIgnoreCase(menuItemName)) {
                return false;
            }
        }
        return true;
    }

    public String updateMenuItem(int menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
    	MenuItem menuItem = getMenuItemById(menuItemId);
    	Boolean isMenuItemNameUnique = isMenuItemNameUnique(menuItemName);
    	
    	if(menuItemId != 0) {
    		if(menuItemName.isEmpty()) {
    			return "Name can not be empty";
    		}
    		
    		if(!menuItemName.equalsIgnoreCase(menuItem.getMenuItemName())) {
    			if(!isMenuItemNameUnique) {
        			return "Name has already been used";
    			}
    		}
    		
    		if(menuItemDescription.isEmpty()) {
    			return "Description can not be empty";
    		}
    		
    		if(menuItemDescription.length() <= 10) {
    			return "Descripton must be more than 10 characters long";
    		} 
    		
    		if(menuItemPrice == null) {
    			return "Prive is invalid, (ex valid: 0.0)";
    		} 
    		
    		if(menuItemPrice < 2.5) {
    			return "Price must be at least 2.5";
    		} 
    		
			Boolean isSuccess = menuItemModel.updateMenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice);
			if(isSuccess) {
				return "Update Menu Item Success";
			}
    		
    		
    		return "Update Menu Item Failed";
    	}
    	return "Nothing to update";
    	
    }

    public String deleteMenuItem(int menuItemId) {
    	if(menuItemId != 0) {
        	Boolean isSuccess = menuItemModel.deleteMenuItem(menuItemId);
        	if(isSuccess) {
        		return "Delete Menu Item Success";
        	}
        	return "Delete Menu Item Failed";	
    	}
    	return "Nothing to delete";
    }

    public ObservableList<MenuItem> getAllMenuItems() {
        return FXCollections.observableArrayList(menuItemModel.getAllMenuItems());
    }

    public MenuItem getMenuItemById(int menuItemId) {
        return menuItemModel.getMenuItemById(menuItemId);
    }
    
    public void refreshTableData(TableView<MenuItem> tableView, TextField menuItemIdTextField, TextField menuItemNameTextField, TextField menuItemDescriptionTextField, TextField menuItemPriceTextField) {
	    ObservableList<MenuItem> updatedMenuItems = getAllMenuItems();
	    tableView.setItems(updatedMenuItems);
	    
	    menuItemIdTextField.setText("");
	    menuItemNameTextField.setText("");
	    menuItemDescriptionTextField.setText("");
	    menuItemPriceTextField.setText("");
	}

}
