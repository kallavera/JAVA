package rendererEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import shaders.ShaderProgram;

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
		GL20.glEnableVertexAttribArray(ShaderProgram.ATT_INDEX_POSITIONS);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(ShaderProgram.ATT_INDEX_POSITIONS);
		GL30.glBindVertexArray(0);
	}
}
