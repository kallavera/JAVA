package com.thinmatrix.tutorial.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera
{
	private Vector3f position = new Vector3f(0,  0,  0);
	private float pitch;
	private float yaw;
	private float roll;

	public Camera()
	{
		
	}
	
	public void move()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_W) == true){ position.z -= 0.1f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_S) == true){ position.z += 0.1f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_A) == true){ position.x -= 0.1f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_D) == true){ position.x += 0.1f; }
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public float getPitch()
	{
		return pitch;
	}

	public float getYaw()
	{
		return yaw;
	}

	public float getRoll()
	{
		return roll;
	}

}