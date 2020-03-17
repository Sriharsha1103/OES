package com.admin.oes.Students_Statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.admin.oes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentsData extends AppCompatActivity {
    DatabaseReference databaseReference;
    private List<StudentsDataMOdel> listData;
    private RecyclerView rv;
    private StudentsDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Students");
        setContentView(R.layout.activity_students_data);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new StudentsDataAdapter(StudentsData.this);

        rv=(RecyclerView)findViewById(R.id.id_Students_list_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();

        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listData.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String name = childDataSnapshot.child("Name").getValue().toString();
                    if(Integer.parseInt(childDataSnapshot.child("Role").getValue().toString())==0)
                    {
                        listData.add(new StudentsDataMOdel(name,childDataSnapshot.getKey()));
                        Log.i("name1234",name);
                    }
                }
                adapter.setlist(listData);
                rv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
