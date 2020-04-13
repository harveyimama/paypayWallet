package com.techland.paypay.wallet.events;

import java.io.Serializable;
import java.sql.Timestamp;

import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.TechLandEvent;
import com.techland.paypay.wallet.helper.Settings;

@TechLandEvent(externalName = "Merchant.MerchantAddedEvent")
public class MerchantAddedEvent implements PayPayEvent, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String id;
	private final String name;
	private final double balance;
	private final double ledgerBalance;
	private final boolean canReceivedFunds;
	private final boolean canSendFunds;
	private final String eventId;
	private final Timestamp timestamp;

	public MerchantAddedEvent(String id, String name) {

		this.id = id;
		this.eventId = com.techland.paypay.Settings.aggregateTag();
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.name = name;
		this.balance =0.0;
		this.ledgerBalance = 0.0;
		this.canReceivedFunds = true;
		this.canSendFunds = true;

	}

	public MerchantAddedEvent(String id, String name,double balance,double ledgerBalance,
			boolean canReceivedFunds,boolean canSendFunds,String eventId,Timestamp timestamp)
	{

		this.id = id;
		this.eventId = eventId;
		this.timestamp = timestamp;
		this.name = name;
		this.balance =balance;
		this.ledgerBalance = ledgerBalance;
		this.canReceivedFunds = canReceivedFunds;
		this.canSendFunds = canSendFunds;

	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getEventId() {
		return this.eventId;
	}

	@Override
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	@Override
	public String getObiquitusName() {
		return Settings.DOMAIN + "." + this.getClass().getSimpleName();
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public double getLedgerBalance() {
		return ledgerBalance;
	}

	public boolean isCanReceivedFunds() {
		return canReceivedFunds;
	}

	public boolean isCanSendFunds() {
		return canSendFunds;
	}
	
	

}



