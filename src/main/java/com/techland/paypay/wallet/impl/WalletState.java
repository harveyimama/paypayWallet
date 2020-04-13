package com.techland.paypay.wallet.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techland.paypay.contracts.PayPayState;
import com.techland.paypay.contracts.TechLandState;

@TechLandState
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
	public void addEvent(String event) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(event);

			if (actualObj.get("class").asText().equals("MerchantAddedEvent")) {
				WalletState json = mapper.readValue(event, WalletState.class);
				this.setBalance(json.getBalance());
				this.setCanReceivedFunds(json.isCanReceivedFunds());
				this.setCanSendFunds(json.isCanSendFunds());
				this.setId(json.getId());
				this.setLedgerBalance(json.getLedgerBalance());
				this.setName(json.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public WalletState getState(String state) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(state);

			if (actualObj.get("class").asText().equals("MerchantAddedEvent")) {
				WalletState json = mapper.readValue(state, WalletState.class);
				this.setBalance(json.getBalance());
				this.setCanReceivedFunds(json.isCanReceivedFunds());
				this.setCanSendFunds(json.isCanSendFunds());
				this.setId(json.getId());
				this.setLedgerBalance(json.getLedgerBalance());
				this.setName(json.getName());
			}

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
