package com.thinmatrix.tutorial.shaders;

import org.lwjgl.util.vector.Matrix4f;

import com.thinmatrix.tutorial.entities.Camera;
import com.thinmatrix.tutorial.toolbox.Maths;

public class StaticShader extends ShaderProgram
{
	public static final int ATT_VERTEX_POSITION = 0;
	public static final int ATT_TEXCOORDS_POSITION = 1;
	
	private static final String VERTEX_FILE = "src/com/thinmatrix/tutorial/shaders/shader.vs";
	private static final String FRAGMENT_FILE = "src/com/thinmatrix/tutorial/shaders/shader.fs";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(ATT_VERTEX_POSITION, "position");
		super.bindAttribute(ATT_TEXCOORDS_POSITION, "texCoords");
	}

	@Override
	protected void geAlltUniformLocation()
	{
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	
	public void loadTranformationMatric(Matrix4f matrix)
	{
		super.loadInUniform(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix)
	{
		super.loadInUniform(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		
		super.loadInUniform(location_viewMatrix, viewMatrix);
	}

}
