package io.lao.alloutjpa;

import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.JpaBook;
import io.lao.alloutjpa.dao.JpaStudent;
import io.lao.alloutjpa.repository.BookRepository;
import io.lao.alloutjpa.repository.StudentRepository;
import io.lao.alloutjpa.service.bookreposervice.BookRepoService;
import org.junit.jupiter.api.*;
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
    BookRepoService bookRepoService;

    @Autowired
    StudentRepository studentRepository;

    long countBefore = 0;

    private JpaStudent testThisJpaStudent = testThisJpaStudent = new JpaStudent("1996", null, null, null, null);

    @BeforeEach
    public void initDb() {
        studentRepository.save(testThisJpaStudent);
        countBefore = studentRepository.count();
    }

    @Test
    public void shouldAddToDb() {
        assertThat(countBefore).isGreaterThan(0);
    }

    @Order(1)
    @Test
    void shouldAddToDB() {
        long countBefore = bookRepository.count();
        bookRepository.save(new JpaBook("11", "To Kill a mocking bird", Genre.SCI_FI, testThisJpaStudent));
        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void serviceShouldSaveBook() {
        long countBefore = bookRepository.count();
        bookRepoService.saveBook(new JpaBook("22", "TestBook", Genre.HISTORY, testThisJpaStudent));
        long countAfter = bookRepository.count();
        assertThat(bookRepoService.countBook()).isEqualTo(countBefore + 1);
        assertThat(countBefore).isLessThan(countAfter);

    }

    @Order(3)
    @Test
    void shouldReturnAllAklat() {
        long bookCount = bookRepoService.countBook();
        assertThat(bookCount).isEqualTo(7);
    }




}
