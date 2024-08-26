package com.nihon.shiken.model;

public class mKanji {
    public String kanji;
    public String hiragana;
    public String meaning;
    public boolean sentry;

    public void setKanji(String kanji){
        this.kanji = kanji;
    }
    public String getKanji(){
        return this.kanji;
    }
    public void setHiragana(String hiragana){
        this.hiragana = hiragana;
    }
    public String getHiragana(){
        return this.hiragana;
    }
    public void setMeaning(String meaning){
        this.meaning = meaning;
    }
    public String getMeaning(){
        return this.meaning;
    }
    public void setSentry(boolean sentry){
        this.sentry = sentry;
    }
    public boolean getSentry(){
        return this.sentry;
    }
}
