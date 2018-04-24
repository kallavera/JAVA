package rendererEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer
{
	public void prepare()
	{
		GL11.glClearColor(0.0f, 0.f, 0.4f, 1.0f);
//		glClearColor(0.06f, 0.13f, 0.447f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void render(RawModel model)
	{
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(Loader.ATT_INDEX_POSITIONS);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		
		GL20.glDisableVertexAttribArray(Loader.ATT_INDEX_POSITIONS);
		GL30.glBindVertexArray(0);
	}
}
