package com.elegantwhelp.tutorial.core.inputs;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Input
{
	public static final int LEFT_CLICK = 0;
	long window;
	private boolean up, down, left, right, close, fire;
	
	public Input(long window)
	{
		this.window = window;
	}
	
	public void   init()
	{
		up = false;
		down = false;
		left = false;
		right = false;
		close = false;
		fire = false;
	}
	
	public void update()
	{
		if(glfwGetKey(window, GLFW_KEY_W) == GL_TRUE)
		{up = true;}else{up = false;}
		if(glfwGetKey(window, GLFW_KEY_S) == GL_TRUE)
		{down = true;}else{down = false;}
		if(glfwGetKey(window, GLFW_KEY_A) == GL_TRUE)
		{left = true;}else{left = false;}
		if(glfwGetKey(window, GLFW_KEY_D) == GL_TRUE)
		{right = true;}else{right = false;}
		
		if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE)
		{close = true;}else{close = false;}
		
		if(glfwGetMouseButton(window, LEFT_CLICK) == GL_TRUE)
		{fire = true;}else{fire = false;}
	}
	
	public boolean isUp()
	{
		return up;
	}

	public boolean isDown()
	{
		return down;
	}

	public boolean isLeft()
	{
		return left;
	}

	public boolean isRight()
	{
		return right;
	}

	public boolean isClose()
	{
		return close;
	}

	public boolean isFire()
	{
		return fire;
	}
}
