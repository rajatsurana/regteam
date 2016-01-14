package com.rajat.registrationcop290;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rajat.registrationcop290.Tools.CheckNetwork;
import com.rajat.registrationcop290.Tools.CustomTextWatcher;
import com.rajat.registrationcop290.Tools.Tools;
import com.rajat.registrationcop290.Volley.CallVolley;
import com.rajat.registrationcop290.Volley.VolleySingleton;


public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;
    int titleId;

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
        teamName.addTextChangedListener(new CustomTextWatcher(teamName));
        entryNum1.addTextChangedListener(new CustomTextWatcher(entryNum1));
        entryNum2.addTextChangedListener(new CustomTextWatcher(entryNum2));
        entryNum3.addTextChangedListener(new CustomTextWatcher(entryNum3));
        name1.addTextChangedListener(new CustomTextWatcher(name1));
        name2.addTextChangedListener(new CustomTextWatcher(name2));
        name3.addTextChangedListener(new CustomTextWatcher(name3));
        submit =(Button)findViewById(R.id.submitNames);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClick();
            }
        });
    }
    public void onSubmitClick(){
        TeamName =teamName.getText().toString();
        entryNumber1=entryNum1.getText().toString();
        entryNumber2=entryNum2.getText().toString();
        entryNumber3=entryNum3.getText().toString();
        studentName1=name1.getText().toString();
        studentName2=name2.getText().toString();
        studentName3=name3.getText().toString();

        //Toast.makeText(RegisterActivity.this,"Submit Clicked",Toast.LENGTH_SHORT).show();
        CheckNetwork chkNet = new CheckNetwork(RegisterActivity.this);
        String URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
        if(chkNet.checkNetwork()){
            VolleySingleton.getInstance(RegisterActivity.this).getRequestQueue().getCache().clear();

            CallVolley.makeRegistrationCall(URL, TeamName, entryNumber1, studentName1,
                    entryNumber2, studentName2,
                    entryNumber3, studentName3, RegisterActivity.this);
        }else{
            Tools.showAlertDialog("Internet Unavailable", RegisterActivity.this);
        }
    }

}
