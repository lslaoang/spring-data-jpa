package io.lao.alloutjpa;

import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.model.Student;
import io.lao.alloutjpa.view.BookView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean("student")
    public Student getStudent(){
        return new Student();
    }

    @Bean("book")
    public Book getBook(){
        return new Book();
    }

    @Bean("bookView")
    public BookView getBookView(){
        return  new BookView();
    }
}
