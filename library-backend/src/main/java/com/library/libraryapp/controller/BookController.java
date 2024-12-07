package com.library.libraryapp.controller;

import com.library.libraryapp.dto.BookDTO;
import com.library.libraryapp.entity.Book;
import com.library.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
//wduwidw
    @Autowired
    private BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
    CRUD = create, read, update, delete
    C= post request
    R= get request
    U=put or PATCH request
    D=Delete request
     */

    @PostMapping("addBook")
    // URL: http://localhost:8080/api/books/addBook
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        BookDTO saveBookDTO = bookService.addBook(bookDTO);
        return new ResponseEntity<>(saveBookDTO, HttpStatus.CREATED);
    }
    //ewf

    @GetMapping("listAll")
    // URL: http://localhost:8080/api/books/listAll
    public ResponseEntity<List<BookDTO>> getAllBooks(){
       List<BookDTO> allBooks = bookService.getAllBooks();
       return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

//this2
}
