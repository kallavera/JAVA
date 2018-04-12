package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera
{
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;

	public Camera()
	{
		
	}

	public Camera(Vector3f position, float pitch, float yaw, float roll)
	{
		this.position = position;
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
	}
	
	public void move()
	{
		/*
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) { this.position.z -= 0.05f; position.x += yaw;}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) { this.position.z += 0.05f; position.x -= yaw;}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) { this.position.x -= 0.05f; position.z -= yaw;}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) { this.position.x += 0.05f; position.z += yaw;}
		*/
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) { this.position.z -= 0.5f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) { this.position.z += 0.5f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) { this.position.x -= 0.5f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) { this.position.x += 0.5f; }
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) { this.pitch -= 1f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) { this.pitch += 1f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) { this.yaw -= 1f; }
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) { this.yaw += 1f; }
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
