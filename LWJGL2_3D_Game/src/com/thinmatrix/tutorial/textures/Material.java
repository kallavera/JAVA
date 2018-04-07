package com.thinmatrix.tutorial.textures;

public class Material
{
	private int textureID;
	
	private float shineDumper = 1;
	private float reflectivity = 0;

	public Material(int id)
	{
		this.textureID = id;
	}
	
	public int getID()
	{
		return this.textureID;
	}

	public float getShineDumper()
	{
		return shineDumper;
	}

	public void setShineDumper(float shineDumper)
	{
		this.shineDumper = shineDumper;
	}

	public float getReflectivity()
	{
		return reflectivity;
	}

	public void setReflectivity(float reflectivity)
	{
		this.reflectivity = reflectivity;
	}
	
}
