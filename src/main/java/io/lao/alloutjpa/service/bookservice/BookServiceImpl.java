package io.lao.alloutjpa.service.bookservice;

import com.sun.istack.NotNull;
import io.lao.alloutjpa.dao.JpaBook;
import io.lao.alloutjpa.model.Books;
import io.lao.alloutjpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Transactional
@Service
public class BookServiceImpl implements BookService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<JpaBook> getAllAklat() {
        LOGGER.info("Retrieving books.");
        return bookRepository.findAll();
    }

    @Override
    public JpaBook findBookById(Integer id) {
        LOGGER.info("Retrieving book by ID.");
        return bookRepository.getById(id);
    }

    @Override
    public void saveBook(@NotNull @Valid final JpaBook jpaBook) {
        LOGGER.info("Creating book record.");
        bookRepository.save(jpaBook);
        LOGGER.info("Book record creation successful");
    }

    @Override
    public void convertToAklatAndSave(@NotNull Books book) {
        LOGGER.info("Converting book to aklat.");
        bookRepository.save(convertBookToAklat(book));
        LOGGER.info("Converting and saving done.");
    }

    @Override
    public long countBook() {
        return bookRepository.count();
    }

    private JpaBook convertBookToAklat(Books book){
        return new JpaBook(
                book.getId(),
                book.getName(),
                book.getGenre()
        );
    }
}
