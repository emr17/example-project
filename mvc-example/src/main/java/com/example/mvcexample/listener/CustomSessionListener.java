package com.example.mvcexample.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSessionListener implements HttpSessionListener {

    Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("Session Created with session id+" + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Session Destroyed, Session id:" + se.getSession().getId());
    }
}