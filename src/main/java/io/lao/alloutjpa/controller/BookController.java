package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.MgaAklat;
import io.lao.alloutjpa.service.bookservice.BookService;
import io.lao.alloutjpa.view.BookView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books", method = {RequestMethod.GET,RequestMethod.POST})
public class BookController {

    @Autowired
    BookService bookService;

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
    public ResponseEntity<Aklat> getBookById(@PathVariable("bookId") int bookId){
        return new ResponseEntity<>(bookService.findBookById(bookId), HttpStatus.OK);
    }

    @PostMapping(value = "/addBook/{bookId}")
    public ResponseEntity<?> addBook(@PathVariable("bookId") int bookId){
        bookService.saveBook(new Aklat(bookId,"Test Post Book",Genre.THRILLER));

        List<BookView> listOfBooks = new ArrayList<>();
        return new ResponseEntity<>(listOfBooks,HttpStatus.ACCEPTED);
    }
}
