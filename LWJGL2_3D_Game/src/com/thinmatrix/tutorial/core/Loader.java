package com.thinmatrix.tutorial.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.thinmatrix.tutorial.model.Geometry;
import com.thinmatrix.tutorial.shaders.StaticShader;

public class Loader
{
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public Geometry loadToVao(float[] positions, float[] texCoords, float[] normals, int[] indices)
	{
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(StaticShader.ATT_VERTEX_POSITION, 3, positions);
		storeDataInAttributeList(StaticShader.ATT_TEXCOORDS_POSITION, 2, texCoords);
		storeDataInAttributeList(StaticShader.ATT_NORMALS_POSITION, 3, normals);
		unbindVAO();
		return new Geometry(vaoID, indices.length);
	}
	
	private int createVAO()
	{
		int vaoID = glGenVertexArrays();
		vaos.add(vaoID);
		glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber, int vecSize, float[] data)
	{
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber, vecSize, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO()
	{
		glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices)
	{
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public int loadTexture(String fileName)
	{
		Texture texture = null;
		try
		{
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/IMAGES/" + fileName + ".png"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	public void cleanUp()
	{
		for (int vao : vaos)
		{
			glDeleteVertexArrays(vao);
		}
		
		for (int vbo : vbos)
		{
			glDeleteBuffers(vbo);
		}
		
		for(int texture : textures)
		{
			glDeleteTextures(texture);
		}
	}

}
