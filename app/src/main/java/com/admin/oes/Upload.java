package com.admin.oes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class Upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        set();
    }

    public void set() {
        String json = null;
        try {
            FileInputStream fis = new FileInputStream(new File("/mnt/sdcard/PYTHON.json"));  // 2nd line
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();

            json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(json);
            Toast.makeText(this, ""+ jsonObject.keys(), Toast.LENGTH_SHORT).show();
            Iterator<String> iter = jsonObject.keys();
            while(iter.hasNext()){
                String key = iter.next();
                JSONObject data = jsonObject.getJSONObject(key);
                for (int i = 1; i < data.length() + 1 ; i++){
                    JSONObject data2 = data.getJSONObject(String.valueOf(i));

                    String QUESTION = data2.getString("QUESTION");
                    String OPT_A = data2.getString("OPT A");
                    String OPT_B = data2.getString("OPT B");
                    String OPT_C = data2.getString("OPT C");
                    String OPT_D = data2.getString("OPT D");
                    String CORRECT_ANS = data2.getString("CORRECT ANS");
                    String EXPLANATION = data2.getString("EXPLANATION");

//To save data in Firebase Database
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tests/" + key);
                    databaseReference.child("QUESTION").setValue(QUESTION);
                    databaseReference.child("OPT_A").setValue(OPT_A);
                    databaseReference.child("OPT_B").setValue(OPT_B);
                    databaseReference.child("OPT_C").setValue(OPT_C);
                    databaseReference.child("OPT_D").setValue(OPT_D);
                    databaseReference.child("CORRECT_ANS").setValue(CORRECT_ANS);
                    databaseReference.child("EXPLANATION").setValue(EXPLANATION);



                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
