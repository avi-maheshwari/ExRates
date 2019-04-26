package com.exrates;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.*;

public class RateDataHandler {
	
	public void insertRateDataInDb(ExchangeRateDataObject exRateDataObj) throws SQLException
	{
		String insValues = "";
		
		MysqlConnection mysqlConn = new MysqlConnection();
		Statement stmt =null;
		
		HashMap<String, String> rates = exRateDataObj.getRates();

		Iterator<String> itr = rates.keySet().iterator();
		
		while(itr.hasNext())
		{
			String sKey = itr.next();
			String sVal = rates.get(sKey);
			insValues=insValues+"('"+sKey+"','"+sVal+"'),";
		}
		
		long time = exRateDataObj.getTimestamp();
		String base = exRateDataObj.getBase();
		insValues=insValues+"('TIME','"+time+"'),";
		insValues=insValues+"('BASE','"+base+"')";
		//insValues = insValues.substring(0, insValues.length() - 1);
		
		Connection conn = mysqlConn.getDbConnection();
		
		stmt = (Statement) conn.createStatement();
		String DelQuery = "Delete from rates";
		String InsQuery = "INSERT INTO rates VALUES "+insValues;
		//System.out.println("RateDataHandler.insertRateDataInDb() : DB Query : "+InsQuery);
		stmt.execute(DelQuery);
        //System.out.println("RateDataHandler.insertRateDataInDb() :  DEL Query Executed Successfully");
		stmt.execute(InsQuery);
        //System.out.println("RateDataHandler.insertRateDataInDb() :  INS Query Executed Successfully");
		        
		stmt.close();
		conn.close();
	}
	
	public ExchangeRateDataObject getRateDatafromDb() throws SQLException
	{
		MysqlConnection mysqlConn = new MysqlConnection();
		Statement stmt =null;
		ResultSet rs = null;
		ExchangeRateDataObject exRateDataObj = new ExchangeRateDataObject();
		HashMap<String, String> rates = new HashMap<>();
		
		Connection conn = mysqlConn.getDbConnection();
		stmt = (Statement) conn.createStatement();
		
		if (stmt.execute("select * from rates")) {
		        rs = stmt.getResultSet();
	    }
		 
		while(rs.next())
		{
			String curr = rs.getString("curr_type");
			String rate = rs.getString("rate");
			if (curr.equals("TIME"))
			{
				exRateDataObj.setTimestamp(Long.parseLong(rate));
			}
			else if (curr.equals("BASE"))
			{
				exRateDataObj.setBase(rate);
			}
			else
			{
				rates.put(curr,rate);
			}
		}
		
		exRateDataObj.setRates(rates);
		stmt.close();
		rs.close();
		conn.close();
		
		return exRateDataObj;
	}
	
	public ExchangeRateDataObject getLiveRateData() throws IOException
	{
		HttpConn httpConnObj = new HttpConn();
		String jsonResp = httpConnObj.sendGET();
		
		ObjectMapper objMapper = new ObjectMapper();
		
		ExchangeRateDataObject exRateDataObj = new ExchangeRateDataObject();
		exRateDataObj = objMapper.readValue(jsonResp, ExchangeRateDataObject.class);
		
		return exRateDataObj;
	}
	
	public void HandleLiveDataUpdateRequest() throws Exception
	{
		System.out.println("RateDataHandler.HandleLiveDataUpdateRequest() : Request For Live Data Update Received at : "+new Date());
		ExchangeRateDataObject erdObj= getLiveRateData();
		insertRateDataInDb(erdObj);
		System.out.println("RateDataHandler.HandleLiveDataUpdateRequest() : Live Data Updated in DB");
	}

}
