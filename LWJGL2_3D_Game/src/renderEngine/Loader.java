package renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

public class Loader
{
	public static final int ATT_POSITIONS = 0;
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();

	public Loader()
	{
		
	}
	
	public RawModel loadToVAO(float[] positions)
	{
		int vaoID = createVAO();
		storeDataInAttributeList(ATT_POSITIONS, positions);
		unbindVAO();
		
		return new RawModel(vaoID, positions.length / 3);
	}
	
	private int createVAO()
	{
		int vaoID = glGenVertexArrays();
		vaos.add(vaoID);
		glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber, float[] data)
	{
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO()
	{
		glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public void cleanUp()
	{
		for(int vao:vaos)
		{
			glDeleteVertexArrays(vao);
		}
		
		for(int vbo:vbos)
		{
			glDeleteBuffers(vbo);
		}
	}
}
