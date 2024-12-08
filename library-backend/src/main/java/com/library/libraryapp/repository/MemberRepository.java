package com.library.libraryapp.repository;

import com.library.libraryapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {




}
