package io.lao.alloutjpa.service.converter;

import io.lao.alloutjpa.dao.JpaBook;
import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookConverterImpl implements BookConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookConverterImpl.class);

    @Override
    public Book viewToBook(BookView bookView) {
        try {
            LOGGER.info("Converting BookView to domain success!");
            return new Book(bookView.getId(), bookView.getName(), bookView.getGenre());
        }catch (RuntimeException e){
            LOGGER.error("Converting View to Book failed.");
            throw new BookConversionError("Conversion of View to Book failed. " +e.getMessage());
        }
    }

    @Override
    public BookView bookToView(Book book) {
        try {
            LOGGER.info("Converting Book to BookView success!");
            return new BookView(book.getId(), book.getName(), book.getGenre());
        }catch (RuntimeException e){
            LOGGER.error("Converting Book to View failed.");
            throw new BookConversionError("Conversion of Book to View failed. " + e.getMessage());
        }
    }

    @Override
    public JpaBook bookToJpaBook(Book book) {
        try {
            JpaBook jpaBook = new JpaBook();
            jpaBook.setName(book.getName());
            jpaBook.setGenre(book.getGenre());
            jpaBook.setId(book.getId());
            LOGGER.info("Converting Book to JpaBook success!");
            return jpaBook;
        } catch (RuntimeException e) {
            LOGGER.error("Converting Book to JpaBook failed.");
            throw new BookConversionError("Conversion of Book to JpaBook failed. " + e.getMessage());
        }
    }

    @Override
    public Book jpaBookToBook(JpaBook jpaBook) {
        try {
            LOGGER.info("Converting JpaBook to Book success!");
            return new Book(jpaBook.getId(), jpaBook.getName(), jpaBook.getGenre());
        } catch (RuntimeException e) {
            LOGGER.error("Converting JpaBook to Book failed.");
            throw new BookConversionError("Conversion of JpaBook to Book failed. " + e.getMessage());
        }
    }
}
