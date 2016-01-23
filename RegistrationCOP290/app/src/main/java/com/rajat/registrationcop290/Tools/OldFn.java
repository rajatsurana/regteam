package com.rajat.registrationcop290.Tools;

import android.widget.Toast;

import java.io.UnsupportedEncodingException;

/**
 * Created by Rajat on 22-01-2016.
 */
public class OldFn {
    /*
    public boolean checkEntry(String entryNumber) throws UnsupportedEncodingException {
        int len = entryNumber.length();
        if(len != 11){
            return false;
        }else{
            String str = entryNumber.substring(0,4);
            int year = Integer.parseInt(str);

            byte[] ye = str.getBytes("US-ASCII");

            for(int i = 0; i < ye.length ; i++){
                if(ye[i]<48 || ye[i] > 57){
                    return false;
                }
            }

            if(year < 2010 || year > 2014 ){
                return false;
            }else{
                String dep = entryNumber.substring(4,6);
                byte[] de = dep.getBytes("US-ASCII");

                for(int i = 0; i < de.length ; i++){
                    if(de[i]> 90 || de[i] < 65){
                        return false;
                    }
                }
                Character forPhd = entryNumber.charAt(6);
                int asc = (int) forPhd;
                boolean tell = (asc >64 && asc < 91)||(asc>47 && asc < 58);
                if(tell = false){
                    return false;
                }
                String lastfour = entryNumber.substring(7,11);


                byte[] lf = lastfour.getBytes("US-ASCII");

                for(int i = 0; i < lf.length ; i++){
                    if(lf[i]<48 || lf[i] > 57){
                        return false;
                    }
                }

            }

        }

        return true;
    }
    public boolean checkName(String studentName) throws UnsupportedEncodingException {
        int len = studentName.length();
        if(len == 0){
            return false;
        }else {


            byte[] ye = studentName.getBytes("US-ASCII");

            for (int i = 0; i < ye.length; i++) {
                boolean charc = (ye[i] > 64 && ye[i] < 91) || (ye[i] == 32);
                if (charc == false) {
                    return false;
                }
            }
        }




        return true;
    }
    public void validate(){
        boolean entries=false;
        boolean cforentry1 = true;
        boolean cforentry2 = true;
        boolean cforentry3 = true;
        boolean cforStudentName1 = true;
        boolean cforStudentName2 = true;
        boolean cforStudentName3 = true;
        try {
            cforentry1= checkEntry(entryNumber1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            cforentry2= checkEntry(entryNumber2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            cforentry3= checkEntry(entryNumber3);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(cforentry3 == false){
            if(entryNumber3.length() == 0 && studentName3.length() == 0){
                cforentry3 = true;
            }
        }
        int teamNamelen = teamName.length();
        boolean cforteamName = true;
        if(teamNamelen == 0){
            cforteamName = false;
        }


        try {
            cforStudentName1 = checkName(studentName1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            cforStudentName2 = checkName(studentName2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            cforStudentName3 = checkName(studentName3);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        int lenStuName3 = studentName3.length();
        if(lenStuName3 == 0 && entryNumber3.length() == 0){
            cforStudentName3 = true;
        }
        if(studentName1.equals(studentName2)){
            cforStudentName2 = false;
        }
        if(studentName1.equals(studentName3)){
            cforStudentName3 = false;
        }
        if(studentName2.equals(studentName3)){
            cforStudentName3 = false;
        }
        if(entryNumber1.equals(entryNumber2)){
            cforentry2= false;
        }
        if(entryNumber1.equals(entryNumber3)){
            cforentry3= false;
        }
        if(entryNumber2.equals(entryNumber3)){
            cforentry3= false;
        }


        boolean ch = cforentry1 && cforentry2 && cforentry3 && cforteamName && cforStudentName1 && cforStudentName2 && cforStudentName3;
        if(ch){
            entries = true;
        }


        if(entries){

            Toast.makeText(RegisterActivity.this, "Submit Clicked", Toast.LENGTH_SHORT).show();
            String URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
            //CallVolley.makeRegistrationCall(URL, TeamName, entryNumber1, studentName1,
            //     entryNumber2, studentName2,
            //    entryNumber3, studentName3, RegisterActivity.this);
        }else{
            if(cforentry1 == false ){
                entryNum1.setText("");

            }
            if( cforentry2 == false){
                entryNum2.setText("");
            }
            if(cforentry3 == false ){
                entryNum3.setText("");
            }
            if(cforStudentName1 == false ){
                name1.setText("");
            }
            if(cforStudentName2 == false ){
                name2.setText("");
            }
            if(cforStudentName3 == false ){
                name3.setText("");
            }
            Toast.makeText(RegisterActivity.this,"Invalid fields have been cleared up",Toast.LENGTH_SHORT).show();
        }
    }
    */
}
