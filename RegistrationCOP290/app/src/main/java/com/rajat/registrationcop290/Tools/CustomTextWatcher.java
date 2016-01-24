package com.rajat.registrationcop290.Tools;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.rajat.registrationcop290.R;

/**
 * Created by Rajat on 14-01-2016.
 */
public class CustomTextWatcher implements TextWatcher {

    EditText eText;
    //id tells whether watcher is applied to teamName of entryNumber or NameOfStudent
    int id;
    //validate editText and show repective drawable whether valid or not
    Validate validation=new Validate();
    public AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String > autoTextAdapter;
    //constructor for team name
    public CustomTextWatcher(EditText et,int id){
        this.eText =et;
        this.id=id;
    }
    //costructor for entryNumber and studentNames
    public CustomTextWatcher(EditText et,int id,AutoCompleteTextView autoCompleteTextView,ArrayAdapter<String> arrAdap){
        eText =et;
        this.id=id;
        this.autoCompleteTextView=autoCompleteTextView;
        autoTextAdapter=arrAdap;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    //show drop down when editText is either studentName of enryNumber
    @Override
    public void onTextChanged(CharSequence userInput, int i, int i1, int i2) {
        if(id==1 || id==2){
            if(autoCompleteTextView!=null && autoTextAdapter!=null){
                showDropDown();
            }
        }
    }
    //change drawables on validation and show dropdown as per userInpur
    @Override
    public void afterTextChanged(Editable editable) {

        if (editable.length() == 0) {
            //only left drawables when fields are empty
            if(id==0){
                eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_group_white_24dp, 0, 0,0);
            }else if(id==1){
                eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_payment_white_24dp,0, 0,0);
            }else if(id==2){
                eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account_circle_white_24dp,0, 0,0);
            }
        } else {
            if(id==1){
                //synchronised validation for entryNumber
                if(!(validation.validate_entryno(eText.getText().toString()))){
                    eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_payment_white_24dp,0, R.drawable.ic_thumb_down_white_24dp,0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_payment_white_24dp, 0, R.drawable.ic_thumb_up_white_24dp, 0);
                }
                onItemDropDownSelect();
            }
            else if(id==2){
                //synchronised validation for studentNames
                if(!(validation.validate_name(eText.getText().toString()))) {
                    eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account_circle_white_24dp, 0, R.drawable.ic_thumb_down_white_24dp, 0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_account_circle_white_24dp, 0, R.drawable.ic_thumb_up_white_24dp, 0);
                }
                onItemDropDownSelect();
            }
            else{
                //synchronised validation for teamName
                if(eText.getText().length()==0) {
                    eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_group_white_24dp, 0, R.drawable.ic_thumb_down_white_24dp, 0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_group_white_24dp, 0, R.drawable.ic_thumb_up_white_24dp, 0);
                }
            }
        }
    }
    //boolean to show dropdown or not when item selected in drop down pop up was shown again
    public boolean dropOrNot=true;

public void showDropDown(){

    //Log.i("rajat", "autoCompleteTextView.isPopUpShowing: " + autoCompleteTextView.isPopupShowing() + " autoCompleteTextView.isFocusable(); " + autoCompleteTextView.isFocusable());
    //get location of editText to place autoComplete Text view below edittext
    final int[] edTextLocation = new int[2];
    eText.getLocationOnScreen(edTextLocation);
    autoCompleteTextView.setText(eText.getText());
    autoCompleteTextView.setVerticalScrollBarEnabled(true);
    autoCompleteTextView.setDropDownBackgroundResource(R.color.ultraLightGray);
//set horizontal offset for autocomplete view
    if(eText!=null){
        int width=ContextCompat.getDrawable(eText.getContext(), R.drawable.transparent).getIntrinsicWidth();
        autoCompleteTextView.setDropDownHorizontalOffset(edTextLocation[0]+width);
        autoCompleteTextView.setDropDownWidth(eText.getWidth() / 2 -width/2);
    }else{
        autoCompleteTextView.setDropDownHorizontalOffset(edTextLocation[0]);
        autoCompleteTextView.setDropDownWidth(eText.getWidth() / 2);
    }
    //set vertical offset for autocomplete view
    autoCompleteTextView.setDropDownVerticalOffset(edTextLocation[1]+(eText.getHeight()*3)/4);

        try {
            //posted a little late so that view can be added to window and not show exception
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Log.i("rajat", autoCompleteTextView.getLineCount()+": autoCompleteTextView.getLineCount()"+ autoCompleteTextView.getAdapter().getCount());
                    if (dropOrNot) {
                        //drop down height is set according to suggestion list size
                        if (autoCompleteTextView.getAdapter().getCount() == 0) {
                            autoCompleteTextView.setDropDownHeight(0);
                        } else if (autoCompleteTextView.getAdapter().getCount() == 1) {
                            autoCompleteTextView.setDropDownHeight((eText.getHeight()*3)/2 );
                        }  else if (autoCompleteTextView.getAdapter().getCount() == 2) {
                            autoCompleteTextView.setDropDownHeight(eText.getHeight() * 3);
                        } else if (autoCompleteTextView.getAdapter().getCount() == 3) {
                            autoCompleteTextView.setDropDownHeight(eText.getHeight() * 4);
                        } else {
                            autoCompleteTextView.setDropDownHeight(eText.getHeight() * 5);
                        }
                        autoCompleteTextView.showDropDown();
                    }
                }
            }, 100L);

        } catch (WindowManager.BadTokenException b) {
            Log.i("rajat", "showDropdown" + b.getLocalizedMessage());
        }

    //Log.i("rajat", edTextLocation[0] + " x,y " + edTextLocation[1] + "editText.getY(): " + eText.getY());
}
    public void onItemDropDownSelect(){
        if (autoCompleteTextView != null) {
            //Log.i("rajat", "autoCompleteTextView.isPopUpShowing: " + autoCompleteTextView.isPopupShowing() + " autoCompleteTextView.isFocusable(); " + autoCompleteTextView.isFocusable());
            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Log.i("rajat", "autoCompleteTextView.onItemClickCalled: " + adapterView.getItemAtPosition(i));
                    //when item is seleted from suggestion list then editText is set with that item
                    eText.setText(adapterView.getItemAtPosition(i) + "");//i + " " + l + " " +
                    //do not show dropdown just after item selection
                    dropOrNot=false;
                    autoCompleteTextView.dismissDropDown();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //dropdown should be shown in future
                            dropOrNot = true;
                        }
                    }, 500);
                }
            });
        }
    }
}
