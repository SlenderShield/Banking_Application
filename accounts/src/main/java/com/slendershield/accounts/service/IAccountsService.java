package com.slendershield.accounts.service;

import com.slendershield.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto costumerDto);

    CustomerDto fetchAccount(String mobileNumber);

}
