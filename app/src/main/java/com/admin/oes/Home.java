package com.admin.oes;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.admin.oes.Statistics.Statistics;
import com.admin.oes.Subjects.Subjects;
import com.admin.oes.Teacher_access.Teacher;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.admin.oes.Auth.Login;
import com.admin.oes.Event.Events;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navView;

    DrawerLayout drawerLayout;
    TextView nav_namec, nav_emailc, nav_rollno;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    static Home instance;
    CharSequence s;
    String role;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    PieChart pieChart;
    PieDataSet pieDataSet;
    List<PieEntry> entries;
    PieData pieData;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        instance = this;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        pieChart = (PieChart) findViewById(R.id.chart1);

        test = findViewById(R.id.id_test);

        sharedPreferences = getApplicationContext().getSharedPreferences("sp", 0);


        //PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        pieDataSet = new PieDataSet(entries,"");
        pieData = new PieData(pieDataSet);
        pieDataSet.setColors(COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateY(3000);


        {

            toolbar = findViewById(R.id.toolbar);
            navView = findViewById(R.id.nav_view);
            drawerLayout = findViewById(R.id.drawer_layout);
//            setSupportActionBar(toolbar);
        }

        {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            navView.setNavigationItemSelectedListener(this);

        }


        //TODO: Teahc diff

        role = sharedPreferences.getString("Role", "0");
        if (role.equals("0")) {
            navView = (NavigationView) findViewById(R.id.nav_view);
            Menu nav_Menu = navView.getMenu();
            nav_Menu.findItem(R.id.nav_teacher).setVisible(false);
        } else if (role.equals("1")) {
            Toast.makeText(instance, "KUJGHFuksdb", Toast.LENGTH_SHORT).show();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Teachers/" + firebaseUser.getUid());
            databaseReference.child("Name").setValue(sharedPreferences.getString("name", "0"));
        }

        navviewdata();

        Date d = new Date();
        s = DateFormat.format("MMMM d, yyyy HH:mm:ss", d.getTime());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Last_used_on/" + sharedPreferences.getString("uid", "Not aval"));
        databaseReference.child("Name").setValue(sharedPreferences.getString("name", "Not aval"));
        databaseReference.child("Email").setValue(sharedPreferences.getString("email", "Not aval"));
        databaseReference.child("Date").setValue(s);

    }
    public static final int[] COLORFUL_COLORS = {
            Color.rgb(61, 219, 135),Color.rgb(244,194,13), Color.rgb(250, 000, 000)
    };

    public void AddValuesToPIEENTRY() {
        final String test_name = sharedPreferences.getString("LastTakenTest", null);
        final String correct = sharedPreferences.getString("lastScore", null);
        final String wrong = sharedPreferences.getString("lastwrong", null);
       final String unanswered=sharedPreferences.getString("UNAnswered",null);

        assert test_name != null;
        Log.d("correct", String.valueOf(correct));

        if (test_name != null) {
            entries = new ArrayList<>();
            test.setText(test_name);
            if (Integer.parseInt(correct) != 0) {
                entries.add(new PieEntry(Float.parseFloat(correct), "Correct"));
            }
                if(Integer.parseInt(unanswered)!=0) {
                    entries.add(new PieEntry(Float.parseFloat(unanswered), "UnAnswered"));
                }
                    if(Integer.parseInt(wrong)!=0)
                    {
                        entries.add(new PieEntry(Float.parseFloat(wrong), "Wrong"));
                    }

         } else {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Users").child(firebaseAuth.getUid()).child("Exams").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String v = "", key = "", w = "";
                    for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                        if (childDataSnapshot.getKey().equals(test_name)) {
                            v = childDataSnapshot.child("Correctans").getValue().toString();
                            w = childDataSnapshot.child("wrongans").getValue().toString();
                            key = childDataSnapshot.getKey();
                            Log.i("Test-149", v);
                        }

                    }
                    if (v.equals("") || w.equals("")) {
                        Log.i("Test", "No Data to Display");

                    }
                    else {
                        entries = new ArrayList<>();
                        test.setText(test_name);
                        entries.add(new PieEntry(Float.parseFloat(v), "Correct"));
                        entries.add(new PieEntry(Float.parseFloat(w), "Wrong"));
                    }

                    }

                    @Override
                    public void onCancelled (DatabaseError databaseError){

                    }
                });

            }
        }

        public static Home getInstance () {
            return instance;
        }

        public void navviewdata () {
            View nav_view = navView.getHeaderView(0);
            nav_emailc = nav_view.findViewById(R.id.nav_email);
            nav_namec = nav_view.findViewById(R.id.nav_name);
            nav_rollno = nav_view.findViewById(R.id.nav_rollno);
            String fb_name_main = sharedPreferences.getString("name", "NO data found");
            String fb_email_main = sharedPreferences.getString("email", "NO data found");
            nav_rollno.setText(sharedPreferences.getString("rollno", "NO data found"));
            nav_namec.setText(fb_name_main);
            nav_emailc.setText(fb_email_main);
        }

        @Override
        public void onBackPressed () {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
//            super.onBackPressed();l
                showexitDialog();
            }
        }


        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.

            int id = item.getItemId();

            if (id == R.id.nav_profile) {
                startActivity(new Intent(Home.this, Profile.class));
            } else if (id == R.id.nav_events) {
                startActivity(new Intent(Home.this, Events.class));
            } else if (id == R.id.nav_Statistics) {
                startActivity(new Intent(Home.this, Statistics.class));
            } else if (id == R.id.nav_practice_tests) {
                startActivity(new Intent(Home.this, Subjects.class));
            } else if (id == R.id.nav_signout) {
                showsignoutDialog();
            } else if (id == R.id.nav_teacher) {
                startActivity(new Intent(Home.this, Teacher.class));
            } else if (id == R.id.nav_about) {
                Toast.makeText(instance, "About", Toast.LENGTH_SHORT).show();
            }
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        //Exit Dialog
        private void showexitDialog () {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
            dialog.setContentView(R.layout.exitdialog);
            dialog.setCancelable(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    dialog.dismiss();
                }
            });

            dialog.show();
            dialog.getWindow().setAttributes(lp);
        }

        public void showsignoutDialog () {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
            dialog.setContentView(R.layout.signout);
            dialog.setCancelable(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


            dialog.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.getInstance().signOut();
                    startActivity(new Intent(Home.this, Login.class));
                    finish();
                    dialog.dismiss();
                }
            });

            dialog.show();
            dialog.getWindow().setAttributes(lp);
        }

        public static String getFormattedDateSimple (Long dateTime){
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            return newFormat.format(new Date(dateTime));
        }

    }