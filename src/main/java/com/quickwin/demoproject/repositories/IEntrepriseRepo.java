package com.quickwin.demoproject.repositories;

import com.quickwin.demoproject.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEntrepriseRepo extends JpaRepository<Entreprise, Long> {

}
