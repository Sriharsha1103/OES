package com.admin.oes;

public class StatisticsModel {
    String test_name,test_date;

    public StatisticsModel(String test_name,String test_date)
    {
        this.test_name = test_name;
        this.test_date=test_date;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_date() {
        return test_date;
    }

    public void setTest_date(String test_date) {
        this.test_date = test_date;
    }
}
