package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.dao.MgaAklat;
import io.lao.alloutjpa.repository.BookRepository;
import io.lao.alloutjpa.view.BookView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books", method = {RequestMethod.GET,RequestMethod.POST})
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public MgaAklat findAll(){
        MgaAklat mgaAklat = new MgaAklat();
        BookView bookView = new BookView();

        //make it a service
        mgaAklat.setMgaAklat(bookRepository.findAll());

        mgaAklat.getMgaAklat().forEach(aklat -> {
            bookView.setId(aklat.getId());
            bookView.setGenre(aklat.getGenre());
            bookView.setName(aklat.getName());
        });

        return mgaAklat;
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<Aklat> getBookById(@PathVariable("bookId") int bookId){
        return new ResponseEntity<>(bookRepository.getById(bookId), HttpStatus.OK);
    }

    @PostMapping(value = "/addBook/{bookId}")
    public ResponseEntity<?> addBook(@PathVariable("bookId") int bookId){
        bookRepository.save(new Aklat(bookId,"Test Post Book",Genre.THRILLER));
        MgaAklat listOfBooks = new MgaAklat();
        listOfBooks.setMgaAklat(bookRepository.findAll());
        return new ResponseEntity<>(listOfBooks,HttpStatus.ACCEPTED);
    }
}
