package shaders;

public class StaticShader extends ShaderProgram
{
	private static final String VERTEX_FILE= "src/shaders/vertex.shader";
	private static final String FRAGMENT_FILE= "src/shaders/fragment.shader";

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(ShaderProgram.ATT_POSITIONS, "position");
	}

}
