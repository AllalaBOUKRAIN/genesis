package com.quickwin.demoproject.controllers;

import com.quickwin.demoproject.entities.Contact;
import com.quickwin.demoproject.repositories.IContactRepo;
import com.quickwin.demoproject.utilities.ContactValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    IContactRepo contactRepo;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        try {
            List<Contact> list = contactRepo.findAll();

            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        Optional<Contact> contact = contactRepo.findById(id);

        if (contact.isPresent()) {
            return new ResponseEntity<>(contact.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
        try {
            if(ContactValidationUtility.validateContactBeforePersist(contact)){
                return new ResponseEntity<>(contactRepo.save(contact), HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/contacts")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        try {
            if(ContactValidationUtility.validateContactBeforePersist(contact)){
                return new ResponseEntity<>(contactRepo.save(contact), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable Long id) {
        try {
            Optional<Contact> contact = contactRepo.findById(id);
            if (contact.isPresent()) {
                contactRepo.delete(contact.get());
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
