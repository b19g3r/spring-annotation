package org.example.service;

import org.example.dao.BookDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class BookService implements ApplicationContextAware {

    @Lazy
    @Autowired
    private BookDao bookDao;

    ApplicationContext applicationContext;

    public BookService() {
        System.out.println("BookService inited");
    }

    public void serviceMethod() {
        System.out.println("bookService serviceMethod");
        bookDao.daoMethod();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
