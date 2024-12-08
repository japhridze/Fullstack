package com.library.libraryapp.repository;

import com.library.libraryapp.entity.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<PostalAddress,Long> {

    //this4
}
