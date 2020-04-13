package com.techland.paypay.wallet.factories;

import com.techland.paypay.contracts.PayPayEvent;
import java.sql.Timestamp;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techland.paypay.wallet.events.MerchantAddedEvent;

public class EventFactory {
	static PayPayEvent paypayEvent;

	public EventFactory(PayPayEvent evt) {
		paypayEvent = evt;
	}

	@SuppressWarnings("unchecked")
	public static <T extends PayPayEvent> T getEvent(String event, String name) {
		try {
			if ("Merchant.MerchantAddedEvent".equals(name)) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode actualObj = mapper.readTree(event);
				Timestamp t = new Timestamp(actualObj.get("timestamp").asLong());
		
				MerchantAddedEvent u = new MerchantAddedEvent(actualObj.get("id").asText(),
						actualObj.get("name").asText(), actualObj.get("balance").asDouble(),
						actualObj.get("ledgerBalance").asDouble(), actualObj.get("canReceivedFunds").asBoolean(),
						actualObj.get("canSendFunds").asBoolean(), actualObj.get("eventId").asText(), t);
				return (T) u;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}