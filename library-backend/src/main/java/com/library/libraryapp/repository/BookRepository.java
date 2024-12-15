package com.library.libraryapp.repository;

import com.library.libraryapp.entity.Book;
import org.hibernate.boot.model.internal.ListBinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {


    // inhereted all crud- related method

    // find book by tittle


    List<Book> findByTitleContainingIgnoreCase(String title);

    // find book by title and author

    List<Book> findByTitleAndAuthor(String title, String author);




   // Book findByTitle(String title);
    //this method will find book by title, assuming there is such column

//this5
}
