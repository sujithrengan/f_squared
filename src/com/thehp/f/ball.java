package com.thehp.f;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class ball {
	
	public static Paint b_paint=new Paint();
	static float vel_y=0,vel_x=0;
	static float x=240;
	static float r=100;
	static float y=400;
	public static void ballinit()
	{
		ball.b_paint.setStyle(Style.FILL);
		ball.b_paint.setColor(Color.BLACK);
		Log.e("Looged","ball.init");
	}

}
