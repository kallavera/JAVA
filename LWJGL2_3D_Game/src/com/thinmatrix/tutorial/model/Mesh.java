package com.thinmatrix.tutorial.model;

import com.thinmatrix.tutorial.textures.Material;

public class Mesh
{
	private Geometry geometry;
	private Material material;

	public Mesh(Geometry geometry, Material material)
	{
		this.geometry = geometry;
		this.material = material;
	}

	public Geometry getGeometry()
	{
		return geometry;
	}

	public Material getMaterial()
	{
		return material;
	}
}
