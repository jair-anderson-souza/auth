 package io.github.jairandersonsouza.authorizer.repositories;

import io.github.jairandersonsouza.authorizer.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
