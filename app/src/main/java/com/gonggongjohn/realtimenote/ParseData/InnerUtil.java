package com.gonggongjohn.realtimenote.ParseData;

public class InnerUtil {
    private String id,word,postag,head,deprel;

    @Override
    public String toString(){
        return "id=" + id + ", word=" + word +
                ", postag" + postag + ", head" + head + ", deprel" + deprel;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getWord(){
        return word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public String getHead(){
        return head;
    }

    public void setHead(String head){
        this.head = head;
    }

    public String getDeprel(){
        return deprel;
    }

    public void setDeprel(String deprel){
        this.deprel = deprel;
    }
}
