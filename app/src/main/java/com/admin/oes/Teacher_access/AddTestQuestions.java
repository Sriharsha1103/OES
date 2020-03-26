package com.admin.oes.Teacher_access;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.admin.oes.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTestQuestions extends AppCompatActivity {
EditText marks,question,o1,o2,o3,o4,correctAnswer,ques_no;
String position,test_name,type,no_of_questions,max_marks,weightage;
int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test_questions);
        Intent i=getIntent();
        position=i.getStringExtra("position");
        Log.d("list123" , position);
        test_name=i.getStringExtra("test name");
        setTitle(test_name);
        type=i.getStringExtra("type");
        no_of_questions=i.getStringExtra("no of questions");
        max_marks=i.getStringExtra("max marks");
        weightage=i.getStringExtra("weightage");

    //    marks=findViewById(R.id.marks);
//        marks.setText(""+weightage);
        question=findViewById(R.id.question);
    //    ques_no=findViewById(R.id.ques_no);
        o1=findViewById(R.id.o1);
        o2=findViewById(R.id.o2);
        o3=findViewById(R.id.o3);
        o4=findViewById(R.id.o4);
        //ques_no.setText("Ques NO: "+position);
        correctAnswer=findViewById(R.id.ans);
        }

    public void Done(View view) {
        if (question.getText().toString().isEmpty()) {
                    question.setError("Enter Question!");
                    question.requestFocus();
                }else if (o1.getText().toString().isEmpty()) {
                    o1.setError("Provide Option!");
                    o1.requestFocus();
                }else if (o2.getText().toString().isEmpty()) {
                    o2.setError("Provide Option!");
                    o2.requestFocus();
                }else if (o3.getText().toString().isEmpty()) {
                    o3.setError("Provide Option!");
                    o3.requestFocus();
                }else if (o4.getText().toString().isEmpty()) {
                    o4.setError("Provide Option!");
                    o4.requestFocus();
                }else if (correctAnswer.getText().toString().isEmpty()) {
                    correctAnswer.setError("Provide Ans!");
                    correctAnswer.requestFocus();
                } else if(!(question.getText().toString().isEmpty() && o1.getText().toString().isEmpty() && o2.getText().toString().isEmpty() && o3.getText().toString().isEmpty() && o4.getText().toString().isEmpty() && correctAnswer.getText().toString().isEmpty())){
                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Tests/"+ test_name+"/"+position);
                    databaseReference2.child("q").setValue(question.getText().toString());
                    databaseReference2.child("a").setValue(o1.getText().toString());
                    databaseReference2.child("b").setValue(o2.getText().toString());
                    databaseReference2.child("c").setValue(o3.getText().toString());
                    databaseReference2.child("d").setValue(o4.getText().toString());
                    databaseReference2.child("ans").setValue(correctAnswer.getText().toString());
                   // databaseReference2.child("marks").setValue(marks.getText().toString());

                    Toast.makeText(AddTestQuestions.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }


    }

