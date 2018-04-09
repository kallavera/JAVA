package renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import models.RawModel;
import models.TexturedModel;
import shaders.ShaderProgram;

public class Renderer
{

	public Renderer()
	{
		
	}
	
	public void prepare()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(0.0f, 0.0f, 0.0f, 1);
	}
	
	public void render(TexturedModel texturedModel)
	{
		RawModel model = texturedModel.getRawModel();
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(ShaderProgram.ATT_POSITIONS);
		glEnableVertexAttribArray(ShaderProgram.ATT_TEX_COORDS);
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texturedModel.getTexture().getID());
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(ShaderProgram.ATT_POSITIONS);
		glDisableVertexAttribArray(ShaderProgram.ATT_TEX_COORDS);
		glBindVertexArray(0);
	}
}
