package com.quickwin.demoproject.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_entreprise")
@Setter
@Getter
@ToString
public class Entreprise {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Column(nullable = false)
    private String numeroTva;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "entreprises")
    private Set<Contact> contacts = new HashSet<>();


}
