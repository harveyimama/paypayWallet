package com.techland.paypay.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.techland.paypay")
public class PayPayWalletpplication {
	public static void main(String[] args) {
		SpringApplication.run(PayPayWalletpplication.class, args);
	}

}
