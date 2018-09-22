package com.sbt.mailsender.api;

public interface EmailService {
    void send(String to, String title, String body);
}
