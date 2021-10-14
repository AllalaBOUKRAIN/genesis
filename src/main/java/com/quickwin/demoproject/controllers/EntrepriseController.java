package com.quickwin.demoproject.controllers;

import com.quickwin.demoproject.entities.Contact;
import com.quickwin.demoproject.entities.Entreprise;
import com.quickwin.demoproject.repositories.IContactRepo;
import com.quickwin.demoproject.repositories.IEntrepriseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EntrepriseController {

    @Autowired
    IEntrepriseRepo entrepriseRepo;

    @Autowired
    IContactRepo contactRepo;

    @GetMapping("/entreprises")
    public ResponseEntity<List<Entreprise>> getAllEntreprises() {
        try {
            List<Entreprise> list = entrepriseRepo.findAll();

            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/entreprises/{id}")
    public ResponseEntity<Entreprise> getEntreprise(@PathVariable Long id) {
        Optional<Entreprise> entreprise = entrepriseRepo.findById(id);

        if (entreprise.isPresent()) {
            return new ResponseEntity<>(entreprise.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/entreprises")
    public ResponseEntity<Entreprise> saveEntreprise(@RequestBody Entreprise entreprise) {
        try {
            return new ResponseEntity<>(entrepriseRepo.save(entreprise), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/entreprises/{entrepriseId}/{contactId}")
    public ResponseEntity<Entreprise> saveEntreprise(Long entrepriseId, Long contactId) {
        try {
            Optional<Entreprise> entreprise = entrepriseRepo.findById(entrepriseId);
            if(entreprise.isPresent()){
                Entreprise ent = entreprise.get();
                Optional<Contact> contact = contactRepo.findById(contactId);
                if(contact.isPresent()){
                    Contact c = contact.get();
                    ent.getContacts().add(c);
                }
                return new ResponseEntity<>(entrepriseRepo.save(ent), HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/entreprises")
    public ResponseEntity<Entreprise> updateEntreprise(@RequestBody Entreprise entreprise) {
        try {
            return new ResponseEntity<>(entrepriseRepo.save(entreprise), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/entreprises/{id}")
    public ResponseEntity<HttpStatus> deleteEntreprise(@PathVariable Long id) {
        try {
            Optional<Entreprise> entreprise = entrepriseRepo.findById(id);
            if (entreprise.isPresent()) {
                entrepriseRepo.delete(entreprise.get());
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
