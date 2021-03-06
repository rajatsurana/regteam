package com.rajat.registrationcop290;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rajat.registrationcop290.Tools.CheckNetwork;
import com.rajat.registrationcop290.Tools.CustomAutoCompleteView;
import com.rajat.registrationcop290.Tools.CustomTextWatcher;
import com.rajat.registrationcop290.Tools.Student;
import com.rajat.registrationcop290.Tools.Tools;
import com.rajat.registrationcop290.Tools.Validate;
import com.rajat.registrationcop290.Volley.CallVolley;
import com.rajat.registrationcop290.Volley.VolleySingleton;

import java.io.UnsupportedEncodingException;


public class RegisterActivity extends AppCompatActivity {
    MediaPlayer sound = null;
    Toolbar toolbar;
    int titleId;
    CustomAutoCompleteView cacv;
    public ArrayAdapter<String> enAdap;
    CustomAutoCompleteView cacvName;
    public ArrayAdapter<String> nameAdap;
    public String[] enNums = new String[] {"Please search...","Don't search...","Please search...","Don't search...","Please search...","Don't search..."};
    public String[] stdNames = new String[] {"Please search...","Don't search...","Please search...","Don't search...","Please search...","Don't search..."};
    EditText teamName,
            entryNum1, entryNum2, entryNum3,
            name1, name2, name3;

    String TeamName="",
            entryNumber1="", entryNumber2="", entryNumber3="",
            studentName1="", studentName2="", studentName3="";
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Registration COP290" + "</font>")));
        Student.getS();
        //ugetS();
        Student.getEntryNum();
        Student.getNames();
        enNums=Student.studentEntryNum;
        stdNames=Student.studentNames;
        initializeViews();
    }
    public void initializeViews(){
        teamName = (EditText)findViewById(R.id.team_name);
        entryNum1 = (EditText)findViewById(R.id.EntryNum1);
        entryNum2 = (EditText)findViewById(R.id.EntryNum2);
        entryNum3 = (EditText)findViewById(R.id.EntryNum3);
        name1 = (EditText)findViewById(R.id.Name1);
        name2 = (EditText)findViewById(R.id.Name2);
        name3 = (EditText)findViewById(R.id.Name3);
        cacv = new CustomAutoCompleteView(RegisterActivity.this);
        cacvName= new CustomAutoCompleteView(RegisterActivity.this);

        enAdap = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_dropdown_item_1line, enNums);
        nameAdap = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_dropdown_item_1line, stdNames);
        cacv.setThreshold(1);
        cacv.setAdapter(enAdap);
        enAdap.notifyDataSetChanged();
        cacvName.setThreshold(1);
        cacvName.setAdapter(nameAdap);
        nameAdap.notifyDataSetChanged();

        teamName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        entryNum1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        entryNum2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        entryNum3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        //name1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        //name2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        //name3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        teamName.addTextChangedListener(new CustomTextWatcher(teamName,0));
        entryNum1.addTextChangedListener(new CustomTextWatcher(entryNum1,1,cacv,enAdap));

        entryNum2.addTextChangedListener(new CustomTextWatcher(entryNum2,1,cacv,enAdap));
        entryNum3.addTextChangedListener(new CustomTextWatcher(entryNum3,1,cacv,enAdap));
        name1.addTextChangedListener(new CustomTextWatcher(name1,2,cacvName,nameAdap));
        name2.addTextChangedListener(new CustomTextWatcher(name2,2,cacvName,nameAdap));
        name3.addTextChangedListener(new CustomTextWatcher(name3,2,cacvName,nameAdap));
        submit =(Button)findViewById(R.id.submitNames);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClick();
            }
        });
    }
    public void onSubmitClick(){
        Validate validate1=new Validate();
        //boolean invalid=false;
        TeamName =teamName.getText().toString();
        entryNumber1=entryNum1.getText().toString().toUpperCase();
        entryNumber2=entryNum2.getText().toString().toUpperCase();
        entryNumber3=entryNum3.getText().toString().toUpperCase();
        studentName1=name1.getText().toString().toUpperCase();
        studentName2=name2.getText().toString().toUpperCase();
        studentName3=name3.getText().toString().toUpperCase();
        if((validate1.validate_entryno(entryNumber1)&&validate1.validate_entryno(entryNumber2)&& validate1.validate_entryno(entryNumber3)&& validate1.validate_name(studentName1)&& validate1.validate_name(studentName2)&& validate1.validate_name(studentName3))) {

            //Toast.makeText(RegisterActivity.this,"Submit Clicked",Toast.LENGTH_SHORT).show();
            CheckNetwork chkNet = new CheckNetwork(RegisterActivity.this);
            String URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
            if (chkNet.checkNetwork()) {
                VolleySingleton.getInstance(RegisterActivity.this).getRequestQueue().getCache().clear();

                //CallVolley.makeRegistrationCall(URL, TeamName, entryNumber1, studentName1,
                //      entryNumber2, studentName2,
                //    entryNumber3, studentName3, RegisterActivity.this);
            } else {
                Tools.showAlertDialog("Internet Unavailable", RegisterActivity.this);
            }
        }else{
            if (sound == null) {
                if(!validate1.validate_entryno(entryNumber1)){
                    sound = MediaPlayer.create(this, R.raw.sentry1);
                }else if(!validate1.validate_entryno(entryNumber2)){
                    sound = MediaPlayer.create(this, R.raw.sentry2);
                }else if(!validate1.validate_entryno(entryNumber3)){
                    sound = MediaPlayer.create(this, R.raw.sentry3);
                }else if(!validate1.validate_name(studentName1)|| !validate1.validate_name(studentName2) ||!validate1.validate_name(studentName3)){
                    sound = MediaPlayer.create(this, R.raw.name);
                }
               // sound = MediaPlayer.create(RegisterActivity.this, R.raw.team_name);
                //sound = MediaPlayer.create(this, R.raw.sentry1);

                //dsound = MediaPlayer.create(con,R.raw.data_not_posted);
                //dsound = MediaPlayer.create(con,R.raw.user_already_registered);
                //dsound = MediaPlayer.create(con,R.raw.registration_completed);
                sound.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sound.release();
                        sound = null;
                    }
                }, sound.getDuration());
            }else {
                Toast.makeText(RegisterActivity.this, "Have some patience duh...", Toast.LENGTH_SHORT).show();
            }
        }
    }
    boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
