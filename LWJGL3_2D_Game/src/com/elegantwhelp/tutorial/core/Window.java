package com.elegantwhelp.tutorial.core;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

public class Window
{
	private long window;
	private int width, height;
	private boolean fullScreen = false;

	public Window()
	{
		setSize(Game.WIDTH, Game.HEIGHT);
	}
	
	public void createWindow()
	{
		window = glfwCreateWindow
				(
					width,
					height,
					Game.TITLE,
					fullScreen ? glfwGetPrimaryMonitor() : 0,
					0
				);
		
		if(window == 0)
		{
			throw new IllegalStateException("[ERROR]: Fallo al crear la ventana");
		}
		
		if(!fullScreen)
		{
			glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
			GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
			glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		}
		
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
	}
	
	public void goFullScreen()
	{
		destroyWindow();
		setFullScreen(true);
		setSize(1280, 1024);
		createWindow();
	}
	
	public void goWindowed()
	{
		destroyWindow();
		setFullScreen(false);
		setSize(Game.WIDTH, Game.HEIGHT);
		createWindow();
	}
	
	public void destroyWindow()
	{
		glfwDestroyWindow(window);
	}
	
	public boolean shouldClose()
	{
		return glfwWindowShouldClose(window) ? true : false;
	}
	
	public void swap()
	{
		glfwSwapBuffers(window);
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setFullScreen(boolean fs)
	{
		fullScreen = fs;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public long getWindow()
	{
		return window;
	}
	
	public boolean getFullScreen()
	{
		return fullScreen;
	}
}
