package yte.intern.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringDataApplication.class, args);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        saveBooks(bookRepository);
        //türetilmiş sorgular
        System.out.println(bookRepository.findByPublishDateAfter(LocalDateTime.of(1964, 1, 1, 0, 0),
                PageRequest.of(1,5)));
        System.out.println(bookRepository.findByAgeGreaterThan(15L, Sort.by("age").descending()));
        System.out.println(bookRepository.findByTitle("The Lord of the Rings"));
        System.out.println(bookRepository.findByTitleContainingIgnoreCase("father"));
        System.out.println(bookRepository.findByAuthorAndAgeGreaterThan("J.R.R. Tolkien", 15L));
        System.out.println(bookRepository.countByAuthor("J.R.R. Tolkien"));
        System.out.println(bookRepository.existsByAuthor("J.R.R. Tolkien"));
      //query
        System.out.println(bookRepository.findByPublishDateAfterWithQuery(LocalDateTime.of(1964, 1, 1, 0, 0),
                PageRequest.of(1,5)));
        System.out.println(bookRepository.findByAgeGreaterThanWithQuery(15L, Sort.by("age").descending()));
        System.out.println(bookRepository.findByTitleWithQuery("The Lord of the Rings"));
        System.out.println(bookRepository.findByTitleContainingIgnoreCaseWithQuery("father"));
        System.out.println(bookRepository.findByAuthorAndAgeGreaterThanWithQuery("J.R.R. Tolkien", 15L));
        System.out.println(bookRepository.countByAuthorWithQuery("J.R.R. Tolkien"));
        System.out.println(bookRepository.existsByAuthorWithQuery("J.R.R. Tolkien"));

    }
    private static void saveBooks(BookRepository bookRepository) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(null, "The Lord of the Rings", "J.R.R. Tolkien", 20L, LocalDateTime.of(1954, 7, 29, 0, 0)));
        books.add(new Book(null, "The Hobbit", "J.R.R. Tolkien", 10L, LocalDateTime.of(1937, 9, 21, 0, 0)));
        books.add(new Book(null, "The Silmarillion", "J.R.R. Tolkien", 30L, LocalDateTime.of(1977, 9, 15, 0, 0)));
        books.add(new Book(null, "The Children of Húrin", "J.R.R. Tolkien", 15L, LocalDateTime.of(2007, 4, 17, 0, 0)));
        books.add(new Book(null, "The Fall of Gondolin", "J.R.R. Tolkien", 25L, LocalDateTime.of(2018, 8, 30, 0, 0)));
        books.add(new Book(null, "The Adventures of Tom Bombadil", "J.R.R. Tolkien", 5L, LocalDateTime.of(1962, 11, 15, 0, 0)));
        books.add(new Book(null, "The Road Goes Ever On", "J.R.R. Tolkien", 10L, LocalDateTime.of(1967, 12, 1, 0, 0)));
        books.add(new Book(null, "The Father Christmas Letters", "J.R.R. Tolkien", 10L, LocalDateTime.of(1976, 11, 2, 0, 0)));
        books.add(new Book(null, "The Letters of J.R.R. Tolkien", "J.R.R. Tolkien", 10L, LocalDateTime.of(1981, 6, 17, 0, 0)));
        books.add(new Book(null, "The Monsters and the Critics", "J.R.R. Tolkien", 10L, LocalDateTime.of(1983, 2, 1, 0, 0)));

        bookRepository.saveAll(books);
    }


}
