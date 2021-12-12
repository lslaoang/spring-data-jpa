package io.lao.alloutjpa;

import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.JpaBook;
import io.lao.alloutjpa.dao.User;
import io.lao.alloutjpa.repository.BookRepository;
import io.lao.alloutjpa.service.bookservice.BookService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@ComponentScan(basePackages = {"io.lao.alloutjpa.bootstrap"})
class AllOutJpaApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;


    User testThisUser = new User(1996,null,null,null,null);



    @Order(1)
    @Test
    void shouldAddToDB(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
        bookRepository.save(new JpaBook(11,"To Kill a mocking bird", Genre.SCI_FI));
        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void serviceShouldSaveBook(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(4);
        bookService.saveBook(new JpaBook(22, "TestBook",Genre.HISTORY));
        long countAfter = bookRepository.count();
        assertThat(bookService.countBook()).isEqualTo(5);
        assertThat(countBefore).isLessThan(countAfter);

    }

    @Order(3)
    @Test
    void shouldReturnAllAklat(){
        long bookCount = bookService.countBook();
        assertThat(bookCount).isEqualTo(5);
    }

}
