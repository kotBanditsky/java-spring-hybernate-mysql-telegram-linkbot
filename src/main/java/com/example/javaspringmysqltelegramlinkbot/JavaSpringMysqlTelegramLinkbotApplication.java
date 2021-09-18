package com.example.javaspringmysqltelegramlinkbot;

import com.example.javaspringmysqltelegramlinkbot.entity.Links;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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

        String message_text = update.getMessage().getText();

        long message_id = update.getMessage().getMessageId();
        long chat_id = update.getMessage().getChatId();

        if (update.hasCallbackQuery()) {

            String call_data = update.getCallbackQuery().getData();
      //      sendInlineMessage(message_text, call_data, incomingMessage, chat_id, message_id);

        } else if (incomingMessage == null || !incomingMessage.getText().matches("wikipedia.com")) {

            sendFunMessage("kek-pek1", incomingMessage, chat_id, message_id);

        } else if (incomingMessage.getText().matches("wikipedia.com")){

            sendFunMessage("kek-pek2", incomingMessage, chat_id, message_id);

        } else {

            sendFunMessage("kek-pek3", incomingMessage, chat_id, message_id);

        }
    }

    private void sendInlineMessage(String message_text, String call_data, Message incomingMessage, long chat_id, long message_id) {
        try {
            SendMessage sendMessage = new SendMessage()
                    .enableMarkdown(true)
                    .setChatId(incomingMessage.getChatId().toString())
                    .setText(String.valueOf(call_data));
            execute(sendMessage.setReplyMarkup(KeyboardFactory.setupInlineKeyboard()));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendTechMessage(String message_text, Message incomingMessage, long chat_id, long message_id) {
        try {
            SendMessage sendMessage = new SendMessage()
                    .enableMarkdown(true)
                    .setChatId(incomingMessage.getChatId().toString())
                    .setText(message_text);
            execute(sendMessage.setReplyMarkup(KeyboardFactory.setupBaseKeyboard()));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendFunMessage(String message_text, Message incomingMessage, long chat_id, long message_id) {
        try {
            String funlink = Dbconnect.fromBase();

            SendMessage sendMessage = new SendMessage()
                    .enableMarkdown(true)
                    .setChatId(incomingMessage.getChatId().toString())
                    .setText(String.valueOf(funlink));
            execute(sendMessage.setReplyMarkup(KeyboardFactory.setupInlineKeyboard()));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
