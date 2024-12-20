package com.library.libraryapp.controller;

import com.library.libraryapp.dto.BookDTO;
import com.library.libraryapp.entity.Book;
import com.library.libraryapp.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class BookController {


    private BookService bookService;


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

    @GetMapping("listAll")
    // URL: http://localhost:8080/api/books/listAll
    public ResponseEntity<List<BookDTO>> getAllBooks(){
       List<BookDTO> allBooks = bookService.getAllBooks();
       return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }


    @GetMapping("{id}")
    //e.g URL: http://localhost:8080/api/books/1
    public  ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId){
      BookDTO bookDTO = bookService.getBookById(bookId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
    @PatchMapping("updateBook/{id}")
    //e.g URL: http://localhost:8080/api/books/updateBook/1
    public ResponseEntity<BookDTO> update(@PathVariable Long id,@RequestBody BookDTO bookDTO){
        bookDTO.setId(id);
        BookDTO updateBook = bookService.updateBook(bookDTO);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("deleteBook/{id}")
    // e.g. URL: http://localhost:8080/api/books/deleteBooks/3
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book successfully deleted.", HttpStatus.OK);

    }

    @GetMapping("search-title")
    // e.g. URL: http:localhost:8080/api/books/search-title?title=Lord of the Rings
    public ResponseEntity<List<BookDTO>> searchBooksByTitle(@RequestParam String title){
       List<BookDTO> books = bookService.findByTitle(title);
       return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("search-title-author")
    // e.g. URL: http:localhost:8080/api/books/search-title-author?title=Lord?author=tolk
    public ResponseEntity<List<BookDTO>> searchBooksByTitleAndAuthor(@RequestParam String title,
                                                                     @RequestParam String author){
        List<BookDTO> books = bookService.findByTitleAndAuthor(title, author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("search")
    //e.g. URL: http://localhost:8080/api/books/search?title=lord&author=tolk
    public ResponseEntity<List<BookDTO>> searchBooks(
            @RequestParam (required = false) String title,
            @RequestParam (required = false) String author,
            @RequestParam(required = false)String isbn,
            @RequestParam(required = false) String barcodeNumber) {
        List<BookDTO> books = bookService.findBooksByCriteria(title,author, isbn, barcodeNumber);
        return new ResponseEntity<>(books,HttpStatus.OK);

    }
}
