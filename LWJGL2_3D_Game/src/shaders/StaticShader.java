package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram
{
	private static final String VERTEX_FILE= "src/shaders/vertex.shader";
	private static final String FRAGMENT_FILE= "src/shaders/fragment.shader";
	
	private int uniform_location_transformationMatrix;

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(ShaderProgram.ATT_POSITIONS, "position");
		super.bindAttribute(ShaderProgram.ATT_TEX_COORDS, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations()
	{
		uniform_location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}
	
	public void loadTransformartionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(uniform_location_transformationMatrix, matrix);
	}

}
