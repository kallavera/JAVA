package com.elegantwhelp.tutorial.core.models;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.elegantwhelp.tutorial.core.shaders.Shader;

public class Model
{
	private int draw_count;
	private int v_id;
	private int t_id;
	private int i_id;
	
	public Model(float[] vertices, float[] tex_coords, int[] indices)
	{
		draw_count = indices.length;
		
		v_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, crateFloatBuffer(vertices), GL_STATIC_DRAW);
		
		t_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glBufferData(GL_ARRAY_BUFFER, crateFloatBuffer(tex_coords), GL_STATIC_DRAW);
		
		i_id = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, crateIntBuffer(indices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void render()
	{
		glEnableVertexAttribArray(Shader.ATT_VERTEX_POSITIONS);
		glEnableVertexAttribArray(Shader.ATT_TEXTURE_COORDS);
		
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glVertexAttribPointer(Shader.ATT_VERTEX_POSITIONS, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glVertexAttribPointer(Shader.ATT_TEXTURE_COORDS, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		glDrawElements(GL_TRIANGLE_FAN, draw_count, GL_UNSIGNED_INT, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(Shader.ATT_VERTEX_POSITIONS);
		glDisableVertexAttribArray(Shader.ATT_TEXTURE_COORDS);
	}
	
	private FloatBuffer crateFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	private IntBuffer crateIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
}
