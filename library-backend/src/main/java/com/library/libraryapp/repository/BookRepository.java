package com.library.libraryapp.repository;

import com.library.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {


    // inhereted all crud- related method

   // Book findByTitle(String title);
    //this method will find book by title, assuming there is such column

//this5
}
