package com.rajat.registrationcop290.Tools;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tools {
    public static AlertDialog blackalert;
    public static ProgressDialog showProgressBar(Context c) {

        ProgressDialog progressDialog = new ProgressDialog(c);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }


    public static void showAlertDialog(String message,Context con)
    {


        final AlertDialog.Builder alert = new AlertDialog.Builder(
               con);

        // alert.setView(promptView);

        alert.setTitle(message);
        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        blackalert.dismiss();
                    }
                });
        blackalert = alert.create();
        blackalert.show();

    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }



}
