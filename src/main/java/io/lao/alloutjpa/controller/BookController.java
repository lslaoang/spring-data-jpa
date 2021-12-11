package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.MgaAklat;
import io.lao.alloutjpa.service.bookservice.BookService;
import io.lao.alloutjpa.service.viewbookservice.BookViewService;
import io.lao.alloutjpa.view.BookView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books", method = {RequestMethod.GET,RequestMethod.POST})
public class BookController {

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
    public ResponseEntity<BookView> getBookById(@PathVariable("bookId") int bookId){
        return new ResponseEntity<>(bookViewService.viewBookById(bookId), HttpStatus.OK);
    }

    @RequestMapping(value = "/add")
    public List<BookView> addBookByParameter(@RequestParam(value = "bookId") int bookId,
                          @RequestParam(value = "bookName") String bookName,
                          @RequestParam(value = "genre") Genre genre){

        bookService.saveBook(new Aklat(bookId,bookName,genre));
        List<BookView> listOfBooks = bookViewService.viewAllBook();
        return listOfBooks;
    }

    //TODO: Implement ModelView aspect
    /*
    @RequestMapping(value = {"/addbookTest","addbooktest.html"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView returnThis(){
        return new ModelAndView("addBook.html");
    }

    @RequestMapping(value = "/addbook")
    public String addBook(@ModelAttribute("Books")Books books){

       // bookService.convertToBookAndSave(books);
        // List<BookView> listOfBooks = bookViewService.viewAllBook();
        return "Success";
    }

     */
}
