package com.thinmatrix.tutorial.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

import com.thinmatrix.tutorial.entities.Entity;
import com.thinmatrix.tutorial.model.Geometry;
import com.thinmatrix.tutorial.model.Mesh;
import com.thinmatrix.tutorial.shaders.StaticShader;
import com.thinmatrix.tutorial.toolbox.Maths;

public class Renderer
{
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000.0f;
	
	private Matrix4f projectionMatrix;

	public Renderer(StaticShader shader)
	{
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare()
	{
		glEnable(GL_DEPTH_TEST);
		glClearColor(0,  0,  0, 1);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void render(Entity entity, StaticShader shader)
	{
		Mesh mesh = entity.getMesh();
		Geometry geo = mesh.getGeometry();
		
		glBindVertexArray(geo.getVaoID());
		glEnableVertexAttribArray(StaticShader.ATT_VERTEX_POSITION);
		glEnableVertexAttribArray(StaticShader.ATT_TEXCOORDS_POSITION);
		
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTranformationMatric(transformationMatrix);
		
		glActiveTexture(mesh.getMaterial().getID());
		glBindTexture(GL_TEXTURE_2D, mesh.getMaterial().getID());
		glDrawElements(GL_TRIANGLES, geo.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(StaticShader.ATT_VERTEX_POSITION);
		glDisableVertexAttribArray(StaticShader.ATT_TEXCOORDS_POSITION);
		glBindVertexArray(0);
	}
	
	private void createProjectionMatrix()
	{
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
 
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
	}
}



































