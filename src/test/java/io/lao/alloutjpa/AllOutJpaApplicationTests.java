package io.lao.alloutjpa;

import io.lao.alloutjpa.domain.Aklat;
import io.lao.alloutjpa.domain.Genre;
import io.lao.alloutjpa.repository.BookRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ComponentScan(basePackages = {"io.lao.alloutjpa.bootstrap"})
class AllOutJpaApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void shouldAddToDB(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
        bookRepository.save(new Aklat(11,"To Kill a mocking bird", Genre.SCI_FI));
        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }


}
