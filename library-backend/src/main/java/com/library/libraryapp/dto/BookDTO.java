package com.library.libraryapp.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;

    private String author;

    private String isbn;

    private String publisher;

    private Integer yearOfPublication;

    private String placeOfPublication;

    private Integer noOfAvailableCopies;

    private String barcodeNumber;


    //this1
}
