package io.lao.alloutjpa.bootstrap;

import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.JpaBook;
import io.lao.alloutjpa.dao.JpaStudent;
import io.lao.alloutjpa.repository.BookRepository;
import io.lao.alloutjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public void run(String... args) throws Exception {

        JpaStudent student = new JpaStudent();
        student.setId(322);
        student.setName("Lao");
        student.setAddress("Makati");
        studentRepository.save(student);

        bookRepository.save(new JpaBook(1, "Percy Jackson", Genre.SCI_FI, student));
        bookRepository.save(new JpaBook(2, "The Alchemist", Genre.FICTION, student));
        bookRepository.save(new JpaBook(3, "Geometry", Genre.EDUCATIONAL, student));
        bookRepository.save(new JpaBook(4, "HEKASI", Genre.EDUCATIONAL, student));


        JpaBook jpaBook = new JpaBook();
        jpaBook.setId(1000);
        jpaBook.setGenre(Genre.THRILLER);
        jpaBook.setName("This Name Of Book");
        jpaBook.setJpaStudent(student);

        bookRepository.save(jpaBook);


    }
}
