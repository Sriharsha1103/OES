package com.admin.oes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Subjects extends AppCompatActivity {
    DatabaseReference databaseReference;
    private List<SubjectsModel> listData;
    private RecyclerView rv;
    private SubjectsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        setTitle("Subjects");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new SubjectsAdapter(Subjects.this);

        rv=(RecyclerView)findViewById(R.id.id_sub_rec_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();

        databaseReference.child("Subjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listData.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String key = childDataSnapshot.getKey();
                    Log.i("subjects",key);
                    listData.add(new SubjectsModel(key));
                }
                adapter.setlist(listData);
                rv.setAdapter(adapter);

                // get total available quest
                int size = (int) dataSnapshot.getChildrenCount();
               TextView usercount = findViewById(R.id.id_size);
                usercount.setText(size+"");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
