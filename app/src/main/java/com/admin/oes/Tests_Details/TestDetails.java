package com.admin.oes.Tests_Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.admin.oes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestDetails extends AppCompatActivity {
    DatabaseReference databaseReference;
    private List<TestDetailsModel> listData;
    private RecyclerView rv;
    private TestDetailsAdapter adapter;
    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);

        Intent i=getIntent();
        String test_name=i.getStringExtra("test_name");
        setTitle(test_name);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new TestDetailsAdapter(TestDetails.this);

        rv = (RecyclerView) findViewById(R.id.id_user_test_questions_details);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();

        databaseReference.child("Users").child(firebaseUser.getUid()).child("Exams").child(test_name).child(test_name).child("questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listData.clear();
                int i=1;
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String ques =String.valueOf(i)+" "+childDataSnapshot.child("questionNumber").getValue().toString();
                    String ans=childDataSnapshot.child("answer").getValue().toString();
                    String cans=childDataSnapshot.child("correctAnswer").getValue().toString();
                    listData.add(new TestDetailsModel(ques,ans,cans));
                    adapter.setlist(listData);
                    rv.setAdapter(adapter);
                    i++;
                }
                Log.i("questions", String.valueOf(dataSnapshot.getChildrenCount()));




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}