package com.example.javaspringmysqltelegramlinkbot;

public class Config {
        public static final String TELEGRAM_BOT_TOKEN = new PrivateProperties().getProperty("TELEGRAM_BOT_TOKEN");
        public static final String TELEGRAM_BOT_USERNAME = new PrivateProperties().getProperty("TELEGRAM_BOT_USERNAME");
        public static final String SAVE_MSG = new PrivateProperties().getProperty("SAVE_MSG");
        public static final String DELETE_MSG = new PrivateProperties().getProperty("DELETE_MSG");
        public static final String VOTE_DOWN = new PrivateProperties().getProperty("VOTE_DOWN");
        public static final String VOTE_UP = new PrivateProperties().getProperty("VOTE_UP");
        public static final String RATING = new PrivateProperties().getProperty("RATING");
}
