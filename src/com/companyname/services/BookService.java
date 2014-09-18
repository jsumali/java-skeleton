package com.companyname.services;

import com.companyname.dao.Repository;
import com.companyname.Logger;
import com.companyname.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

public class BookService {

    private Logger logger;
    private Repository repository;

    @Autowired
    public BookService(Logger logger, Repository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    public Book getBook(String bookId) {
        return repository.get(Book.class, bookId);
    }
}
