package com.nihon.shiken.model;

public class mMultipleChoice {
    public String kanji;
    public String hiragana;
    public String meaning;
    public String selection1;
    public String selection2;
    public String selection3;
    public String selection4;
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
    public void setSelection1(String selection1){
        this.selection1 = selection1;
    }
    public String getSelection1(){
        return this.selection1;
    }
    public void setSelection2(String selection2){
        this.selection2 = selection2;
    }
    public String getSelection2(){
        return this.selection2;
    }
    public void setSelection3(String selection3){
        this.selection3 = selection3;
    }
    public String getSelection3(){
        return this.selection3;
    }
    public void setSelection4(String selection4){
        this.selection4 = selection4;
    }
    public String getSelection4(){
        return this.selection4;
    }
    public void setSentry(boolean sentry){
        this.sentry = sentry;
    }
    public boolean getSentry(){
        return this.sentry;
    }
}
