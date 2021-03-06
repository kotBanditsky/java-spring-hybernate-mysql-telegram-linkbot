package com.example.javaspringmysqltelegramlinkbot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {
    public static ReplyKeyboard setupInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        rowInline.add(new InlineKeyboardButton().setText(Config.VOTE_UP).setCallbackData(Config.VOTE_UP));
     // rowInline.add(new InlineKeyboardButton().setText(String.valueOf(rate)).setCallbackData(Config.RATING));
        rowInline.add(new InlineKeyboardButton().setText(Config.VOTE_DOWN).setCallbackData(Config.VOTE_DOWN));

        rowsInline.add(rowInline);
        inlineKeyboard.setKeyboard(rowsInline);
        return inlineKeyboard;
    }

    public static ReplyKeyboardMarkup setupBaseKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(); // Create ReplyKeyboardMarkup object
        List<KeyboardRow> keyboard = new ArrayList<>(); // Create the keyboard (list of keyboard rows)
        KeyboardRow row = new KeyboardRow(); // Create a keyboard row

        row.add("/top"); // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("/help");
        row.add("/fun");

        keyboard.add(row); // Add the first row to the keyboard
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
