package com.admin.oes.Teacher_access;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.admin.oes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestionDetails extends AppCompatActivity {
    EditText marks, question, o1, o2, o3, o4, correctAnswer, ques_no;
    String weightage, position;
    DatabaseReference databaseReference;
    String test_name, question_name, ques,x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);
        Intent i = getIntent();
        test_name = i.getStringExtra("Test_name");
        position = i.getStringExtra("position");
        ques = i.getStringExtra("ques");
        question_name = i.getStringExtra("question_Name");
        Log.i("pos1", "" + position);
      //  marks = findViewById(R.id.marks);
//        marks.setText("" + weightage);
        question = findViewById(R.id.question);
     //   ques_no = findViewById(R.id.ques_no);
        o1 = findViewById(R.id.o1);
        o2 = findViewById(R.id.o2);
        o3 = findViewById(R.id.o3);
        o4 = findViewById(R.id.o4);
//        ques_no.setText(position);
        correctAnswer = findViewById(R.id.ans);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Tests").child(test_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    if (childDataSnapshot.getKey().equals(ques)) {
                        Log.i("qqqq", ques);
                        x=ques;
                        question.setText(childDataSnapshot.child("q").getValue().toString());
                        o1.setText(childDataSnapshot.child("a").getValue().toString());
                        o2.setText(childDataSnapshot.child("b").getValue().toString());
                        o3.setText(childDataSnapshot.child("c").getValue().toString());
                        o4.setText(childDataSnapshot.child("d").getValue().toString());
                        correctAnswer.setText(childDataSnapshot.child("ans").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void Done(View view) {
        if (!(question.getText().toString().isEmpty() && o1.getText().toString().isEmpty() && o2.getText().toString().isEmpty() && o3.getText().toString().isEmpty() && o4.getText().toString().isEmpty() && correctAnswer.getText().toString().isEmpty())) {
            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Tests/" + test_name + "/" + x);
            databaseReference2.child("q").setValue(question.getText().toString());
            databaseReference2.child("a").setValue(o1.getText().toString());
            databaseReference2.child("b").setValue(o2.getText().toString());
            databaseReference2.child("c").setValue(o3.getText().toString());
            databaseReference2.child("d").setValue(o4.getText().toString());
            databaseReference2.child("ans").setValue(correctAnswer.getText().toString());
            Toast.makeText(QuestionDetails.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}