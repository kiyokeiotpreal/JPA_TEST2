package com.example.jpa_test2.Service;

import com.example.jpa_test2.Model.Account;
import org.springframework.data.domain.Page;

public interface IAccounts {
    public boolean addAccount(Account account);
    public boolean reviseAccount(Account account);
    public boolean deleteAccount (int accountId);
    public Account findAccountByName(String name);
    Page<Account> findAccountByPage(int pageNumber, int limit);
}
