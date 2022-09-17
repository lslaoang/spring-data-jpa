package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.controller.exception.BookAlreadyExistsException;
import io.lao.alloutjpa.controller.exception.BookNotFoundException;
import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.service.viewbookservice.BookViewService;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static io.lao.alloutjpa.dao.Genre.UNDEFINED;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    final BookViewService bookViewService;

    public BookController(final BookViewService bookViewService) {
        this.bookViewService = bookViewService;
    }

    @GetMapping
    public String defaultLandingPage() {
        return "addBook";
    }

    @GetMapping("/view-all")
    public ResponseEntity<?> viewAllBook() throws BookNotFoundException {
        final List<BookView> bookViewList = bookViewService.viewAllBook();
        if (!bookViewList.isEmpty()) {
            return new ResponseEntity<>(bookViewList, HttpStatus.OK);
        } else {
            LOGGER.warn("No record detected.");
            throw new BookNotFoundException("No record found in the book repository. ");
        }
    }

    @GetMapping(value = "/search/{bookId}")
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

    @ResponseBody
    @PostMapping(value = "/add/v1")
    public ResponseEntity<?> addBookByParameter(@RequestParam(value = "bookId") @Valid String bookId,
                                                @RequestParam(value = "bookName") String bookName,
                                                @RequestParam(value = "genre")  String genre)
            throws BookAlreadyExistsException {

        bookViewService.saveBookView(new BookView(bookId, bookName, translateGenre(genre)));
        LOGGER.info("Adding new book success.");
        return new ResponseEntity<>("Saving new book record success!", HttpStatus.CREATED);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<?> updateBookByParameter(@RequestParam(value = "bookId") @Valid String bookId,
                                                   @RequestParam(value = "bookName") String bookName,
                                                   @RequestParam(value = "genre") String genre)
            throws BookNotFoundException {
        bookViewService.updateBookView(new BookView(bookId, bookName, translateGenre(genre)));
        LOGGER.info("Updating book success.");
        return new ResponseEntity<>("Successfully updated book with ID: " +bookId, HttpStatus.FOUND);
    }


    @PostMapping(value = "/add/v2")
    public ResponseEntity<?> addBookByObject(@RequestBody @Valid BookView bookview) throws BookAlreadyExistsException {
        if (bookview != null) {
            bookViewService.saveBookView(bookview);
            LOGGER.info("Adding new book with ID {} success.", bookview.getId());
            return new ResponseEntity<>("Book added successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/addBookV3", method = RequestMethod.POST)
    public String addBook(@RequestBody Book book){


        LOGGER.info("ID = " +book.getId()
        + "\n NAME = " +book.getName()
        +"\n GENRE = " +book.getGenre());

        try{
            bookViewService.saveBookView(new BookView(book.getId(),book.getName(),book.getGenre()));
            return "/all-books";
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return "/error";
        }
    }

    private Genre translateGenre(String genreStr){
        try{
            return Genre.valueOf(genreStr.toUpperCase(Locale.ROOT));
        } catch (RuntimeException e) {
            LOGGER.warn("Error occurred getting genre. Setting genre to default.");
            return UNDEFINED;
        }
    }
}
