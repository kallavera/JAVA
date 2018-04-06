package com.thinmatrix.tutorial.entities;

import org.lwjgl.util.vector.Vector3f;

import com.thinmatrix.tutorial.model.Mesh;

public class Entity
{
	private Mesh mesh;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	
	public Entity(Mesh mesh, Vector3f position, float rotX, float rotY, float rotZ, float scale)
	{
		this.mesh = mesh;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	public void translate(float dx, float dy, float dz)
	{
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void rotate(float rotX, float rotY, float rotZ)
	{
		this.rotX += rotX;
		this.rotY += rotY;
		this.rotZ += rotZ;
	}
	
	public void scale(float scale)
	{
		this.scale += scale;
	}

	public Mesh getMesh()
	{
		return mesh;
	}

	public void setMesh(Mesh mesh)
	{
		this.mesh = mesh;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public float getRotX()
	{
		return rotX;
	}

	public void setRotX(float rotX)
	{
		this.rotX = rotX;
	}

	public float getRotY()
	{
		return rotY;
	}

	public void setRotY(float rotY)
	{
		this.rotY = rotY;
	}

	public float getRotZ()
	{
		return rotZ;
	}

	public void setRotZ(float rotZ)
	{
		this.rotZ = rotZ;
	}

	public float getScale()
	{
		return scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}
	
	
}
