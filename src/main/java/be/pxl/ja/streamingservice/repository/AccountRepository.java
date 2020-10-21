package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.exception.DuplicateEmailException;
import be.pxl.ja.streamingservice.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        if(findAccount(account.getEmail()) == null) {
            throw new DuplicateEmailException();
        }

        accounts.add(account);
    }

    public Account findAccount(String email) {
        return accounts.stream().filter(account -> email.equals(account.getEmail())).findAny().orElse(null);
    }
}
