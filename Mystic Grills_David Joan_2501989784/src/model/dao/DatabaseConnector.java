package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String HOST = "localhost:3306";
	private final String DATABASE = "mysticgrills";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatements;
	
	private static DatabaseConnector dbConnector;
	
	private DatabaseConnector() {
		// TODO Auto-generated constructor stub
		try {
			connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//singleton
	public static synchronized DatabaseConnector getConnection() {
		if(dbConnector == null) {
			synchronized (DatabaseConnector.class) {
				if(dbConnector== null) {
					dbConnector = new DatabaseConnector();
				}
			}
		}
		//kalau sudah ada langsung return
		return dbConnector;
	}

	
	//untuk menyiapkan PreparedStatement query
	public PreparedStatement prepare(String query) {
		preparedStatements = null;
		try {
			preparedStatements = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatements;
	}
	
	public PreparedStatement prepare(String query, int returnGeneratedKeys) {
	    PreparedStatement preparedStatement = null;
	    try {
	        preparedStatement = connection.prepareStatement(query, returnGeneratedKeys);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return preparedStatement;
	}
	
	//buat query SELECT
	public ResultSet executePreparedQueryS (PreparedStatement ps) {
		resultSet = null;
		try {
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	//buat query selain SELECT (INSERT, UPDATE, DELETE)
	public void executePreparedQueryIUD (PreparedStatement ps) {
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//digunakan sebelum menutup app nanti
	public void closeConnections() {
	    try {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        resultSet = null;
	    }
	    
	    try {
	        if (preparedStatements != null) {
	            preparedStatements.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        preparedStatements = null;
	    }
	    
	    try {
	        if (connection != null) {
	            connection.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        connection = null;
	    }
	}
	
}
