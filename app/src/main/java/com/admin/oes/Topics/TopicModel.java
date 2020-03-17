package com.admin.oes.Topics;

public class TopicModel {
    String key,sub;

    public TopicModel(String key,String sub) {
        this.key = key;
        this.sub=sub;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
