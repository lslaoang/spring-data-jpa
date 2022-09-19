package io.lao.alloutjpa.controller;


import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.model.Genre;
import io.lao.alloutjpa.view.BookView;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BookControllerTest extends AbstractTestClass {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getBookList() throws Exception {
        String uri = "/books/view-all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Book[] bookList = super.mapFromJson(content, Book[].class);
        assertTrue(bookList.length > 0);
    }

    @Test
    public void shouldAddBookInTheDB() throws Exception {
        String uri = "/books/add/v2";
        BookView book = new BookView();
        book.setId("99");
        book.setName("TestBook");
        book.setGenre(Genre.THRILLER);

        String inputJson = super.mapToJson(book);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Book added successfully!");

    }

    @Test
    public void shouldGetOneRecordFromDB() throws Exception {
        String uri ="/books/search/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        BookView bookView = super.mapFromJson(content, BookView.class);
        Assertions.assertThat(bookView.getName()).isNotBlank();

    }


}
