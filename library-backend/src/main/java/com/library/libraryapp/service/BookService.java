package com.library.libraryapp.service;

import com.library.libraryapp.dto.BookDTO;
import com.library.libraryapp.entity.Book;

import java.util.List;

public interface BookService {


    BookDTO addBook(BookDTO bookDTO);

    List<BookDTO>getAllBooks();


   BookDTO getBookById(long bookId);

   BookDTO updateBook(BookDTO bookDTO);



 }
