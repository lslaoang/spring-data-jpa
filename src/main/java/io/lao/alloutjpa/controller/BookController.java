package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.MgaAklat;
import io.lao.alloutjpa.model.Books;
import io.lao.alloutjpa.service.bookservice.BookService;
import io.lao.alloutjpa.service.viewbookservice.BookViewService;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books", method = {RequestMethod.GET,RequestMethod.POST})
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    final BookService bookService;
    final BookViewService bookViewService;

    public BookController(BookService bookService, BookViewService bookViewService) {
        this.bookService = bookService;
        this.bookViewService = bookViewService;
    }

    @GetMapping
    public List<BookView> findAll(){
        MgaAklat mgaAklat = new MgaAklat();
        mgaAklat.setMgaAklat(bookService.getAllAklat());
        final List<BookView> bookViewList  = new ArrayList<>();
        mgaAklat.getMgaAklat().forEach(aklat ->
                bookViewList.add(new BookView(aklat.getId(),aklat.getName(), aklat.getGenre())));

        return bookViewList;
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId") int bookId){

        BookView bookView;
        try{
            bookView = bookViewService.viewBookById(bookId);
        } catch (NullPointerException e){
            LOGGER.warn("No record of {} detected.",bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOGGER.warn("Fetching record of {} success.",bookId);
        return new ResponseEntity<>(bookView, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?>  addBookByParameter(@RequestParam(value = "bookId") @Valid int bookId,
                          @RequestParam(value = "bookName") String bookName,
                          @RequestParam(value = "genre") @Valid Genre genre){

        try{
            bookService.convertToAklatAndSave(new Books(bookId,bookName,genre));
            LOGGER.info("Adding new book success.");
        } catch (MethodArgumentTypeMismatchException | NullPointerException e){
            LOGGER.info("Argument mismatch or invalid book detected.");
            return new ResponseEntity<>("Something went wrong.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Saving new book record success!",HttpStatus.OK);
    }


    @PostMapping(value = "/add/v2")
    public ResponseEntity<?> addBookByObject(@RequestBody Books books){

        try{
            bookService.convertToAklatAndSave(books);
            LOGGER.info("Adding new book success.");
         } catch (MethodArgumentTypeMismatchException | NullPointerException e){
            LOGGER.info("Argument mismatch or invalid book detected.");
            return new ResponseEntity<>("Something went wrong.",HttpStatus.BAD_REQUEST);
         }
        return new ResponseEntity<>("Saving new book record success!",HttpStatus.ACCEPTED);
    }

    //TODO: Implement ModelView aspect
}
