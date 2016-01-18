package com.rajat.registrationcop290.Tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.rajat.registrationcop290.R;

/**
 * Created by Rajat on 14-01-2016.
 */
public class CustomTextWatcher implements TextWatcher {
    EditText eText;
    int id;
    Validate validation=new Validate();
    public CustomTextWatcher(EditText et,int id){
        this.eText =et;
        this.id=id;
        }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void afterTextChanged(Editable editable) {

        if (editable.length() == 0) {
            eText.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.home,0);
        } else {
            if(id==1){
                if(!(validation.validate_entryno(eText.getText().toString()))){
                    eText.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.abc_btn_radio_material,0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.about, 0);
                }
            }
            else if(id==2){
                if(!(validation.validate_name(eText.getText().toString()))) {
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.abc_btn_radio_material, 0);
                }
                else{
                    eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.about, 0);
                }
            }
            else{
                eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.about, 0);
            }
        }
    }
}
