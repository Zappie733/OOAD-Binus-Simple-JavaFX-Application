package main;

import model.User;

public class SessionManager {
    private static User currentUser = new User();
    private static String currentPage = null;
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

	public static String getCurrentPage() {
		return currentPage;
	}

	public static void setCurrentPage(String currentPage) {
		SessionManager.currentPage = currentPage;
	}
    
	public static void endSession() {
		setCurrentUser(new User());
		setCurrentPage(null);
	}
}

