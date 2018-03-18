package com.gonggongjohn.realtimenote.ParseData;

public class InnerUtil {
    private String word,postag,deprel,cpostag;
    private int id,head;

    @Override
    public String toString(){
        cpostag = DPR.resolvePostag(postag);
        return "标识符=" + id + ", 目标词汇=" + word +
                ", 词性=" + cpostag + ", 继承源=" + head + ", 句子成分=" + deprel;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getWord(){
        return word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public int getHead(){
        return head;
    }

    public void setHead(int head){
        this.head = head;
    }

    public String getPostag(){
        return postag;
    }

    public void setPostag(String postag){
        this.postag = postag;
    }

    public String getDeprel(){
        return deprel;
    }

    public void setDeprel(String deprel){
        this.deprel = deprel;
    }
}
