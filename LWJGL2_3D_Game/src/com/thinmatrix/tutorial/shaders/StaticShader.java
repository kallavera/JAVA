package com.thinmatrix.tutorial.shaders;

public class StaticShader extends ShaderProgram
{
	public static final int ATT_VERTEX_POSITION = 0;
	
	private static final String VERTEX_FILE = "src/com/thinmatrix/tutorial/shaders/shader.vs";
	private static final String FRAGMENT_FILE = "src/com/thinmatrix/tutorial/shaders/shader.fs";

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(ATT_VERTEX_POSITION, "position");
	}

}
