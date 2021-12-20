package io.lao.alloutjpa.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookErrorHandler implements ErrorController {

    @GetMapping(value = "/error")
    public ResponseEntity<?> errorHandler() {
        return new ResponseEntity<>("The page you requested doesn't exist or under maintenance.", HttpStatus.NOT_FOUND);
    }
}
