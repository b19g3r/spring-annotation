package org.example.dao;

import org.springframework.stereotype.Repository;

/**
 * 名字默认是类名首字母小写
 */
// @Lazy
@Repository
public class BookDao {

    public BookDao() {
        System.out.println("bookDao inited");
    }

    public void daoMethod() {
        System.out.println("bookDao daoMethod");
    }
}
