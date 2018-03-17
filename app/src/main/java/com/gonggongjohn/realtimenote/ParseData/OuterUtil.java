package com.gonggongjohn.realtimenote.ParseData;

public class OuterUtil {
    private int log_id;
    private String text;
    private String items;

    @Override
    public String toString(){
        return "log_id=" + log_id + ", text=" + text + ", items" + items;
    }

    public int getLog_id(){
        return log_id;
    }

    public void setLog_id(int log_id){
        this.log_id = log_id;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getItems(){
        return items;
    }

    public void setItems(String items){
        this.items = items;
    }
}
