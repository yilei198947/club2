package com.iscard.club.dialog;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iscard.club.R;

public class DialogUtil {
	
	public static void dateDialog(Context context, OnDateSetListener listener) {
		Calendar calendar = Calendar.getInstance();
		Dialog dateDialog = new DatePickerDialog(context, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		dateDialog.show();
	}
	
	public static Dialog dialogWait(Context context, String type) {

		Dialog dialog = new Dialog(context, R.style.waitDialog);
		dialog.setContentView(R.layout.activity_login_await);
		dialog.setCancelable(false);
		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dialog.dismiss();
					return true;
				}
				return false;
			}
		});

		TextView dialogText = (TextView) dialog.findViewById(R.id.logDialogText);
		dialogText.setText(type);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		lp.alpha = 1f;
		dialogWindow.setAttributes(lp);
		return dialog;

	}

}
