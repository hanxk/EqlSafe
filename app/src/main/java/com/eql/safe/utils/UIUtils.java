package com.eql.safe.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class UIUtils {

	public UIUtils() {
	}


	public static void showToast(final Context context, int tipRes) {

		String text = context.getString(tipRes);
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

	}
	
	

	public static void showToast(final Context context, String tipText) {

		Toast.makeText(context, tipText, Toast.LENGTH_SHORT).show();

	}
	

	/**
	 * 获取屏幕的宽高
	 * 
	 * @param context
	 *            0: width 1: height
	 * @return
	 */
	public static int[] getScreenSize(Context context) {

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int[] widthAndHeight = { display.getWidth(), display.getHeight() };

		return widthAndHeight;
	}

}
