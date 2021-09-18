package com.example.javaspringmysqltelegramlinkbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PrivateProperties extends Properties {

    public PrivateProperties() {
        try {
            this.load(new FileInputStream(new File("src/main/resources/private.config")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
