package com.admin.oes.Teacher_access;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.admin.oes.Event.Model;
import com.admin.oes.Event.MyAdapter;
import com.admin.oes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModifyTests extends AppCompatActivity {
    DatabaseReference databaseReference,reference;
    private List<Model> listData,list,modelList;
    private RecyclerView rv,practice_recycler_view;
    private ModifyTestsAdapter adapter,modifyTestsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_tests);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        reference=FirebaseDatabase.getInstance().getReference();
        adapter = new ModifyTestsAdapter(ModifyTests.this);
        modifyTestsAdapter=new ModifyTestsAdapter(ModifyTests.this);
        rv=(RecyclerView)findViewById(R.id.id_events_tests_recycler_view);
        practice_recycler_view=findViewById(R.id.id_practice_tests_recycler_view);
        rv.setHasFixedSize(true);
        practice_recycler_view.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        practice_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();
        list=new ArrayList<>();

        databaseReference.child("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listData.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String key = childDataSnapshot.getKey();
                    listData.add(new Model(key));
                    Log.i("test_name", String.valueOf(listData.size()));
                }
                adapter.setlist(listData);
                rv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        databaseReference.child("Subjects").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                list.clear();
//
//                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                    String key = childDataSnapshot.getKey();
////                    Log.i("subjects",key);
////                    list.add(new Model(key));
//                    reference.child("Subjects").child(key).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                                String key1 = childDataSnapshot.getKey();
//                                Log.i("subjects",key1);
//                                list.add(new Model(key1));
//                            }
//                         }
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                        }
//                    });
//
//                }
//                modifyTestsAdapter.setlist(list);
//                practice_recycler_view.setAdapter(modifyTestsAdapter);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
