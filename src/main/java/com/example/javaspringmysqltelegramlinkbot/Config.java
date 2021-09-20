package com.example.javaspringmysqltelegramlinkbot;

public class Config {
        public static final String TELEGRAM_BOT_TOKEN = new PrivateProperties().getProperty("TELEGRAM_BOT_TOKEN");
        public static final String TELEGRAM_BOT_USERNAME = new PrivateProperties().getProperty("TELEGRAM_BOT_USERNAME");
        public static final String VOTE_DOWN = "-1";
        public static final String VOTE_UP = "+1";
}
