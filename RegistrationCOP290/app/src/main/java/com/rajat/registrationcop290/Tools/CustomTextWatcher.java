package com.rajat.registrationcop290.Tools;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.rajat.registrationcop290.R;
import com.rajat.registrationcop290.RegisterActivity;

/**
 * Created by Rajat on 14-01-2016.
 */
public class CustomTextWatcher implements TextWatcher {
    EditText eText;
    int id;
    Validate validation=new Validate();


    //EditText editText;
    public  CustomAutoCompleteView cv;
    ArrayAdapter<String > cvAdapter;
    public CustomTextWatcher(EditText et,int id){
        this.eText =et;
        this.id=id;
    }
    public CustomTextWatcher(EditText et,int id,CustomAutoCompleteView cacv,ArrayAdapter<String> arrAdap){
        eText =et;
        this.id=id;
        cv=cacv;
        cvAdapter=arrAdap;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence userInput, int i, int i1, int i2) {
        if(id==1 || id==2){
             /*
                    List<Models> products = databaseH.readModels(userInput.toString());
                    int rowCount = products.size();

                    String[] model = new String[rowCount];
                    int x = 0;

                    for (Models record : products) {

                        model[x] = record.getModelName();
                        x++;
                    }
                    */
            //con.item = mainActivity.getItemsFromDb(userInput.toString());
            /*String[] model = new String[3];
            String[] one= {"Dont","Dont","Dont"};
            String[] two= {"PDont","PDont","PDont"};
            if(userInput.toString().equals("P")){
                model=two;
            }else{
                model=one;
            }*/
            if(cv!=null && cvAdapter!=null){
                //cv.dismissDropDown();
                // update the adapater
                /*cvAdapter.notifyDataSetChanged();
                cvAdapter = new ArrayAdapter<String>(cv.getContext(), android.R.layout.simple_dropdown_item_1line, model);
                cv.setAdapter(cvAdapter);*/
                //cv.showDropDown();
                cvDrop();

            }
        }
    }
    @Override
    public void afterTextChanged(Editable editable) {

        if (editable.length() == 0) {
            eText.setCompoundDrawablesWithIntrinsicBounds(0,0, 0,0);
        } else {
            if(id==1){
                if(!(validation.validate_entryno(eText.getText().toString()))){
                    eText.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.cclose,0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ccheck, 0);
                }
                cvSelect();
                //cv.getDropDownBackground();

            }
            else if(id==2){
                if(!(validation.validate_name(eText.getText().toString()))) {
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cclose, 0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ccheck, 0);
                }
                cvSelect();
            }
            else{
                if(eText.getText().length()==0) {
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cclose, 0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ccheck, 0);
                }
                //eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }
        }
    }
    public boolean dropOrNot=true;
public void cvDrop(){

    Log.i("rajat", "cv.isPopUpShowing: " + cv.isPopupShowing() + " cv.isFocusable(); " + cv.isFocusable());
    final int[] edTextLocation = new int[2];
    eText.getLocationOnScreen(edTextLocation);
    cv.setText(eText.getText());
    cv.setVerticalScrollBarEnabled(true);
    cv.setDropDownBackgroundResource(R.color.ultraLightGray);
    //cv.setDropDownHeight(400);
    //cv.setDropDownHeight(eText.getHeight() * 5);
    //Log.i("rajat", cv.getLineCount()+": cv.getLineCount()"+ cv.getAdapter().getCount());
    cv.setDropDownWidth(eText.getWidth()/2);
    cv.setDropDownHorizontalOffset(edTextLocation[0]);
    cv.setDropDownVerticalOffset(edTextLocation[1] + eText.getHeight());
   /* Handler h = new Handler();
    h.postDelayed(new Runnable() {
        @Override
        public void run() {
//            Looper.prepare();
            int[] edTextLocationC = new int[2];
            eText.getLocationOnScreen(edTextLocationC);
            if(edTextLocationC!=edTextLocation){
                cv.setDropDownHorizontalOffset(edTextLocationC[0]);
                cv.setDropDownVerticalOffset(edTextLocationC[1] + eText.getHeight());
                if (dropOrNot) {
                    cv.showDropDown();
                }
            }

            //Looper.loop();
        }
    }, 2000);*/
   //eText.getLocationOnScreen();
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i("rajat", cv.getLineCount()+": cv.getLineCount()"+ cv.getAdapter().getCount());
                    if (dropOrNot) {
                        if (cv.getAdapter().getCount() == 0) {
                            cv.setDropDownHeight(0);
                        } else if (cv.getAdapter().getCount() == 1) {
                            cv.setDropDownHeight(eText.getHeight() );
                        }  else if (cv.getAdapter().getCount() == 2) {
                            cv.setDropDownHeight(eText.getHeight() * 3);
                        } else if (cv.getAdapter().getCount() == 3) {
                            cv.setDropDownHeight(eText.getHeight() * 4);
                        } else {
                            cv.setDropDownHeight(eText.getHeight() * 5);
                        }
                        cv.showDropDown();
                    }
                }
            }, 100L);

        } catch (WindowManager.BadTokenException b) {
            Log.i("rajat", "showDropdown" + b.getLocalizedMessage());

        }

    //}
    Log.i("rajat", edTextLocation[0] + " x,y " + edTextLocation[1] + "editText.getY(): " + eText.getY());
}
    public void cvSelect(){
        if (cv != null) {

            Log.i("rajat", "cv.isPopUpShowing: " + cv.isPopupShowing() + " cv.isFocusable(); " + cv.isFocusable());
            cv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.i("rajat", "cv.onItemClickCalled: " + adapterView.getItemAtPosition(i));

                    eText.setText(adapterView.getItemAtPosition(i)+"");//i + " " + l + " " +
                    dropOrNot=false;
                    cv.dismissDropDown();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            dropOrNot = true;
                        }
                    }, 500);

                }
            });
        }
    }

}
