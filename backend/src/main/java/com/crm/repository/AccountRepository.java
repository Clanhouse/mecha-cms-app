package com.crm.repository;

import com.crm.model.db.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByLogin(final String login);
    boolean existsByLogin(final String login);
    boolean existsByEmail(final String login);
}
