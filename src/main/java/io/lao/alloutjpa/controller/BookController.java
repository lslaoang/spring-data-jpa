package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.controller.exception.BookAlreadyExistsException;
import io.lao.alloutjpa.controller.exception.BookNotFoundException;
import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.service.viewbookservice.BookViewService;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String defaultLandingPage() {
        return "index";
    }

    @GetMapping(value = {"/books", "/books.html"})
    public ResponseEntity<?> viewAllBook() throws BookNotFoundException {
        final List<BookView> bookViewList = bookViewService.viewAllBook();
        if (!bookViewList.isEmpty()) {
            return new ResponseEntity<>(bookViewList, HttpStatus.OK);
        } else {
            LOGGER.warn("No record detected.");
            throw new BookNotFoundException("No record found in the book repository. ");
        }
    }

    @GetMapping(value = "/book/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId") @Valid String bookId) throws BookNotFoundException {

        BookView bookView = bookViewService.viewBookById(bookId);
        if (bookView != null) {
            LOGGER.info("Fetching record of {} success.", bookId);
            return new ResponseEntity<>(bookView, HttpStatus.OK);
        } else {
            LOGGER.warn("No record of {} detected.", bookId);
            throw new BookNotFoundException("No record found for " +bookId +".");
        }
    }

    @PostMapping(value = "book/add")
    public ResponseEntity<?> addBookByParameter(@RequestParam(value = "bookId") @Valid String bookId,
                                                @RequestParam(value = "bookName") String bookName,
                                                @RequestParam(value = "genre") @Valid Genre genre)
            throws BookAlreadyExistsException {

        bookViewService.saveBookView(new BookView(bookId, bookName, genre));
        LOGGER.info("Adding new book success.");
        return new ResponseEntity<>("Saving new book record success!", HttpStatus.CREATED);
    }

    @PatchMapping(value = "book/update")
    public ResponseEntity<?> updateBookByParameter(@RequestParam(value = "bookId") @Valid String bookId,
                                                   @RequestParam(value = "bookName") String bookName,
                                                   @RequestParam(value = "genre") @Valid Genre genre)
            throws BookNotFoundException {
        bookViewService.updateBookView(new BookView(bookId, bookName, genre));
        LOGGER.info("Adding new book success.");
        return new ResponseEntity<>("Successfully updated book wiht ID:" +bookId, HttpStatus.FOUND);
    }


    @PostMapping(value = "book/add/v1")
    public ResponseEntity<?> addBookByObject(@RequestBody @Valid BookView bookview) throws BookAlreadyExistsException {
        if (bookview != null) {
            bookViewService.saveBookView(bookview);
            LOGGER.info("Adding new book with ID {} success.", bookview.getId());
            return new ResponseEntity<>("Book added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }
}
