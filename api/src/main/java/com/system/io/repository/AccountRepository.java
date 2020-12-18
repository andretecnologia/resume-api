package com.system.io.repository;

import com.system.io.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);
    Account findByAccountId(String accountId);
}
