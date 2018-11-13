package mworkstation.com.mworkstation.MatchGame;

import java.io.Serializable;

public class Match implements Serializable {
    String word;
    int res;
    int id;
    boolean answer;

    public Match() {
    }

    public Match(String word, int res, int id) {
        this.word = word;
        this.res = res;
        this.id = id;
    }

    public Match(String word, int res, int id, boolean answer) {
        this.word = word;
        this.res = res;
        this.id = id;
        this.answer = answer;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
