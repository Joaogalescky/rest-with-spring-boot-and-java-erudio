package com.joaogalescky.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joaogalescky.model.Book;

@Repository // Opcional a partir da versão Spring Boot 3.0.1
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("SELECT b FROM Book b WHERE b.title LIKE LOWER(CONCAT ('%',:title,'%'))")
	Page<Book> findBooksByTitle(@Param("title") String title, Pageable pageable);
}