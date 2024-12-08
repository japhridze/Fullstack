package com.library.libraryapp.service.impl;
import com.library.libraryapp.dto.BookDTO;
import com.library.libraryapp.entity.Book;
import com.library.libraryapp.mapper.BookMapper;
import com.library.libraryapp.repository.BookRepository;
import com.library.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        //save (create) book in DB
        Book book = BookMapper.mapToBookEntity(bookDTO);
        book = bookRepository.save(book);
        return BookMapper.mapToBookDTO(book);
    }
}
