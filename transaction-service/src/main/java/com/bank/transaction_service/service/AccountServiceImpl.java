package com.bank.transaction_service.service;

import com.example.AccountDTO;
import com.example.UpdateBalanceDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountServiceImpl implements AccountService {

    private final RestTemplate restTemplate;
    private final String ACCOUNT_SERVICE_URL = "http://localhost:8080/accounts";

    public AccountServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AccountDTO getAccount(Integer accountId) {
        return restTemplate.getForObject(ACCOUNT_SERVICE_URL + "/" + accountId, AccountDTO.class);
    }

    @Override
    public void updateAccountBalance(UpdateBalanceDTO updateBalance) {
        restTemplate.put(ACCOUNT_SERVICE_URL + "/update-balance", updateBalance);
    }
}
