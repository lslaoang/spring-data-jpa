package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.controller.exception.BookAlreadyExistsException;
import io.lao.alloutjpa.controller.exception.BookNotFoundException;
import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.model.Genre;
import io.lao.alloutjpa.service.viewbookservice.BookViewError;
import io.lao.alloutjpa.service.viewbookservice.BookViewService;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

import static io.lao.alloutjpa.model.Genre.UNDEFINED;

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
        return "all-books";
    }

    @GetMapping("/view-all")
    public ResponseEntity<?> viewAllBook() throws BookNotFoundException {

        try {
            return new ResponseEntity<>(bookViewService.viewAllBook(), HttpStatus.OK);
        } catch (BookViewError e) {
            LOGGER.warn("Error occurred fetching book(s). ");
            throw new BookNotFoundException("Error occurred fetching book(s). " + e.getMessage());
        }
    }

    @GetMapping(value = "/search/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId") @Valid String bookId) throws BookNotFoundException {

        try {
            BookView bookView = bookViewService.viewBookById(bookId);
            LOGGER.info("Fetching record of {} success.", bookId);
            return new ResponseEntity<>(bookView, HttpStatus.OK);
        } catch (BookViewError e) {
            LOGGER.warn("No record of {} detected.", bookId);
            throw new BookNotFoundException("No record found for " + bookId + ".");
        }
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<?> updateBookByParameter(@RequestParam(value = "bookId") @Valid String bookId,
                                                   @RequestParam(value = "bookName") String bookName,
                                                   @RequestParam(value = "genre") String genre)
                                                    throws BookNotFoundException {
        try {
            bookViewService.updateBookView(new BookView(bookId, bookName, translateGenre(genre)));
            LOGGER.info("Updating book success.");
            return new ResponseEntity<>("Successfully updated book with ID: " + bookId, HttpStatus.FOUND);
        }catch (BookViewError e){
            return new ResponseEntity<>("Updating book failed " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/add")
    public String addBookByPage(){
        return "addBook";
    }

    @PostMapping(value = "/add/v1")
    public ResponseEntity<?> addBookByParameter(@RequestParam(value = "bookId") @Valid String bookId,
                                                @RequestParam(value = "bookName") String bookName,
                                                @RequestParam(value = "genre")  String genre)
            throws BookAlreadyExistsException {
        try {
            bookViewService.saveBookView(new BookView(bookId, bookName, translateGenre(genre)));
            LOGGER.info("Adding new book success.");
            return new ResponseEntity<>("Saving new book record success!", HttpStatus.CREATED);
        } catch (BookViewError e) {
            return new ResponseEntity<>("Adding book to database failed " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add/v2")
    public ResponseEntity<?> addBookByObject(@RequestBody @Valid BookView bookview) throws BookAlreadyExistsException {

        try{
            bookViewService.saveBookView(bookview);
            LOGGER.info("Adding new book with ID {} success.", bookview.getId());
            return new ResponseEntity<>("Book added successfully!", HttpStatus.CREATED);
        } catch (BookViewError e){
            return new ResponseEntity<>("Something went wrong. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/add/v3", method = RequestMethod.POST)
    public String addBook(@RequestBody Book book){
        try{
            bookViewService.saveBookView(new BookView(book.getId(),book.getName(),book.getGenre()));
            LOGGER.info("Adding books successful!");
            return "redirect:view-all-books";
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/view-all-books", method = RequestMethod.GET)
    public String viewAllBooks(){
        return "all-books";
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
