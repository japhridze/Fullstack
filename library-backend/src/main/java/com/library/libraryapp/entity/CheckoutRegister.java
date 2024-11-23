package com.library.libraryapp.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class CheckoutRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;

    private Double overdueFine;


}
