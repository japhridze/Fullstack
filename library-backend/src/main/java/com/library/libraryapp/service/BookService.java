package com.library.libraryapp.service;

import com.library.libraryapp.dto.BookDTO;

import java.util.List;

public interface BookService {


    BookDTO addBook(BookDTO bookDTO);

    List<BookDTO>getAllBooks();


   BookDTO getBookById(long bookId);

   BookDTO updateBook(BookDTO bookDTO);


   void deleteBook(Long bookId);

   List<BookDTO> findByTitle(String title);

   List<BookDTO> findByTitleAndAuthor(String title, String author);


 }
