package com.thinmatrix.tutorial.core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FPS_CAP = 60;
	
	public static final String TITLE = "LWJGL2 3D Tutorial";

	public DisplayManager()
	{
		
	}
	
	public static void createDisplay()
	{
		ContextAttribs attribs = new ContextAttribs(3,2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setVSyncEnabled(true);
			Display.setTitle(TITLE);
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public static void updateDisplay()
	{
		Display.sync(FPS_CAP);
		Display.update();
	}
	
	public static void closeDisplay()
	{
		Display.destroy();
	}

}
