package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.service.viewbookservice.BookViewService;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
public class BookController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    final BookViewService bookViewService;

    public BookController(final BookViewService bookViewService) {
        this.bookViewService = bookViewService;
    }

    @GetMapping(value = {"/books","/books.html"})
    public ResponseEntity<?> viewAllBook() {
        final List<BookView> bookViewList = bookViewService.viewAllBook();
        if (!bookViewList.isEmpty()) {
            return new ResponseEntity<>(bookViewList, HttpStatus.OK);
        } else {
            LOGGER.warn("No record detected.");
            return new ResponseEntity<>("No record found in the book repository.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/book/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId") @Valid String bookId) {

        BookView bookView = bookViewService.viewBookById(bookId);
        if (bookView != null) {
            LOGGER.info("Fetching record of {} success.", bookId);
            return new ResponseEntity<>(bookView, HttpStatus.OK);
        } else {
            LOGGER.warn("No record of {} detected.", bookId);
            return new ResponseEntity<>("No record found.", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "book/add")
    public ResponseEntity<?> addBookByParameter(@RequestParam(value = "bookId") @Valid String bookId,
                                                @RequestParam(value = "bookName") String bookName,
                                                @RequestParam(value = "genre") @Valid Genre genre) {

        try {
            bookViewService.saveBookView(new BookView(bookId, bookName, genre));
            LOGGER.info("Adding new book success.");
        } catch (MethodArgumentTypeMismatchException | NullPointerException e) {
            LOGGER.warn("Argument mismatch or invalid book detected.");
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Saving new book record success!", HttpStatus.OK);
    }


    @PostMapping(value = "book/add/v1")
    public ResponseEntity<?> addBookByObject(@RequestBody @Valid BookView bookview) {
        if (bookview != null) {
            bookViewService.saveBookView(bookview);
            LOGGER.info("Adding new book with ID {} success.", bookview.getId());
            return new ResponseEntity<>("Book with Saving new book record success!", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }
}
