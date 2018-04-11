package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import entities.Light;
import toolBox.Maths;

public class StaticShader extends ShaderProgram
{
	private static final String VERTEX_FILE= "src/shaders/vertex.shader";
	private static final String FRAGMENT_FILE= "src/shaders/fragment.shader";
	
	private int uniform_location_transformationMatrix;
	private int uniform_location_projectionMatrix;
	private int uniform_location_viewMatrix;
	private int uniform_location_lightPosition;
	private int uniform_location_lightColor;

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(ShaderProgram.ATT_POSITIONS, "position");
		super.bindAttribute(ShaderProgram.ATT_TEX_COORDS, "textureCoords");
		super.bindAttribute(ShaderProgram.ATT_NORMALS, "normal");
	}

	@Override
	protected void getAllUniformLocations()
	{
		uniform_location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		uniform_location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		uniform_location_viewMatrix = super.getUniformLocation("viewMatrix");
		uniform_location_lightPosition = super.getUniformLocation("lightPosition");
		uniform_location_lightColor = super.getUniformLocation("lightColor");
	}
	
	public void loadTransformartionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(uniform_location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(uniform_location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		
		super.loadMatrix(uniform_location_viewMatrix, viewMatrix);
	}
	
	public void loadLight(Light light)
	{
		super.loadVector(uniform_location_lightPosition, light.getPosition());
		super.loadVector(uniform_location_lightColor, light.getColor());
	}
}
