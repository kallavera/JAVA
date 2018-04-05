package com.thinmatrix.tutorial.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer
{

	public Renderer()
	{
		
	}
	
	public void prepare()
	{
		glClearColor(0,  0,  0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void render(Geometry geo)
	{
		glBindVertexArray(geo.getVaoID());
		glEnableVertexAttribArray(Loader.ATTRIB_POSITIONS);
		
		glDrawElements(GL_TRIANGLES, geo.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(Loader.ATTRIB_POSITIONS);
		glBindVertexArray(0);
	}
}
