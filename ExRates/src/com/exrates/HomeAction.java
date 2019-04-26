package com.exrates;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private ExchangeRateDataObject exRateDataObj;
	private String lastUpdateTime; 
	
	@Override
    public String execute() throws Exception {
		
		System.out.println("HomeAction.execute() : Action Called");
    	try {
    		RateDataHandler rdhObj = new RateDataHandler();
//    		ExchangeRateDataObject erdObj= rdhObj.getLiveRateData();
//    		System.out.println("HomeAction.execute() : Live Data As Received : "+erdObj.toString());
//    		rdhObj.insertRateDataInDb(erdObj);
    		
    		exRateDataObj = rdhObj.getRateDatafromDb();
    		long timestamp = exRateDataObj.getTimestamp();
    		
    		Date dateTime = new Date(timestamp*1000);
    		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    		
    		lastUpdateTime = format.format(dateTime);
    		//System.out.println("HomeAction.execute() : Time : "+lastUpdateTime);
    		setLastUpdateTime(lastUpdateTime);
    		//setLastUpdateTime(dateTime.toString());
    		
    		setExRateDataObj(exRateDataObj);
    		System.out.println("HomeAction.execute() : Rate data from DB : "+exRateDataObj.toString());
    		
    	} catch (Exception e) {
			System.out.println("HomeAction.execute() : Exception Occured : "+e.getMessage());
		}
    	return SUCCESS;
    }

	public ExchangeRateDataObject getExRateDataObj() {
		return exRateDataObj;
	}

	public void setExRateDataObj(ExchangeRateDataObject exRateDataObj) {
		this.exRateDataObj = exRateDataObj;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
