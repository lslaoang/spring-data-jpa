package io.lao.alloutjpa.view.converter;

import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ViewToDomainImpl implements ViewToDomain{

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewToDomainImpl.class);

    @Override
    public Book viewToBook(BookView bookView) {
        LOGGER.info("Converting BookView to domain success!");
        return  new Book(bookView.getId(), bookView.getName(),bookView.getGenre());
    }
}
