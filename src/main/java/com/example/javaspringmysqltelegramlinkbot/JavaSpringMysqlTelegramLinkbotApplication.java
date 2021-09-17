package com.example.javaspringmysqltelegramlinkbot;

import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static java.lang.Math.toIntExact;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSpringMysqlTelegramLinkbotApplication extends TelegramLongPollingBot {

    public String getBotUsername() {
        return Config.TELEGRAM_BOT_USERNAME;
    }
    public String getBotToken() {
        return Config.TELEGRAM_BOT_TOKEN;
    }

    public static void main(String[] args) {
        try {
            ApiContextInitializer.init(); // Initializing api
            new TelegramBotsApi().registerBot(new JavaSpringMysqlTelegramLinkbotApplication()); // Create a new bot
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) { // Instructions when receiving a message

        Message incomingMessage = update.getMessage(); // Write the incoming message to the variable

        String message_text = update.getCallbackQuery().getMessage().getText();
        String call_data = update.getCallbackQuery().getData();
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();

        if (update.hasCallbackQuery()) {

            sendInlineMessage(call_data, incomingMessage, chat_id, message_id);

        } else if (incomingMessage == null || !incomingMessage.getText().matches("wikipedia.com")) {

         //   sendMenuMessage(incomingMessage.getText());

        } else if (incomingMessage.getText().matches("wikipedia.com")){

         //   sendTechMessage(incomingMessage, "Enter bot command or wiki link 1");

        } else {

         //   sendTechMessage(incomingMessage, "Enter bot command or wiki link 2");

        }
    }

    private void sendInlineMessage(String call_data, Message incomingMessage, long chat_id, long message_id) {

            switch (call_data) {
                case Config.SAVE_MSG:
                    sendCommandMessage(incomingMessage, chat_id, message_id);
                    break;
                case Config.DELETE_MSG:
                    sendCommandMessage(incomingMessage, chat_id, message_id);
                    break;
                case Config.VOTE_UP:
                    sendCommandMessage(incomingMessage, chat_id, message_id);
                    break;
                case Config.VOTE_DOWN:
                    sendCommandMessage(incomingMessage, chat_id, message_id);
                    break;
                default:
                    String commandMessage = "Не знаем такого";
                    break;
            }

        try {
            SendMessage sendMessage = new SendMessage()
                    .enableMarkdown(true)
                    .setChatId(replyToMessage.getChatId().toString())
                    .setText(String.valueOf(funlink));
            execute(sendMessage.setReplyMarkup(KeyboardFactory.setupInlineKeyboard(rate)));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    private void sendMenuMessage(String call_data) {
//
//        switch (incomingMessage.getText()) {
//            case "/notes":
//                monthString = "Январь";
//                break;
//            case "/help":
//                monthString = "Февраль";
//                break;
//            case "/fun":
//                monthString = "Март";
//                break;
//            case "/del":
//                monthString = "Апрель";
//                break;
//            default: monthString = "Не знаем такого";
//                break;
//        }
//
//        ArrayList<String> queries = SingletonMongo.main("answer", "deleter", "fun", replyToMessage.getChatId());
//        String funlink = queries.get(0).toString();
//        String rate  = queries.get(1).toString();
//
//        try {
//            SendMessage sendMessage = new SendMessage()
//                    .enableMarkdown(true)
//                    .setChatId(replyToMessage.getChatId().toString())
//                    .setText(String.valueOf(funlink));
//            execute(sendMessage.setReplyMarkup(KeyboardFactory.setupInlineKeyboard(rate)));
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
}
