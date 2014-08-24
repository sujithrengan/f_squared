package com.thehp.f;

import java.util.Random;




import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class Activity_mainmenu extends Activity  {
	//boolean touched=false,game=true;
	Paint temp=new Paint();
	class RenderView extends View {
			
		public RenderView(Context context) {
		super(context);
		}
		protected void onDraw(final Canvas canvas) {
			//draw level background
			canvas.drawPaint(f_engine.levels[f_engine.l_index].bg);
			//draw collectible
			
			temp=f_engine.levels[f_engine.l_index].bg;
			temp.setAlpha(150);
			if(f_engine.l_index==f_engine.maxlevel+1)
			temp.setColor(Color.BLACK);
			for(int i=0;i<f_engine.levels[f_engine.l_index].n;i++)
			{
				if(f_engine.levels[f_engine.l_index].c_hit[i]==0)
				{
					
					canvas.drawCircle(f_engine.levels[f_engine.l_index].c[i].x, 
							f_engine.levels[f_engine.l_index].c[i].y,
							f_engine.levels[f_engine.l_index].c[i].c_r, ball.b_paint);
					canvas.drawCircle(f_engine.levels[f_engine.l_index].c[i].x, 
							f_engine.levels[f_engine.l_index].c[i].y,
							f_engine.levels[f_engine.l_index].c[i].c_r*0.80f, temp);

				}
				else
				{
					canvas.drawCircle(f_engine.levels[f_engine.l_index].c[i].x, 
							f_engine.levels[f_engine.l_index].c[i].y,
							--f_engine.levels[f_engine.l_index].c[i].c_r, ball.b_paint);
		
					if(f_engine.levels[f_engine.l_index].c[i].c_r>0)
						{ball.r-=0.07f;
						canvas.drawCircle(ball.x, ball.y,ball.r, ball.b_paint);
						canvas.drawCircle(ball.x, ball.y,ball.r*0.92f, temp);
					canvas.drawCircle(ball.x, ball.y,ball.r*0.70f, ball.b_paint);
				}
				}
			}
			//Draw_border(canvas,ball.b_paint);
			//draw ball
			canvas.drawCircle(ball.x, ball.y,ball.r, ball.b_paint);
			canvas.drawCircle(ball.x, ball.y,ball.r*0.92f, temp);
		canvas.drawCircle(ball.x, ball.y,ball.r*0.85f, ball.b_paint);
		
			if(f_engine.game==false)
			{
			
			canvas.drawText(f_engine.title, 240, 400, f_engine.txt);
			
			}
			else if(f_engine.gameover==false)
			{
				
				if(f_engine.txt.getAlpha()<=0)f_engine.txt.setAlpha(0);
				else
				{
					f_engine.txt.setAlpha((int) (f_engine.txt.getAlpha()*0.95));
					canvas.drawText(f_engine.title, 240, 400, f_engine.txt);
				}
				
			}
			else
			{
				canvas.drawText(f_engine.endtitle, 240, 400, f_engine.txt);
				
			}
			
			
			
					f_engine.update(canvas);

		
		invalidate();
		}
		public void Draw_border(Canvas canvas,Paint paint)
		{
			canvas.drawRect(0, 0, 10, canvas.getHeight(), paint);
			canvas.drawRect(0, 0, canvas.getWidth(), 10, paint);
			canvas.drawRect(canvas.getWidth()-10, 0, canvas.getWidth(), canvas.getHeight()-10, paint);
			canvas.drawRect(0, canvas.getHeight()-10, canvas.getWidth(), canvas.getHeight(), paint);
		}
		@Override
		  public boolean onTouchEvent(MotionEvent event) {
		    
			final float X = event.getX();
		    final float Y = event.getY();
		    
		    switch (event.getAction()) {
		    case MotionEvent.ACTION_DOWN:
		    	
						f_engine.touched(X,Y);
		    	
		      return true;
		    default:
		      return false;
		    }
		}
	
		

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
				f_engine.init();
				ball.ballinit();
				Log.e("logged","init init");
		setContentView(new RenderView(this));
		}

	@Override
	protected void onPause() {
		Log.e("state","Paused");
		super.onPause();
	}
	@Override
	protected void onStop() {
		f_engine.init();
		ball.ballinit();
		Log.e("state","Stopped");
		super.onStop();
	}
}

