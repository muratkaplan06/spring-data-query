package yte.intern.springdata;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByAgeGreaterThan(Long age, Sort sort);
    List<Book> findByPublishDateAfter(LocalDateTime publishDate, PageRequest pageRequest);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorAndAgeGreaterThan(String author, Long age);
    Long countByAuthor(String author);
    Boolean existsByAuthor(String author);

    @Query("select b from Book b where b.title=?1")
    Book findByTitleWithQuery(String title);
    @Query("select b from Book b where b.age>?1")
    List<Book> findByAgeGreaterThanWithQuery(Long age, Sort sort);
    @Query("select b from Book b where b.publishDate>?1")
    List<Book> findByPublishDateAfterWithQuery(LocalDateTime publishDate, PageRequest pageRequest);
    @Query("select b from Book b where b.title like %?1%")
    List<Book> findByTitleContainingIgnoreCaseWithQuery(String title);
    @Query("select b from Book b where b.author=?1 and b.age>?2")
    List<Book> findByAuthorAndAgeGreaterThanWithQuery(String author, Long age);
    @Query("select count(b) from Book b where b.author=?1")
    Long countByAuthorWithQuery(String author);
    @Query("select case when count(b)>0 then true else false end from Book b where b.author=?1")
    Boolean existsByAuthorWithQuery(String author);
}
