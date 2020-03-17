package com.admin.oes.Teacher_access;

public class AddTestModel {
    String type,test_name,no_of_questions,max_marks;
    int weightage;
    String question;

    public AddTestModel(String type, String test_name, String no_of_questions, String max_marks, int weightage) {
        this.type = type;
        this.test_name = test_name;
        this.no_of_questions = no_of_questions;
        this.max_marks = max_marks;
        this.weightage = weightage;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getNo_of_questions() {
        return no_of_questions;
    }

    public void setNo_of_questions(String no_of_questions) {
        this.no_of_questions = no_of_questions;
    }

    public String getMax_marks() {
        return max_marks;
    }

    public void setMax_marks(String max_marks) {
        this.max_marks = max_marks;
    }

    public int getWeightage() {
        return weightage;
    }

    public void setWeightage(int weightage) {
        this.weightage = weightage;
    }
}

