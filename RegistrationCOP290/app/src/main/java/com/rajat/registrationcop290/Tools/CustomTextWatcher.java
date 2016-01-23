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
    public CustomTextWatcher(EditText et){
        eText =et;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() == 0) {
            eText.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ccheck,0);
        } else{
            eText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cclose, 0);
        }
    }
}
