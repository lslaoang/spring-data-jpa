package io.lao.alloutjpa;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.User;
import io.lao.alloutjpa.repository.BookRepository;
import io.lao.alloutjpa.service.BookRepoService;
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
    BookRepoService bookRepoServiceImp;


    User testThisUser = new User(1996,null,null,null,null);



    @Order(1)
    @Test
    void shouldAddToDB(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
        bookRepository.save(new Aklat(11,"To Kill a mocking bird", Genre.SCI_FI));
        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void serviceShouldSaveBook(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(4);
        bookRepoServiceImp.save(new Aklat(22, "TestBook",Genre.HISTORY));
        long countAfter = bookRepository.count();
        assertThat(bookRepoServiceImp.count()).isEqualTo(5);
        assertThat(countBefore).isLessThan(countAfter);

    }

    @Order(3)
    @Test
    void shouldReturnAllAklat(){
        long bookCount = bookRepoServiceImp.count();
        assertThat(bookCount).isEqualTo(5);
    }

}
