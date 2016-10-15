package com.stevefat.novel.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.stevefat.novel.R;

/**
 * Author: ngh
 * date: 2016/10/12
 */

public class DialogUtils {
    Dialog dialog;
    Context context;

    public DialogUtils(Context context,String msg) {
        this.context = context;
        initDialog(msg);
    }

    private void initDialog(String msg) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        dialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        ProgressWheel progressWheel = (ProgressWheel) view.findViewById(R.id.progress_wheel);
        TextView tv = (TextView) view.findViewById(R.id.txt_msg);
        tv.setText(msg);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
