package DB;

import java.sql.Connection;
import java.sql.DriverManager;

import com.sun.org.glassfish.external.statistics.Statistic;

import jdk.internal.dynalink.beans.StaticClass;

public class ConnPool {
	
	/**
	 * The Connection pooling is a well-known data access pattern,whose main purpose is to reduce the overhead involved in performing 
	 * database connections and read/write database operations. 
	 * a connection pool is, at the most basic level, a database connection cache implementation
	 * It Contains: 
	 *  
	 */
	
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