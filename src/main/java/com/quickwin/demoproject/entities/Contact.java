package com.quickwin.demoproject.entities;


import javax.persistence.*;

import com.quickwin.demoproject.utilities.ContactEntrepriseRelationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_contact")
@Setter
@Getter
@ToString
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String address;

    private String numeroTva;

    private ContactEntrepriseRelationType relationType;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "contact_entreprise",
            joinColumns = {@JoinColumn(name="contact_id")},
            inverseJoinColumns = {@JoinColumn(name="entreprise_id")}
    )
    private Set<Entreprise> entreprises = new HashSet<Entreprise>();

    public String getNumeroTva() {
        return numeroTva;
    }

    public void setNumeroTva(String numeroTva) {
        this.numeroTva = numeroTva;
    }

    public ContactEntrepriseRelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(ContactEntrepriseRelationType relationType) {
        this.relationType = relationType;
    }

    public Set<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Set<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }
}
