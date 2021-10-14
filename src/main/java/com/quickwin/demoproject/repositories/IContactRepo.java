package com.quickwin.demoproject.repositories;

import com.quickwin.demoproject.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IContactRepo extends JpaRepository<Contact, Long> {

}
