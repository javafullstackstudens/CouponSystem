package DB;

import java.sql.Connection;
import java.sql.DriverManager;

import com.sun.org.glassfish.external.statistics.Statistic;

import jdk.internal.dynalink.beans.StaticClass;

public class ConnPool {
	
	static Connection conn;
	
	private static ConnPool instance = new ConnPool();
	
	private ConnPool() {
	}
	
	public static ConnPool getInstance() {
		return instance;
	}
	
	public static Connection getConnection()throws Exception { 
		
		conn = DriverManager.getConnection(Database.getDBUrl());
		return conn;  
	}
	
	public static void returnConnection(Connection connection) {
		
	}
	
	public static void closeAllConnections() {
		
	}
	

}