package models;

import textures.Texture;

public class TexturedModel
{
	private RawModel model;
	private Texture texture;
	
	public TexturedModel(RawModel model, Texture texture)
	{
		this.model = model;
		this.texture = texture;
	}

	public RawModel getModel()
	{
		return model;
	}

	public Texture getTexture()
	{
		return texture;
	}
}
