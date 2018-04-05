package com.thinmatrix.tutorial.core;

public class Geometry
{
	private int vaoID;
	private int vertexCount;
	
	public Geometry(int vaoID, int vertexCount)
	{
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID()
	{
		return vaoID;
	}

	public int getVertexCount()
	{
		return vertexCount;
	}
}