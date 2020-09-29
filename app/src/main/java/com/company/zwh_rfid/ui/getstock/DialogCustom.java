package com.company.zwh_rfid.ui.getstock;
 
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.company.zwh_rfid.R;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * 公用的自定义对话框
 * @author Administrator
 *
 */
public class DialogCustom{
	
	private android.app.AlertDialog.Builder builder;
	private int layout;
	private AlertDialog dialog;
	private Window window;
	Context context;

	public DialogCustom(Context context, int layout){
		builder = new AlertDialog.Builder(context);
		this.layout = layout;
	}

	public DialogCustom(Context context, int theme, int layout){
		builder = new AlertDialog.Builder(context, theme);
		this.layout = layout;
	}
	
	public Builder getBuilder(){
		return builder;
	}
	
	/**
	 * 获取对话框的Window对象
	 * @return
	 */
	public Window getWindow(){
		dialog = builder.create();
		dialog.show();
		dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		window = dialog.getWindow();
		window.setContentView(layout);
		return window;
	}





	
	/**
	 * 通过ID获取对应的View
	 * @param id
	 * @return
	 */
	public View getViewById(int id){
		if(window == null) getWindow();
		return window.findViewById(id);
	}

	/**
	 * 设置需要添加关闭事件的按钮ID
	 * @param id
	 */
	public void setDismissButtonId(int id){
		View view = getViewById(id);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

	}

	/**
	 *
	 */

	public View setString(int id){
		final View view = getViewById(id);
		return view;
	}
	public EditText setString1(int id){
		final EditText view = dialog.findViewById(id);
		view.requestFocus();
		return view;
	}
	/**
	 * 关闭对话框
	 */
	public void dismiss(){
		if(dialog != null){
			dialog.dismiss();
		}
	}



}