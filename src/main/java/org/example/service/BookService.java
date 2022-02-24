package org.example.service;

import org.example.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class BookService {

    @Lazy
    @Autowired
    private BookDao bookDao;

    public BookService() {
        System.out.println("BookService inited");
    }

    public void serviceMethod() {
        System.out.println("bookService serviceMethod");
        bookDao.daoMethod();
    }

}
