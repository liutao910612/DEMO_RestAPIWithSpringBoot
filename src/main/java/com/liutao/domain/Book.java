package com.liutao.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by liutao on 2017/3/26.
 */
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JacksonXmlRootElement(localName = "book")
public class Book {
    private String bookName;
    private String ownPerson;

    public Book() {
    }

    public Book(String bookName, String ownPerson) {
        this.bookName = bookName;
        this.ownPerson = ownPerson;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getOwnPerson() {
        return ownPerson;
    }

    public void setOwnPerson(String ownPerson) {
        this.ownPerson = ownPerson;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", ownPerson='" + ownPerson + '\'' +
                '}';
    }
}
