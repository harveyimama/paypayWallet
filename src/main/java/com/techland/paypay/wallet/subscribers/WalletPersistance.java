package com.techland.paypay.wallet.subscribers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.techland.paypay.contracts.PayPayState;
import com.techland.paypay.contracts.StateSubscriber;
import com.techland.paypay.contracts.TechLandSubscriber;
import com.techland.paypay.persistence.StateFailure;
import com.techland.paypay.persistence.StateFailureRepository;
import com.techland.paypay.wallet.impl.WalletState;
import com.techland.paypay.wallet.persistence.Wallet;
import com.techland.paypay.wallet.persistence.WalletRepository;
@Component
@TechLandSubscriber(events = {"Merchant.MerchantAddedEvent"},isstate=true)
public class WalletPersistance  implements StateSubscriber {
	@Autowired
	private Wallet wallet;
	@Autowired
	private StateFailure failure;
	@Autowired
	private StateFailureRepository failureRepo;
	@Autowired
	private WalletRepository walletRepo;
	
	@Override
	public boolean isState() {
		return true;
	}

	
	@Override
	public void process(final PayPayState walletState) {
				
		try {
			
			wallet  = setEntity((WalletState) walletState);
			Wallet ret = walletRepo.save(wallet);	
			
			if(ret == null)
				handleError(walletState,failure,failureRepo);
			else
				handleSuccess(walletState,failure,failureRepo);
			
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			
		} catch (Exception r)	
		{
			r.printStackTrace();
			handleError(walletState,failure,failureRepo);
		}
		
	}
	
	private  Wallet setEntity(final WalletState walletState)
	{
		wallet.setBalance(walletState.getBalance());
		wallet.setCanReceivedFunds(walletState.isCanReceivedFunds());
		wallet.setCanSendFunds(walletState.isCanSendFunds());
		wallet.setId(walletState.getId());
		wallet.setLedgerBalance(walletState.getLedgerBalance());
		wallet.setName(walletState.getName());
		
		return wallet;
	}

	@Override
	public   void handleError(PayPayState userState, StateFailure failure, StateFailureRepository failureRepo) {
		failure.setStateUserId(((WalletState) userState).getId());
		failure.setStateSubscriber(this.getClass().getSimpleName());
	
		failureRepo.save(failure);
		
	}

	@Override
	public  void handleSuccess(PayPayState userState, StateFailure failure, StateFailureRepository failureRepo) {
		failure.setStateUserId(((WalletState) userState).getId());
		failure.setStateSubscriber(this.getClass().getSimpleName());
		
		failureRepo.delete(failure);
		
	}

	

}
