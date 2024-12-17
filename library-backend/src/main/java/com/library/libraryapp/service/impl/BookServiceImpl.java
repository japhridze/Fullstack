package com.library.libraryapp.service.impl;
import com.library.libraryapp.dto.BookDTO;
import com.library.libraryapp.entity.Book;
import com.library.libraryapp.mapper.BookMapper;
import com.library.libraryapp.repository.BookRepository;
import com.library.libraryapp.service.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    private EntityManager entityManager;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        //save (create) book in DB
        Book book = BookMapper.mapToBookEntity(bookDTO);
        book = bookRepository.save(book);
        return BookMapper.mapToBookDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books= bookRepository.findAll();

        // we have to interates over the lsit of entities
        //then map every entity to dto,
        //then return list of dto's
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(long bookId) {
        Optional<Book> optionalBook =   bookRepository.findById(bookId);
        Book book = optionalBook.get();
        return BookMapper.mapToBookDTO(book);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        //1. find existing book by id
        Optional<Book> bookOptional = bookRepository.findById(bookDTO.getId());

        //2. do partial update of the book (we will update on non-null fields)
        Book bookToUpdate = bookOptional.get();
        updateBookEntityFromDTO(bookToUpdate, bookDTO);

        //3. save updated book to database
        Book savedBook = bookRepository.save(bookToUpdate);


        //4. return bookDTO using mapper

        return BookMapper.mapToBookDTO(savedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
       List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findByTitleAndAuthor(String title, String author) {
        List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
        return books.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findBooksByCriteria(String title, String author, String isbn, String barcodeNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        if (title != null && !title.isEmpty()){
            predicates.add(cb.like(cb.lower(book.get("title")), "%" + title.toLowerCase() + "%"));
        }
        if (author !=null && !author.isEmpty()){
            predicates.add(cb.like(cb.lower(book.get("author")), "%" + author.toLowerCase() +"%"));
        }
        if (isbn !=null && !isbn.isEmpty()){
            predicates.add(cb.like(cb.lower(book.get("isbn")), "%" + isbn.toLowerCase() +"%"));
        }
        if (barcodeNumber !=null && !barcodeNumber.isEmpty()){
            predicates.add(cb.like(cb.lower(book.get("barcodeNumber")), "%" + barcodeNumber.toLowerCase() +"%"));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        List<Book> result = entityManager.createQuery(cq).getResultList();
        return result.stream()
                .map(BookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }


    private void updateBookEntityFromDTO(Book book, BookDTO bookDTO) {
        if (bookDTO.getTitle() !=null){
            book.setTitle(bookDTO.getTitle());
        }
        if (bookDTO.getAuthor() !=null){
            book.setAuthor(bookDTO.getAuthor());
        }
        if (bookDTO.getIsbn() !=null){
            book.setIsbn(bookDTO.getIsbn());
        }
        if (bookDTO.getPublisher() !=null){
            book.setPublisher(bookDTO.getPublisher());
        }
        if (bookDTO.getYearOfPublication() !=null){
            book.setYearOfPublication(bookDTO.getYearOfPublication());
        }
        if (bookDTO.getPlaceOfPublication() !=null){
            book.setPlaceOfPublication(bookDTO.getPlaceOfPublication());
        }
        if (bookDTO.getNoOfAvailableCopies()!=null){
            book.setNoOfAvailableCopies(bookDTO.getNoOfAvailableCopies());
        }
        if (bookDTO.getBarcodeNumber() !=null){
            book.setBarcodeNumber(bookDTO.getBarcodeNumber());
        }
    }
}
