package com.informationengineering.internetapplicationproject.Models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Email {

    String emailAddress;
    String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date;

    public Email(String emailAddress, String content, Date date) {
        this.emailAddress = emailAddress;
        this.content = content;
        this.date = date;
    }

    public Email() {

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
