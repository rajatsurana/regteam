package com.rajat.registrationcop290.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rajat.registrationcop290.R;
import com.rajat.registrationcop290.Tools.Tools;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class CallVolley {
        //dialog box appears showing progress
        private static ProgressDialog pDialog;
        //media player object
        private static MediaPlayer dsound=null;
        //TimeOut and maximum number of tries
        private static void setCustomRetryPolicy(StringRequest jsonObjReq) {
                Log.i("rajat", "setCustomRetryPolicy");
                jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
        // method that will send request  to server and get a response back
        public static void makeRegistrationCall(String url, final String teamname,final String entrynum1,final String name1,final String entrynum2,final String name2,final String entrynum3,final String name3, final Context context)
        {
                pDialog=  Tools.showProgressBar(context);

                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                        {

                        // if a reponse is recieved after sending request
                                @Override
                                public void onResponse(String response)
                                {
                                        try
                                        {
                                                Log.i("rajat", " onResponseActive " + response);
                                                SubmitApiJsonParser(response, context);
                                                pDialog.dismiss();
                                        }
                                        catch (Exception localException)
                                        {
                                                Log.i("rajat"," onResponseException "+localException.getMessage());
                                                localException.printStackTrace();
                                        }
                                }
                        }
                                , new Response.ErrorListener()
                        {
                                //if error occurs
                                @Override
                                public void onErrorResponse(VolleyError error)
                                {
                                        Log.i("rajat", "onErrorResponse" + error.toString());
                                           pDialog.dismiss();


                                }
                        })
                        {
                                @Override
                                // map containing team details sent to server while sending request
                                protected Map<String, String> getParams()
                                {
                                        Log.i("rajat", " onResponseParamsExecute");
                                        Map<String, String> params = new HashMap<String, String>();

                                        try {
                                                Log.i("rajat", " onResponseParams" + teamname);
                                                params.put("teamname", teamname);
                                                params.put("entry1",entrynum1);
                                                params.put("name1",name1);
                                                params.put("entry2",entrynum2);
                                                params.put("name2",name2);
                                                params.put("entry3",entrynum3);
                                                params.put("name3",name3);
                                                Log.i("rajat","request: "+params);
                                        }
                                        catch (Exception e)
                                        {
                                                Log.i("rajat", "onExceptionParams" + e.toString());
                                        }

                                        return params;
                                }
                        };
                //how many times to try and for how much duration
                        setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                        VolleySingleton.getInstance(context).addToRequestQueue(request);
                }
                // method will give message to user depending on respons from server
                public static void SubmitApiJsonParser(String JsonStringResult,Context con)
                {
                        try {
                                String status = "";
                                String message="";

                                //create json object from response string
                                JSONObject resultJson = new JSONObject(JsonStringResult);
                                if (resultJson.has("RESPONSE_SUCCESS"))
                                {
                                        //status can havetwo values 0 and 1
                                        //message can have 3 values
                                        status = resultJson.getString("RESPONSE_SUCCESS");
                                        message = resultJson.getString("RESPONSE_MESSAGE");

                                        if(status.equals("0"))
                                        {
                                                if(message.equals("Data not posted!"))
                                                {
                                                        Tools.showAlertDialog("Data not posted!",con);
                                                        //sound is created and played
                                                        dsound = MediaPlayer.create(con,R.raw.data_not_posted);
                                                        dsound.start();
                                                }
                                                else if(message.equals("User already registered"))
                                                {
                                                        Tools.showAlertDialog("User Already Registered",con);
                                                        //sound is created and played
                                                        dsound = MediaPlayer.create(con,R.raw.user_already_registered);
                                                        dsound.start();
                                                }
                                        }
                                        else
                                        {
                                                if(message.equals("Registration completed"))
                                                {
                                                        Tools.showAlertDialog("Registration completed",con);
                                                        //sound is created and played
                                                        dsound = MediaPlayer.create(con, R.raw.registration_completed);
                                                        dsound.start();
                                                }
                                        }
                                }
                        }
                        catch (Exception e)
                        {
                                Log.i("rajat", e.getLocalizedMessage());
                        }
                }
        }