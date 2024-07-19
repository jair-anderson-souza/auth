package io.github.jairandersonsouza.authorizer.repository;

import io.github.jairandersonsouza.authorizer.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findById(String id);

}
