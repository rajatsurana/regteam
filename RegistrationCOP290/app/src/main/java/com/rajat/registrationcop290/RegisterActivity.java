package com.rajat.registrationcop290;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rajat.registrationcop290.Volley.CallVolley;


public class RegisterActivity extends AppCompatActivity {
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
        submit =(Button)findViewById(R.id.submitNames);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeamName =teamName.getText().toString();
                entryNumber1=entryNum1.getText().toString();
                entryNumber2=entryNum2.getText().toString();
                entryNumber3=entryNum3.getText().toString();
                studentName1=name1.getText().toString();
                studentName2=name2.getText().toString();
                studentName3=name3.getText().toString();

                Toast.makeText(RegisterActivity.this,"Submit Clicked",Toast.LENGTH_SHORT).show();
                String URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
                CallVolley.makeSubmissionCall(URL,TeamName,entryNumber1,studentName1,
                                                            entryNumber2,studentName2,
                                                            entryNumber3,studentName3,RegisterActivity.this);
            }
        });
    }
}
