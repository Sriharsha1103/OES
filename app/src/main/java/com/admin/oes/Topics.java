package com.admin.oes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Topics extends AppCompatActivity {
    DatabaseReference databaseReference;
    private List<TopicModel> listData;
    private RecyclerView rv;
    private TopicAdapter adapter;
    String sub_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        setTitle("Topics");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new TopicAdapter(Topics.this);

        rv=(RecyclerView)findViewById(R.id.id_topic_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(Topics.this));
        listData=new ArrayList<>();
        Intent i=getIntent();
        sub_name=i.getStringExtra("subject_name");
//       Intent i1=new Intent(getApplicationContext(),PracticeTests.class);
//        i1.putExtra("subj_name",sub_name);
//        startActivity(i1);
       // TextView x=findViewById(R.id.id_topic_name);
        //x.setText(topic_name);
        databaseReference.child("Subjects").child(sub_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listData.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String key = childDataSnapshot.getKey();
                    listData.add(new TopicModel(key));
                }
                adapter.setlist(listData);
                rv.setAdapter(adapter);

                // get total available quest
                int size = (int) dataSnapshot.getChildrenCount();
              TextView usercount = findViewById(R.id.id_topic_name);
                usercount.setText(size+"");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void Topic_name(View view) {
        Intent intent = new Intent(getApplicationContext(), PracticeTests.class);
        intent.putExtra("td" , databaseReference.getKey());
        intent.putExtra("subj_name",sub_name);
        startActivity(intent);
    }
}
