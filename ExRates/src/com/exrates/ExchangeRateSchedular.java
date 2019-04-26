package com.exrates;

import java.util.TimerTask;

public class ExchangeRateSchedular extends TimerTask{

	@Override
	public void run() {
		RateDataHandler rdhObj = new RateDataHandler();
		try {
			rdhObj.HandleLiveDataUpdateRequest();
		} catch (Exception e) {
			System.out.println("ExchangeRateSchedular.run() : Exception Occured : "+e.getMessage());
		}
	}

}
