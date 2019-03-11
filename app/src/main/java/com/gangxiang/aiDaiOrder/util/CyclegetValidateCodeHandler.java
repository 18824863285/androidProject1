package com.gangxiang.aiDaiOrder.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 循环获取验证码
 * @author brock
 *
 */
public class CyclegetValidateCodeHandler {
	public static Timer timer;
	private static Handler handler;

	public static void getValidateCode(final Button button,final Context context) {
		handler = new Handler();
		button.setTextSize(15);
		button.setEnabled(false);
		button.setText("60"+ "s重新发送");
		TimerTask task = new TimerTask() {
			int i = 60;	
			@Override
			public void run() {
				if (i >= 1) {
					
					handler.post(new Runnable() {

						@Override
						public void run() {
							button.setTextSize(15);
							button.setText(i+ "s重新发送");
						}
					});
					i--;
				} else {
					timer.cancel();
					handler.post(new Runnable() {

						@Override
						public void run() {
							button.setText("发送验证码");
							button.setTextSize(15);
							button.setEnabled(true);
						}
					});
				}

			}
		};
		
		if (timer != null) {
			timer.cancel();
		} 
		
	    timer = new Timer(true);		
		timer.schedule(task, 1000, 1000);		
	}	
}
