package com.techland.paypay.wallet.events;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.TechLandEvent;
import com.techland.paypay.wallet.helper.Settings;

@JsonIgnoreProperties(ignoreUnknown = true)
@TechLandEvent(externalName = "Merchant.MerchantAddedEvent")
public class MerchantAddedEvent implements PayPayEvent, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String id;
	private final String name;
	private final String eventId;
	private final Timestamp timestamp;

	public MerchantAddedEvent(String id, String name, String eventId, Timestamp timestamp) {

		this.id = id;
		this.eventId = eventId;
		this.timestamp = timestamp;
		this.name = name;

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

	@Override
	public String toString() {
		return "{\"class\":\"MerchantAddedEvent\",\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"eventId\":\""
				+ eventId + "\", \"timestamp\":\"" + timestamp + "\"}";
	}

}
