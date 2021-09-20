package com.example.javaspringmysqltelegramlinkbot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSpringMysqlTelegramLinkbotApplication extends TelegramLongPollingBot {

    public int flag = 0;
    public String getBotUsername() {
        return Config.TELEGRAM_BOT_USERNAME;
    }
    public String getBotToken() {
        return Config.TELEGRAM_BOT_TOKEN;
    }

    public static void main(String[] args) {
        try {
            ApiContextInitializer.init();
            new TelegramBotsApi().registerBot(new JavaSpringMysqlTelegramLinkbotApplication());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            sendCallbackMessage(update);
        } else if (update.getMessage() == null || !(update.getMessage().getText()).startsWith("https://www.wikipedia.org/")) {
            sendFunMessage(update);
        } else if ((update.getMessage().getText()).startsWith("https://www.wikipedia.org/")){
            sendSaveMessage(update);
        }
    }

    private void sendMessage(String message, int flag, Message incomingMessage) {
        try {
            SendMessage sendMessage = new SendMessage()
                    .enableMarkdown(true)
                    .setChatId(incomingMessage.getChatId().toString())
                    .setText(message);
            if (flag == 1) {
                execute(sendMessage.setReplyMarkup(KeyboardFactory.setupInlineKeyboard()));
            } else {
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendFunMessage(Update incomingMessage) {
        String funlink = null;

        switch (incomingMessage.getMessage().getText()) {
            case "/fun": funlink = Dbconnect.fromBase(); flag = 1; break;
            case "/help": funlink = "Instructions: /fun - get a new article, /top - get the 10 most popular articles. To add a new article to the database - send the bot a link to Wikipedia."; flag = 0; break;
            case "/top": funlink = Dbconnect.getTop(); flag = 0; break;
            default: funlink = "Something went wrong"; flag = 0;
        }

        sendMessage(funlink, flag, incomingMessage.getMessage());
    }

    private void sendSaveMessage(Update incomingMessage) {
        flag = 0;
        String answer = Dbconnect.addBase(incomingMessage.getMessage().getText());
        sendMessage(answer, flag, incomingMessage.getMessage());
    }

    private void sendCallbackMessage(Update incomingMessage) {
        flag = 0;
        String rate = Dbconnect.rateLink(incomingMessage.getCallbackQuery().getMessage().getText(), incomingMessage.getCallbackQuery().getData());
        String answer = "Your vote has been taken, the current rating of the article: " + rate;
        sendMessage(answer, flag, incomingMessage.getCallbackQuery().getMessage());
    }

}
