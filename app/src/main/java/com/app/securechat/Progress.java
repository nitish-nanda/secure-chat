package com.app.securechat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.app.securechat.R;

public class Progress {

    private static Dialog dialog = null;

    public static void show(Context context) {
        if (dialog != null) {
            dialog = null;
        }
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.progress_dialog);
        dialog.show();
    }

    public static void hide(Context context) {
        if (dialog != null) {
            dialog.hide();
            dialog = null;
        }
    }
}
