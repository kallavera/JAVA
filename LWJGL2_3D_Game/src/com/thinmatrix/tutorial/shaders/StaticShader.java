package com.thinmatrix.tutorial.shaders;

import org.lwjgl.util.vector.Matrix4f;

import com.thinmatrix.tutorial.entities.Camera;
import com.thinmatrix.tutorial.entities.Light;
import com.thinmatrix.tutorial.toolbox.Maths;

public class StaticShader extends ShaderProgram
{
	public static final int ATT_VERTEX_POSITION = 0;
	public static final int ATT_TEXCOORDS_POSITION = 1;
	public static final int ATT_NORMALS_POSITION = 2;
	
	private static final String VERTEX_FILE = "src/com/thinmatrix/tutorial/shaders/shader.vs";
	private static final String FRAGMENT_FILE = "src/com/thinmatrix/tutorial/shaders/shader.fs";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColor;
	private int location_shineDumper;
	private int location_reflectivity;

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(ATT_VERTEX_POSITION, "position");
		super.bindAttribute(ATT_TEXCOORDS_POSITION, "texCoords");
		super.bindAttribute(ATT_NORMALS_POSITION, "normals");
	}

	@Override
	protected void geAlltUniformLocation()
	{
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColor = super.getUniformLocation("lightColor");
		location_shineDumper = super.getUniformLocation("shineDumper");
		location_reflectivity = super.getUniformLocation("reflectivity");
	}
	
	public void loadShineVariables(float dumper, float reflectivity)
	{
		super.loadInUniform(location_shineDumper, dumper);
		super.loadInUniform(location_reflectivity, reflectivity);
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
	
	public void loadLight(Light light)
	{
		super.loadInUniform(location_lightPosition, light.getPosition());
		super.loadInUniform(location_lightColor, light.getColor());
	}

}