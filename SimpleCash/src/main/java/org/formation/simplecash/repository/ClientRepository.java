package org.formation.simplecash.repository;

import org.formation.simplecash.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByConseillerId(Long conseillerId);
}
