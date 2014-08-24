package com.thehp.f;

import java.util.Locale;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

public class f_engine {

	public static final int TIME_DELAY=4000;
	public static String title="bounce.";
	public static String endtitle="Well bounced.";
	public static int level=1;
	public static Paint bg=new Paint();
	public static Paint txt=new Paint();
	public static float g= 0.7f;
	public static boolean game=false,tut=true,gameover=false;
	public static Level levels[]; 
	public static int unlocked[];
	public static int numlevels=6,l_index,maxlevel=0;
	public static int screenX=400,screenY=800;

	public static void init()
	{
		
		levels=new Level[numlevels];
		l_index=0;
		levels[l_index]=new Level();
		Log.e("logged","fengine init");
		unlocked=new int[numlevels];
		for(int i=0;i<numlevels;i++)unlocked[i]=0;
		unlocked[0]=1;
		txt_init();
		}
	
	public static void txt_init()
	{
		txt.setStyle(Style.FILL_AND_STROKE);
		txt.setStrokeWidth(6);
		txt.setColor(Color.BLACK);
		txt.setTextAlign(Align.CENTER);
		txt.setTextSize(50);
		txt.setTextSkewX((float) -0.25);
	}
	public static void update(Canvas canvas)
	{
		
		int temp=0;
		screenX=canvas.getWidth();
		screenY=canvas.getHeight();
		for(int i=0;i<levels[f_engine.l_index].n;i++)
		{
			double cdist=Math.sqrt(Math.pow(levels[f_engine.l_index].c[i].x-ball.x, 2.0)+
					Math.pow(levels[f_engine.l_index].c[i].y-ball.y, 2.0));
			if(cdist<=ball.r+levels[f_engine.l_index].c[i].c_r)
				{if(levels[l_index].c[i].c_r>0)levels[l_index].c_hit[i]=1;
				
				}
			if(levels[l_index].c_hit[i]==1)temp++;
			
		}
		if(temp==levels[l_index].n){
			levels[l_index].completed=true;
		}
		ball.x=ball.x+ball.vel_x;
		ball.vel_y=ball.vel_y+f_engine.g;
		ball.y=ball.y+ball.vel_y;
		if((ball.y>(canvas.getHeight()-ball.r))||(ball.y<ball.r))
		{if(ball.y>canvas.getHeight()-ball.r){ball.y=canvas.getHeight()-ball.r;//game=touched=false;
		//if(touched==true)
			//gamequitcode
			}
		if(ball.y<ball.r)ball.y=ball.r;
		
		ball.vel_y*=-0.88f;
		ball.vel_x*=0.95f;
		//ball.r-=10;
		}
		if(f_engine.l_index==0)
		{
		if((ball.x>(canvas.getWidth()-ball.r))||(ball.x<ball.r))
		{
			
			if(f_engine.levels[l_index].completed)
			{
				if(ball.x>canvas.getWidth()&&ball.vel_x>0){
				ball.x=0;
			++l_index;if(unlocked[l_index]==0){f_engine.levels[l_index]=new Level();
			unlocked[l_index]=1;++maxlevel;
			}
				}
				
				else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
			}
		else 
			{
			if(ball.vel_x>0)
				{ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;}
			else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
			}

		
		}
		}
		else if(f_engine.l_index<=maxlevel)
		{
			if(maxlevel<numlevels-2){
			if(levels[l_index].completed){
				if(ball.x>canvas.getWidth()){
					ball.x=0;++l_index;if(unlocked[l_index]==0){f_engine.levels[l_index]=new Level();
					unlocked[l_index]=1;++maxlevel;
					}
				}
					else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth();
					--l_index;}
			}
			else
			{	
				if(ball.x>canvas.getWidth()-ball.r&&ball.vel_x>0){
					ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;
				}
					
					
					else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth();
					--l_index;}
			}
						
		}
			else
				{
				
				if(levels[l_index].completed){
					gameover=true;
					if(ball.x>canvas.getWidth()){
						ball.x=0;++l_index;if(unlocked[l_index]==0){f_engine.levels[l_index]=new Level(false);
						//unlocked[l_index]=1;++maxlevel;
						f_engine.txt.setStrokeWidth(2);
						txt.setTextSize(28);
						f_engine.txt.setColor(Color.WHITE);
						}
					}
						else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth(); --l_index;}
						//else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
				}
				else
				{	
					if(ball.x>canvas.getWidth()-ball.r&&ball.vel_x>0){
						ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;
					}
						
						
						else if(ball.x<0&&ball.vel_x<0){ball.x=canvas.getWidth();
						--l_index;}
					
					
					
				}
				}
				}
		
		else
		{
			if((ball.x>(canvas.getWidth()-ball.r))||(ball.x<ball.r))
			{
				if(ball.x>canvas.getWidth()-ball.r&&ball.vel_x>0)
				{ball.x=canvas.getWidth()-ball.r;ball.vel_x*=-0.88f;}
			else if (ball.x<ball.r&&ball.vel_x<0){ball.x=ball.r;ball.vel_x*=-0.88f;}
				
		}	
		}
}
	public static void touched(float eventX,float eventY)
	{
		double dist=Math.sqrt(Math.pow(eventX-ball.x, 2.0)+Math.pow(eventY-ball.y, 2.0));
	    if(dist<ball.r*2){
	    	game=true;
	
	     float tan =Math.abs((eventY-ball.y)/(eventX-ball.x));
	    if(eventY>ball.y&&eventX<ball.x)
	     {ball.vel_y= (float) -(Math.sin(Math.atan(tan))*20);
	     ball.vel_x= (float) (Math.cos(Math.atan(tan))*20);
	     }
	    else if(eventY>ball.y&&eventX>ball.x)
	     {ball.vel_y= (float) -(Math.sin(Math.atan(tan))*20);
	     ball.vel_x= (float) -(Math.cos(Math.atan(tan))*20);
	     }
	    else if(eventY<ball.y&&eventX<ball.x)
	     {ball.vel_y= (float) (Math.sin(Math.atan(tan))*20);
	     ball.vel_x= (float) (Math.cos(Math.atan(tan))*20);
	     }
	    else if(eventY<ball.y&&eventX>ball.x)
	     {ball.vel_y= (float) (Math.sin(Math.atan(tan))*20);
	     ball.vel_x= (float) -(Math.cos(Math.atan(tan))*20);
	     }
	    }
	}
}
