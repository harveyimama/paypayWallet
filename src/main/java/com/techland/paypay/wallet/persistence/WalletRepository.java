package com.techland.paypay.wallet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,String>  {


}
