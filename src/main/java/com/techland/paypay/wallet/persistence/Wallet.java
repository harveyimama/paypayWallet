package com.techland.paypay.wallet.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Wallet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private double balance;
	private double ledgerBalance;
	private boolean canReceivedFunds;
	private boolean canSendFunds;
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
	
	

	
	

}
