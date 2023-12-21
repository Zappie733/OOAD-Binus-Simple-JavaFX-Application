package model;

public class MenuItem {
	private int menuItemId;
    private String menuItemName;
    private String menuItemDescription;
    private Double menuItemPrice;

	public MenuItem() {
		// TODO Auto-generated constructor stub
	}

	public int getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}
	public String getMenuItemName() {
		return menuItemName;
	}
	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}
	public String getMenuItemDescription() {
		return menuItemDescription;
	}
	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}
	public Double getMenuItemPrice() {
		return menuItemPrice;
	}
	public void setMenuItemPrice(Double menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}

	
}
