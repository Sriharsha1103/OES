package com.admin.oes;

public class TestDetailsModel {
    String Ques,cans,gans;

    public TestDetailsModel(String ques, String cans, String gans) {
        Ques = ques;
        this.cans = cans;
        this.gans = gans;
    }

    public String getQues() {
        return Ques;
    }

    public void setQues(String ques) {
        Ques = ques;
    }

    public String getCans() {
        return cans;
    }

    public void setCans(String cans) {
        this.cans = cans;
    }

    public String getGans() {
        return gans;
    }

    public void setGans(String gans) {
        this.gans = gans;
    }
}
