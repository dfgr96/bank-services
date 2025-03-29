package com.bank.transaction_service.service;

import com.example.AccountDTO;
import com.example.UpdateBalanceDTO;

public interface AccountService {

    AccountDTO getAccount(Integer accountId);

    void updateAccountBalance(UpdateBalanceDTO updateBalance);
}
