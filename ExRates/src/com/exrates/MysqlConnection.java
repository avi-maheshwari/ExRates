package com.exrates;

import java.sql.*;

import com.mysql.cj.jdbc.*;

public class MysqlConnection {
	
	
	public Connection getDbConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			conn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/exrates?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","exrates","exrates123");
			System.out.println("mysqlConnection.getDbConnection() : Mysql Connecton Object retrieved Successfully");
		}
		catch (Exception e) {
			System.out.println("mysqlConnection.getDbConnection() : Exception in DB Connection "+e.getMessage());
			e.printStackTrace();
		}
		
		return conn;
	}

}
