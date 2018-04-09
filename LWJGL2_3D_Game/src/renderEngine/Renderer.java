package renderEngine;

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
		glClearColor(0.7f, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void render(RawModel model)
	{
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(Loader.ATT_POSITIONS);
		
		glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
		
		glDisableVertexAttribArray(Loader.ATT_POSITIONS);
		glBindVertexArray(0);
	}
}
