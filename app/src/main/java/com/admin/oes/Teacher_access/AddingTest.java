package com.admin.oes.Teacher_access;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.admin.oes.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddingTest extends AppCompatActivity {
    EditText test_name,no_of_questions,max_marks;
    CheckBox events,practice_tests,equal_weightage_yes,equal_weightage_no;
    String type="Events";
    SharedPreferences sharedPreferences;
    RecyclerView rv;
    AddTestAdapter addTestAdapter;
    List<AddTestModel> addTestModelList;
    String weightage="";
    private Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_test);
        test_name=findViewById(R.id.id_add_test_test_name);
        no_of_questions=findViewById(R.id.id_add_test_no_of_questions);
        max_marks=findViewById(R.id.id_add_test_max_marks);
//        spinner = (Spinner)findViewById(R.id.spinner);
//        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item,paths);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
//        events=findViewById(R.id.id_add_test_events_type);
//        practice_tests=findViewById(R.id.id_add_test_Practice_tests_type);
//        equal_weightage_yes=findViewById(R.id.id_equal_weightage_yes);
//        equal_weightage_no=findViewById(R.id.id_equal_weightage_no);
        if(events.isChecked())
        {
            Log.i("events","events selected");
            type="Events";
        }
        if(practice_tests.isChecked())
        {
            type="Subjects";
        }
//        weightage = Integer.parseInt(max_marks.getText().toString()) / Integer.parseInt(no_of_questions.getText().toString());
        addTestAdapter = new AddTestAdapter(AddingTest.this);
        Log.i("list123", test_name.getText().toString());
        Log.i("list1234",type);
        Log.i("list12345", max_marks.getText().toString());
        Log.i("list123456", no_of_questions.getText().toString());

        rv=findViewById(R.id.id_add_test_add_questiond_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        addTestModelList=new ArrayList<>();




}

    public void TestDone(View view) {
        if (test_name.getText().toString().isEmpty()) {
            test_name.setError("Enter Test Name!");
            test_name.requestFocus();
        }else if (no_of_questions.getText().toString().isEmpty()) {
            no_of_questions.setError("Enter Number of Questions");
            no_of_questions.requestFocus();
        }else if (max_marks.getText().toString().isEmpty()) {
            max_marks.setError("Please enter Max Marks");
            max_marks.requestFocus();
        }
        else if(!(test_name.getText().toString().isEmpty() && no_of_questions.getText().toString().isEmpty() && max_marks.getText().toString().isEmpty() )){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(type +"/"+ test_name.getText().toString());
            databaseReference.setValue(test_name.getText().toString());
            databaseReference.child("Number of Questions").setValue(no_of_questions.getText().toString());
            databaseReference.child("Max Marks").setValue(max_marks.getText().toString());
            databaseReference.child("Equal Weightage").setValue(equal_weightage_yes.getText().toString());
            int q=Integer.parseInt(no_of_questions.getText().toString());
            weightage=String.valueOf((Integer.parseInt(max_marks.getText().toString()))/(Integer.parseInt(no_of_questions.getText().toString())));
            for(int i=0;i<q;i++)
            {
                addTestModelList.add(new AddTestModel(type,test_name.getText().toString(),no_of_questions.getText().toString(),max_marks.getText().toString(),weightage));
            }
            rv.setAdapter(addTestAdapter);
            addTestAdapter.setlist(addTestModelList);
          //  DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference(type + test_name.getText().toString());
            Toast.makeText(AddingTest.this, "Test Created", Toast.LENGTH_SHORT).show();
        }
    }


}

