package com.admin.oes.Teacher_access;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.admin.oes.R;
import com.admin.oes.Students_Statistics.StudentsData;

public class Teacher extends AppCompatActivity {

    private AppCompatEditText question;
    private AppCompatEditText description;
    private AppCompatEditText o1;
    private AppCompatEditText o2;
    private AppCompatEditText o3;
    private AppCompatEditText o4;
    private AppCompatEditText ans, marks;
    private AppCompatEditText testname;
    int i = 1;
    Button done;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

//        question = (AppCompatEditText) findViewById(R.id.question);
//        description = (AppCompatEditText) findViewById(R.id.description);
//        o1 = (AppCompatEditText) findViewById(R.id.o1);
//        o2 = (AppCompatEditText) findViewById(R.id.o2);
//        o3 = (AppCompatEditText) findViewById(R.id.o3);
//        o4 = (AppCompatEditText) findViewById(R.id.o4);
//        ans = (AppCompatEditText) findViewById(R.id.ans);
//        marks = findViewById(R.id.marks);
//        done = findViewById(R.id.button_done);
//        testname = findViewById(R.id.testname);

//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (question.getText().toString().isEmpty()) {
//                    question.setError("Enter Question!");
//                    question.requestFocus();
//                }else if (testname.getText().toString().isEmpty()) {
//                    testname.setError("Provide Testname!");
//                    testname.requestFocus();
//                }else if (o1.getText().toString().isEmpty()) {
//                    o1.setError("Provide Option!");
//                    o1.requestFocus();
//                }else if (o2.getText().toString().isEmpty()) {
//                    o2.setError("Provide Option!");
//                    o2.requestFocus();
//                }else if (o3.getText().toString().isEmpty()) {
//                    o3.setError("Provide Option!");
//                    o3.requestFocus();
//                }else if (o4.getText().toString().isEmpty()) {
//                    o4.setError("Provide Option!");
//                    o4.requestFocus();
//                } else if (marks.getText().toString().isEmpty()) {
//                    marks.setError("Provide Marks!");
//                    marks.requestFocus();
//                }else if (ans.getText().toString().isEmpty()) {
//                    ans.setError("Provide Ans!");
//                    ans.requestFocus();
//                } else if(!(question.getText().toString().isEmpty() && o1.getText().toString().isEmpty() && o2.getText().toString().isEmpty() && o3.getText().toString().isEmpty() && o4.getText().toString().isEmpty() && ans.getText().toString().isEmpty())){
//                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Events/" + testname.getText().toString());
//                    databaseReference.setValue(testname.getText().toString());
//                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Tests/" + testname.getText().toString()+"/"+i);
//                    databaseReference2.child("q").setValue(question.getText().toString());
//                    databaseReference2.child("a").setValue(o1.getText().toString());
//                    databaseReference2.child("b").setValue(o2.getText().toString());
//                    databaseReference2.child("c").setValue(o3.getText().toString());
//                    databaseReference2.child("d").setValue(o4.getText().toString());
//                    databaseReference2.child("ans").setValue(ans.getText().toString());
//                    databaseReference2.child("marks").setValue(marks.getText().toString());
//                    i++;
//                    question.setHint("Enter Question");
//                    o1.setHint("saofina");
//                    o2.setHint("saofina");
//                    o3.setHint("saofina");
//                    o4.setHint("saofina");
//                    ans.setHint("saofina");
//
//                    Toast.makeText(Teacher.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    public void studentstats(View view) {
        Intent i=new Intent(getApplicationContext(), StudentsData.class);
        startActivity(i);
    }

    public void Addtest(View view) {
        Intent intent=new Intent(getApplicationContext(), AddingTest.class);
        startActivity(intent);
    }

    public void Modifytests(View view) {
        Intent intent=new Intent(getApplicationContext(),ModifyTests.class);
        startActivity(intent);
    }
}