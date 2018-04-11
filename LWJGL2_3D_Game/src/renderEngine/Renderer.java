package renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import shaders.ShaderProgram;
import shaders.StaticShader;
import textures.ModelTexture;
import toolBox.Maths;

public class Renderer
{
	public static final float FOV = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 1000f;
	
	private Matrix4f projectionMatrix;
	private StaticShader shader;

	public Renderer(StaticShader shader)
	{
		this.shader = shader;
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare()
	{
		glEnable(GL_DEPTH_TEST);
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		glClearColor(0.6f, 0.0f, 0.0f, 1);
//		glClearColor(0.0f, 0.0f, 0.0f, 1);
	}
	
	public void render(Map<TexturedModel, List<Entity>> entities)
	{
		
	}
	
	public void render(Entity entity, StaticShader shader)
	{
		TexturedModel texturedModel = entity.getModel();
		RawModel model = texturedModel.getRawModel();
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(ShaderProgram.ATT_POSITIONS);
		glEnableVertexAttribArray(ShaderProgram.ATT_TEX_COORDS);
		glEnableVertexAttribArray(ShaderProgram.ATT_NORMALS);
		
		Matrix4f transformationMatrix = Maths.createTransformationMatric(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformartionMatrix(transformationMatrix);
		
		ModelTexture texture = texturedModel.getTexture();
		shader.loadShineVariables(texture.getShineDampler(), texture.getReflectivity());
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texturedModel.getTexture().getID());
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(ShaderProgram.ATT_POSITIONS);
		glDisableVertexAttribArray(ShaderProgram.ATT_TEX_COORDS);
		glDisableVertexAttribArray(ShaderProgram.ATT_NORMALS);
		glBindVertexArray(0);
	}
	
	private void createProjectionMatrix()
	{
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
    }
}
