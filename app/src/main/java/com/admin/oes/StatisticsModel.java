package com.admin.oes;

import java.util.ArrayList;

public class StatisticsModel {
    String Test_Name,Name, TotalQ,role,Correctans , wrongans , Time , Teacher, ID;
    ArrayList<QuestionModel> question;
    public StatisticsModel(String Test_name, String name, String totalQ, String role,String correctans, String wrongans, String time, String teacher, String ID, ArrayList<QuestionModel> question) {
        this.Test_Name=Test_name;
        Name = name;
        TotalQ = totalQ;
        this.role=role;
        Correctans = correctans;
        this.wrongans = wrongans;
        Time = time;
        Teacher = teacher;
        this.ID = ID;
        this.question=question;
    }

    public StatisticsModel(String name) {
        Name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<QuestionModel> getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<QuestionModel> question) {
        this.question = question;
    }

    public String getTest_Name() {
        return Test_Name;
    }

    public void setTest_Name(String test_Name) {
        Test_Name = test_Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotalQ() {
        return TotalQ;
    }

    public void setTotalQ(String totalQ) {
        TotalQ = totalQ;
    }

    public String getCorrectans() {
        return Correctans;
    }

    public void setCorrectans(String correctans) {
        Correctans = correctans;
    }

    public String getWrongans() {
        return wrongans;
    }

    public void setWrongans(String wrongans) {
        this.wrongans = wrongans;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
