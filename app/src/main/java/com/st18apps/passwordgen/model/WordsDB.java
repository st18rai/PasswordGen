package com.st18apps.passwordgen.model;

import com.orm.SugarRecord;

/**
 * Created by st18rai on 23.05.17.
 */

public class WordsDB extends SugarRecord {

    private String word;
    private String type;

    public WordsDB() {

    }

    public WordsDB(String word, String type) {
        this.word = word;
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
