package com.exrates;

import com.opensymphony.xwork2.ActionSupport;

public class RateDataAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private ExchangeRateDataObject ExchangeRateData;
	
	@Override
    public String execute() throws Exception {
		
		System.out.println("RateDataAction.execute() : Action Called");
    	try {
    		RateDataHandler rdhObj = new RateDataHandler();
    		ExchangeRateData = rdhObj.getRateDatafromDb();
    		ExchangeRateData.setLicense("My License String");
    		ExchangeRateData.setDisclaimer("My Disclaimer String");
    		setExchangeRateData(ExchangeRateData);
    		
    	} catch (Exception e) {
			System.out.println("RateDataAction.execute() : Exception Occured : "+e.getMessage());
		}
    	return SUCCESS;
    }

	public ExchangeRateDataObject getExchangeRateData() {
		return ExchangeRateData;
	}

	public void setExchangeRateData(ExchangeRateDataObject exchangeRateData) {
		ExchangeRateData = exchangeRateData;
	}
	
}
