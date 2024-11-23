package com.library.libraryapp.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "postal_address_id")
    private PostalAddress postalAddress;


    private String email;

    private String phone;

    @Column(nullable = false)
    private LocalDate membershipStarted;

    private LocalDate membershipEnded;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private String barcodeNumber;


}
