package com.admin.oes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.oes.Auth.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private EditText profileName;
    public TextView profileEmail;
    private TextView profileRollno;
    private EditText profilePhoneno;
    private TextView profileGender;
    private TextView profileBrandhandyear;
    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference=databaseReference = FirebaseDatabase.getInstance().getReference("Users/" + firebaseUser.getUid());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = (EditText) findViewById(R.id.profile_name);
        profileEmail = (TextView) findViewById(R.id.profile_email);
        profileRollno = (TextView) findViewById(R.id.profile_rollno);
        profilePhoneno = (EditText) findViewById(R.id.profile_phoneno);
        profileGender = (TextView) findViewById(R.id.profile_gender);
        profileBrandhandyear = (TextView) findViewById(R.id.profile_brandhandyear);

        sharedPreferences = getApplicationContext().getSharedPreferences("sp", 0);
        profileName.setText(sharedPreferences.getString("name", "NO data found"));
        profileEmail.setText(sharedPreferences.getString("email", "NO data found"));
        profileRollno.setText(sharedPreferences.getString("rollno", "NO data found"));
        profilePhoneno.setText(sharedPreferences.getString("phoneno", "NO data found"));
        profileBrandhandyear.setText(sharedPreferences.getString("year", "NO data found") + " " + sharedPreferences.getString("branch", "NO data found"));
        profileGender.setText(sharedPreferences.getString("gender", "NO data found"));

    }

    public void name(View view) {
        String nameedit=profileName.getText().toString();
        databaseReference.child("Name").setValue(nameedit);
        Log.i("name123",profileName.getText().toString());
        Toast.makeText(this,"Profile Name Updated",Toast.LENGTH_SHORT).show();
       // profileName.setFocusable(false);

    }

    public void roll(View view) {
        String rolledit=profileRollno.getText().toString();
        databaseReference.child("RollNO").setValue(rolledit);
        Toast.makeText(this,"Roll Number Updated",Toast.LENGTH_SHORT).show();
        profileRollno.setFocusable(false);

    }

    public void phone(View view) {
        String phoneedit=profilePhoneno.getText().toString();
        databaseReference.child("PhoneNO").setValue(phoneedit);
        Toast.makeText(this,"Phone NUmber Updated",Toast.LENGTH_SHORT).show();
        profilePhoneno.setFocusable(false);
    }

    public void ForgotPassword(View view) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(profileEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this,"Verification Email Sent Successfuly",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
