package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dto.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> books(){
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookRecordDto){
        return new ResponseEntity<>(service.saveBook(bookRecordDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBook(UUID uuid){
        service.deleteBook(uuid);
        return ResponseEntity.noContent().build();
    }


}
