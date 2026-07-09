package com.library.service;

import org.springframework.stereotype.Service;
import com.library.repository.BookRepository;

@Service
public class BookService {

private BookRepository bookRepository;

public BookService() {
}

public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
    System.out.println("Constructor Injection Executed");
}

public void setBookRepository(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
    System.out.println("Setter Injection Executed");
}

public void issueBook() {
    System.out.println("Book Service Method Executed");
    bookRepository.display();
}

}
