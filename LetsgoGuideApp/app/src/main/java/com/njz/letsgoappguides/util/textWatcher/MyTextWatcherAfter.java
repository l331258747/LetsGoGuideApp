package com.njz.letsgoappguides.util.textWatcher;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by LGQ
 * Time: 2019/3/7
 * Function:
 */

public abstract class MyTextWatcherAfter implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        callback(s);
    }

    public abstract void callback(Editable s);

}
