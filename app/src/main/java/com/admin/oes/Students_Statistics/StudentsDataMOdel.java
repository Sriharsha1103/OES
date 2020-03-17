package com.admin.oes.Students_Statistics;

public class StudentsDataMOdel {
    String name,uid;

    public StudentsDataMOdel(String name,String uid) {
        this.name = name;
        this.uid=uid;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
