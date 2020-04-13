package com.techland.paypay.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.techland.paypay.TechLandScheduler;
import com.techland.paypay.contracts.EventSubscriber;
import com.techland.paypay.contracts.StateSubscriber;
import com.techland.paypay.wallet.factories.SubscriberFactory;
import com.techland.paypay.wallet.helper.Settings;
import com.techland.paypay.wallet.impl.WalletState;

public class WalletScheduler {
	

	@Autowired
	private SubscriberFactory subscriberFactory;
	@Autowired
	TechLandScheduler techLandSchedular;
	

	
	@Scheduled(fixedRate = Settings.SELF_HEAL_RUN_TIME)
	public void eventSelfHeal() {

		List<EventSubscriber> subscribers = subscriberFactory.getAllEventSubscribers();
		
		techLandSchedular.eventSelfHeal(subscribers);

	
	}

	@Scheduled(fixedRate = Settings.SELF_HEAL_RUN_TIME)
	public void stateSelfHeal() {

		List<StateSubscriber> subscribers = subscriberFactory.getAllStateSubscribers();
		techLandSchedular.stateSelfHeal(subscribers,new WalletState());
		
	}

}
