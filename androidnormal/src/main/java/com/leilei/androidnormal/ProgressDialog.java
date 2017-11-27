package com.leilei.androidnormal;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;

/**
 * Created by leilei on 2017/11/27.
 */

public class ProgressDialog extends Dialog {
    public ProgressDialog(@NonNull Context context) {
        super(context);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setContentView(R.layout.normal_progress);
    }
}
