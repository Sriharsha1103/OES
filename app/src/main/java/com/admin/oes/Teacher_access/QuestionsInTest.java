package com.admin.oes.Teacher_access;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.admin.oes.Event.Model;
import com.admin.oes.Event.MyAdapter;
import com.admin.oes.R;
import com.admin.oes.Tests_Details.TestDetailsModel;
import com.admin.oes.Topics.TopicModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionsInTest extends AppCompatActivity {
    DatabaseReference databaseReference,dbref;
    private List<TestDetailsModel> listData;
    private RecyclerView rv;
    private QuestionsInTestAdapter adapter;
    String test_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_in_test);
        Intent i = getIntent();
        test_name = i.getStringExtra("testname");
        setTitle(test_name);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new QuestionsInTestAdapter(QuestionsInTest.this);

        rv = (RecyclerView) findViewById(R.id.id_questions_in_selected_test);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();

        databaseReference.child("Tests").child(test_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listData.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String key = childDataSnapshot.child("q").getValue().toString();
                    listData.add(new TestDetailsModel(key,childDataSnapshot.getKey(),test_name));
                    Log.i("data1", key);
                    Log.i("data2", childDataSnapshot.getKey());
                    Log.i("data3", test_name);
                }
                adapter.setlist(listData);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void RemoveTest(View view) {
        dbref = FirebaseDatabase.getInstance().getReference();
        dbref.child("Tests").child(test_name).removeValue();
        dbref.child("Events").child(test_name).removeValue();
        Toast.makeText(QuestionsInTest.this, "Test Removed Successfully", Toast.LENGTH_SHORT).show();
        finish();


    }

}
