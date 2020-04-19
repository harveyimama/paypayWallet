package com.techland.paypay.wallet.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techland.paypay.contracts.PayPayState;
import com.techland.paypay.contracts.TechLandState;

@TechLandState
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletState implements PayPayState {

	/**
	 * 
	 */

	private String id;
	private String name;
	private double balance;
	private double ledgerBalance;
	private boolean canReceivedFunds;
	private boolean canSendFunds;

	public WalletState() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getLedgerBalance() {
		return ledgerBalance;
	}

	public void setLedgerBalance(double ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}

	public boolean isCanReceivedFunds() {
		return canReceivedFunds;
	}

	public void setCanReceivedFunds(boolean canReceivedFunds) {
		this.canReceivedFunds = canReceivedFunds;
	}

	public boolean isCanSendFunds() {
		return canSendFunds;
	}

	public void setCanSendFunds(boolean canSendFunds) {
		this.canSendFunds = canSendFunds;
	}

	@Override
	public WalletState addEvent(String event) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(event);

			if (actualObj.get("class").asText().equals("MerchantAddedEvent")) {
				WalletState json = mapper.readValue(event, WalletState.class);
				this.setBalance(this.getBalance()+0.0);
				this.setCanReceivedFunds(true);
				this.setCanSendFunds(true);
				this.setId(json.getId());
				this.setLedgerBalance(this.getLedgerBalance()+0.0);
				this.setName(json.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public WalletState getState(String state) {

		try {
			ObjectMapper mapper = new ObjectMapper();

				WalletState json = mapper.readValue(state, WalletState.class);
				this.setBalance(json.getBalance());
				this.setCanReceivedFunds(json.isCanReceivedFunds());
				this.setCanSendFunds(json.isCanSendFunds());
				this.setId(json.getId());
				this.setLedgerBalance(0.0);
				this.setName(json.getName());
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public String toString() {
		return "{\"class\":\"WalletState\",\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"balance\":\"" + balance
				+ "\", \"ledgerBalance\":\"" + ledgerBalance + "\", \"canReceivedFunds\":\"" + canReceivedFunds
				+ "\", \"canSendFunds\":\"" + canSendFunds + "\"}";
	}

}
